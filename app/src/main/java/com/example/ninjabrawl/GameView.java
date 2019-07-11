package com.example.ninjabrawl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private Bitmap shield;
    private Bitmap heart;
    private Bitmap arrow;
    private Bitmap arrowup;
    private Bitmap arrowdown;
    private Bitmap arrowright;
    private Bitmap tryagain;
    private Bitmap arrowdeath;
    private float x;
    private float y;
    private float speed = 0;
    private float dx;
    private float dy;
    private int shieldX;
    private int shieldY;
    private boolean gameOver = false;

    private boolean desLeft, desRight, desUp, desDown;

    private int a;
    private int score = 0;
    String shielddir;



    private int delay = 0;
    private int direction;
    private ArrayList<Arrow> arrows =new ArrayList<Arrow>();

    ImageView box = (ImageView) findViewById(R.id.box);
    public MainThread thread;



    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        shield = BitmapFactory.decodeResource(getResources(), R.drawable.shield);
        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        tryagain = BitmapFactory.decodeResource(getResources(), R.drawable.tryagain);

        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);

        arrowup = BitmapFactory.decodeResource(getResources(), R.drawable.arrowup);

        arrowdown =BitmapFactory.decodeResource(getResources(), R.drawable.arrowdown);

        arrowright = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);



        }


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        if(gameOver == true){
            for(int i = 0; i < arrows.size(); i++){
                arrows.remove(i);
            }



        } else {
            if (delay <= 0) {
                delay = (int)(Math.random() * (100 - speed) + (30 - speed));
                System.out.println("delay: " + delay);
                Arrow arrow = new Arrow((int) (Math.random() * 5));
                arrows.add(arrow);
                speed += .5;
            } else {
                delay--;
                System.out.println("delay: " + delay);
            }


            for (int i = 0; i < arrows.size(); i++) {

                if (arrows.get(i).direction == "left") {
                    arrows.get(i).x += speed * arrows.get(i).smultiplier -1;
                }
                if (arrows.get(i).direction == "right") {
                    arrows.get(i).x -= speed * arrows.get(i).smultiplier -1;
                }
                if (arrows.get(i).direction == "up") {
                    arrows.get(i).y += speed * arrows.get(i).smultiplier;
                }
                if (arrows.get(i).direction == "down") {
                    arrows.get(i).y -= speed * arrows.get(i).smultiplier;
                }

            }


            dx = x - screenWidth / 2;
            dy = y - screenHeight / 2;
            if (x < screenWidth / 2) {
                if (y < screenHeight / 2 && Math.abs(dy) > Math.abs(dx)) {
                    shieldY = -150;
                    shieldX = 0;
                    shielddir = "up";


                } else if (y > screenHeight / 2 && Math.abs(dy) > Math.abs(dx)) {
                    shieldY = 150;
                    shieldX = 0;
                    shielddir = "down";

                } else {
                    shieldX = -150;
                    shieldY = 0;
                    shielddir = "left";


                }

            } else {
                if (y < screenHeight / 2 && Math.abs(dy) > Math.abs(dx)) {
                    shieldY = -150;
                    shieldX = 0;
                    shielddir = "up";
                } else if (y > screenHeight / 2 && Math.abs(dy) > Math.abs(dx)) {
                    shieldY = 150;
                    shieldX = 0;
                    shielddir = "down";
                } else {
                    shieldX = 150;
                    shieldY = 0;
                    shielddir = "right";
                }


            }


            //COLLISION

            for (int i = 0; i < arrows.size(); i++) {
                if (arrows.get(i).direction == "left") {
                    if (arrows.get(i).x + arrow.getWidth() >= screenWidth / 2 - 200 && shielddir == "left") {
                        arrows.remove(i);
                        score +=1;
                        desLeft = true;
                    } else if (arrows.get(i).x + arrow.getWidth() >= screenWidth / 2 && shielddir != "left") {
                        gameOver = true;
                    }

                } else if (arrows.get(i).direction == "right") {
                    if (arrows.get(i).x <= screenWidth / 2 + 200 && shielddir == "right") {
                        arrows.remove(i);
                        score +=1;
                        desRight = true;
                    } else if (arrows.get(i).x <= screenWidth / 2 && shielddir != "right") {
                        gameOver = true;
                    }
                } else if (arrows.get(i).direction == "up") {
                    if (arrows.get(i).y + arrowup.getHeight() >= screenHeight / 2 - 200 && shielddir == "up") {
                        arrows.remove(i);
                        score +=1;
                        desUp = true;
                    } else if (arrows.get(i).y + arrowup.getHeight() >= screenHeight / 2 && shielddir != "up") {
                        gameOver = true;
                    }
                } else if (arrows.get(i).direction == "down") {
                    if (arrows.get(i).y <= screenHeight / 2 + 200 && shielddir == "down") {
                        arrows.remove(i);
                        score +=1;
                        desDown = true;
                    } else if (arrows.get(i).y <= screenHeight / 2 && shielddir != "down") {
                        gameOver = true;
                    }
                }


            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            Paint paint = new Paint();

            canvas.drawColor(Color.GREEN);

            canvas.drawBitmap(heart, (screenWidth - shield.getWidth())/2,
                    (screenHeight-shield.getHeight())/2,null );

            canvas.drawBitmap(shield, ((screenWidth - shield.getWidth())/2) + shieldX,
                    ((screenHeight-shield.getHeight())/2) + shieldY,null );

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);

            canvas.drawText("Score: " + score, screenWidth/2 + 300, 100, paint);

            for(int i = 0; i<arrows.size(); i++){
                if(arrows.get(i).direction == "left") {
                    canvas.drawBitmap(arrow, arrows.get(i).x, arrows.get(i).y , null);
                }
                if(arrows.get(i).direction == "right"){
                    canvas.drawBitmap(arrowright, arrows.get(i).x, arrows.get(i).y, null);
                }
                if(arrows.get(i).direction == "up"){
                    canvas.drawBitmap(arrowup, arrows.get(i).x , arrows.get(i).y , null);
                }
                if(arrows.get(i).direction == "down"){
                    canvas.drawBitmap(arrowdown, arrows.get(i).x , arrows.get(i).y , null);
                }
            }

            if(desLeft){
                canvas.drawBitmap(arrowDeath[a], screenWidth / 2 - 200, screenHeight/2, null);
                desLeft = false;
            }
            if(desRight) {
                canvas.drawBitmap(arrowDeath[a], screenWidth / 2 + 200, screenHeight/2, null);
                desRight = false;
            }
            if(desUp) {
                canvas.drawBitmap(arrowDeath[a], screenWidth/2, screenHeight / 2 - 200, null);
                desUp = false;
            }
            if(desDown){
                canvas.drawBitmap(arrowDeath[a], screenHeight / 2 , screenHeight/2 +200, null);
                desDown = false;

            }


            if(gameOver){

                canvas.drawColor(Color.WHITE);


                paint.setTextAlign(Paint.Align.CENTER);
                paint.setColor(Color.BLACK);
                paint.setTextSize(200);
                canvas.drawText("Game Over!", screenWidth/2, screenHeight/2 - ((paint.descent() + paint.ascent()) / 2), paint);
                canvas.drawBitmap(tryagain, (screenWidth - tryagain.getWidth())/2,
                        (screenHeight-tryagain.getHeight())/2 +300,null );
                if(x > (screenWidth - tryagain.getWidth())/2 && x < (screenWidth - tryagain.getWidth())/2 + tryagain.getWidth()
                        && y > (screenHeight-tryagain.getHeight())/2 +300 && y < (screenHeight-tryagain.getHeight())/2 +300 + tryagain.getHeight()) {
                    gameOver = false;
                    speed = 0;
                    score = 0;

                }

            }





        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = (int)event.getX();
        y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;


    }




}
