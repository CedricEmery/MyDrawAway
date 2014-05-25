package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Figure {

    public Circle(int iX, int iY, int iRayon,int iLargeur,int iAngle,Paint myPaint) {
        setX(iX);
        setY(iY);
        setLargeur(iLargeur);
        setHauteur(iRayon);
        setAngle(iAngle);
        setPaint(myPaint);
    }


    public void create(Canvas canvas) {
        canvas.drawCircle(this.getX(), this.getY(), this.getHauteur(), this.getPaint());
    }
}
