package UI;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import DB.DBSettings;

public class MusteriEkle extends JFrame{
    private JPanel musteriekleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton evetRadioButton;
    private JRadioButton hayırRadioButton;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton ekleButton;

    public MusteriEkle(){
        this.setTitle("Müşteri Bilgilerini Giriniz");
        this.setContentPane(musteriekleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();

                String ad = textField1.getText().toUpperCase();
                String soyad = textField2.getText().toUpperCase();
                String telefon = textField3.getText();
                String mail = textField4.getText().toUpperCase();
                String yas = textField5.getText();
                String cinsiyet = textField6.getText().toUpperCase();
                String adres = textField7.getText();
                String kangrubu = textField8.getText().toUpperCase();
                String vatandaslik = textField9.getText().toUpperCase();
                String rezerve_tarih = textField10.getText();
                String rezervea_tarih = textField11.getText();

                if((rezerve_tarih).equals(""))
                    rezerve_tarih = ("0000-00-00");

                if((rezervea_tarih).equals(""))
                    rezervea_tarih = ("0000-00-00");

                Boolean rezerv = false;
                if (evetRadioButton.isSelected()){
                    rezerv=true;
                }

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("INSERT INTO musteri(musteri_ad, musteri_soyad, musteri_telefon_no, musteri_mail, musteri_yas, musteri_cinsiyet, musteri_adres, musteri_kan_grubu, musteri_vatandaslik, rezervasyon_durum, rezervasyonun_yapildigi_tarih, rezervasyonun_alindigi_tarih) VALUES ('"+ad+"','"+soyad+"',"+telefon+",'"+mail+"','"+yas+"','"+cinsiyet+"','"+adres+"','"+kangrubu+"','"+vatandaslik+"',"+rezerv+",'"+rezerve_tarih+"','"+rezervea_tarih+"')");
                    JOptionPane.showMessageDialog(null,"Müşteri Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(evetRadioButton.isSelected())
                {
                    textField10.setEditable(true);
                    textField11.setEditable(true);
                }
                else if(hayırRadioButton.isSelected())
                {
                    textField10.setEditable(false);
                    textField11.setEditable(false);
                }
            }
        };
        evetRadioButton.addActionListener(listener);
        hayırRadioButton.addActionListener(listener);
    }
}
