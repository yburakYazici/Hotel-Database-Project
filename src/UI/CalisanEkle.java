package UI;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import DB.DBSettings;

public class CalisanEkle extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField10;
    private JButton ekleButton;
    private JRadioButton evetRadioButton1;
    private JRadioButton hayırRadioButton1;
    private JPanel calisanEkleForm;
    private JTextField textField9;

    public CalisanEkle(){
        this.setTitle("Çalışan Bilgilerini Giriniz");
        this.setContentPane(calisanEkleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String ad = textField1.getText().toUpperCase();
                String soyad = textField2.getText().toUpperCase();
                String parola = textField3.getText();
                String alan = textField4.getText().toUpperCase();
                String telefon = textField5.getText();
                String cinsiyet = textField6.getText().toUpperCase();
                String doğum_tarihi = textField7.getText();
                String kangrubu = textField8.getText();
                String maas = textField9.getText();
                String tecrube = textField10.getText();
                int maas_int = Integer.parseInt(maas);
                int tecrube_int = Integer.parseInt(tecrube);

                Boolean admin = false;
                if (evetRadioButton1.isSelected()){
                    admin=true;
                }
                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("INSERT INTO CALİSANLAR(calisan_ad, calisan_soyad, calisan_kontrol_admin, calisan_parola, calisan_alan,calisan_telefon_no, calisan_cinsiyet,calisan_dogum_tarihi,calisan_tecrube_sene,calisan_maas, calisan_kan_grubu) VALUES ('"+ad+"','"+soyad+"',"+admin+",'"+parola+"','"+alan+"','"+telefon+"','"+cinsiyet+"','"+doğum_tarihi+"','"+tecrube_int+"','"+maas_int+"','"+kangrubu+"')");
                    JOptionPane.showMessageDialog(null,"Çalışan Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        textField7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField7.setText("");
            }
        });
    }
}
