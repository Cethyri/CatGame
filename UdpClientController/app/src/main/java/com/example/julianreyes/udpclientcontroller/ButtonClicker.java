package com.example.julianreyes.udpclientcontroller;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ButtonClicker extends AppCompatActivity {

    private int counter = 0;
    private double counterLimit;

    private ImageView img;
    private AnimationDrawable frameAnimation;
    private TextView textCounter;
    private Button clicker, again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_clicker);

        clicker = (Button)findViewById(R.id.CLICKER_id);
        again = (Button)findViewById(R.id.START_OVER_id);

        counterLimit = Math.random() * 100;
        Log.d("Clicker", "Counter limit == " + counterLimit);

        textCounter = (TextView)findViewById(R.id.COUNTER_id);
        textCounter.setText(String.valueOf(counter));

        img = (ImageView)findViewById(R.id.SLEEPING_CAT_id);
        img.setBackgroundResource(R.drawable.cat_animation);

        frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation
        frameAnimation.start();
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void returnTOController(View view) {
        finish();
    }

    public void playAgain(View view) {
        counterLimit = Math.random() * 100;
        counter = 0;
        textCounter.setText(String.valueOf(counter));

        frameAnimation.stop();

        img = (ImageView)findViewById(R.id.SLEEPING_CAT_id);
        img.setBackgroundResource(R.drawable.cat_animation);

        frameAnimation = (AnimationDrawable) img.getBackground();

        frameAnimation.start();

        clicker.setVisibility(View.VISIBLE);
        again.setVisibility(View.GONE);
    }

    public void incrementClicker(View view) {
        if (counter < counterLimit) {
            counter++;
            textCounter.setText(String.valueOf(counter));

        }else if(counter >= counterLimit) {
            frameAnimation.stop();
            img.setBackgroundResource(R.drawable.awoken_cat_animation);
            frameAnimation = (AnimationDrawable) img.getBackground();
            frameAnimation.start();

            clicker.setVisibility(View.GONE);
            again.setVisibility(View.VISIBLE);
        }
    }



}
