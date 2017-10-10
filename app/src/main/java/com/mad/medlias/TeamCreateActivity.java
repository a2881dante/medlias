package com.mad.medlias;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mad.medlias.app.DBHelper;
import com.mad.medlias.app.DataManager;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.Team;
import com.mad.medlias.dao.TeamBoard;

import java.io.IOException;
import java.util.ArrayList;

public class TeamCreateActivity extends AppCompatActivity implements View.OnClickListener{

    private DBHelper mDBHelper;
    private SQLiteDatabase mDb;

    private ArrayList<String> teams;
    private ArrayList<String> teams_list;

    private ListView lstTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws SQLException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);

        teams = new ArrayList<>();
        teams_list = new ArrayList<>();

        mDBHelper = new DBHelper(this);
        try {
            mDBHelper.updateDataBase();
            mDb = mDBHelper.getWritableDatabase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        Cursor cursor = mDb.rawQuery("SELECT * FROM teams", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            teams.add(cursor.getString(1));
            Log.i("DB-team", cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        Button btnNew = (Button)findViewById(R.id.team_new);
        Button btnNext = (Button)findViewById(R.id.team_next);
        lstTeam = (ListView)findViewById(R.id.team_list);
        btnNew.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        teams_list.add(DataManager.getRandomUniqElement(teams));
        teams_list.add(DataManager.getRandomUniqElement(teams));
        lstTeam.setAdapter(new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1, teams_list));

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.team_new){
            if(teams_list.size()<10){
                teams_list.add(DataManager.getRandomUniqElement(teams));
                lstTeam.setAdapter(new ArrayAdapter<>(this
                        , android.R.layout.simple_list_item_1, teams_list));
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        getString(R.string.team_mess_max),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }else if(view.getId() == R.id.team_next){
            ArrayList<Team> teamsList = new ArrayList<>();
            for(int i=0; i<teams_list.size(); i++){
                teamsList.add(new Team(i, teams_list.get(i)));
            }
            TeamBoard teamBoard = new TeamBoard(teamsList);
            Intent intent = new Intent(TeamCreateActivity.this, GamePreferencesActivity.class);
            intent.putExtra(ExtrasName.TEAMS_BOARD, teamBoard);
            startActivity(intent);
            this.finish();
        }
    }
}
