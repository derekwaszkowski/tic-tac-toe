package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerEntry extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_entry);
        getSupportActionBar().hide();


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerEntry();
            }
        });
    }
    public void PlayerEntry(){

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);

        String player1 = editText1.getText().toString();
        String player2 = editText2.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name1", player1);
        intent.putExtra("name2", player2);
        startActivity(intent);
        finish();


    }

}
