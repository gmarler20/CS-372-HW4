package com.example.gmarler20.yahtzee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private int finalsum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ImageButton d1 = findViewById(R.id.die1);
        ImageButton d2 = findViewById(R.id.die2);
        ImageButton d3 = findViewById(R.id.die3);
        ImageButton d4 = findViewById(R.id.die4);
        ImageButton d5 = findViewById(R.id.die5);

        Button Rbutton = findViewById(R.id.RollButton);
        final Button Sumbutton = findViewById(R.id.SumButton);


        Random setter = new Random();

       final Yahtzee[] ys = new Yahtzee[5];
        ys[0] = new Yahtzee(d1);
        ys[1] = new Yahtzee(d2);
        ys[2] = new Yahtzee(d3);
        ys[3] = new Yahtzee(d4);
        ys[4] = new Yahtzee(d5);

        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread[] ts = new Thread[5];
                for(int j = 0; j< ts.length; j++) {
                    ts[j] = new Thread(ys[j]);
                    ts[j].start();
                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        ;
                    }
                }

                for(int i = 0; i < ts.length; i++) {
                    try {
                        ts[i].join();
                    } catch (InterruptedException exa) {
                        ;
                    }
                }
                for(int a = 0; a < ys.length; a++) {
                    finalsum = finalsum + ys[a].getSum();
                }
                Sumbutton.setText(Integer.toString(finalsum));
                finalsum = 0;
            }
        });


    }
}
