package com.emery_cedric.mydrawaway.app;


public class Point {

    protected float m_fX;
    protected float m_fY;

    public Point(float fX, float fY) {
        InitVariable(fX, fY);
    }

    public Point() {
        InitVariable(0, 0);
    }

    public float getX() {
        return m_fX;
    }

    public void setX(float fX) {
        this.m_fX = fX;
    }


    public float getY() {
        return m_fY;
    }

    public void setY(float fY) {
        this.m_fY = fY;
    }

    protected void InitVariable(float fX, float fY)
    {
        setY(fX);
        setY(fY);
    }
}
