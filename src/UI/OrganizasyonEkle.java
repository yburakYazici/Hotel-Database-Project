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

public class OrganizasyonEkle extends JFrame{
    private JPanel organizasyonekleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton ekleButton;

    public OrganizasyonEkle(){
        this.setTitle("Organizasyon Bilgilerini Giriniz");
        this.setContentPane(organizasyonekleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String ad = textField1.getText().toUpperCase();
                String turid = textField2.getText();
                String zaman = textField3.getText();

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");

                    st.executeUpdate("INSERT INTO ORGANİZASYON(organizasyon_isim, organizasyon_tur_id, organizasyon_saati) VALUES ('"+ad+"','"+turid+"','"+zaman+"')");
                    JOptionPane.showMessageDialog(null,"Organizasyon Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
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
                textField2.setText("");
            }
        });
            }
}
