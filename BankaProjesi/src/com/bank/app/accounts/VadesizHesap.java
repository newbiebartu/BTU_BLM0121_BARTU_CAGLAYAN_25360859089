package com.bank.app.accounts;

import com.bank.app.cards.KrediKarti;

public class VadesizHesap extends BankaHesabi {
    
    private String hesapTuru;

    
    public VadesizHesap(double bakiye) {
        super(bakiye);
        this.hesapTuru = "Vadesiz";
    }

    public void paraTransferi(BankaHesabi aliciHesap, BankaHesabi gonderenHesap, double miktar) {
        if (gonderenHesap.getBakiye() >= miktar) {
            
            gonderenHesap.setBakiye(gonderenHesap.getBakiye() - miktar);
            aliciHesap.setBakiye(aliciHesap.getBakiye() + miktar);
            
            System.out.println("Başarılı: " + miktar + " TL transfer edildi.");
            System.out.println("Kalan Bakiyeniz: " + gonderenHesap.getBakiye() + " TL");}
         else {
            System.out.println("Hata: Transfer için vadesiz hesabınızda yeterli bakiye bulunmuyor.");
        }
    }
    public void krediKartiBorcOdeme(KrediKarti kart, double miktar) {
        if (this.getBakiye() >= miktar) {
            
            this.setBakiye(this.getBakiye() - miktar);
            
            kart.setGuncelBorc(kart.getGuncelBorc() - miktar);
            
            System.out.println("Başarılı: Kredi kartı borcunuzdan " + miktar + " TL ödendi.");
            System.out.println("Kalan Kart Borcu: " + kart.getGuncelBorc() + " TL");}
         else {
            System.out.println("Hata: Borç ödemesi için vadesiz hesabınızda yeterli bakiye yok.");
        }
    }

    public String getHesapTuru() { return hesapTuru; }
    public void setHesapTuru(String hesapTuru) { this.hesapTuru = hesapTuru; }

    public String toString() {
        return super.toString() + " | Hesap Türü: " + hesapTuru;
    }
}