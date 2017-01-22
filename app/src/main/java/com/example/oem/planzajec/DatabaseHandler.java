package com.example.oem.planzajec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "planZajecManager7";
    private static final String TABLE_PLAN_ZAJEC = "plan_zajec7";

    private static final String KEY_ID = "id";
    private static final String KEY_DZIEN_TYGODNIA = "dzien_tygodnia";
    private static final String KEY_GODZINA1 = "godzina_1";
    private static final String KEY_GODZINA2 = "godzina_2";
    private static final String KEY_NAZW_PRZED = "nazwa_przedmiotu";
    private static final String KEY_SALA = "sala";
    private static final String KEY_RAND = "rand";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAN_ZAJEC + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DZIEN_TYGODNIA + " TEXT," + KEY_GODZINA1 + " DATETIME DEFAULT CURRENT_TIME," + KEY_GODZINA2  + " TEXT," + KEY_NAZW_PRZED + " TEXT," + KEY_SALA + " TEXT,"
                + KEY_RAND + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAN_ZAJEC);

        onCreate(db);
    }

    public void dodajZajecieDH(Dane dane) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_DZIEN_TYGODNIA, dane.getDzienTygodnia());
        wartosci.put(KEY_GODZINA1, dane.getGodzina1());
        wartosci.put(KEY_GODZINA2, dane.getGodzina2());
        wartosci.put(KEY_NAZW_PRZED, dane.getNazwaPrzedmiotu());
        wartosci.put(KEY_SALA, dane.getSala());
        wartosci.put(KEY_RAND, dane.getRand());

        db.insert(TABLE_PLAN_ZAJEC, null,  wartosci);
        db.close();
    }

    public List<Dane> wyswietlZajeciaDH(String dzien) {
        List<Dane> contactList = new ArrayList<Dane>();

        String selectQuery = "SELECT  * FROM " + TABLE_PLAN_ZAJEC + " WHERE DZIEN_TYGODNIA = '" + dzien + "'" + " ORDER BY " + KEY_GODZINA1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Dane dane = new Dane();
                dane.setID(Integer.parseInt(cursor.getString(0)));
                dane.setDzienTygodnia(cursor.getString(1));
                dane.setGodzina1(cursor.getString(2));
                dane.setGodzina2(cursor.getString(3));
                dane.setNazwaPrzedmiotu(cursor.getString(4));
                dane.setSala(cursor.getString(5));
                dane.setRand(Integer.parseInt(cursor.getString(6)));

                contactList.add(dane);
            } while (cursor.moveToNext());
        }


        return contactList;
    }

    public int updateZajecieDH(Dane dane) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_RAND + "=" + dane.getRand();
        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_GODZINA1, dane.getGodzina1());
        wartosci.put(KEY_GODZINA2, dane.getGodzina2());
        wartosci.put(KEY_NAZW_PRZED, dane.getNazwaPrzedmiotu());
        wartosci.put(KEY_SALA, dane.getSala());

        return db.update(TABLE_PLAN_ZAJEC, wartosci, where, null);
    }

    public void usunTest(int ran) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] argumenty = {""+ran};
        db.delete(TABLE_PLAN_ZAJEC, KEY_RAND + " = ?", argumenty);
    }
}
