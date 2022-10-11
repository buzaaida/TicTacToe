package com.game.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.game.tictactoe.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;
    TextView mTextView;

    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int activePlayer = PLAYER_O;
    boolean isGameActive = true;
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining ui components
        mTextView = (TextView) findViewById(R.id.UserTurnTxt);
        mButton0 = (Button) findViewById(R.id.Button0);
        mButton1 = (Button) findViewById(R.id.Button1);
        mButton2 = (Button) findViewById(R.id.Button2);
        mButton3 = (Button) findViewById(R.id.Button3);
        mButton4 = (Button) findViewById(R.id.Button4);
        mButton5 = (Button) findViewById(R.id.Button5);
        mButton6 = (Button) findViewById(R.id.Button6);
        mButton7 = (Button) findViewById(R.id.Button7);
        mButton8 = (Button) findViewById(R.id.Button8);
        restartGameBtn = (Button) findViewById(R.id.RestartGameBtn);

        //getting button tags and setting text
        mButton0.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);


        //restart game listener implementation
        restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    //calling if any button is clicked
    @Override
    public void onClick(View view) {

        //nothing will be done if game is not active
        if (!isGameActive)
            return;

        //getting button ids
        Button clickedBtn = findViewById(view.getId());

        //getting tag of clicked button
        int clickedTag = Integer.parseInt(view.getTag().toString());

        //nothing will be done if the button was already clicked
        if (filledPositions[clickedTag] != -1) {
            return;

        }
        //updating value in array with the constant user value
        filledPositions[clickedTag] = activePlayer;

        //if active player is O, button text is set to O, button color is changed, next move is set to X
        if (activePlayer == PLAYER_O) {
            clickedBtn.setText("o");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.purple_500));
            activePlayer = PLAYER_X;
            mTextView.setText("X's turn.");
        } else {
            clickedBtn.setText("x");
            activePlayer = PLAYER_O;
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            mTextView.setText("O's turn.");
        }

        //checking winner
        checkWinner();

        //checking draw
        checkDraw();
    }

    private void checkDraw() {
        int count = 0;

        //checking values in array, increasing count if different than default value
        for (int i = 0; i < 9; i++) {
            if (filledPositions[i] != -1) {
                count++;
            }
        }
        //if all values are different from default value, game is draw
        if (count == 9) {
            //checking if any player won before draw
            if (checkWinner())
                return;
            showWinner("Draw!");
            isGameActive = false; //game set to inactive
        }
    }

    private boolean checkWinner() {

        //possible winning positions
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        //getting all winning positions
        for (int i = 0; i < 8; i++) {
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            //checking if value is different than default value
            if (filledPositions[value0] != -1) {
                if (filledPositions[value0] == filledPositions[value1] && filledPositions[value1] == filledPositions[value2]) {

                    //game set to inactive
                    isGameActive = false;

                    //getting winner
                    if (filledPositions[value0] == PLAYER_O) {
                        showWinner("O wins!");

                    } else {
                        showWinner("X wins!");
                    }
                    //returning true to not show draw result
                    return true;
                }


            }
        }
        return false;
    }

    private void showWinner(String winner) {

        //alert to display winner and restart option
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Restart?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();
    }

    private void restartGame() {

        //setting default player to O
        activePlayer = PLAYER_O;

        //setting all values to default
        filledPositions = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        //setting button texts to empty
        mButton0.setText("");
        mButton1.setText("");
        mButton2.setText("");
        mButton3.setText("");
        mButton4.setText("");
        mButton5.setText("");
        mButton6.setText("");
        mButton7.setText("");
        mButton8.setText("");

        //setting default button colors
        mButton0.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton1.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton2.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton3.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton4.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton5.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton6.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton7.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mButton8.setBackgroundColor(Color.parseColor("#BFBFBF"));

        //setting text to player O's turn
        mTextView.setText("O's turn.");

        //game set to active
        isGameActive = true;
    }
}