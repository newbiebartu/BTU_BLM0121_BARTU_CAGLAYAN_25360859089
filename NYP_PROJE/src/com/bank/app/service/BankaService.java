package com.bank.app.service;

import com.bank.app.people.BankaPersoneli;
import com.bank.app.people.Musteri;


public class BankaService {
 public void musteriAta(BankaPersoneli personel, Musteri musteri) {
     personel.getMusteriler().add(musteri);
     System.out.println("Sistem Bilgisi: " + musteri.getAd() + " " + musteri.getSoyad() + " isimli müşteri, " + personel.getAd() + " isimli personele başarıyla atandı.");}

 public void personelMusterileriniListele(BankaPersoneli personel) {
     System.out.println(" " + personel.getAd() + " İsimli Personelin Portföyü");
   
     if (personel.getMusteriler().isEmpty()) {
         System.out.println("Bu personele atanmış bir müşteri bulunmamaktadır.");}
      else {
         for (Musteri m : personel.getMusteriler()) {
             System.out.println("- " + m.getAd() + " " + m.getSoyad() + " (Müşteri No: " + m.getMusteriNumarasi() + ")");
         }  
      }
 }
}