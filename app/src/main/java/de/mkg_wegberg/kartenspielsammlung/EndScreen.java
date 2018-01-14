package de.mkg_wegberg.kartenspielsammlung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndScreen extends AppCompatActivity {

    private TextView tEndtext;

    private boolean zeigeGewonnen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);


        TextView tEndtext = (TextView) findViewById(R.id.tEndtextid);

        Bundle extras = getIntent().getExtras();
        zeigeGewonnen = extras.getBoolean("pHatGewonnen", false);


        if(!zeigeGewonnen)
        {
            tEndtext.setText("Leider verloren...");
        }


    }


}
