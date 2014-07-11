package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Alarm;
import view.AlarmEkrani;
import view.AnaEkranFrame;

public class AlarmKontrol implements ActionListener {
    AnaEkranFrame anaEkran;
    static ArrayList<Alarm> alarmlar;
    static Alarm siradakiAlarm;
    
    public AlarmKontrol() {
        alarmlar = new ArrayList<>();
        anaEkran = new AnaEkranFrame();
        degerleriAta();
        timerBaslat();
        anaEkran.show();
        siradakiAlarmiBul();
    }
    
 
    //Viewden gelen tetiklemelerde
    public AlarmKontrol(AnaEkranFrame v) {
        anaEkran = v;
    }
 
    
    public void timerBaslat(){
        TimerTask timerTask = new TimerTask() {
        @Override
            public void run() {
                alarmKontrol();
                System.out.println("a");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 5000);
    }
    
    public void alarmKontrol(){
        if(siradakiAlarm != null){
            if(siradakiAlarm.getTarih().compareTo(new Date()) <= 0){
                try {
                    alarmGoster();
                } catch (IOException ex) {
                    Logger.getLogger(AlarmKontrol.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public void alarmGoster() throws IOException{
        if(siradakiAlarm.getAlarmDurumu() != Alarm.Durum.pasif){
            AlarmEkrani alarm = new AlarmEkrani(siradakiAlarm);
            alarm.show();
            alarmlar.remove(siradakiAlarm);
        }
        
        //alarmı sonlandır deyince çalış
        siradakiAlarmiBul();
    }
    
    public void siradakiAlarmiBul(){
        if(alarmlar.size()>0){
            siradakiAlarm = alarmlar.get(0);

            for(int i=0; i<alarmlar.size(); i++){
                if(alarmlar.get(i).getTarih().compareTo(siradakiAlarm.getTarih()) < 0 && alarmlar.get(i).getAlarmDurumu() != Alarm.Durum.pasif){
                    siradakiAlarm = alarmlar.get(i);
                }
            }

            anaEkran.alarmPanel.txtSiradakiAlarm.setText(siradakiAlarm.getAlarmNotu()+ " : " + siradakiAlarm.getTarih().toString());
        }else{
            siradakiAlarm = null;
            anaEkran.alarmPanel.txtSiradakiAlarm.setText("Aktif Alarm Yok");
        }
    }
    
    
    public void degerleriAta(){
        cmbDegerler();
        //Bu bilgiler veritabanından çekilecek 
        /*
        for(int i = 0; i < 3; i++){
            Alarm a = new Alarm();
            a.setTarih(new Date());
            a.setKategori("Genel");
            a.setAlarmNotu("Alarm "+i);
            alarmlar.add(a);
        }
        */
        anaEkran.alarmPanel.btnAlarmEkle.addActionListener(new AlarmKontrol(anaEkran));
        tabloGuncelle(alarmlar);
    }
    
    private void cmbDegerler(){
        Date simdikiTarih = new Date();
        String[] gunler = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        anaEkran.alarmPanel.cmbGun.setModel(new javax.swing.DefaultComboBoxModel(gunler));
        anaEkran.alarmPanel.cmbGun.setSelectedIndex(simdikiTarih.getDate()-1);
        
        
        String[] aylar = { "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
        anaEkran.alarmPanel.cmbAy.setModel(new javax.swing.DefaultComboBoxModel(aylar));
        anaEkran.alarmPanel.cmbAy.setSelectedIndex(simdikiTarih.getMonth());
        
        
        String[] yillar = { "2014", "2015"};
        anaEkran.alarmPanel.cmbYil.setModel(new javax.swing.DefaultComboBoxModel(yillar));
        anaEkran.alarmPanel.cmbYil.setSelectedIndex(simdikiTarih.getYear() - 114);
        
        String[] kategoriler = {"Genel","Önemli","İş","Kişisel"}; 
        anaEkran.alarmPanel.cmbKategori.setModel(new javax.swing.DefaultComboBoxModel(kategoriler));

        String[] saat = new String[24];
        for(int i=0; i<24; i++){
            saat[i] = i+"";
        }
        anaEkran.alarmPanel.cmbSaat.setModel(new javax.swing.DefaultComboBoxModel(saat));
        anaEkran.alarmPanel.cmbSaat.setSelectedIndex(simdikiTarih.getHours());
        
        String[] dakika = new String[60];
        for(int i=0; i<60; i++){
            dakika[i] = i+"";
        }
        anaEkran.alarmPanel.cmbDakika.setModel(new javax.swing.DefaultComboBoxModel(dakika));
        anaEkran.alarmPanel.cmbDakika.setSelectedIndex(simdikiTarih.getMinutes());
    }
    
    private void tabloGuncelle(ArrayList<Alarm> alarmlar){
        
        DefaultTableModel a = new DefaultTableModel();
        
        String kolonAdlari [] = {
            "Tarih",
            "Saat",
            "Hatırlatma Notu",
            "Kategori"
        };
        
        Object [][] data = new Object[alarmlar.size()][kolonAdlari.length];
         
        for(int i=0;i<alarmlar.size(); i++){
            data[i][0] = alarmlar.get(i).getTarih().getDate()+" - "+(alarmlar.get(i).getTarih().getMonth()+1)+" - "+(alarmlar.get(i).getTarih().getYear()+1900);
            data[i][1] = alarmlar.get(i).getTarih().getHours()+" : "+alarmlar.get(i).getTarih().getMinutes();
            data[i][2] = alarmlar.get(i).getAlarmNotu();
            data[i][3] = alarmlar.get(i).getKategori();
        }
        
        a.setDataVector(data, kolonAdlari);

        
        anaEkran.alarmPanel.tblAlarmlar.setModel(a);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == anaEkran.alarmPanel.btnAlarmEkle){
            Alarm a = new Alarm();
            Date d = new Date(anaEkran.alarmPanel.cmbYil.getSelectedIndex()+114,
                              anaEkran.alarmPanel.cmbAy.getSelectedIndex(),
                              anaEkran.alarmPanel.cmbGun.getSelectedIndex()+1,
                              anaEkran.alarmPanel.cmbSaat.getSelectedIndex(),
                              anaEkran.alarmPanel.cmbDakika.getSelectedIndex(),
                              0
                              );
            a.setTarih(d);
            
            a.setAlarmDurumu(Alarm.Durum.aktif);
            a.setAlarmNotu(anaEkran.alarmPanel.txtNot.getText());
            a.setKategori((String)anaEkran.alarmPanel.cmbKategori.getSelectedItem());
            alarmlar.add(a);
            
            tabloGuncelle(alarmlar);
            siradakiAlarmiBul();
        }
    }
}
