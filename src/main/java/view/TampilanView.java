package view;

import java.util.List;
import model.Informasi;

public class TampilanView {
    
    //=== MENU DAN PESAN ===
    public void tampilkanSambutan() {
        System.out.println("=== SISTEM MANAJEMEN KLUB SEPAK BOLA");
        System.out.println("Selamat Datang, Manajer");
    }
    
    public void tampilkanMenuUtama() {
        System.out.println("=== MENU ===");
        System.out.println("1. Menu Pemain");
        System.out.println("2. Menu Pelatih");
        System.out.println("3. Menu Pertandingan");
        System.out.println("4. Keluar");
    }
        
    public void tampilkanMenuPemain() {
        System.out.println("1. Tambah Pemain");
        System.out.println("2. Lihat Semua Pemain");
        System.out.println("3. Hapus Pemain");
        System.out.println("4. Update Status Pemain");
        System.out.println("5. Kembali ke Menu Utama");
    }
        
    public void tampilkanMenuPelatih() {
        System.out.println("1. Tambah Pelatih");
        System.out.println("2. Lihat Semua Pelatih");
        System.out.println("3. Kembali ke Menu Utama");
    }
    
    public void tampilkanMenuPertandingan() {
        System.out.println("=== MENU PERTANDINGAN ===");
        System.out.println("1. Tambah Pertandingan");
        System.out.println("2. Lihat Riwayat Pertandingan");
        System.out.println("3. Kembali ke Menu Utama");
    }
    
    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
    
    // === POLYMORPHISM == 
    private void tampilkanInfo(Informasi i) {
        System.out.println(i.getInfo());
        System.out.println("--------------------------------");
    }
    
    public void tampilkanDaftar(String judul, List<? extends Informasi> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data " + judul.toLowerCase());
            return;
        }
    System.out.println("=== DAFTAR " + judul.toUpperCase() + " ===");
        for (Informasi i : daftar) {
            tampilkanInfo(i);
        }
    }   
}