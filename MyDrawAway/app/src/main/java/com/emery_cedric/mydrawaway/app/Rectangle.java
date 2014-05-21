package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

/**
 * Created by Hor on 30/04/14.
 */
public class Rectangle extends Figure {

    public void create(Canvas canvas) {

        //Point.x = Doigt.x + cos(rotation <- radian) * distance
        //Point.y = Doigt.y + sin(rotation <-radian aussi) * distance



        //Quand on clique il sagit du point central de la figure donc on calcule en fonction de.
        Path tri = new Path();

        //Calcul des point de base utile
        float point1X=this.getX() - (this.getLargeur() / 2);
        float point1Y= this.getY()-(this.getHauteur()/2);
        ////////////////////////////////

        //Calcul de la distance//
        float distance = (float)sqrt(  pow(point1X-this.getX(),2) + pow(point1Y-this.getY(),2) );
        /////////////////////////

        //Calcul des angles pour chaque points
        double angleR = this.getAngle()*Math.PI/180;
        double angleR2 = (this.getAngle()+90)*Math.PI/180;
        double angleR3 = (this.getAngle()+180)*Math.PI/180;
        double angleR4 = (this.getAngle()+270)*Math.PI/180;
        /////////////////////////////////////////


        //Calcul des nouveaux points
        float pt1xN = this.getX() + (float)(cos(angleR)*distance);
        float pt1yN = this.getY() +(float)(sin(angleR)*distance);

        float pt2xN = this.getX() + (float)(cos(angleR2)*distance);
        float pt2yN = this.getY() +(float)(sin(angleR2)*distance);

        float pt3xN = this.getX() + (float)(cos(angleR3)*distance);
        float pt3yN = this.getY() +(float)(sin(angleR3)*distance);

        float pt4xN = this.getX() + (float)(cos(angleR4)*distance);
        float pt4yN = this.getY() +(float)(sin(angleR4)*distance);

        //Demarrage
        tri.moveTo(pt4xN, pt4yN);

        //Différent points
        tri.lineTo(pt1xN, pt1yN);
        tri.lineTo(pt2xN , pt2yN);

        tri.lineTo(pt3xN ,pt3yN);
        tri.lineTo(pt4xN ,pt4yN);
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


}
