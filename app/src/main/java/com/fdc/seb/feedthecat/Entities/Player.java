package com.fdc.seb.feedthecat.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.fdc.seb.feedthecat.Class.Animation;

/**
 * Created by Sebos on 07/10/2015.
 */
public class Player extends GameObject {
    private Bitmap bitmap;

    private int score;


    private int dya; //acceleration
    private boolean up, down, left, right;

    private Animation animation;
    private long startTime;

    public Player(Bitmap bitmap, int numFrames, int w, int h) {

    }

    public Player(Bitmap bitmap, int numFrames, int w, int h, int xStart, int xSpacing, int yStart) {
        animation = new Animation();
        this.bitmap = bitmap;

        x = 100;
        y = (int) Background.HEIGHT / 2;
        dy = 0;

        height = h;
        width = w;

        Bitmap[] frames = new Bitmap[numFrames];

        for (int i = 0; i < frames.length; i++) {

            frames[i] = Bitmap.createBitmap(bitmap, xStart + i * (width + xSpacing), yStart, width, height);
        }

        animation.setFrames(frames);
        animation.setDelay(100);
        startTime = System.nanoTime();
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }

        animation.update();
        if (up) {
            dy = -3;//(dya -= 3);
            up = false;
        } else if (down) {
            dy = +3;//(dya += 3);
            down = false;
        }

        //if (dy > 15) dy = 15;
        //if (dy < -15) dy = -15;

        y += dy * 2;
        dy = 0;

        y = y > 400 ? 400 : y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), x, y, null);
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
