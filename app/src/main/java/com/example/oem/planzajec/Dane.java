package com.example.oem.planzajec;

public class Dane {

    int _id;
    String _dzien_tygodnia;
    String _godzina1;
    String _godzina2;
    String _nazwa_przedmiotu;
    String _sala;
    int _rand;

    public Dane(){

    }

    public Dane(int id, String _dzien_tygodnia, String _godzina1, String _godzina2, String _nazwa_przedmiotu, String _sala, int _rand){
        this._id = id;
        this._dzien_tygodnia = _dzien_tygodnia;
        this._godzina1 = _godzina1;
        this._godzina2 = _godzina2;
        this._nazwa_przedmiotu = _nazwa_przedmiotu;
        this._sala = _sala;
        this._rand = _rand;
    }

    public Dane(String _dzien_tygodnia, String _godzina1, String _godzina2, String _nazwa_przedmiotu, String _sala, int _rand){
        this._dzien_tygodnia = _dzien_tygodnia;
        this._godzina1 = _godzina1;
        this._godzina2 = _godzina2;
        this._nazwa_przedmiotu = _nazwa_przedmiotu;
        this._sala = _sala;
        this._rand = _rand;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }


    public String getDzienTygodnia(){
        return this._dzien_tygodnia;
    }

    public void setDzienTygodnia(String dz){    this._dzien_tygodnia = dz;  }


    public String getGodzina1(){
        return this._godzina1;
    }

    public void setGodzina1(String godzina1){
        this._godzina1 = godzina1;
    }


    public String getGodzina2(){
        return this._godzina2;
    }

    public void setGodzina2(String godzina2){
        this._godzina2 = godzina2;
    }


    public String getNazwaPrzedmiotu(){
        return this._nazwa_przedmiotu;
    }

    public void setNazwaPrzedmiotu(String name){
        this._nazwa_przedmiotu = name;
    }


    public String getSala(){
        return this._sala;
    }

    public void setSala(String phone_number){
        this._sala = phone_number;
    }


    public int getRand(){   return this._rand;  }

    public void setRand(int rand) {   this._rand = rand;    }
}