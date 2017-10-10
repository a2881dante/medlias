package com.mad.medlias;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mad.medlias.adapter.EditAnswerAdapter;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.Answer;
import com.mad.medlias.dao.GamePreferences;
import com.mad.medlias.dao.TeamBoard;
import com.mad.medlias.dao.Word;

import java.util.ArrayList;

public class RoundEditorActivity extends AppCompatActivity {

    private ListView lstEditorAnswers;
    private Button btnNext;

    private TeamBoard teamBoard;
    private GamePreferences gamePreferences;
    private ArrayList<Word> words;
    private ArrayList<Answer> answers;
    private EditAnswerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_round_editor);
        teamBoard = getIntent().getParcelableExtra(ExtrasName.TEAMS_BOARD);
        gamePreferences = getIntent().getParcelableExtra(ExtrasName.GAME_PREFERENCES);
        words = getIntent().getParcelableArrayListExtra(ExtrasName.WORDS);
        answers = getIntent().getParcelableArrayListExtra(ExtrasName.ANSWERS);

        lstEditorAnswers = (ListView)findViewById(R.id.round_editor_list);
        btnNext = (Button)findViewById(R.id.round_editor_next);

        adapter = new EditAnswerAdapter(this, answers);
        lstEditorAnswers.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Answer> answers = adapter.getAnswers();
                int sum = 0;
                for(int i=0; i<answers.size(); i++){
                    if(answers.get(i).isTrue()){
                        sum++;
                    }else{
                        sum--;
                    }
                }
                int cnt = teamBoard.getTeam(teamBoard.getTurn()).getCnt();
                teamBoard.getTeam(teamBoard.getTurn()).setCnt(cnt+sum);
                if(teamBoard.nextTurn()){
                    gamePreferences.setRound(gamePreferences.getRound()+1);
                }
                Intent intent;
                if(gamePreferences.getRoundsPref() < gamePreferences.getRound()){
                    intent = new Intent(RoundEditorActivity.this, WinnerActivity.class);
                }else {
                    intent = new Intent(RoundEditorActivity.this, TeamBoardActivity.class);
                }
                intent.putExtra(ExtrasName.TEAMS_BOARD, teamBoard);
                intent.putExtra(ExtrasName.GAME_PREFERENCES, gamePreferences);
                intent.putParcelableArrayListExtra(ExtrasName.WORDS, words);
                startActivity(intent);
                RoundEditorActivity.this.finish();
            }
        });

    }
}
