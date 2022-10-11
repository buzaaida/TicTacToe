package com.game.tictactoe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.game.tictactoe.MainActivity;
import com.game.tictactoe.R;

public class UsersActivity extends AppCompatActivity {

    Button singlePlayerBtn, multiPlayerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        singlePlayerBtn = (Button) findViewById(R.id.SinglePlayerBtn);
        multiPlayerBtn = (Button) findViewById(R.id.MultiPlayerBtn);

        //implementing listener, getting single player activity on button click
        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
                startActivity(intent);
            }
        });

        //implementing listener, getting main activity on button click
        multiPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}