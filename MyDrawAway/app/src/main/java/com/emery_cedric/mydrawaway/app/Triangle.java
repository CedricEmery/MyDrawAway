package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;


public class Triangle extends Figure {

    public void create(Canvas canvas){

        Point trianglePoint[] = new Point[3];
        InitPoint(trianglePoint);

        float fTriangleDistance[] = new float[3];
        CalculDistance(fTriangleDistance, trianglePoint);


        float fTriangleRotation[] = new float[3];
        CalculRotation(fTriangleRotation, trianglePoint, fTriangleDistance[0]);

        // Calcul de l'angle de rotation
        double dAngleR = this.getAngle()*Math.PI/180;

        Point finalTrianglePoint[] = new Point[3];
        FinalCalculForTrianglePoint(fTriangleRotation, finalTrianglePoint, fTriangleDistance, dAngleR);


        //Chaque point est construit par rapport au centre
        Path tri = new Path();
        tri.moveTo(finalTrianglePoint[2].getX(), finalTrianglePoint[2].getY());

        for(Point p : finalTrianglePoint) {
            tri.lineTo(p.getX(), p.getY());
        }

        tri.close();
        canvas.drawPath(tri, this.getPaint());

    }


    public Triangle(int iX, int iY, int iBase, int iHauteur, int iAngle, Paint myPaint){
        setX(iX);
        setY(iY);
        setLargeur(iBase);
        setHauteur(iHauteur);
        setPaint(myPaint);
        setAngle(iAngle);
    }

    protected void InitPoint(Point trianglePoint[])
    {
        trianglePoint[0].setX(this.getX());
        trianglePoint[0].setY(this.getY()-(this.getHauteur()/2));

        trianglePoint[1].setX(this.getX()+(this.getLargeur()/2));
        trianglePoint[1].setY(this.getY()+(this.getHauteur()/2));

        trianglePoint[2].setX(this.getX()-(this.getLargeur()/2));
        trianglePoint[2].setY(this.getY()+(this.getHauteur()/2));
    }

    protected void CalculDistance(float fTriangleDistance[], Point trianglePoint[]){
        int i = 0;
        for(float f : fTriangleDistance){
            f = (float)sqrt(  pow(trianglePoint[i].getX()-this.getX(),2) + pow(trianglePoint[i].getY()-this.getY(),2) );
            i++;
        }
    }

    protected void CalculRotation(float fTriangleRotation[], Point trianglePoint[], float fDistance){
        int i = 0;
        for(float f : fTriangleRotation){
            f = ( (trianglePoint[i].getX() - this.getX())*fDistance) / (float)sqrt(pow(trianglePoint[i].getX() - this.getX(),2) + pow(trianglePoint[i].getY() - this.getY(),2));
            i++;
        }
    }

    protected void FinalCalculForTrianglePoint(float fTriangleRotation[], Point trianglePoint[], float fTriangleDistance[], double dAngleR){
        int i = 0;
        for(Point p : trianglePoint){
            p.setX(this.getX() + (float) (cos(dAngleR + fTriangleRotation[i]) * fTriangleDistance[i]));
            p.setY(this.getY() +(float)(sin(dAngleR+fTriangleRotation[i])*fTriangleDistance[i]));
            i++;
        }
    }
}
