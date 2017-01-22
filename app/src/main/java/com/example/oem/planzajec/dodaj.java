package com.example.oem.planzajec;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dodaj extends AppCompatActivity {

    Button dodaj;
    public EditText godzina1_zaj, godzina2_zaj, nazwa_zaj, sala_zaj;
    private DatabaseHandler db;
    TextView dayy;
    RandomNumber rnd;
    public String dzien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);

        db = new DatabaseHandler(this);
        rnd = new RandomNumber();

        Intent intent = getIntent();
        dzien = intent.getStringExtra("Day");
        dayy = (TextView)findViewById(R.id.dayy);
        dayy.setText(String.valueOf(dzien));

        dodajPrzedmiot();
    }

    public void dodajPrzedmiot()
    {
        dodaj = (Button)findViewById(R.id.dodajPozycje);

        godzina1_zaj = (EditText)findViewById(R.id.h_zajec1);
        godzina2_zaj = (EditText)findViewById(R.id.h_zajec2) ;
        nazwa_zaj = (EditText)findViewById(R.id.nazwa_zajec);
        sala_zaj = (EditText)findViewById(R.id.sala_gab);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(godzina1_zaj.length() == 5 && godzina2_zaj.length() == 5) {
                    db.dodajZajecieDH(new Dane(dzien, String.valueOf(godzina1_zaj.getText()), String.valueOf(godzina2_zaj.getText()),
                            String.valueOf(nazwa_zaj.getText()), String.valueOf(sala_zaj.getText()), rnd.rndNumber()));
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("AddMessage", "Przedmiot został dodany");
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Podaj poprawny format godziny - HH:MM", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
