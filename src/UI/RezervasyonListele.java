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

public class RezervasyonListele extends JFrame{
    private JPanel listele;
    private JTextField textField1;
    private JButton araButton;
    private JTable rezervasyontablosu;


    public RezervasyonListele(){
        this.setTitle("Rezervasyon Listesi");
        this.setContentPane(listele);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Rezervasyon ID");
        tableModel.addColumn("Kapora");
        tableModel.addColumn("Giriş Tarih");
        tableModel.addColumn("Çıkış Tarih");
        tableModel.addColumn("Müşteri ID");
        tableModel.addColumn("Ad");
        tableModel.addColumn("Soyad");
        tableModel.addColumn("Oda ID");


        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM rezervasyon INNER JOIN musteri ON rezervasyon.musteri_id = musteri.musteri_id");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("rezervasyon_id"),
                        rs.getString("kapora"),
                        rs.getString("giris_tarih"),
                        rs.getString("cikis_tarih"),
                        rs.getString("musteri_id"),
                        rs.getString("musteri_ad"),
                        rs.getString("musteri_soyad"),
                        rs.getString("oda_id")

                });
            }
            rezervasyontablosu.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField1.getText();

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.rezervasyon WHERE rezervasyon_id='" + a + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Rezervasyon Bulunamadı!");
                    } else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        rezervasyontablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(a));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
