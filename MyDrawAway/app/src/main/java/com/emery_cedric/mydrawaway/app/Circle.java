package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Hor on 30/04/14.
 */
public class Circle extends Figure {

    int _radius;
    int _x, _y;
    Paint _myPaint;

    /*public Paint get_myPaint() {
        return _myPaint;
    }

    public void set_myPaint(Paint _myPaint) {
        this._myPaint = _myPaint;
    }*/

    public Circle(int x, int y, int radius,int largeur,int angle,Paint myPaint) {
        setX(x);
        setY(y);
        setLargeur(largeur);
        setHauteur(radius);
        setPaint(myPaint);
        setAngle(angle);
    }


    public void create(Canvas canvas) {
        canvas.drawCircle(this.getX(), this.getY(), this.getHauteur(), this.getPaint());
    }

  /*  public int getRadius() {
        return _radius;
    }*/

}
