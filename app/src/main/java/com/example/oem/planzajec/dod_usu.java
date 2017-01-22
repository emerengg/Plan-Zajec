package com.example.oem.planzajec;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class dod_usu extends AppCompatActivity {

    Button edytuj, usun;
    public EditText godzina1_zaj, godzina2_zaj, nazwa_zaj, sala_zaj;
    private DatabaseHandler db;
    TextView dayy;
    public String dzien, id, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dod_usu);

        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        dzien = intent.getStringExtra("Day");
        test = intent.getStringExtra("Test");

        dayy = (TextView)findViewById(R.id.dayy);
        dayy.setText(String.valueOf(dzien));

        edytujPrzedmiot();
        usunPrzedmiot();
    }

    public void edytujPrzedmiot()
    {
        edytuj = (Button)findViewById(R.id.edytuj);
        godzina1_zaj = (EditText)findViewById(R.id.h_zajec1);
        godzina2_zaj = (EditText)findViewById(R.id.h_zajec2) ;
        nazwa_zaj = (EditText)findViewById(R.id.nazwa_zajec);
        sala_zaj = (EditText)findViewById(R.id.sala_gab);

        edytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateZajecieDH(new Dane(dzien, String.valueOf(godzina1_zaj.getText()), String.valueOf(godzina2_zaj.getText()),
                        String.valueOf(nazwa_zaj.getText()), String.valueOf(sala_zaj.getText()), Integer.valueOf(test)));

                Intent returnIntent = new Intent();
                returnIntent.putExtra("EditMessage", "Przedmiot został zedytowany");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    public void usunPrzedmiot()
    {
        usun = (Button)findViewById(R.id.usun);
        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.usunTest(Integer.valueOf(test));

                Intent returnIntent = new Intent();
                returnIntent.putExtra("DeleteMessage", "Przedmiot został usuniety");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}

