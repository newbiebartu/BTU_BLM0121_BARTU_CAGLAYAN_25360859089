package com.bank.app.people;

import java.util.ArrayList;
import java.util.Random;

public class BankaPersoneli extends Kisi {
    private String personelID;
    private ArrayList<Musteri> musteriler; // müşteriler listesi 

    public BankaPersoneli(String ad, String soyad, String email, int telefonNumarasi) {
        super(ad, soyad, email, telefonNumarasi); 
        
        Random rand = new Random();
        this.personelID = "P-" + (100 + rand.nextInt(400));
        
        this.musteriler = new ArrayList<>();
    }

    public String getPersonelID() { 
    	return personelID; 
    }
    public ArrayList<Musteri> getMusteriler() { 
    	return musteriler; 
    }
    public String toString() {
        return super.toString() + " Personel ID: " + personelID;
    }
  }