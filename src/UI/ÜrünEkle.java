package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBSettings;

public class ÜrünEkle extends JFrame {
    private JPanel urunEkleForm;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton ekleButton;

    public ÜrünEkle() {
        this.setTitle("Ürün Bilgilerini Giriniz");
        this.setContentPane(urunEkleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String urunfiyat = textField2.getText().toUpperCase();
                String urunad = textField3.getText().toUpperCase();
                String urunmiktar = textField4.getText().toUpperCase();
                String urunstokdurum = textField5.getText().toUpperCase();
                int urunfiyat_int = Integer.parseInt(urunfiyat);
                int urunmiktar_int = Integer.parseInt(urunmiktar);
                int urunstokdurum_int = Integer.parseInt(urunstokdurum);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("INSERT INTO MARKET(urun_fiyat, urun_ad, urun_stok, urun_stok_durum) VALUES ('"+urunfiyat_int+"','"+urunad+"','"+urunmiktar_int+"',"+urunstokdurum_int+")");
                    JOptionPane.showMessageDialog(null,"Ürün Eklendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }

}
