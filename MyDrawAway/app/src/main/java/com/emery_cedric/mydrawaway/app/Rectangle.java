package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;


public class Rectangle extends Figure {

    public void create(Canvas canvas) {

        Point centralPoint= new Point();
        centralPoint.setX(this.getX() - (this.getLargeur() / 2));
        centralPoint.setY(this.getY()-(this.getHauteur()/2));

        float fDistance = (float)sqrt(  pow(centralPoint.getX()-this.getX(),2) + pow(centralPoint.getY()-this.getY(),2) );

        double dRectangleAngle[] = new double[4];
        InitAngle(dRectangleAngle);

        Point RactanglePoint[] = new Point[3];
        InitPoint(dRectangleAngle, RactanglePoint, fDistance);


        Path tri = new Path();
        tri.lineTo(RactanglePoint[4].getX(), RactanglePoint[4].getY());

        int i = 0;
        for(Point p : RactanglePoint){
            tri.lineTo(RactanglePoint[i].getX(), RactanglePoint[i].getY());
            i++;
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

    protected void InitAngle(double dRectangleAngle[])
    {
        int i = 0;
        for(double angle : dRectangleAngle){
            angle = (this.getAngle()+(90*i))*Math.PI/180;
            i++;
        }
    }

    protected void InitPoint(double dRectangleAngle[], Point RactanglePoint[], float fDistance)
    {
        int i = 0;
        for(Point p : RactanglePoint){
            p.setX(this.getX() + (float)(cos(dRectangleAngle[i])*fDistance));
            p.setY(this.getY() +(float)(sin(dRectangleAngle[i])*fDistance));
            i++;
        }
    }
}
