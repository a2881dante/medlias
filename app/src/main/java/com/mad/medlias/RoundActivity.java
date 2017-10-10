package com.mad.medlias;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mad.medlias.app.DataManager;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.Answer;
import com.mad.medlias.dao.GamePreferences;
import com.mad.medlias.dao.TeamBoard;
import com.mad.medlias.dao.Word;

import java.util.ArrayList;

public class RoundActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int MILI_SEC = 1000;

    private TeamBoard teamBoard;
    private GamePreferences gamePreferences;
    private CountDownTimer timer;
    private ArrayList<Word> words;
    private ArrayList<Answer> answers;
    private Word word;
    private int sec;

    private TextView txtTimer;
    private TextView txtWord;
    private Button btnTrue;
    private Button btnFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        teamBoard = getIntent().getParcelableExtra(ExtrasName.TEAMS_BOARD);
        gamePreferences = getIntent().getParcelableExtra(ExtrasName.GAME_PREFERENCES);
        words = getIntent().getParcelableArrayListExtra(ExtrasName.WORDS);
        sec = gamePreferences.getTimePref();
        answers = new ArrayList<>();

        txtTimer = (TextView)findViewById(R.id.round_timer);
        txtWord = (TextView)findViewById(R.id.round_word);
        btnTrue = (Button)findViewById(R.id.round_true);
        btnFalse = (Button)findViewById(R.id.round_false);
        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);

        nextWord();
        runTimer();
    }

    @Override
    public void onStop(){
        super.onStop();
        timer.cancel();
    }

    private void runTimer(){
        timer = new CountDownTimer(sec*MILI_SEC, MILI_SEC) {
            @Override
            public void onTick(long l) {
                sec--;
                txtTimer.setText(Integer.toString(sec));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(RoundActivity.this, RoundEditorActivity.class);
                intent.putExtra(ExtrasName.TEAMS_BOARD, teamBoard);
                intent.putExtra(ExtrasName.GAME_PREFERENCES, gamePreferences);
                intent.putParcelableArrayListExtra(ExtrasName.WORDS, words);
                intent.putParcelableArrayListExtra(ExtrasName.ANSWERS, answers);
                startActivity(intent);
                RoundActivity.this.finish();
            }
        };
        timer.start();
    }

    @Override
    public void onClick(View view) {
        if(word != null) {
            if (view.getId() == R.id.round_true) {
                answers.add(new Answer(word.getWord(), true));
                nextWord();
            } else if (view.getId() == R.id.round_false) {
                answers.add(new Answer(word.getWord(), false));
                nextWord();
            }
        }
    }

    private void nextWord(){
        word = DataManager.getRandomUniqWord(words);
        if(word != null) {
            txtWord.setText(word.getWord());
        }else{
            txtWord.setText("");
        }
    }
}
