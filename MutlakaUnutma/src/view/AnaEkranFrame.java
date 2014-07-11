package view;

import controller.AlarmKontrol;
import controller.MenuKontrol;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AnaEkranFrame extends javax.swing.JFrame {
    public BorderLayout borderLayout;
    public MenuPanel menuPanel;
    public AlarmPanel alarmPanel;

    public AnaEkranFrame() {
        //Kontrol sınıfından erişebilmek için global tanımlandı
        borderLayout = new BorderLayout(20,30); 
        setLayout(borderLayout);
        
        //İcon resmi
        ImageIcon icon = new ImageIcon("resimler/alarm_1.png");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resimler/alarm_1.png")));

        //Pencere Başlığı
        setTitle("Mutlak Hatırlatıcı");

        //Pencere kapanınca programı sonlandır
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        menuPanel = new MenuPanel();
        alarmPanel = new AlarmPanel();
        
        alarmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Hatırlatmalar"));
         
        //Menu Paneldeki butonlara Action Listener ekleme 
        JButton[] butonlar = {menuPanel.btnAlarmlar,
                              menuPanel.btnDogumGunleri,
                              menuPanel.btnEkle,
                              menuPanel.btnFilmler,
                              menuPanel.btnMuzikler,
                              menuPanel.btnSiteler
                            };
        
        for(int i=0; i<butonlar.length; i++){
            butonlar[i].addActionListener(new MenuKontrol(this));
        }
        
        
        this.getContentPane().add(menuPanel, BorderLayout.LINE_START);
        this.getContentPane().add(alarmPanel, BorderLayout.LINE_END);
        
        
        
        
        pack();
    }
    
}
