package controller;

import model.Pertandingan;
import java.util.ArrayList;
import model.Pemain;
import model.Pelatih;
import view.TampilanView;

public class ManajemenKlub {
    private final ArrayList<Pemain> daftarPemain;
    private final ArrayList<Pelatih> daftarPelatih;
    private final ArrayList<Pertandingan> daftarPertandingan;
    
    private final TampilanView tampilanView;
    
    public ManajemenKlub() {
        daftarPemain = new ArrayList<>();
        daftarPelatih = new ArrayList<>();
        daftarPertandingan = new ArrayList<>();
        
        tampilanView = new TampilanView();
        
        //=== DUMMY DATA ===
        daftarPemain.add(new Pemain("Rizky Pratama", "GK", 1, 24, "Tersedia"));
        daftarPemain.add(new Pemain("Fadli Ramadhan", "LB", 4, 22, "Tersedia"));
        daftarPemain.add(new Pemain("Andika Saputra", "CB", 5, 26, "Cedera"));
        daftarPemain.add(new Pemain("Bagas Wijaya", "CDM", 6, 23, "Tersedia"));
        daftarPemain.add(new Pemain("Ilham Nugroho", "CM", 8, 21, "Cedera"));
        daftarPemain.add(new Pemain("Reza Firmansyah", "RW", 7, 25, "Tersedia"));
        daftarPemain.add(new Pemain("Wahyu Alamsyah", "ST", 9, 23, "Tersedia"));
 
        daftarPelatih.add(new Pelatih("Agus Salim", "Pelatih Kepala", 10));
        daftarPelatih.add(new Pelatih("Bambang Hartono", "Pelatih Kiper", 6));
        daftarPelatih.add(new Pelatih("Cahyo Wibowo", "Pelatih Fisik", 4));
 
        daftarPertandingan.add(new Pertandingan("Persija FC", "12-08-2026", "Liga 1", 3, 1, "Kandang"));
        daftarPertandingan.add(new Pertandingan("Bali United", "19-08-2026", "Liga 1", 1, 1, "Tandang"));
        daftarPertandingan.add(new Pertandingan("Borneo FC", "26-08-2026", "Liga 1", 0, 2, "Kandang"));
        daftarPertandingan.add(new Pertandingan("Macan Kemayoran", "02-09-2026", 2, 2, "Tandang"));
    }
    
    public void tambahPemain(Pemain p) {
        daftarPemain.add(p);
    }
    
    public void tampilkanSemuaPemain(){
        tampilanView.tampilkanDaftar("Pemain", daftarPemain);
    }
    
    public boolean updateStatusPemain(String nama, String statusBaru){
        for (Pemain p : daftarPemain) {
            if (p.getNama().equalsIgnoreCase(nama)) {
                p.setStatusKesehatan(statusBaru);
                return true;
            }
        }
        return false;
    }
    
    public boolean hapusPemain(String nama){
        Pemain ditemukan = null;
        for (Pemain p : daftarPemain){
            if (p.getNama().equalsIgnoreCase(nama)){
            ditemukan = p;
            break;
            }
        }
        if (ditemukan != null){
            daftarPemain.remove(ditemukan);
            return true;
        }
        else {
            return false;
        }
    }
    
    public void tambahPelatih(Pelatih p){
        daftarPelatih.add(p);
    }
    
    public void tampilkanSemuaPelatih(){
        tampilanView.tampilkanDaftar("Pelatih", daftarPelatih);
    }
    
    public void tambahPertandingan(Pertandingan p){
        daftarPertandingan.add(p);
    }
    
    public void tampilkanRiwayatPertandingan(){
        tampilanView.tampilkanDaftar("Riwayat Pertandingan", daftarPertandingan);
    }
}        