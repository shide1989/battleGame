package com.fdc.seb.BattleKnights.Class;

import android.graphics.Bitmap;

/**
 * Created by Sebos on 07/10/2015.
 */

public class Animation {
    private Bitmap[] frames;
    private int currentFrame = 0;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public Animation() {
        startTime = System.nanoTime();
    }

    public boolean isPlayedOnce() {
        return playedOnce;
    }


    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int i) {
        currentFrame = i;
    }

    public void setFrames(Bitmap[] frames) {
        this.frames = frames;
        currentFrame = 0;
        playedOnce =false;
        startTime = System.nanoTime();
    }

    public void setDelay(int delay) {
        this.delay = delay;
        startTime = System.nanoTime();
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            startTime = System.nanoTime();
            currentFrame++;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
                playedOnce = true;
            }
        }


    }

    public Bitmap getImage() {
        return frames[currentFrame];
    }
}
