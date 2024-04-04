package UI;

import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarketStokGüncelle extends JFrame{
    private JTextField textField1;
    private JButton stoğuGüncelleButton;
    private JPanel stokgüncelForm;

    public MarketStokGüncelle(){
        this.setTitle("Market Stok Güncelleme Paneli");
        this.setContentPane(stokgüncelForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        stoğuGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField1.getText();

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
                        st.executeUpdate("UPDATE MARKET SET urun_stok_durum = urun_stok");
                        JOptionPane.showMessageDialog(null,"Ürün Stoğu Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
