package model;

public abstract class AnggotaKlub implements Informasi{
    private final String nama;
    
    public AnggotaKlub(String nama) {
        this.nama = nama;
    }
    
    public final String getNama() {
        return nama;
    }
    
    public abstract String getPeran();
    
    @Override
    public String getInfo() {
        return "Peran            : " + getPeran()
             + "\nNama             : " + nama; 
    }
}

