package com.bank.app.accounts;

import java.util.Random;

public class BankaHesabi {
    private String iban;
    private double bakiye;

    public BankaHesabi(double bakiye) {
        this.bakiye = bakiye;
        Random rand = new Random();
        this.iban = "TR" + (1000 + rand.nextInt(9000)) + " " + (1000 + rand.nextInt(9000)); 
   }
    public String getIban() { 
                  return iban; }
    public double getBakiye() { 
    	          return bakiye; } 
    public void setBakiye(double bakiye) { 
    	          this.bakiye = bakiye; }

    public String toString() {
        return "IBAN:" + iban +" Bakiye:" + bakiye +" TL";
    }
  }