package com.emery_cedric.mydrawaway.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;


/**
 * Created by Hor on 30/04/14.
 */
public class GeometryLayer extends View {
    public static ArrayList<Figure> Listfigure = new ArrayList<Figure>();
    public GeometryLayer(Context context) {
        super(context);
    }

    public GeometryLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GeometryLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addFigure(Figure f)
    {
        Listfigure.add(f);
        invalidate();
    }

    public void removeFigure(Figure f)
    {
        Listfigure.remove(f);
        invalidate();
    }

    public void cleanSlate()
    {
        Listfigure.clear();
        invalidate();
    }


    public boolean existe()
    {
        if(Listfigure.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void actualiseScreen(){
        invalidate();
    }


    public Figure findToMove(int Xpos,int Ypos){

        int procheX = 100000;
        int procheY = 100000;

        Figure figureEnCour = null;

        for(Figure f : Listfigure){
            if((abs(Ypos - f.getY())<procheY)&&(abs(Xpos - f.getX())<procheX)){

                //On garde la figure en cour
                figureEnCour = f;

                //On garde la différence de distance pour les prochaine comparaison
                procheX = abs(Xpos - f.getX());
                procheY =abs(Ypos - f.getY());
            }
        }
        return figureEnCour;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Dessine la liste des figures
        for(Figure f : Listfigure){
            f.create(canvas);
        }
    }

}
