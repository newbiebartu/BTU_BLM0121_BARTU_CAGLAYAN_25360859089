package com.bank.app.accounts;

public class YatirimHesabi extends BankaHesabi {
    private String hesapTuru;

    public YatirimHesabi(double bakiye) {
        super(bakiye);
        this.hesapTuru = "Yatırım Hesabı";
    }
    public void paraEkle(double miktar) {
        if (miktar > 0) {
            double yeniBakiye = getBakiye() + miktar;
            setBakiye(yeniBakiye);
            System.out.println("İşlem Başarılı: Yatırım hesabına " + miktar + " TL eklendi.");}
         else {
            System.out.println("Hata: Geçersiz miktar!");
        }
    }

    public void paraCek(double miktar) {
        if (getBakiye() >= miktar) {
            setBakiye(getBakiye() - miktar);
            System.out.println("İşlem Başarılı: Yatırım hesabından " + miktar + " TL çekildi.");}
         else {
            System.out.println("Hata: Yatırım hesabında yeterli bakiye yok! Mevcut: " + getBakiye());
        }
    }
    public String getHesapTuru() { return hesapTuru; }
    public void setHesapTuru(String hesapTuru) { this.hesapTuru = hesapTuru; }

    public String toString() {
        return super.toString() + " Tür: " + hesapTuru;
    }
}