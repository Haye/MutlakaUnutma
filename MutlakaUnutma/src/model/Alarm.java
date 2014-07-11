package model;

import java.sql.Time;
import java.util.Date;


public class Alarm {
    
    public enum Durum { aktif,pasif,ertelenmis }
    
    private Date tarih;
    private String alarmNotu;
    private String kategori; //enum yap
    private Durum alarmDurumu;
    private int ertelenmeSuresi;

    public Alarm() {
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getAlarmNotu() {
        return alarmNotu;
    }

    public void setAlarmNotu(String alarmNotu) {
        this.alarmNotu = alarmNotu;
    }

    public Durum getAlarmDurumu() {
        return alarmDurumu;
    }

    public void setAlarmDurumu(Durum alarmDurumu) {
        this.alarmDurumu = alarmDurumu;
    }

    public int getErtelenmeSuresi() {
        return ertelenmeSuresi;
    }

    public void setErtelenmeSuresi(int ertelenmeSuresi) {
        this.ertelenmeSuresi = ertelenmeSuresi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    
    
}
