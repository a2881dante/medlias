package com.mad.medlias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mad.medlias.adapter.EditAnswerAdapter;
import com.mad.medlias.adapter.WinnerAdapter;
import com.mad.medlias.app.DataManager;
import com.mad.medlias.app.ExtrasName;
import com.mad.medlias.dao.Answer;
import com.mad.medlias.dao.GamePreferences;
import com.mad.medlias.dao.Team;
import com.mad.medlias.dao.TeamBoard;
import com.mad.medlias.dao.Word;

import java.util.ArrayList;
import java.util.Comparator;

public class WinnerActivity extends AppCompatActivity {

    private TeamBoard teamBoard;

    private ListView lstWinner;
    private Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        teamBoard = getIntent().getParcelableExtra(ExtrasName.TEAMS_BOARD);
        ArrayList<Team> winners = DataManager.sortTeam(teamBoard.getTeams());

        lstWinner = (ListView)findViewById(R.id.winner_list);
        btnMain = (Button)findViewById(R.id.winner_main);

        WinnerAdapter adapter = new WinnerAdapter(this, winners);
        lstWinner.setAdapter(adapter);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WinnerActivity.this.finish();
            }
        });
    }
}
