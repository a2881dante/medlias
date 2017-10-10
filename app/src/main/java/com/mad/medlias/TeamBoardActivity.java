package com.mad.medlias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.medlias.adapter.PointBoardAdapter;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.GamePreferences;
import com.mad.medlias.dao.TeamBoard;
import com.mad.medlias.dao.Word;

import java.util.ArrayList;

public class TeamBoardActivity extends AppCompatActivity {

    private TeamBoard teamBoard;
    private GamePreferences gamePreferences;
    private ArrayList<Word> words;

    private ListView lstTeams;
    private TextView txtRound;
    private TextView txtNextTeam;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_board);

        teamBoard = getIntent().getParcelableExtra(ExtrasName.TEAMS_BOARD);
        gamePreferences = getIntent().getParcelableExtra(ExtrasName.GAME_PREFERENCES);
        words = getIntent().getParcelableArrayListExtra(ExtrasName.WORDS);

        lstTeams = (ListView)findViewById(R.id.board_team_list);
        txtRound = (TextView)findViewById(R.id.board_round);
        txtNextTeam = (TextView)findViewById(R.id.board_next_team);
        btnPlay = (Button)findViewById(R.id.board_play_round);

        PointBoardAdapter adapter = new PointBoardAdapter(this, teamBoard.getTeams());
        lstTeams.setAdapter(adapter);
        txtRound.setText(getString(R.string.board_round)+" "+gamePreferences.getRound());
        txtNextTeam.setText(getString(R.string.board_next_team)+" "
                +teamBoard.getTeam(teamBoard.getTurn()));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamBoardActivity.this, RoundActivity.class);
                intent.putExtra(ExtrasName.TEAMS_BOARD, teamBoard);
                intent.putExtra(ExtrasName.GAME_PREFERENCES, gamePreferences);
                intent.putParcelableArrayListExtra(ExtrasName.WORDS, words);
                startActivity(intent);
                TeamBoardActivity.this.finish();
            }
        });
    }
}
