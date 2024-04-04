package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizasyonSil extends JFrame{
    private JPanel organizasyonsilForm;
    private JTextField textField1;
    private JButton silButton;

    public OrganizasyonSil(){
        this.setTitle("Organizasyon Bilgilerini Giriniz");
        this.setContentPane(organizasyonsilForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                int input = JOptionPane.showConfirmDialog(null, "Organizasyonu silmek istediğinize emin misiniz ?", "UYARI!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(input==JOptionPane.YES_OPTION) {
                    try {
                        Connection connection = dbAccess.getConnection();
                        Statement st = connection.createStatement();
                        st.executeUpdate("USE HOTEL");
                        String sql = "(SELECT * FROM HOTEL.organizasyon WHERE organizasyon_id='"+id+"');";
                        ResultSet up = st.executeQuery(sql);
                        boolean var = up.next();
                        if(!var)
                        {
                            JOptionPane.showMessageDialog(null,"Organizasyon zaten yok!");
                        }
                        else
                        {
                            st.executeUpdate("DELETE FROM organizasyon WHERE organizasyon_id=" + id);
                            JOptionPane.showMessageDialog(null, "Organizasyon Silindi!", "Onaylandı", JOptionPane.INFORMATION_MESSAGE);
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
