package com.example.gmarler20.yahtzee;

import java.net.URL;
import android.widget.ImageButton;

import java.net.URL;
import java.util.Random;

public class Yahtzee implements Runnable {
    private ImageButton _button;
    private int sum;
    private Random rnd = new Random();

    public Yahtzee(ImageButton btn) {
        _button = btn;
    }

    public int getSum() {
        return sum;
    }

    public void run() {
        Random setter = new Random();
        for (int i = 0; i < setter.nextInt(100); i++) {
            sum = 0;
            Random gen = new Random();
            int chooser = gen.nextInt(6) + 1;
            if (chooser == 1) {
                _button.setImageResource(R.drawable.d11);
                sum = 1;
            } else if (chooser == 2) {
                _button.setImageResource(R.drawable.d12);
                sum = 2;
            } else if (chooser == 3) {
                _button.setImageResource(R.drawable.d13);
                sum = 3;
            } else if (chooser == 4) {
                _button.setImageResource(R.drawable.d14);
                sum = 4;
            } else if (chooser == 5) {
                _button.setImageResource(R.drawable.d15);
                sum = 5;
            } else if (chooser == 6) {
                _button.setImageResource(R.drawable.d16);
                sum = 6;
            }
        }
    }

}
