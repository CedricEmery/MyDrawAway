package com.emery_cedric.mydrawaway.app;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class MainActivity extends Activity {
    GeometryLayer calque;
    String figure;
    Spinner listForm = null;
    private Button btnValider;

    EditText largeur;
    EditText hauteur;

    //Booleen de vérouillage de bouton
    public static boolean boolDessin = false;
    public static boolean boolSupr = false;
    public static boolean boolEdit = false;


    private Figure figureEnCour;
    public Figure figureTemp;

    //Pour la musique
    MediaPlayer mp;



    //Variable boite de dialogue des couleurs
    private ColorPicker colorPicker;
    private Button button;
    public int rouge = 0;
    public int vert = 0;
    public int bleu = 0;

    //share all informations with other classes (public static variables)
    public static int X, Y;


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

        //Lancement de la musique en boucle
        mp = MediaPlayer.create(this, R.raw.musique);
        mp.start();
        mp.setLooping(true);

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


        //initialize variables of the class
        X =0;
        Y =0;

        //Calque de la vue
        calque  = (GeometryLayer)findViewById(R.id.calque_dessin);

        //initialize the array of figures
        initAvailableForms();

        //Initialisation des evenements
        final View touchView = findViewById(R.id.calque_dessin);

        touchView.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View myView, MotionEvent event) {
                        int action = event.getAction();

                        //coordonnées optenuent du tactile
                        int Xpos = (int) event.getX();
                        int Ypos = (int) event.getY();

                        int ilargeur = 0;
                        int ihauteur =0;

                        try{
                            //Coordonnées tapés
                            largeur = (EditText) findViewById(R.id.largeur);
                            hauteur = (EditText) findViewById(R.id.hauteur);

                            ilargeur = Integer.parseInt(largeur.getText().toString());
                            ihauteur = Integer.parseInt(hauteur.getText().toString());
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Erreur");
                        }

                        //Paramètre de couleur et mise en forme
                        Paint myPaint = new Paint();
                        myPaint.setARGB(255, rouge, vert, bleu);
                        myPaint.setStrokeWidth(3);
                        myPaint.setStyle(Paint.Style.STROKE);

                        //Paramètre de l'angle d'orientation de la figure
                        SeekBar seekBar = (SeekBar) findViewById(R.id.angle);
                        int angle = 0;
                        angle = seekBar.getProgress();

                        //En fonction du type d'actions
                        if (action==MotionEvent.ACTION_DOWN)
                        {

                            //Si il y a au moins une figure
                            if(calque.existe()){

                                //Récupère au clic la figure la plus proche
                                figureEnCour = calque.findToMove(Xpos, Ypos);
                            }


                            //Si le boutton supprimer est actif alors on supprime
                            if(boolSupr){
                                calque.removeFigure(figureEnCour);

                                //Récupère au clic la figure la plus proche
                                figureEnCour = calque.findToMove(Xpos, Ypos);
                            }


                            //Si le boutton edition est actif alors on edite
                            if(boolEdit){

                                //On modifie en fonction des paramètre
                                figureEnCour.setPaint(myPaint);
                                figureEnCour.setLargeur(ilargeur);
                                figureEnCour.setHauteur(ihauteur);
                                figureEnCour.setAngle(angle);

                                //On actualise l'écran
                                calque.actualiseScreen();
                            }

                            if (boolDessin == true) {

                                if (figure.equals("Rectangle")) {
                                    Figure form;
                                    form = new Rectangle(ilargeur, ihauteur, Xpos, Ypos,angle,myPaint);
                                    //form.setX(Xpos);
                                    //form.setY(Ypos);
                                    calque.addFigure(form);
                                } else if (figure.equals("Cercle")) {
                                    Figure form;
                                    form = new Circle(Xpos, Ypos, ihauteur,ilargeur,angle,myPaint);
                                    //form.setX(Xpos);
                                    //form.setY(Ypos);
                                    calque.addFigure(form);
                                } else if (figure.equals("Triangle")) {
                                    Figure form;
                                    form = new Triangle(Xpos, Ypos, ilargeur, ihauteur,angle,myPaint);
                                    //form.setX(Xpos);
                                    //form.setY(Ypos);
                                    calque.addFigure(form);
                                }
                            }




                        }
                        else if (action==MotionEvent.ACTION_UP)
                        {

                            //Confirmation de la suppresion
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
                        else if (action==MotionEvent.ACTION_MOVE)
                        {

                            //Si il y a au moins un calque
                            if(calque.existe()){
                                //Pour le déplacement de la figure la plus proche
                                if(boolSupr!=true && boolDessin!=true){
                                    Figure TMP =  figureEnCour;

                                    TMP.setX((int) event.getX());
                                    TMP.setY((int) event.getY());

                                    //On supprime la figure passé
                                    calque.removeFigure(figureEnCour);

                                    //figureEnCour = figureTemp;
                                    calque.addFigure(TMP);
                                }
                            }

                        }

                        return true;
                    }
                }
        );

    }




    public void DrawFigure(View v) {

        //On passe le booleen a vrai et on donne le droit de dessiner
        boolDessin = true;
        boolSupr = false;
        boolEdit = false;
    }

    public void SuprFigure(View v) {

        //On passe le booleen a vrai et on donne le droit de Supprimer
        boolSupr = true;
        boolDessin = false;
        boolEdit = false;
    }

    public void EditFigure(View v) {

        //On passe le booleen a vrai et on donne le droit de Supprimer
        boolEdit = true;
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
