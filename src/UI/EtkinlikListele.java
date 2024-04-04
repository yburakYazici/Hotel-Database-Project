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

public class EtkinlikListele extends JFrame{
    private JTextField textField1;
    private JButton araButton;
    private JTable etkinliktablosu;
    private JPanel etkinlikAraForm;

    public EtkinlikListele() {
        this.setTitle("Etkinlik Listesi");
        this.setContentPane(etkinlikAraForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Etkinlik ID");
        tableModel.addColumn("Etkinlik Adı");
        tableModel.addColumn("Etkinlik Tarihi");
        tableModel.addColumn("Etkinlik Yaş Sınırı");
        tableModel.addColumn("Etkinlik Kişi Sınırı");

        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM ETKİNLİK");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("etkinlik_id"),
                        rs.getString("etkinlik_isim"),
                        rs.getString("etkinlik_zaman"),
                        rs.getString("yas_limit"),
                        rs.getString("sinir_limit")
                });
            }
            etkinliktablosu.setModel(tableModel);

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
                    String stat = ("SELECT * FROM HOTEL.etkinlik WHERE etkinlik_isim='" + a + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Etkinlik Bulunamadı!");
                    } else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        etkinliktablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(a));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
