package com.example.oem.planzajec;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DzienTyg extends AppCompatActivity {

    private DatabaseHandler db;
    TextView dzienT;
    Button dodaj_btn;
    public String day, id;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzien_tyg);

        db = new DatabaseHandler(this);

        intent = getIntent();
        day = intent.getStringExtra("Day");

        dzienT = (TextView)findViewById(R.id.dzienT);
        dzienT.setText(day);

        dodajZajecia();

        wyswietlaZajecia(day);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                wyswietlaZajecia(day);
                finish();
                startActivity(getIntent());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    public void dodajZajecia()
    {
        dodaj_btn = (Button) findViewById(R.id.dodaj_btn);
        dodaj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(DzienTyg.this, dodaj.class);
                myIntent.putExtra("Day", String.valueOf(day));
                startActivityForResult(myIntent, 1);
            }
        });
    }

    public void wyswietlaZajecia(String day)
    {
        final String dzien = day;
        List<Dane> contacts = db.wyswietlZajeciaDH(day);
        for (Dane dn : contacts) {

            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
            DateFormat df = new SimpleDateFormat("HH:mm");
            String wysDf = df.format(c.getTime());
            int currentGodzina = znaki(wysDf);
            int godzina1 = znaki(dn.getGodzina1());
            int godzina2 = znaki(dn.getGodzina2());

            final LinearLayout ll = (LinearLayout)findViewById(R.id.lvlgod);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 10);

            final Button btnTag = new Button(this);
            btnTag.setLayoutParams(lp);
            btnTag.setPadding(20, 5, 0, 5);
            btnTag.setText(dn.getGodzina1() + " - " + dn.getGodzina2()  + "\n"  + dn.getNazwaPrzedmiotu() + "\n" + "Sala: " + dn.getSala());
            btnTag.setId(dn.getRand());
            btnTag.setGravity(0x03);

            Calendar calendar = Calendar.getInstance();
            int din = calendar.get(Calendar.DAY_OF_WEEK);

            if(currentGodzina >= godzina1 && currentGodzina <= godzina2){
                btnTag.setBackgroundColor(Color.parseColor("#50e536"));
            }else if(currentGodzina < godzina1 && currentGodzina < godzina2){
                btnTag.setBackgroundColor(Color.parseColor("#36aee0"));
            }else if(currentGodzina > godzina1 && currentGodzina > godzina2){
                btnTag.setBackgroundColor(Color.parseColor("#b9bab1"));
            }Log.d("TEST", "CURRENTA GODZINA: " + currentGodzina + " | GODZINA 1: " + godzina1 + " | GODZINA 1: " + godzina2);

            ll.addView(btnTag);
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent myIntent = new Intent(DzienTyg.this, dod_usu.class);
                    myIntent.putExtra("Day", String.valueOf(dzien));
                    myIntent.putExtra("Test", String.valueOf(btnTag.getId()));
                    startActivityForResult(myIntent, 1);
                }
            });
        }
    }

    public int znaki(String znak)
    {
        List<Character> lol = new ArrayList<Character>();
        for(int i = 0; i<=znak.length() - 1; i++)
        {
            lol.add(znak.charAt(i));
        }
        String wynik = String.valueOf(lol.get(0)) + String.valueOf(lol.get(1)) + String.valueOf(lol.get(3)) + String.valueOf(lol.get(4));
        int wtn = Integer.valueOf(wynik);
        return  wtn;
    }
}