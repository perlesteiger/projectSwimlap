/**
 * SwimLapProject from stephenbroyerproject
 * March to June 2014
 * Licence pro DIM 7
 * CCI 74 & IUT Annecy Departement informatique
 */

package com.dim.swimlap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dim.swimlap.parser.FFNexDataGetter;
import com.dim.swimlap.ui.GlobalContainer;

import java.io.IOException;

public class MainActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FFNexDataGetter ffNexDataGetter = new FFNexDataGetter();
                try {
                    ffNexDataGetter.createDirectory();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, GlobalContainer.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}