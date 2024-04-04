package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calisan extends JFrame {
    private JPanel calisanpanel;
    private JButton odaListeleButton;
    private JButton odaGüncelleButton;
    private JButton giriseDonButton;
    private JButton marketSatışButton;
    private JButton marketListeleButton;
    private JButton stokGüncelleButton;
    private JButton müşteriEkleButton;
    private JButton müşteriSilButton;
    private JButton müşteriListeleButton;
    private JButton müşteriGüncelleButton;
    private JButton etkinlikGüncelleButton;
    private JButton etkinlikEkleButton;
    private JButton etkinlikSilButton;
    private JButton etkinlikListeleButton;
    private JButton organizasyonGüncelleButton;
    private JButton organizasyonEkleButton;
    private JButton organizasyonSilButton;
    private JButton organizasyonListeleButton;
    private JButton rezervasyonEkleButton;
    private JButton rezervasyonSilButton;
    private JButton rezervasyonListeleButton;
    private JButton rezervasyonGüncelleButton;

    public Calisan(String calisan_id) {
        this.setTitle("Calisan Panel");
        this.setContentPane(calisanpanel);
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

        marketSatışButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarketSatis();
            }
        });
        stokGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarketStokGüncelle();
            }
        });
        marketListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ÜrünListele();
            }
        });
        odaListeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OdaListele();
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
        etkinlikGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtkinlikGüncelle();
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
