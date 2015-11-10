package com.fdc.seb.BattleKnights.Entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.fdc.seb.BattleKnights.App;
import com.fdc.seb.BattleKnights.Class.Animation;
import com.fdc.seb.BattleKnights.Class.GameObject;
import com.fdc.seb.BattleKnights.R;

/**
 * Created by Sebos on 07/10/2015.
 */
public class Player extends GameObject {


    public enum TChar {WILLIAM, ELISA}

    //private Bitmap bitmap;

    private int score;

    private boolean up, down, left, right;

    private boolean side;

    private Animation defaultAnim;
    private Animation jump, move, attack, defend, special;
    private Animation currentAnimation;

    private long startTime;
    private boolean isJumping;

    private TChar character;

    public Player(TChar tchar) {

        startTime = System.nanoTime();

        character = tchar;
        x = 100;
        y = (int) Background.HEIGHT / 2;
        dy = 0;

        switch (character) {
            case ELISA:
                initElisaAnimations();
                break;
            case WILLIAM:
                break;

        }
    }

    private void initElisaAnimations() {

        Bitmap[] frames = new Bitmap[3];
        //resource coordinates
        int xStart = 4;
        int xSpacing = 3;
        int yStart = 4;

        //Creating default animation
        defaultAnim = new Animation();
        height = 54;
        width = 54;


        for (int i = 0; i < frames.length; i++) {
            frames[i] = Bitmap.createBitmap(BitmapFactory.decodeResource(App.context.getResources(), R.drawable.elisaspritesheet),
                    xStart + i * (width + xSpacing), yStart, width, height);
        }

        defaultAnim.setDelay(100);
        defaultAnim.setFrames(frames);
        currentAnimation = defaultAnim;

        //create jump animations
        jump = new Animation();
        jump.setPlayOnce(true);
        frames = new Bitmap[4];
        xStart = 9;
        yStart = 62;

        for (int i = 0; i < frames.length; i++) {
            frames[i] = Bitmap.createBitmap(BitmapFactory.decodeResource(App.context.getResources(), R.drawable.elisaspritesheet),
                    xStart + i * (width + xSpacing), yStart, width, height);
        }

        jump.setDelay(100);
        jump.setFrames(frames);

        //Create move animation
        move = new Animation();
        frames = new Bitmap[8];

        xStart = 5;
        yStart = 121;

        for (int i = 0; i < frames.length; i++) {
            frames[i] = Bitmap.createBitmap(BitmapFactory.decodeResource(App.context.getResources(), R.drawable.elisaspritesheet),
                    xStart + i * (width + xSpacing), yStart, width, height);
        }

        move.setDelay(100);
        move.setFrames(frames);
    }
    //Setters

    public void setUp(boolean up) {
        this.up = up;
        this.down = false;
    }

    public void setDown(boolean down) {
        this.down = down;
        this.up = false;
    }

    public void setRight(boolean right) {
        this.right = right;
        this.left = false;
    }

    public void setLeft(boolean left) {
        this.left = left;
        this.right = false;
    }


    public void setSide(boolean side) {
        this.side = side;
    }

    //Class Methods

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (elapsed > 100) {
            startTime = System.nanoTime();
            dy += 3;//(dya += 3);
            //down = false;
            //if (dy > 15) dy = 15;
            //if (dy < -15) dy = -15;
        }

        //Player Jump animation
        if (up) {
            if (y >= 400 && elapsed > 100 && !isJumping) {
                jump.setPlayedOnce(false);
                currentAnimation = jump;
                isJumping = true;
                dy = -7;//(dya -= 1);
                up = false;
            }
        } else if (down) {
            //TODO : implement down actions
        }


        //Player move animation
        if (right) {
            if (currentAnimation == defaultAnim) {
                move.setPlayReversed(false);
                currentAnimation = move;
            }
            dx += 2;
        } else if (left) {
            if (currentAnimation == defaultAnim) {
                move.setPlayReversed(true);
                currentAnimation = move;
            }
            dx -= 2;
        } else {
            if (dx > 0) {
                dx -= 1;
            } else if (dx < 0) {
                dx += 1;
            }

        }

        if (dx > 5) {
            dx = 5;
        }
        if (dx < -5) {
            dx = -5;
        }


        currentAnimation.update();

        y += dy * 2;

        x += dx;
        if (x < 0) {
            x = 0;
        }

        //Player jump animation
        //400 = ground at the moment
        if (y >= 400) {
            y = 400;
            dy = 0;
            isJumping = false;
        }

        if (!isJumping && dx == 0) {
            currentAnimation = defaultAnim;
        }


        if (!isJumping && currentAnimation == jump) {
            currentAnimation = defaultAnim;
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentAnimation.getImage(), x, y, null);
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

}
