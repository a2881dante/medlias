package com.mad.medlias;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.mad.medlias.app.DBHelper;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.GamePreferences;
import com.mad.medlias.dao.Level;
import com.mad.medlias.dao.TeamBoard;
import com.mad.medlias.dao.Word;

import java.io.IOException;
import java.util.ArrayList;

public class GamePreferencesActivity extends AppCompatActivity {

    private GamePreferences gamePreferences;
    private TeamBoard teamBoard;
    private int time;
    private int rounds;

    private SeekBar sbRoundTime;
    private SeekBar sbRoundCnt;
    private TextView txtRoundTime;
    private TextView txtRoundCnt;
    private Spinner spnLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_preferences);

        ArrayList<Level> levels = new ArrayList<>();
        this.teamBoard = getIntent().getParcelableExtra(ExtrasName.TEAMS_BOARD);
        DBHelper mDBHelper = new DBHelper(this);
        SQLiteDatabase mDb;
        try {
            mDBHelper.updateDataBase();
            mDb = mDBHelper.getWritableDatabase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        Cursor cursor = mDb.rawQuery("SELECT * FROM level", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            levels.add(new Level(cursor.getInt(0), cursor.getString(1)));
            Log.i("DB-level", cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        sbRoundTime = (SeekBar)findViewById(R.id.preferences_round_time);
        sbRoundCnt = (SeekBar)findViewById(R.id.preferences_round_cnt);
        txtRoundTime = (TextView)findViewById(R.id.preferences_round_time_cnt);
        txtRoundCnt = (TextView)findViewById(R.id.preferences_round_cnt_cnt);
        spnLevel = (Spinner)findViewById(R.id.preferences_level);
        Button btnNext = (Button) findViewById(R.id.preferences_next);

        this.time = GamePreferences.TIME_MIN+sbRoundTime.getProgress();
        this.rounds = GamePreferences.ROUNDS_MIN+sbRoundCnt.getProgress();
        txtRoundTime.setText(Integer.toString(time));
        txtRoundCnt.setText(Integer.toString(rounds));
        spnLevel.setAdapter(new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1, levels));

        sbRoundTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                time = GamePreferences.TIME_MIN+sbRoundTime.getProgress();
                txtRoundTime.setText(""+time);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sbRoundCnt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rounds = GamePreferences.ROUNDS_MIN+sbRoundCnt.getProgress();
                txtRoundCnt.setText(""+rounds);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Word> words = new ArrayList<>();
                DBHelper mDBHelper = new DBHelper(GamePreferencesActivity.this);
                SQLiteDatabase mDb;
                try {
                    mDBHelper.updateDataBase();
                    mDb = mDBHelper.getWritableDatabase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }
                Cursor cursor = mDb.rawQuery("SELECT * FROM words", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    words.add(new Word(cursor.getInt(0), cursor.getString(1)));
                    cursor.moveToNext();
                }
                cursor.close();
                gamePreferences = new GamePreferences(time,rounds
                        , ((Level)spnLevel.getSelectedItem()).getId());
                Intent intent = new Intent(GamePreferencesActivity.this
                        , TeamBoardActivity.class);
                intent.putExtra(ExtrasName.TEAMS_BOARD, teamBoard);
                intent.putExtra(ExtrasName.GAME_PREFERENCES, gamePreferences);
                intent.putParcelableArrayListExtra(ExtrasName.WORDS, words);
                startActivity(intent);
                GamePreferencesActivity.this.finish();
            }
        });
    }
}
