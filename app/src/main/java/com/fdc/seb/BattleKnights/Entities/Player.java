package com.fdc.seb.BattleKnights.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.fdc.seb.BattleKnights.App;
import com.fdc.seb.BattleKnights.Class.Animation;
import com.fdc.seb.BattleKnights.R;

/**
 * Created by Sebos on 07/10/2015.
 */
public class Player extends GameObject {

    public enum TChar {WILLIAM, ELISA}

    //private Bitmap bitmap;

    private int score;


    private int dya = 0; //acceleration
    private boolean up, down, left, right;

    private Animation defaultAnim;
    private Animation jump, attack, defend, special;
    private Animation currentAnimation;

    private long startTime;
    private boolean isJumping;


    private TChar character;

    //TODO
    public Player(TChar tchar) {

        startTime = System.nanoTime();

        character = tchar;
        switch (character) {
            case ELISA:
                initElisaAnimations();
                break;
            case WILLIAM:
                break;

        }
    }

    private void initElisaAnimations() {

        //Creating default animation
        defaultAnim = new Animation();

        x = 100;
        y = (int) Background.HEIGHT / 2;
        dy = 0;

        height = 54;
        width = 54;

        Bitmap[] frames = new Bitmap[3];
        int xStart = 4;
        int xSpacing = 3;
        int yStart = 4;


        for (int i = 0; i < frames.length;i++){
                    frames[i] = Bitmap.createBitmap(BitmapFactory.decodeResource(App.context.getResources(), R.drawable.elisaspritesheet),
                            xStart + i * (width + xSpacing), yStart, width, height);
        }

        defaultAnim.setDelay(100);
        defaultAnim.setFrames(frames);
        currentAnimation = defaultAnim;
    }


    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void jump() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (y >= 400 && elapsed > 100 && !isJumping) {
            currentAnimation = jump;
            isJumping = true;
            dy = -7;//(dya -= 1);
            up = false;
        }
    }

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (elapsed > 100) {
            startTime = System.nanoTime();
            dy += 3;//(dya += 3);
            //down = false;
            //if (dy > 15) dy = 15;
            //if (dy < -15) dy = -15;
        }

        currentAnimation.update();


        y += dy * 2;
        //dy = 0;

        //400 = ground at the moment
        if (y > 400) {
            currentAnimation = defaultAnim;
            y = 400;
            dy = 0;
            isJumping = false;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentAnimation.getImage(), x, y, null);
    }

    public void resetDYA() {
        dya = 0;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

}
