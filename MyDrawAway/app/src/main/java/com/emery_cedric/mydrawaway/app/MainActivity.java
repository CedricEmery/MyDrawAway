package com.emery_cedric.mydrawaway.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;



public class MainActivity extends Activity {
    protected GeometryLayer calque;
    protected String figure;

    protected EditText largeur;
    protected EditText hauteur;

    protected boolean boolDessin = false;
    protected boolean boolSupr = false;
    protected boolean boolEdit = false;


    protected Figure figureEnCour;




    //Variable boite de dialogue des couleurs
    protected ColorPicker colorPicker;
    protected Button button;
    protected int rouge = 0;
    protected int vert = 0;
    protected int bleu = 0;

    protected int X = 0, Y = 0;


    //Fonction pour la boite de dialogue de selection de couleur
    private void showColorPickerDialogDemo() {

        int initialColor = Color.WHITE;

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, initialColor, new ColorPickerDialog.OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {
                rouge = Color.red(color);
                bleu = Color.blue(color);
                vert = Color.green(color);
            }

        });
        colorPickerDialog.show();

    }


    private AdapterView.OnItemSelectedListener figureSelected;
    {
        figureSelected = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                figure = getResources().getStringArray(R.array.form_array)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }

    private void initAvailableForms() {
        Spinner spinner = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.form_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //create the listeners
        spinner.setOnItemSelectedListener(figureSelected);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boite de dialogue des couleur
        colorPicker = (ColorPicker) findViewById(R.id.colorPicker);

        button = (Button) findViewById(R.id.couleur);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //Ouverture de la boite de dialogue
                showColorPickerDialogDemo();
                boolDessin = true;
            }
        });

        calque  = (GeometryLayer)findViewById(R.id.calque_dessin);

        // initialize the array of figures
        initAvailableForms();

        // Initialisation des evenements
        final View touchView = findViewById(R.id.calque_dessin);


        // Lorsque l'on touche l'ecran
        touchView.setOnTouchListener(
            new View.OnTouchListener() {
                public boolean onTouch(View myView, MotionEvent event) {

                    int action = event.getAction();

                    int iXpos, iYpos, iLargeur, iHauteur;
                    iXpos = iYpos = iLargeur = iHauteur= 0;

                    try{
                        // recuperation des dimentions
                        largeur = (EditText) findViewById(R.id.largeur);
                        hauteur = (EditText) findViewById(R.id.hauteur);

                        // recuperation des donnees d'emplacement
                        iXpos = (int) event.getX();
                        iYpos = (int) event.getY();
                        iLargeur = Integer.parseInt(largeur.getText().toString());
                        iHauteur = Integer.parseInt(hauteur.getText().toString());
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    Paint myPaint = new Paint();
                    myPaint.setARGB(255, rouge, vert, bleu);
                    myPaint.setStrokeWidth(3);
                    myPaint.setStyle(Paint.Style.STROKE);

                    // angle d'orientation de la figure
                    SeekBar seekBar = (SeekBar) findViewById(R.id.angle);
                    int iAngle = seekBar.getProgress();


                    if (action==MotionEvent.ACTION_DOWN){
                        OnActionDown(iXpos, iYpos, iHauteur, iLargeur, iAngle,myPaint);
                    }
                    else if (action==MotionEvent.ACTION_UP){
                        OnActionUp();
                    }
                    else if (action==MotionEvent.ACTION_MOVE){
                        OnActionMove(event);
                    }
                    return true;
                }
            }
        );
    }

    protected void OnActionMove(MotionEvent event){
        if(calque.existe()){

            if(boolSupr!=true && boolDessin!=true){
                Figure TMP =  figureEnCour;
                calque.removeFigure(figureEnCour);

                TMP.setX((int) event.getX());
                TMP.setY((int) event.getY());

                calque.addFigure(TMP);
            }
        }
    }

    protected void OnActionUp(){
        if(boolSupr){
            boolSupr = false;
        }
        if(boolDessin){
            boolDessin = false;
        }
        if(boolEdit){
            boolEdit = false;
        }
    }

    protected void OnActionDown(int iXpos, int iYpos, int iHauteur, int iLargeur, int iAngle,Paint myPaint){
        if(calque.existe()){

            // Permet de recuperer la figure la plus proche
            figureEnCour = calque.findToMove(iXpos, iYpos);
        }

        if(boolSupr){
            calque.removeFigure(figureEnCour);
        }
        else if(boolEdit){
            AlterFigure(iHauteur, iLargeur, iAngle, myPaint);
        }
        else if (boolDessin == true){
            AddFigure(iXpos, iYpos, iHauteur, iLargeur, iAngle,myPaint);
        }
        calque.actualiseScreen();
    }

    protected void AddFigure(int iXpos, int iYpos, int iHauteur, int iLargeur, int iAngle,Paint myPaint){
        if (figure.equals("Rectangle")) {
            Figure form;
            form = new Rectangle(iLargeur, iHauteur, iXpos, iYpos,iAngle,myPaint);
            calque.addFigure(form);
        } else if (figure.equals("Cercle")) {
            Figure form;
            form = new Circle(iXpos, iYpos, iHauteur,iLargeur,iAngle,myPaint);
            calque.addFigure(form);
        } else if (figure.equals("Triangle")) {
            Figure form;
            form = new Triangle(iXpos, iYpos, iLargeur, iHauteur,iAngle,myPaint);
            calque.addFigure(form);
        }
    }

    protected void AlterFigure(int iHauteur, int iLargeur, int iAngle,Paint myPaint){
        figureEnCour.setPaint(myPaint);
        figureEnCour.setLargeur(iLargeur);
        figureEnCour.setHauteur(iHauteur);
        figureEnCour.setAngle(iAngle);
    }

    public void DrawFigure(View v) {

        DisableButton();
        boolDessin = true;
    }

    public void SuprFigure(View v) {

        DisableButton();
        boolSupr = true;
    }

    public void EditFigure(View v) {

        DisableButton();
        boolEdit = true;
    }

    public void DisableButton(){
        boolEdit = false;
        boolDessin = false;
        boolSupr = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void onRaz(View view) {
        calque.cleanSlate();
    }



}
