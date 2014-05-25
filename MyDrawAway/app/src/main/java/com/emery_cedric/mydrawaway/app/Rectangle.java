package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;


public class Rectangle extends Figure {

    protected double m_dRectangleAngle[];
    protected Point m_RectanglePoint[];
    protected float m_fDistance;

    public void create(Canvas canvas) {

        Point centralPoint= new Point();
        centralPoint.setX(this.getX() - (this.getLargeur() / 2));
        centralPoint.setY(this.getY()-(this.getHauteur()/2));

        m_fDistance = (float)sqrt(  pow(centralPoint.getX()-this.getX(),2) + pow(centralPoint.getY()-this.getY(),2) );

        m_dRectangleAngle = new double[4];
        this.InitAngle();

        m_RectanglePoint = new Point[4];
        this.InitPoint();

        Path tri = new Path();
        tri.moveTo(m_RectanglePoint[3].getX(), m_RectanglePoint[3].getY());

        for(int i = 0; i < 4; i++){
            tri.lineTo(m_RectanglePoint[i].getX(), m_RectanglePoint[i].getY());
        }
        tri.close();

        canvas.drawPath(tri, this.getPaint());
    }


    public Rectangle(int largeur, int hauteur, int x, int y,int angle,Paint myPaint) {

        setX(x);
        setY(y);
        setLargeur(largeur);
        setHauteur(hauteur);
        setPaint(myPaint);
        setAngle(angle);
    }

    protected void InitAngle()
    {
        for(int i = 0; i < 4; i++){
            m_dRectangleAngle[i] = (this.getAngle()+(90*i))*Math.PI/180;
        }
    }

    protected void InitPoint()
    {
        for(int i = 0; i < 4; i++){
            m_RectanglePoint[i] = new Point();
            m_RectanglePoint[i].setX(this.getX() + (float)(Math.cos(m_dRectangleAngle[i]) * m_fDistance));
            m_RectanglePoint[i].setY(this.getY() +(float)(Math.sin(m_dRectangleAngle[i]) * m_fDistance));
        }
    }
}
