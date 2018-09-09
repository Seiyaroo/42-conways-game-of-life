package com.example.conwaysgameoflife;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Square {
    private float xx;
    private float yy;
    private float width;
    private float height;
    private int color;

    public Square(float xx, float yy, float width, float height, int color) {
        this.xx = xx;
        this.yy = yy;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void setX(float xx) {
        this.xx = xx;
    }

    public void setY(float yy) {
        this.yy = yy;
    }

    public float getX() {
        return this.xx;
    }

    public float getY() {
        return this.yy;
    }

    public void draw(Canvas canvas) {
        Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        brush.setColor(this.color);
        canvas.drawRect(this.xx, this.yy, this.width, this.height, brush);
    }
}