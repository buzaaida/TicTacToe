package com.game.tictactoe.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.game.tictactoe.R;

import java.util.Random;

public class SinglePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8;
    Button restartGameBtn;
    TextView mTextView;

    int PLAYER_Computer = 0;
    int PLAYER_Human = 1;
    int activePlayer = PLAYER_Computer;
    boolean isGameActive = true;
    int[] filledPositions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

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

        //call to automatically set text and call user to click button
        computerPlayer();
    }

    private void computerPlayer() {

        //getting random value
        int randomNumber = new Random().nextInt(filledPositions.length);

        //getting value from array
        int buttonToClick = filledPositions[randomNumber];
        //check if button is clicked, set text and background color for button, set text for user turn
        if (buttonToClick == -1) {
            if (randomNumber == 0) {
                mButton0.setText("O");
                filledPositions[randomNumber] = PLAYER_Computer; //updating value in array to check if button is clicked and to check for winner
                mButton0.setBackgroundColor(getResources().getColor(R.color.purple_500));
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 1) {
                mButton1.setText("O");
                mButton1.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 2) {
                mButton2.setText("O");
                mButton2.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 3) {
                mButton3.setText("O");
                mButton3.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 4) {
                mButton4.setText("O");
                mButton4.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 5) {
                mButton5.setText("O");
                mButton5.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 6) {
                mButton6.setText("O");
                mButton6.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 7) {
                mButton7.setText("O");
                mButton7.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
            if (randomNumber == 8) {
                mButton8.setText("O");
                mButton8.setBackgroundColor(getResources().getColor(R.color.purple_500));
                filledPositions[randomNumber] = PLAYER_Computer;
                activePlayer = PLAYER_Human;
                mTextView.setText("Your turn, human.");
            }
        } else {
            computerPlayer(); //calling computerPlayer method if generated value button is already clicked

        }

        //checking for winner
        checkWinner();

        //checking for draw
        checkDraw();

    }

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

        //if active player is computer, button text is set to O, button color is changed, next move is set to X
        if (activePlayer == PLAYER_Computer) {
            computerPlayer();
        } else {
            clickedBtn.setText("x");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            mTextView.setText("Computer's turn.");

            //calling computerPlayer method to automatically click next button
            computerPlayer();
        }

        //checking for winner
        checkWinner();

        //checking for draw
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
                    if (filledPositions[value0] == PLAYER_Computer) {
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

        //calling same intent to restart game
        Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
        startActivity(intent);
        finish();
    }

}