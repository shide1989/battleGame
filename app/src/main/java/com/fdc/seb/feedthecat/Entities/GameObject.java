package com.fdc.seb.feedthecat.Entities;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Sebos on 07/10/2015.
 */
public abstract class GameObject {

    protected String TAG = getClass().getSimpleName();

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


    public Rect getRectangle() {
        return new Rect(x, y, x + width, y + height);
    }
}
