package com.fdc.seb.BattleKnights.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.fdc.seb.BattleKnights.Class.GamePanel;

/**
 * Created by Sebos on 30/09/2015.
 */

public class Background {

    private Bitmap bitmap;
    private int x, y;

    public static float WIDTH = 0;
    public static float HEIGHT = 0;


    public Background(Bitmap bitmap) {
        this.bitmap = bitmap;
        WIDTH = bitmap.getWidth();
        HEIGHT = bitmap.getHeight();
    }

    public void update() {
        x += GamePanel.MOVESPEED;
        if (x <= - WIDTH) {
            x = 0;
        }
    }

    public void draw(Canvas canvas) {

//        bitmap.reconfigure(canvas.getWidth(), canvas.getHeight(), bitmap.getConfig());
        canvas.drawBitmap(bitmap, x, y, null);
        if (x <= 0) {
            canvas.drawBitmap(bitmap, x + WIDTH, y, null);
//            int screenDiff = GamePanel.SCREEN_WIDTH - WIDTH;
//            if (x <= screenDiff - WIDTH) {
//                canvas.drawBitmap(bitmap, x + 2*WIDTH , y, null);
//            }
        }

//        canvas.drawText("y : " + y, 20, 10, null);
    }
}
