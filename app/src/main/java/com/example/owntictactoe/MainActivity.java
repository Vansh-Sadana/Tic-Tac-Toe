package com.example.owntictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView headertext;

    int Player_o = 0;
    int Player_x = 1;

    int ActivePlayer = Player_o;

    boolean isGameActive = true;
    int[] filledpos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headertext = findViewById(R.id.headerText);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(!isGameActive)
        {
            return;
        }

        Button clickedbtn = findViewById(v.getId());
        int btntag = Integer.parseInt(v.getTag().toString());


        if(filledpos[btntag] != -1) {
            return;
        }

        filledpos[btntag] = ActivePlayer;

        if (ActivePlayer == Player_o)
        {
            clickedbtn.setText("o");
            ActivePlayer = Player_x;
            clickedbtn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            headertext.setText("x's turn");
        }
        else {
            clickedbtn.setText("x");
            ActivePlayer = Player_o;
            clickedbtn.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            headertext.setText("o's turn");
        }

        checkForWin();

    }

    private void checkForWin()
    {
        int winningpos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i =0; i< 8;i++)
        {
            int val0 = winningpos[i][0];
            int val1 = winningpos[i][1];
            int val2 = winningpos[i][2];


            if(filledpos[val0] != -1) {
                if (filledpos[val0] == filledpos[val1] && filledpos[val1] == filledpos[val2]) {

                    isGameActive = false;

                    if(filledpos[val0] == Player_o)
                    {
                        showDialog("o is winner");

                    }
                    else
                    {
                        showDialog("x is winner");
                    }

                }
            }
        }


    }
    public void showDialog(String winner)
    {
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }
    public void restartGame()
    {
        ActivePlayer = Player_o;

        filledpos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};

        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn4.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn5.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn6.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn7.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        btn8.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

        isGameActive = true;
    }
}