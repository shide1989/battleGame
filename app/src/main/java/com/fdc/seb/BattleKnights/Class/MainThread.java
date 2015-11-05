package com.fdc.seb.BattleKnights.Class;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Sebos on 30/09/2015.
 */
public class MainThread extends Thread{
    private int FPS = 30;
    private double avgFPS;

    public double getAvgFPS() {
        return avgFPS;
    }

    private SurfaceHolder holder;
    private GamePanel gamePanel;
    private boolean running;

    private static Canvas canvas;
    private String TAG = "MainThread/";

    public MainThread(SurfaceHolder holder, GamePanel gamePanel) {
        super();
        this.holder = holder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        long targetTime = 1000/FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //Try lock the Canvas for editing
            try {
                canvas = this.holder.lockCanvas();
                synchronized (holder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {

            }
            finally {
                if (canvas != null) {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }

            timeMillis = System.nanoTime() - startTime;
            timeMillis /= 1000000;

            waitTime = targetTime - timeMillis;
            try {
                sleep(waitTime);
            } catch (Exception ignored) {

            }
            totalTime += System.nanoTime() - startTime;

            frameCount++;
            if (frameCount == FPS) {
                avgFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                //Log.i(TAG, String.valueOf(avgFPS));
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

