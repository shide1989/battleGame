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
    private boolean playOnce = false;
    private boolean playReversed = false;

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

    public void setPlayReversed(boolean playReversed) {
        this.playReversed = playReversed;
    }

    public void setPlayOnce(boolean playOnce) {
        this.playOnce = playOnce;
    }

    public void setPlayedOnce(boolean playedOnce) {
        this.playedOnce = playedOnce;
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
            if (playOnce && playedOnce) {
                return;
            }
            if (!playReversed) {
                currentFrame++;
                if (currentFrame >= frames.length) {
                    currentFrame = 0;
                    playedOnce = true;
                }
            } else {
                currentFrame--;
                if (currentFrame <= 0) {
                    currentFrame = frames.length -1;
                    playedOnce = true;
                }
            }
        }


    }

    public Bitmap getImage() {
        return frames[currentFrame];
    }

}
