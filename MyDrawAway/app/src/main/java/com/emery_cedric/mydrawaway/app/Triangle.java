package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import java.util.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

/**
 * Created by Hor on 06/05/14.
 */
public class Triangle extends Figure {

    public void create(Canvas canvas){

        //Calcul des point de base utile
        float point1X=this.getX();
        float point1Y= this.getY()-(this.getHauteur()/2);

        float point2X=this.getX()+(this.getLargeur()/2);
        float point2Y= this.getY()+(this.getHauteur()/2);

        float point3X=this.getX()-(this.getLargeur()/2);
        float point3Y= this.getY()+(this.getHauteur()/2);
        ////////////////////////////////


        //Calcul de la distance
        float distance = (float)sqrt(  pow(point1X-this.getX(),2) + pow(point1Y-this.getY(),2) );
        float distance2 = (float)sqrt(  pow(point2X-this.getX(),2) + pow(point2Y-this.getY(),2) );
        float distance3 = (float)sqrt(  pow(point3X-this.getX(),2) + pow(point3Y-this.getY(),2) );
        /////////////////////////


        float rotationR = ( (point1X - this.getX())*distance) / (float)sqrt(pow(point1X - this.getX(),2) + pow(point1Y - this.getY(),2));
        float rotationR2 = ( (point2X - this.getX())*distance) / (float)sqrt(pow(point2X - this.getX(),2) + pow(point2Y - this.getY(),2));
        float rotationR3 = ( (point3X - this.getX())*distance) / (float)sqrt(pow(point3X - this.getX(),2) + pow(point3Y - this.getY(),2));

        //Calcul des angles pour chaque points////
        double angleR = this.getAngle()*Math.PI/180;
        /////////////////////////////////////////


        //Calcul des nouveaux points
        float pt1xN = this.getX() + (float)(cos(angleR+rotationR)*distance);
        float pt1yN = this.getY() +(float)(sin(angleR+rotationR)*distance);

        float pt2xN = this.getX() + (float)(cos(angleR+rotationR2)*distance2);
        float pt2yN = this.getY() +(float)(sin(angleR+rotationR2)*distance2);

        float pt3xN = this.getX() + (float)(cos(angleR+rotationR3)*distance3);
        float pt3yN = this.getY() +(float)(sin(angleR+rotationR3)*distance3);


        //pour le cas du triangle les point sont construit par rapport au centre
        Path tri = new Path();
        tri.moveTo(pt3xN ,pt3yN);
        tri.lineTo(pt1xN ,pt1yN);
        tri.lineTo(pt2xN ,pt2yN);
        tri.lineTo(pt3xN ,pt3yN);
        tri.close();
        canvas.drawPath(tri, this.getPaint());

    }


    public Triangle(int x, int y,int base,int hauteur,int angle,Paint myPaint){
        setX(x);
        setY(y);
        setLargeur(base);
        setHauteur(hauteur);
        setPaint(myPaint);
        setAngle(angle);
    }

}
