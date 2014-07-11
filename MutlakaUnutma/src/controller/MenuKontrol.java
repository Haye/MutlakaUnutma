package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AlarmPanel;
import view.AnaEkranFrame;
import view.SitelerPanel;

public class MenuKontrol implements ActionListener{
    AnaEkranFrame anaEkran;
    
    public MenuKontrol(AnaEkranFrame anaEkran) {
        this.anaEkran = anaEkran;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //Sağ taraftaki içeriği temizler
        anaEkran.getContentPane().remove(anaEkran.borderLayout.getLayoutComponent(BorderLayout.LINE_END));  
        
        //Hangi butona tıklandıysa ona uygun içeriği sağ tarafa ekler
        if(ae.getSource() == anaEkran.menuPanel.btnAlarmlar){
            anaEkran.alarmPanel = new AlarmPanel();
            new AlarmKontrol(anaEkran).degerleriAta();
            anaEkran.alarmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Hatırlatmalar"));
            anaEkran.getContentPane().add(anaEkran.alarmPanel, BorderLayout.LINE_END);            
        }else if(ae.getSource() == anaEkran.menuPanel.btnSiteler){
            anaEkran.getContentPane().add(new SitelerPanel(), BorderLayout.LINE_END);  
        }else if(ae.getSource() == anaEkran.menuPanel.btnMuzikler){
            anaEkran.getContentPane().add(new SitelerPanel(), BorderLayout.LINE_END);  
        }else if(ae.getSource() == anaEkran.menuPanel.btnFilmler){
            anaEkran.getContentPane().add(new SitelerPanel(), BorderLayout.LINE_END);  
        }else if(ae.getSource() == anaEkran.menuPanel.btnDogumGunleri){
            anaEkran.getContentPane().add(new SitelerPanel(), BorderLayout.LINE_END);  
        }
        
        //Formun yeniden şekillenip gösterilmesini sağlar
        anaEkran.revalidate();
        anaEkran.repaint();
    }

   
    
}
