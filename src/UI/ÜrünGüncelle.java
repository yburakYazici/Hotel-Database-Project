package UI;

import DB.DBSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ÜrünGüncelle extends JFrame {
    private JPanel listeleme;
    private JPanel guncellemeUrunForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTable urunTablosu;
    private JButton güncelleButton;

    public ÜrünGüncelle(){
        this.setTitle("Ürün Bilgilerini Giriniz");
        this.setContentPane(guncellemeUrunForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Ürün ID");
        tableModel.addColumn("Ürün Adı");
        tableModel.addColumn("Ürün Fiyatı");
        tableModel.addColumn("Ürün Stok Miktarı");
        tableModel.addColumn("Ürün Stok Durumu");

        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM MARKET");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("urun_id"),
                        rs.getString("urun_ad"),
                        rs.getString("urun_fiyat"),
                        rs.getString("urun_stok"),
                        rs.getString("urun_stok_durum")
                });
            }
            urunTablosu.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        urunTablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = urunTablosu.getSelectedRow();
                textField1.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField4.setText(tableModel.getValueAt(selectedRow,3).toString());
                textField5.setText(tableModel.getValueAt(selectedRow,4).toString());

            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                String urunfiyat = textField3.getText().toUpperCase();
                String urunad = textField2.getText().toUpperCase();
                String urunmiktar = textField4.getText().toUpperCase();
                String urunstokdurum = textField5.getText().toUpperCase();
                int urunfiyat_int = Integer.parseInt(urunfiyat);
                int urunmiktar_int = Integer.parseInt(urunmiktar);
                int urunstokdurum_int = Integer.parseInt(urunstokdurum);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE MARKET SET urun_fiyat='"+urunfiyat_int+"', urun_ad='"+urunad+"', urun_stok='"+urunmiktar_int+"', urun_stok_durum='"+urunstokdurum_int+"' WHERE urun_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Ürün Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
