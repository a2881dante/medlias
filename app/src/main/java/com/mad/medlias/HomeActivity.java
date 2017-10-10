package com.mad.medlias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnNewGame = (Button) findViewById(R.id.home_new_game);
        Button btnRule = (Button) findViewById(R.id.home_rule);
        Button btnVocabulary = (Button)findViewById(R.id.home_vocabulary);
        //Button btnAuthor = (Button) findViewById(R.id.home_author);
        btnNewGame.setOnClickListener(this);
        btnRule.setOnClickListener(this);
        btnVocabulary.setOnClickListener(this);
        //btnAuthor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.home_new_game){
            intent = new Intent(HomeActivity.this, TeamCreateActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.home_rule){
            intent = new Intent(HomeActivity.this, RuleActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.home_vocabulary){
            intent = new Intent(HomeActivity.this, GagActivity.class);
            startActivity(intent);
        }/*else if(view.getId() == R.id.home_author){
            intent = new Intent(HomeActivity.this, GagActivity.class);
            startActivity(intent);
        }*/
    }
}
