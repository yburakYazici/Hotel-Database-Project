package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RezervasyonEkle extends JFrame{
    private JPanel rezervekleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ekleButton;
    private JTextField textField5;


    public RezervasyonEkle(){
        this.setTitle("Rezervasyon Bilgilerini Giriniz");
        this.setContentPane(rezervekleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                String kapora = textField2.getText();
                String giris = textField3.getText();
                String çıkış = textField4.getText();
                String oda = textField5.getText();
                int id_int = Integer.parseInt(id);
                int oda_int = Integer.parseInt(oda);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("INSERT INTO rezervasyon(musteri_id, kapora, giris_tarih, cikis_tarih, oda_id) VALUES ("+id_int+",'"+kapora+"','"+giris+"','"+çıkış+"',"+oda_int+")");
                    JOptionPane.showMessageDialog(null,"Rezervasyon Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });

        textField3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField3.setText("");
            }
        });
        textField4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField4.setText("");
            }
        });
    }
}
