package com.fdc.seb.BattleKnights.Class;

import android.graphics.Rect;

/**
 * Created by Sebos on 07/10/2015.
 */
public abstract class GameObject {

    protected String TAG = getClass().getSimpleName();

    protected int x;
    protected int y;
    protected int dx; //acceleration
    protected int dy; //acceleration
    protected int width;
    protected int height;

    /*Getter / Setter*/
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Rect getRectangle() {
        return new Rect(x, y, x + width, y + height);
    }

    public boolean checkColision(Rect rec) {
        return getRectangle().intersect(rec);
    }
}
