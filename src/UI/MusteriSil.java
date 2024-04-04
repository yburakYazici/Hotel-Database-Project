package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBSettings;

public class MusteriSil extends JFrame{
    private JPanel musteriSilForm;
    private JTextField textField1;
    private JButton silButton;

    public  MusteriSil(){
        this.setTitle("Müşteri Bilgilerini Giriniz");
        this.setContentPane(musteriSilForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                int input = JOptionPane.showConfirmDialog(null, "Müşteriyi silmek istediğinize emin misiniz ?", "UYARI!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(input==JOptionPane.YES_OPTION) {
                    try {
                        Connection connection = dbAccess.getConnection();
                        Statement st = connection.createStatement();
                        st.executeUpdate("USE HOTEL");
                        String sql = "(SELECT * FROM HOTEL.musteri WHERE musteri_id='" + id + "');";
                        ResultSet up = st.executeQuery(sql);
                        boolean var = up.next();
                        if (!var) {
                            JOptionPane.showMessageDialog(null, "Müşteri zaten yok!");
                        } else {
                            st.executeUpdate("DELETE FROM musteri WHERE musteri_id=" + id);
                            JOptionPane.showMessageDialog(null, "Müşteri Silindi!", "Onaylandı", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }
        });
    }
}
