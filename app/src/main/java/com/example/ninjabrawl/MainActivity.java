package com.example.ninjabrawl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;






/*
        shield.setTranslationX(100);

        shield.setX(-100);
        shield.setY(-100);


        addTouchListener();


*/
    }

/*
    private void addTouchListener(){
        box.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                x = event.getX();
                y = event.getY();

                checkDirection();

                return false;
            }
        });
    }

    private void checkDirection(){
        dx = x - screenWidth/2;
        dy = y- screenHeight/2;
        if(x < screenWidth/2) {
            if(y < screenHeight/2 && Math.abs(dy) > Math.abs(dx)){
                resetshield();
                shield.setTranslationY(-100);
            } else if (y > screenHeight/2 && Math.abs(dy) > Math.abs(dx)){
                resetshield();
                shield.setTranslationY(100);
            } else{
                resetshield();
                shield.setTranslationX(-100);
            }

        }

        else{
            if(y < screenHeight/2 && Math.abs(dy) > Math.abs(dx)){
                resetshield();
                shield.setTranslationY(-100);

            } else if(y > screenHeight/2 && Math.abs(dy) > Math.abs(dx)) {
                resetshield();
                shield.setTranslationY(100);
            } else {
                resetshield();
                shield.setTranslationX(100);
            }



        }
    }

    private void resetshield(){
        shield.setTranslationX(0);
        shield.setTranslationY(0);
    }*/
}

