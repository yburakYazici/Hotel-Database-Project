package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Yonetici extends JFrame{
    private JPanel adminpanel;
    private JButton odaListeleButton;
    private JButton odaGüncelleButton;
    private JButton çalışanEkleButton;
    private JButton müşteriListeleButton;
    private JButton etkinlikGüncelleButton;
    private JButton çalışanGüncelleButton;
    private JButton çalışanSilButton;
    private JButton organizasyonGüncelleButton;
    private JButton giriseDonButton;
    private JButton marketGüncelleButton;
    private JButton çalışanListeleButton;
    private JButton marketListeleButton;
    private JButton ürünEkleButton;
    private JButton müşteriSilButton;
    private JButton müşteriEkleButton;
    private JButton odaEkleButton;
    private JButton odaSilButton;
    private JButton ürünSilButton;
    private JButton müşteriGüncelleButton;
    private JButton etkinlikEkleButton;
    private JButton organizasyonEkleButton;
    private JButton etkinlikSilButton;
    private JButton organizasyonSilButton;
    private JButton etkinlikListeleButton;
    private JButton organizasyonListeleButton;
    private JButton rezervasyonEkleButton;
    private JButton rezervasyonSilButton;
    private JButton rezervasyonListeleButton;
    private JButton rezervasyonGüncelleButton;

    public Yonetici(){
        this.setTitle("Yonetici Panel");
        this.setContentPane(adminpanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        giriseDonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Giris();
            }
        });
        ürünEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünEkle();
            }
        });
        ürünSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünSil();
            }
        });
        marketListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünListele();
            }
        });
        odaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OdaEkle();
            }
        });
        çalışanListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalisanListele();
            }
        });
        çalışanEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalisanEkle();
            }
        });
        çalışanSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalisanSil();
            }
        });
        etkinlikEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtkinlikEkle();
            }
        });
        etkinlikSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtkinlikSil();
            }
        });
        etkinlikListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtkinlikListele();
            }
        });
        marketGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünGüncelle();
            }
        });
        çalışanGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalisanGuncelle();
            }
        });
        etkinlikGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtkinlikGüncelle();
            }
        });
        odaListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OdaListele();
            }
        });
        odaSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OdaSil();
            }
        });
        organizasyonEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrganizasyonEkle();
            }
        });
        organizasyonSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrganizasyonSil();
            }
        });
        organizasyonListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrganizasyonListele();
            }
        });
        odaGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OdaGüncelle();
            }
        });
        müşteriEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MusteriEkle();
            }
        });
        müşteriSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MusteriSil();
            }
        });
        müşteriListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MusteriListele();
            }
        });
        müşteriGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MüşteriGüncelle();
            }
        });
        organizasyonGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrganizasyonGüncelle();
            }
        });
        rezervasyonEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RezervasyonEkle();
            }
        });
        rezervasyonSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RezervasyonSil();
            }
        });
        rezervasyonListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RezervasyonListele();
            }
        });
        rezervasyonGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RezervasyonGüncelle();
            }
        });
    }
}
