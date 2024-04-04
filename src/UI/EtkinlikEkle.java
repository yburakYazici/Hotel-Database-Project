package UI;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBSettings;

public class EtkinlikEkle extends JFrame{
    private JPanel etkinlikEkleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button1;

    public EtkinlikEkle(){
        this.setTitle("Etkinlik Bilgilerini Giriniz");
        this.setContentPane(etkinlikEkleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String etkinlikad = textField1.getText().toUpperCase();
                String etkinliktarih = textField2.getText();
                String yas_siniri = textField3.getText();
                String kisi_siniri = textField4.getText();
                int yas_siniri_int = Integer.parseInt(yas_siniri);
                int kisi_siniri_int = Integer.parseInt(kisi_siniri);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");

                    st.executeUpdate("INSERT INTO ETKİNLİK(etkinlik_isim, etkinlik_zaman, yas_limit, sinir_limit) VALUES ('"+etkinlikad+"','"+etkinliktarih+"','"+yas_siniri_int+"',"+kisi_siniri_int+")");
                    JOptionPane.showMessageDialog(null,"Etkinlik Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField2.setText("");
            }
        });
    }
}
