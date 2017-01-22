package com.example.oem.planzajec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button pon, wto, sro, czw, piat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        int dzien = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dzien) {
            case Calendar.MONDAY:
                iDay("Poniedziałek");
                break;
            case Calendar.TUESDAY:
                iDay("Wtorek");
                break;
            case Calendar.WEDNESDAY:
                iDay("Środa");
                break;
            case Calendar.THURSDAY:
                iDay("Czwartek");
                break;
            case Calendar.FRIDAY:
                iDay("Piątek");
                break;
        }

        initPon();
        initWto();
        initSro();
        initCzw();
        initPiat();

    }

    public void iDay(String dzienTyg)
    {
        Intent mon = new Intent(MainActivity.this, DzienTyg.class);
        mon.putExtra("Day", String.valueOf(dzienTyg));
        startActivity(mon);
    }

    public void initPon()
    {
        pon = (Button)findViewById(R.id.pon);
        pon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDay("Poniedziałek");
            }
        });
    }

    public void initWto()
    {
        wto = (Button)findViewById(R.id.wto);
        wto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDay("Wtorek");
            }
        });
    }

    public void initSro()
    {
        sro = (Button)findViewById(R.id.sro);
        sro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDay("Środa");
            }
        });
    }
    public void initCzw()
    {
        czw = (Button)findViewById(R.id.czw);
        czw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDay("Czwartek");
            }
        });
    }
    public void initPiat()
    {
        piat = (Button)findViewById(R.id. piat);
        piat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDay("Piątek");
            }
        });
    }

}
