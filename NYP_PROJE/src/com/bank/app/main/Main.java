package com.bank.app.main;

import com.bank.app.people.Musteri;
import com.bank.app.people.BankaPersoneli;
import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;
import com.bank.app.service.BankaService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tarayici = new Scanner(System.in);
        BankaService bankaServisi = new BankaService();
        
        BankaPersoneli personel = new BankaPersoneli("Bartu", "Caglayan", "bartu@btu.edu.tr", 224333);
        Musteri aktifMusteri = null;

        System.out.println("********** BTU BANKA OTOMASYONUNA HOŞ GELDİNİZ **********");

        boolean devamEt = true;
        while (devamEt) {
            System.out.println("\n--- İŞLEM MENÜSÜ ---");
            System.out.println("1- Müşteri Oluştur");
            System.out.println("2- Hesap Aç (Vadesiz/Yatırım)");
            System.out.println("3- Hesaba Para Yatır/Para Çek (Hesap Seçerek)");
            System.out.println("4- Para Transferi Yap (Hesaplar Arası Seçerek)");
            System.out.println("5- Kredi Kartı Tanımla");
            System.out.println("6- Kredi Kartı Borcu Öde");
            System.out.println("7- Hesap Sil");
            System.out.println("8-Kredi Kartı ile harcama yap");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");
            
            int secim = tarayici.nextInt();
            tarayici.nextLine(); 

            switch (secim) {
                case 1:
                    System.out.print("Adınız: ");
                    String ad = tarayici.nextLine();
                    System.out.print("Soyadınız: ");
                    String soyad = tarayici.nextLine();
                    System.out.print("Email: ");
                    String email = tarayici.nextLine();
                    System.out.print("Telefon: ");
                    int tel = tarayici.nextInt();
                    
                    aktifMusteri = new Musteri(ad, soyad, email, tel);
                    bankaServisi.musteriAta(personel, aktifMusteri);
                    break;

                case 2:
                    if (aktifMusteri == null) {
                        System.out.println("Önce müşteri oluşturmalısınız!");
                        break;
                    }
                    System.out.print("Hesap Türü (Vadesiz/Yatirim): ");
                    String tur = tarayici.nextLine();
                    aktifMusteri.hesapEkle(tur);
                    break;

                case 3:
                    if (aktifMusteri == null || aktifMusteri.getHesaplar().isEmpty()) {
                        System.out.println("İşlem yapılacak hesap bulunamadı!");
                        break;
                    }
                    
                    // Mevcut hesapları listele
                    System.out.println("\n--- Hesaplarınız ---");
                    for (int i = 0; i < aktifMusteri.getHesaplar().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getHesaplar().get(i).toString());
                    }
                    System.out.print("İşlem yapmak istediğiniz hesabın numarasını seçin: ");
                    int islemIndex = tarayici.nextInt();
                    
                    if (islemIndex >= 0 && islemIndex < aktifMusteri.getHesaplar().size()) {
                        BankaHesabi secilenHesap = aktifMusteri.getHesaplar().get(islemIndex);
                        
                        
                        System.out.println("Ne işlemi yapmak istiyorsunuz?");
                        System.out.println("1- Para Yatır");
                        System.out.println("2- Para Çek");
                        System.out.print("Seçiminiz: ");
                        int altSecim = tarayici.nextInt();
                        
                        System.out.print("Miktar giriniz: ");
                        double miktar = tarayici.nextDouble();
                        
                        if (altSecim == 1) { // PARA YATIRMA İŞLEMİ
                            if (secilenHesap instanceof YatirimHesabi) {
                                ((YatirimHesabi) secilenHesap).paraEkle(miktar);
                            } else {
                                secilenHesap.setBakiye(secilenHesap.getBakiye() + miktar);
                                System.out.println("İşlem Başarılı. Hesabınıza " + miktar + " TL yatırıldı. Güncel bakiye: " + secilenHesap.getBakiye() + " TL");
                            }
                        } 
                        else if (altSecim == 2) { // PARA ÇEKME İŞLEMİ
                            if (secilenHesap instanceof YatirimHesabi) {
                                ((YatirimHesabi) secilenHesap).paraCek(miktar); 
                            } else {
                                // Vadesiz hesap için bakiye kontrolü
                                if (secilenHesap.getBakiye() >= miktar) {
                                    secilenHesap.setBakiye(secilenHesap.getBakiye() - miktar);
                                    System.out.println("İşlem Başarılı. Hesabınızdan " + miktar + " TL çekildi. Kalan bakiye: " + secilenHesap.getBakiye() + " TL");
                                } else {
                                    System.out.println("Hata: Para çekmek için bakiyeniz yetersiz! (Mevcut: " + secilenHesap.getBakiye() + " TL)");
                                }
                            }
                        } 
                        else {
                            System.out.println("Geçersiz işlem seçtiniz!");
                        }
                    } else {
                        System.out.println("Geçersiz hesap seçimi!");
                    }
                    break;
                    
                case 4:
                    if (aktifMusteri == null || aktifMusteri.getHesaplar().size() < 2) {
                        System.out.println("Transfer için en az 2 hesabınızın olması gerekir!");
                        break;
                    }
                    // Hesapları listele
                    for (int i = 0; i < aktifMusteri.getHesaplar().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getHesaplar().get(i).toString());
                    }
                    
                    System.out.print("Paranın çekileceği (Gönderen) hesap numarası: ");
                    int gonderenIdx = tarayici.nextInt();
                    System.out.print("Paranın yatacağı (Alıcı) hesap numarası: ");
                    int aliciIdx = tarayici.nextInt();
                    
                    if (gonderenIdx != aliciIdx && gonderenIdx < aktifMusteri.getHesaplar().size() && aliciIdx < aktifMusteri.getHesaplar().size()) {
                        BankaHesabi gonderen = aktifMusteri.getHesaplar().get(gonderenIdx);
                        BankaHesabi alici = aktifMusteri.getHesaplar().get(aliciIdx);
                        
                        //  Gönderen hesap VadesizHesap olmalı 
                        if (gonderen instanceof VadesizHesap) {
                            System.out.print("Transfer Miktarı: ");
                            double miktar = tarayici.nextDouble();
                            ((VadesizHesap) gonderen).paraTransferi(alici, gonderen, miktar);
                        } else {
                            System.out.println("Hata: Sadece vadesiz hesaplardan transfer yapabilirsiniz.");
                        }
                    } else {
                        System.out.println("Geçersiz seçim yaptınız.");
                    }
                    break;

                case 5:
                    if (aktifMusteri == null) break;
                    System.out.print("Kart Limiti: ");
                    double limit = tarayici.nextDouble();
                    aktifMusteri.krediKartiEkle(limit);
                    break;

                case 6:
                    // Hem hesabın hem de kredi kartının olup olmadığını kontrol ediyoruz
                    if (aktifMusteri == null || aktifMusteri.getKrediKartlari().isEmpty() || aktifMusteri.getHesaplar().isEmpty()) {
                        System.out.println("İşlem için hesap veya kredi kartı eksik!");
                        break;
                    }
                    
                    // 1. ADIM: Kredi Kartlarını gösterip işlem için hangisi kullanılacaksa seçme imkanı veriyor
                    System.out.println("\n--- Kredi Kartlarınız ---");
                    for (int i = 0; i < aktifMusteri.getKrediKartlari().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getKrediKartlari().get(i).toString());
                    }
                    System.out.print("Borcunu ödemek istediğiniz kartın numarasını seçin: ");
                    int kartIdx = tarayici.nextInt();
                    
                    if(kartIdx < 0 || kartIdx >= aktifMusteri.getKrediKartlari().size()){
                        System.out.println("Geçersiz kart seçimi!");
                        break;
                    }
                    KrediKarti secilenKart = aktifMusteri.getKrediKartlari().get(kartIdx);

                    // 2. ADIM: Banka Hesaplarını gösterip işlem için hangisi kullanılacaksa seçme imkanı veriyor
                    System.out.println("\n--- Banka Hesaplarınız ---");
                    for (int i = 0; i < aktifMusteri.getHesaplar().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getHesaplar().get(i).toString());
                    }
                    System.out.print("Ödemenin çekileceği hesabın numarasını seçin: ");
                    int odeyecekHesapIdx = tarayici.nextInt();
                    
                    if(odeyecekHesapIdx < 0 || odeyecekHesapIdx >= aktifMusteri.getHesaplar().size()){
                        System.out.println("Geçersiz hesap seçimi!");
                        break;
                    }
                    
                    // Seçilen hesap gerçekten Vadesiz Hesap mı kontrolü yapılıyor
                    if (aktifMusteri.getHesaplar().get(odeyecekHesapIdx) instanceof VadesizHesap) {
                        VadesizHesap vh = (VadesizHesap) aktifMusteri.getHesaplar().get(odeyecekHesapIdx);
                        
                        System.out.print("Ödenecek Tutar: ");
                        double tutar = tarayici.nextDouble();
                        
                        // vadesiz hesabın içindeki metodu kullanarak, seçilen kartın borcunu ödüyoruz
                        vh.krediKartiBorcOdeme(secilenKart, tutar);
                    } else {
                        System.out.println("Hata: Kredi kartı borcu sadece Vadesiz Hesaptan ödenebilir!");
                    }
                    break;

                case 7:
                    if (aktifMusteri == null || aktifMusteri.getHesaplar().isEmpty()) break;
                    System.out.println("Silinecek hesabı seçin:");
                    for (int i = 0; i < aktifMusteri.getHesaplar().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getHesaplar().get(i).toString());
                    }
                    int silinecekIdx = tarayici.nextInt();
                    aktifMusteri.hesapSil(aktifMusteri.getHesaplar().get(silinecekIdx));
                    break;

                case 8:
                    if (aktifMusteri == null || aktifMusteri.getKrediKartlari().isEmpty()) {
                        System.out.println("Harcama yapmak için kredi kartınız bulunmamaktadır!");
                        break;
                    }
                    
                    System.out.println("\n--- Kredi Kartlarınız ---");
                    for (int i = 0; i < aktifMusteri.getKrediKartlari().size(); i++) {
                        System.out.println(i + " - " + aktifMusteri.getKrediKartlari().get(i).toString());
                    }
                    System.out.print("Harcama yapmak istediğiniz kartın numarasını seçin: ");
                    int harcamaKartIdx = tarayici.nextInt();
                    
                    if(harcamaKartIdx < 0 || harcamaKartIdx >= aktifMusteri.getKrediKartlari().size()){
                        System.out.println("Böyle bir kart bulunamadı!");
                        break;
                    }
                    KrediKarti harcamaKart = aktifMusteri.getKrediKartlari().get(harcamaKartIdx);

                    System.out.print("Alışveriş / Harcama Tutarı: ");
                    double harcamaTutari = tarayici.nextDouble();

                    if (harcamaTutari <= harcamaKart.getKullanilabilirLimit()) {
                        // Mevcut borcun üzerine yeni harcamadan oluşan borcu ekliyoruz
                        harcamaKart.setGuncelBorc(harcamaKart.getGuncelBorc() + harcamaTutari);
                        System.out.println("İşlem Başarılı: " + harcamaTutari + " TL tutarında harcama yapıldı.");
                        System.out.println("Güncel Kart Borcunuz: " + harcamaKart.getGuncelBorc() + " TL");
                        System.out.println("Kalan Limitiniz: " + harcamaKart.getKullanilabilirLimit() + " TL");
                    } else {
                        System.out.println("Hata: Kartınızın kullanılabilir limiti bu işlem için yetersiz!");
                    }
                    break;
                    
                case 0:
                    devamEt = false;
                    System.out.println("Çıkış yapıldı.");
                    break;
            }
        }
        tarayici.close();
    }
}