package com.emery_cedric.mydrawaway.app;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;


public class Triangle extends Figure {

    protected Point m_trianglePoint[];
    protected Point m_finalTrianglePoint[];
    protected float m_fTriangleDistance[];
    protected float m_fTriangleRotation[];
    protected double m_dAngleR;

    public void create(Canvas canvas){

        m_trianglePoint = new Point[3];
        InitPoint();

        m_fTriangleDistance = new float[3];
        CalculDistance();


        m_fTriangleRotation = new float[3];
        CalculRotation();

        // Calcul de l'angle de rotation
        m_dAngleR = this.getAngle()*Math.PI/180;

        m_finalTrianglePoint = new Point[3];
        FinalCalculForTrianglePoint();


        //Chaque point est construit par rapport au centre
        Path tri = new Path();
        tri.moveTo(m_finalTrianglePoint[2].getX(), m_finalTrianglePoint[2].getY());

        for(int i = 0; i < 3; i++){
            tri.lineTo(m_finalTrianglePoint[i].getX(), m_finalTrianglePoint[i].getY());
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

    protected void InitPoint(){

        for(int i = 0; i < 3; i++) {
            m_trianglePoint[i] = new Point();
        }

        m_trianglePoint[0].setX(this.getX());
        m_trianglePoint[0].setY(this.getY()-(this.getHauteur()/2));

        m_trianglePoint[1].setX(this.getX()+(this.getLargeur()/2));
        m_trianglePoint[1].setY(this.getY()+(this.getHauteur()/2));

        m_trianglePoint[2].setX(this.getX()-(this.getLargeur()/2));
        m_trianglePoint[2].setY(this.getY()+(this.getHauteur()/2));
    }

    protected void CalculDistance(){
        for(int i = 0; i < 3; i++){
            m_fTriangleDistance[i] = (float)sqrt(  pow(m_trianglePoint[i].getX()-this.getX(),2) + pow(m_trianglePoint[i].getY()-this.getY(),2) );
            i++;
        }
    }

    protected void CalculRotation(){
        for(int i = 0; i < 3; i++){
            m_fTriangleRotation[i] = ( (m_trianglePoint[i].getX() - this.getX())*m_fTriangleDistance[0]) / (float)sqrt(pow(m_trianglePoint[i].getX() - this.getX(),2) + pow(m_trianglePoint[i].getY() - this.getY(),2));
            i++;
        }
    }

    protected void FinalCalculForTrianglePoint(){
        for(int i = 0; i < 3; i++){
            m_finalTrianglePoint[i] = new Point();
            m_finalTrianglePoint[i].setX(this.getX() + (float) (cos(m_dAngleR + m_fTriangleRotation[i]) * m_fTriangleDistance[i]));
            m_finalTrianglePoint[i].setY(this.getY() +(float)(sin(m_dAngleR+m_fTriangleRotation[i]) * m_fTriangleDistance[i]));
        }
    }
}
