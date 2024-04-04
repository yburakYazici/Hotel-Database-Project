package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RezervasyonSil extends JFrame{
    private JTextField textField1;
    private JButton silButton;
    private JPanel rezervasyonsilfrm;

    public RezervasyonSil(){
        this.setTitle("Rezervasyon Bilgilerini Giriniz");
        this.setContentPane(rezervasyonsilfrm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                int input = JOptionPane.showConfirmDialog(null, "Rezervasyonu silmek istediğinize emin misiniz ?", "UYARI!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(input==JOptionPane.YES_OPTION) {
                    try {
                        Connection connection = dbAccess.getConnection();
                        Statement st = connection.createStatement();
                        st.executeUpdate("USE HOTEL");
                        String sql = "(SELECT * FROM HOTEL.rezervasyon WHERE rezervasyon_id='" + id + "');";
                        ResultSet up = st.executeQuery(sql);
                        boolean var = up.next();
                        if (!var) {
                            JOptionPane.showMessageDialog(null, "Rezervasyon zaten yok!");
                        } else {
                            st.executeUpdate("DELETE FROM rezervasyon WHERE rezervasyon_id=" + id);
                            JOptionPane.showMessageDialog(null, "Rezervasyon Silindi!", "Onaylandı", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }
        });
    }
}
