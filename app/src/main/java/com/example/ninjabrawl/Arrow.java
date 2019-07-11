package com.example.ninjabrawl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Arrow {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    int x;
    int y;
    String direction;
    float smultiplier;


    public Arrow(int dir){
        this.smultiplier =  (int)(Math.random() * 4 + 1);
        //LEFT
        if(dir == 0){
            this.x = -100;
            this.y = screenHeight/2;
            direction = "left";
        }
        //RIGHT
        if(dir == 1){
            this.x = screenWidth + 100;
            this.y = screenHeight/2;
            direction = "right";
        }
        //UP
        if(dir == 2){
            this.x = screenWidth/2;
            this.y = -100;
            direction = "up";
        }
        //DOWN
        if(dir == 3){
            this.x = screenWidth/2;
            this.y = screenHeight+100;
            direction = "down";
        }
    }
}
