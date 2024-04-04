package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarketSatis extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton satButton;
    private JPanel satisForum;

    public MarketSatis(){
        this.setTitle("Satış Panel");
        this.setContentPane(satisForum);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        satButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();
                String miktar = textField2.getText();
                int miktar_int = Integer.parseInt(miktar);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    String sql = "(SELECT * FROM HOTEL.market WHERE urun_id='"+id+"');";
                    ResultSet up = st.executeQuery(sql);
                    boolean var = up.next();
                    if(!var)
                    {
                        JOptionPane.showMessageDialog(null,"Ürün Bulunamadı!");
                    }
                    else
                    {
                        st.executeUpdate("UPDATE MARKET SET urun_stok_durum = urun_stok_durum - '"+miktar_int+"' WHERE urun_id = '"+id+"' and urun_stok_durum > 0");
                        JOptionPane.showMessageDialog(null,"Ürün Satıldı!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
