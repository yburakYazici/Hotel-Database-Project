package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBSettings;

public class ÜrünSil extends JFrame{
    private JPanel urunSilForm;
    private JButton silButton;
    private JTextField textField1;

    public ÜrünSil(){
        this.setTitle("Ürün Bilgilerini Giriniz");
        this.setContentPane(urunSilForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                int input = JOptionPane.showConfirmDialog(null, "Ürünü silmek istediğinize emin misiniz ?", "UYARI!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(input==JOptionPane.YES_OPTION) {
                    try {
                        Connection connection = dbAccess.getConnection();
                        Statement st = connection.createStatement();
                        st.executeUpdate("USE HOTEL");
                        String sql = "(SELECT * FROM HOTEL.market WHERE urun_id='"+id+"');";
                        ResultSet up = st.executeQuery(sql);
                        boolean var = up.next();
                        if(!var)
                        {
                            JOptionPane.showMessageDialog(null,"Ürün zaten yok!");
                        }
                        else
                        {
                            st.executeUpdate("DELETE FROM MARKET WHERE URUN_ID=" + id);
                            JOptionPane.showMessageDialog(null, "Ürün Silindi!", "Onaylandı", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }
        });
    }
}
