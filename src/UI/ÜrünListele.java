package UI;

import DB.DBSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ÜrünListele extends JFrame {
    private JPanel urunListelemeForm;
    private JTextField textField1;
    private JButton araButton;
    private JTable urunTablosu;

    public ÜrünListele() {
        this.setTitle("Ürün Listesi");
        this.setContentPane(urunListelemeForm);
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

        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField1.getText().toUpperCase();
                System.out.println(a);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.MARKET WHERE urun_ad='" + a + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Ürün Bulunamadı!");
                    } else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        urunTablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(a));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}