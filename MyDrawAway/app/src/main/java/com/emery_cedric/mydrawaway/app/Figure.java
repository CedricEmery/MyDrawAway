package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Hor on 30/04/14.
 */
public abstract class Figure {
    private int X;
    private int Y;
    private int hauteur;
    private int largeur;
    private int angle;
    private Paint paint;


    //Setter des paramètres
    public void setX(int X) {
        this.X = X;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void setAngle(int angle){ this.angle = angle;}


    //Getteur des paramètres
    public int getY() {
        return this.Y;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public int getX() {
        return X;
    }

    public int getAngle() {
        return angle;
    }



    protected Figure() {
        X = 0;
        Y = 0;
    }

    public abstract void create(Canvas canvas);
}
