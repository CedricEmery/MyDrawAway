package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Hor on 30/04/14.
 */
public abstract class Figure {
    private int m_iX;
    private int m_iY;
    private int m_iHeight;
    private int m_iWidth;
    private int m_iAngle;
    private Paint m_paint;

// ----------------------------------PUBLIC----------------------------------------------------
    //Setter des paramètres
    public void setX(int iX) {
        this.m_iX = iX;
    }

    public void setY(int iY) {
        this.m_iY = iY;
    }

    public void setAngle(int iAngle){ this.m_iAngle = iAngle;}

    public void setLargeur(int iWidth) {
        this.m_iWidth = iWidth;
    }

    public void setHauteur(int iHeight) {
        this.m_iHeight = iHeight;
    }

    public void setPaint(Paint paint) {
        this.m_paint = paint;
    }


    //Getteur des paramètres
    public int getY() {
        return this.m_iY;
    }

    public int getHauteur() {
        return this.m_iHeight;
    }

    public int getLargeur() {
        return this.m_iWidth;
    }

    public Paint getPaint() {
        return this.m_paint;
    }

    public int getX() {
        return this.m_iX;
    }

    public int getAngle() {
        return this.m_iAngle;
    }


    public abstract void create(Canvas canvas);

    // ----------------------------------PROTECTED---------------------------------------------
    protected Figure() {
        setX(0);
        setY(0);
    }

}
