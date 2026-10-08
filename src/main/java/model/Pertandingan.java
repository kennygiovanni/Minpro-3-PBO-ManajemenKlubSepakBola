package model;

public final class Pertandingan implements Informasi {
    private final String lawan;
    private final String tanggal;
    private final String kompetisi;
    private int skorKlub;
    private int skorLawan;
    private String lokasi;

    public Pertandingan(String lawan, String tanggal, String kompetisi, int skorKlub, int skorLawan, String lokasi) {
        this.lawan = lawan;
        this.tanggal = tanggal;
        this.kompetisi = kompetisi;
        setSkorKlub(skorKlub);
        setSkorLawan(skorLawan);
        setLokasi(lokasi);
    }
    
    public Pertandingan(String lawan, String tanggal, int skorKlub, int skorLawan, String lokasi) {
        this(lawan, tanggal, "Pertandingan Persahabatan", skorKlub, skorLawan, lokasi);
    }

    private void setSkorKlub(int skorKlub) {
        if (skorKlub >= 0) {
            this.skorKlub = skorKlub;
        } 
        else {
            this.skorKlub = 0;
            System.out.println("Skor tidak boleh negatif, diset ke 0.");
        }
    }

    private void setSkorLawan(int skorLawan) {
        if (skorLawan >= 0) {
            this.skorLawan = skorLawan;
        } 
        else {
            this.skorLawan = 0;
            System.out.println("Skor tidak boleh negatif, diset ke 0.");
        }
    }
    
    private void setLokasi(String lokasi) {
        if (lokasi.equalsIgnoreCase("Kandang") || lokasi.equalsIgnoreCase("Tandang")) {
            this.lokasi = lokasi;
        }
        else {
            this.lokasi = "Kandang";
            System.out.println("Lokasi tidak dikenali, diset ke Kandang.");
        }
    }
    
    private String getHasil() {
        if (skorKlub > skorLawan) {
            return "Menang";
        }
        else if (skorKlub < skorLawan) {
            return "Kalah";
        }
        else{
            return "Seri";
        }
    }
    
    @Override
    public String getInfo() {
        return "Lawan     : " + lawan
              + "\nTanggal   : " + tanggal
              + "\nKompetisi : " + kompetisi
              + "\nSkor      : " + skorKlub + " - " + skorLawan
              + "\nLokasi    : " + lokasi
              + "\nHasil     : " + getHasil();
    }
}