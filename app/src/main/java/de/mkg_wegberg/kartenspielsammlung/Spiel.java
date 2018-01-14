package de.mkg_wegberg.kartenspielsammlung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Spiel extends AppCompatActivity implements View.OnClickListener
{
    private ArrayList<Karte> kartendeck = new ArrayList<Karte>();
    private ArrayList<Karte> stapel = new ArrayList<Karte>();
    private ArrayList<Karte> ziehStapel = new ArrayList<Karte>();
    private ArrayList<Karte> kartentemp = new ArrayList<Karte>();
    private ArrayList<Spieler> spielerliste = new ArrayList<Spieler>();
    private ArrayList<LinearLayout> lineareLayouts = new ArrayList<LinearLayout>();
    private ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
    private Spielverwaltung verwaltung = new Spielverwaltung();

    private ImageView iAktuelleKarte;

    private ScrollView svHand;

    private TextView tStatus;

    public LinearLayout llvertikal;
    public LinearLayout llhorizontal1;
    public LinearLayout llhorizontal2;
    public LinearLayout llhorizontal3;
    public LinearLayout llhorizontal4;
    public LinearLayout llhorizontal5;
    public LinearLayout llhorizontal6;
    public LinearLayout llhorizontal7;
    public LinearLayout llhorizontal8;
    public LinearLayout llhorizontal9;
    public LinearLayout llhorizontal10;

    private Button bAblegen;
    private Button bZiehen;

    private int kartenanzahl;
    private int botanzahl;
    private int playeranzahl = 1;
    private int spieleranzahl;
    private int anzahlProSpieler;
    private int zaehlen;
    private int thread = 0;

    private int spielgeschwindigkeit = 500;

    private int current = 0;

    private boolean einmalGedrueckt = false;
    private boolean nachMenge;

    private String bild;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        iAktuelleKarte  = (ImageView) findViewById(R.id.iAktuelleKarteId);
        svHand          = (ScrollView) findViewById(R.id.svHandId);
        tStatus         = (TextView) findViewById(R.id.tStatusanzeigeId);
        bAblegen        = (Button) findViewById(R.id.bAblegenId);
        bZiehen         = (Button) findViewById(R.id.bZiehenId);

        llvertikal      = (LinearLayout) findViewById(R.id.llvertikalId);
        llhorizontal1   = (LinearLayout) findViewById(R.id.llhorizintal1Id);
        llhorizontal2   = (LinearLayout) findViewById(R.id.llhorizintal2Id);
        llhorizontal3   = (LinearLayout) findViewById(R.id.llhorizintal3Id);
        llhorizontal4   = (LinearLayout) findViewById(R.id.llhorizintal4Id);
        llhorizontal5   = (LinearLayout) findViewById(R.id.llhorizintal5Id);
        llhorizontal6   = (LinearLayout) findViewById(R.id.llhorizintal6Id);
        llhorizontal7   = (LinearLayout) findViewById(R.id.llhorizintal7Id);
        llhorizontal8   = (LinearLayout) findViewById(R.id.llhorizintal8Id);
        llhorizontal9   = (LinearLayout) findViewById(R.id.llhorizintal9Id);
        llhorizontal10  = (LinearLayout) findViewById(R.id.llhorizintal10Id);

        lineareLayouts.add(0,llhorizontal10);
        lineareLayouts.add(0,llhorizontal9);
        lineareLayouts.add(0,llhorizontal8);
        lineareLayouts.add(0,llhorizontal7);
        lineareLayouts.add(0,llhorizontal6);
        lineareLayouts.add(0,llhorizontal5);
        lineareLayouts.add(0,llhorizontal4);
        lineareLayouts.add(0,llhorizontal3);
        lineareLayouts.add(0,llhorizontal2);
        lineareLayouts.add(0,llhorizontal1);


        bAblegen.setOnClickListener(this);
        bZiehen.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        kartenanzahl = extras.getInt("pKartenAnzahl", 32);
        botanzahl = extras.getInt("pBotanzahl", 3);
        anzahlProSpieler  = extras.getInt("pAnzahlProSpieler", 6);
        nachMenge = extras.getBoolean("pNachMenge", true);

        spieleranzahl = botanzahl + playeranzahl;

        erzeugeSpieler();
        kartendeck = verwaltung.erzeugeKartendeck(kartenanzahl);
        mischen();
        austeilen(nachMenge);
        aktualisiereAktuelleKarte();
        aktualisiereHand(0);
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bAblegenId:
                gewaehltesAblegen(0);
                if(spielerliste.get(0).getHandzahl() != 0)
                {
                    bots();
                }
                else
                {
                    spielende();
                }
                break;
            case R.id.bZiehenId:
                ziehen(0);
                //@TODO Spielernummer festgelegt.
                break;

            default: break;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == event.KEYCODE_BACK)
        {

            if(!einmalGedrueckt){
                einmalGedrueckt = true;
                Toast.makeText(getApplicationContext(), "Erneut drücken, um zu beenden.", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        einmalGedrueckt = false;
                    }
                }, 3000);
            }
            else
            {
                einmalGedrueckt = false;
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setzeStatus(String pStatus)
    {
        status = pStatus;

        try {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    tStatus.setText(status);
                }
            });
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void runThread() {

        new Thread() {
            public void run() {
                while (thread++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                tStatus.setText("#" + thread);
                            }
                        });
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


            private void erzeugeSpieler() {
                if (spielerliste.isEmpty()) {
                    for (int i = 0; i < botanzahl; i++) {
                        Bot bot = new Bot(i + "", this);
                        spielerliste.add(0, bot);
                    }

                    for (int i = 0; i < playeranzahl; i++) {
                        Player player = new Player(i + "", this);
                        spielerliste.add(0, player);
                    }
                }
            }

            private void mischen() {
                kartendeck = verwaltung.mischeKarten(kartendeck);
            }

            private void austeilen(boolean nachMenge) {
                if (!nachMenge) {
                    while (kartendeck.size() > 10) {
                        for (int i = 0; i < spieleranzahl; i++) {
                            spielerliste.get(i).gebeKarte(kartendeck.get(0));
                            kartendeck.remove(0);
                        }
                    }
                    stapel.add(0, kartendeck.get(0));
                    kartendeck.remove(0);
                    ziehStapel = kartendeck;
                } else {
                    if ((spieleranzahl * anzahlProSpieler + 10) < kartenanzahl) {
                        for (int i = 0; i < anzahlProSpieler; i++) {
                            for (int j = 0; j < spieleranzahl; j++) {
                                spielerliste.get(j).gebeKarte(kartendeck.get(0));
                                kartendeck.remove(0);
                            }
                        }

                        stapel.add(0, kartendeck.get(0));
                        kartendeck.remove(0);
                        ziehStapel = kartendeck;
                    } else {
                        anzahlProSpieler = (kartenanzahl - 10) / spieleranzahl;
                        austeilen(nachMenge);
                    }
                }

            }

            private void aktualisiereAktuelleKarte() {
                Karte temp = stapel.get(0);
                bild = temp.getBilder();

                int bildId = getResources().getIdentifier(bild, "drawable", getPackageName());
                iAktuelleKarte.setImageResource(bildId);
            }

            private void gewaehltesAblegen(int pSpielernummer) {
                if (current == 0) {
                    ArrayList<Karte> temp = new ArrayList<Karte>();
                    if (!spielerliste.get(pSpielernummer).gibGewaehlteKarten().isEmpty()) {
                        for (int i = 0; i < spielerliste.get(pSpielernummer).gibGewaehlteKarten().size(); i++) {
                            temp.add(spielerliste.get(pSpielernummer).gibGewaehlteKarten().get(i));
                        }
                        for (int i = 0; i < temp.size(); i++) {
                            stapel.add(0, temp.get(i));
                        }
                        aktualisiereAktuelleKarte();
                        aktualisiereHand(pSpielernummer);
                        hatGewonnen(spielerliste.get(pSpielernummer));
                        zugVorbei();
                        if (stapel.get(0).getNummer() == "8") {
                            tStatus.setText("Spieler " + current + " wird übergangen!");
                            zugVorbei();
                        }
                    } else {
                        tStatus.setText("Wähle zunächst eine Karte!");
                    }
                } else {
                    tStatus.setText("Du bist nicht dran!");
                }
            }

            public boolean darfLegen(ArrayList<Karte> pListe) {
                Karte vgl = stapel.get(0);
                for (int i = 0; i < pListe.size(); i++) {
                    switch (vgl.getNummer()) {
                        //@TODO Sonderfälle implementieren
                        case "bube":
                            break;
                        case "7":
                            break;
                        default:
                            if (vgl.getName().equals(pListe.get(i))) {
                                return true;
                            } else {
                                return false;
                            }
                    }
                }
                return false;
            }

            private void ziehen(int pSpielernummer) {
                Karte temp = ziehStapel.get(0);
                ziehStapel.remove(0);
                spielerliste.get(pSpielernummer).nehmeKarte(temp);
            }

            private void zugVorbei() {
                if (current == (spieleranzahl - 1)) {
                    current = 0;
                    tStatus.setText("Du bist dran!");
                } else {
                    current++;
                    thread = 0;
                }
            }

            private void bots()
            {
                //-1, damit beim ersten durchlauf ein Delay von 1s entsteht(defaultcase)
                thread = -1;
                new Thread() {
                    public void run() {
                            while (thread++ < 3) {
                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            //Absicherung gegen unerwünschtes Abstürzen
                                            if(spielerliste.get(current).istBot()) {
                                                switch (thread) {
                                                    case 1:
                                                        botzug1();
                                                        break;
                                                    case 2:
                                                        botzug2();
                                                        break;
                                                    case 3:
                                                        botzug3();
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            else
                                            {
                                                tStatus.setText("Fehler 1: Aktueller spieler ist kein Bot!");
                                            }
                                        }
                                    });
                                    Thread.sleep(spielgeschwindigkeit);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                    }

                    }
                }.start();

            }


            private void botzug1() {
                tStatus.setText("Bot " + current + " ist am Zug.");
            }

            private void botzug2()
            {
                ArrayList<Karte> temp = (((Bot) (spielerliste.get(current))).macheZug());
                for (int i = 0; i < temp.size(); i++) {
                    stapel.add(0, temp.get(0));
                    temp.remove(0);
                }
                aktualisiereAktuelleKarte();
                tStatus.setText("Bot " + current + " hat gelegt");

            }

            private void botzug3()
            {
                hatGewonnen(spielerliste.get(current));
                zugVorbei();
                if (stapel.get(0).getNummer() == "8") {
                    tStatus.setText("Spieler " + current + " wird übergangen!");
                    zugVorbei();
                }
            }

            private void hatGewonnen(Spieler pSpieler) {
                if (pSpieler.getHandzahl() == 0) {
                  spielende();
                }
            }



            private void spielende() {
                setzeStatus("Spieler " + current + " hat gewonnen! Congrats!");
                Intent oeffneEndscreen = new Intent(getBaseContext(), EndScreen.class);
                oeffneEndscreen.putExtra("pHatGewonnen", spielerliste.get(0).gibHand().isEmpty());
                startActivity(oeffneEndscreen);
                finish();
            }

            public void aktualisiereHand(int pSpielernummer) {
                for (int i = 0; i < lineareLayouts.size(); i++) {
                    if (lineareLayouts.get(i).getChildCount() > 0) {
                        lineareLayouts.get(i).removeAllViews();
                    }
                }
                imageButtons.clear();

                for (int i = 0; i < spielerliste.get(pSpielernummer).gibHand().size(); i++) {
                    kartentemp.add(spielerliste.get(pSpielernummer).gibHand().get(i));
                }

                //Test
                zaehlen = 1;
                int j = 0;
                while (!kartentemp.isEmpty()) {
                    for (int i = 0; !kartentemp.isEmpty() && i < 5; i++)
                    //Test
                    {
                        bild = kartentemp.get(0).getBilder();
                        kartentemp.remove(0);
                        int bildId = getResources().getIdentifier(bild, "drawable", getPackageName());

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(140, 190);

                        ImageButton btn = new ImageButton(this);
                        btn.setId(zaehlen + i);
                        btn.setImageResource(bildId);
                        btn.setLayoutParams(lp);
                        btn.setPadding(0, 0, 0, 0);
                        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (int i = 0; i < imageButtons.size(); i++) {
                                    if (imageButtons.get(i).getId() == v.getId()) {
                                        //@TODO Spielernummer festgelegt --> geht nur im SP
                                        spielerliste.get(0).waehleKarte(i);
                                        aktualisiereHand(0);
                                        break;
                                    }

                                }

                            }
                        });
                        lineareLayouts.get(j).addView(btn);
                        ImageButton button = (ImageButton) findViewById(btn.getId());
                        imageButtons.add(button);

                    }
                    zaehlen = zaehlen + 5;
                    j++;
                }
            }
        }



