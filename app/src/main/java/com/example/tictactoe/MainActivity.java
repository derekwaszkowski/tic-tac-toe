package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 2 dimensional array
    private Button [][] buttons = new Button[3][3];

    // Game start player one is X
    private boolean player1Turn = true;

    // if rounds = 9 then we have a draw.
    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private String Player1 ;
    private String Player2 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        Player1 = extras.getString("name1");
        Player2 = extras.getString("name2");

        if (Player1 != null && !Player1.isEmpty()) {


        }
        else {Player1 = "Player 1";}

       if (Player2 != null && !Player2.isEmpty()){

       }
       else {Player2 = "Player 2";}



        //References


        textViewPlayer1.setText(Player1);
        textViewPlayer2.setText(Player2);



        //nested loop to reference array
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                //verison of findViewById
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()){
            if (player1Turn){
                player1Wins();
            }
            else{
                player2Wins();
            }
        }
        else if (roundCount == 9){
            draw();
        }
        else{
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        //saves all the buttons in a string called field
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //checks to make 3 are in a row and not all empty
        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return true;

            }
        }

        //checks to make 3 are in a row and not all empty
        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;

            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;

        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;

        }

        //checkForWin method returns false stating no winner
        return false;

    }

    private void player1Wins(){

        player1Points++;
        Toast.makeText(this, Player1 +" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void player2Wins(){

        player2Points++;
        Toast.makeText(this, Player2 +" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void draw(){

        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePointsText(){

        textViewPlayer1.setText( Player1 +": " + player1Points);
        textViewPlayer2.setText( Player2 +": " + player2Points);

    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
    }
}
