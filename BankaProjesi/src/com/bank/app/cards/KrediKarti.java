package com.bank.app.cards;

import java.util.Random;

public class KrediKarti {
    private String kartNumarasi;
    private double limit;
    private double guncelBorc;
    private double kullanilabilirLimit;

    public KrediKarti(double limit, double guncelBorc) {
        this.limit = limit;
        this.guncelBorc = guncelBorc;
        kullanilabilirLimit = limit - guncelBorc;
        
        Random rand = new Random();
        this.kartNumarasi = "4500-" + (1000 + rand.nextInt(9000)) + "-" + (1000 + rand.nextInt(9000));
    }

    public String getKartNumarasi() { return kartNumarasi; }
    public void setKartNumarasi(String kartNumarasi) { this.kartNumarasi = kartNumarasi; }

    public double getLimit() { return limit; }
    public void setLimit(double limit) { 
        this.limit = limit; 
        limitGuncelle();
    }

    public double getGuncelBorc() { return guncelBorc; }
    public void setGuncelBorc(double guncelBorc) { 
        this.guncelBorc = guncelBorc; 
        limitGuncelle();
    }

    public double getKullanilabilirLimit() { return kullanilabilirLimit; }
    
    private void limitGuncelle() {
        this.kullanilabilirLimit = this.limit - this.guncelBorc;
    }
    public String toString() {
        return "Kart No: " + kartNumarasi + " | Limit: " + limit + " TL | Borç: " + guncelBorc + " TL | Kullanılabilir: " + kullanilabilirLimit + " TL";
    }
  }