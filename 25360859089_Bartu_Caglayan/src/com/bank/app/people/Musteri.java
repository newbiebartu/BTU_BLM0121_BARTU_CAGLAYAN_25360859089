package com.bank.app.people;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;

import java.util.ArrayList;
import java.util.Random;

public class Musteri extends Kisi {
	
    private String musteriNumarasi;
    
    private ArrayList<BankaHesabi> hesaplar; 
    
    private ArrayList<KrediKarti> krediKartlari; 

    public Musteri(String ad, String soyad, String email, int telefonNumarasi) {
        super(ad, soyad, email, telefonNumarasi);
        
        Random rand = new Random();
        
        this.musteriNumarasi = "M-" + (1000 + rand.nextInt(4000));  
        this.hesaplar = new ArrayList<>();
        this.krediKartlari = new ArrayList<>();
    }

//Yeni hesap türü ekler ve öncekiyle aynı mı kontrolü yapar.
    public void hesapEkle(String hesapTuru) {
        if (hesapTuru.equalsIgnoreCase("Vadesiz")) {
            // Yeni hesap açılırken bakiye varsayılan olarak 0 başlatılır
            hesaplar.add(new VadesizHesap(0.0)); 
        } else if (hesapTuru.equalsIgnoreCase("Yatirim")) {
            hesaplar.add(new YatirimHesabi(0.0));
        }
    }

    
    public void krediKartiEkle(double limit) {
        krediKartlari.add(new KrediKarti(limit, 0));
    }

    
    public void hesapSil(BankaHesabi hesap) {
        if (hesap.getBakiye() > 0) {
            System.out.println("Lütfen öncelikle bakiyenizi başka bir hesaba aktarınız.");
        } else {
            hesaplar.remove(hesap);
            System.out.println("Hesap başarıyla silindi.");
        }
    }

    // Borç kontrolü yapılarak kart silinir
    public void krediKartiSil(KrediKarti kart) {
        if (kart.getGuncelBorc() > 0) {
            System.out.println("Lütfen öncelikle borç ödemesi yapınız."); 
        } else {
            krediKartlari.remove(kart);
         System.out.println("Kredi kartı silindi.");
        }
    }

    public String getMusteriNumarasi() { return musteriNumarasi; }
    
    public ArrayList<BankaHesabi> getHesaplar() { return hesaplar; }
    public ArrayList<KrediKarti> getKrediKartlari() { return krediKartlari; }

    public String toString() {
        return super.toString() + " Müşteri No: " + musteriNumarasi;
    }
  }