package UI;

import DB.DBSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MusteriListele extends JFrame{
    private JPanel musteriListeleForm;
    private JTextField textField1;
    private JButton araButton;
    private JTable musteritablosu;
    private JTextField textField2;
    String a;

    public MusteriListele(){
        this.setTitle("Müşteri Listesi");
        this.setContentPane(musteriListeleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Adı");
        tableModel.addColumn("Soyadı");
        tableModel.addColumn("Telefon");
        tableModel.addColumn("Mail");
        tableModel.addColumn("Yaş");
        tableModel.addColumn("Cinsiyet");
        tableModel.addColumn("Adres");
        tableModel.addColumn("Kan Grubu");
        tableModel.addColumn("Vatandaşlık");
        tableModel.addColumn("Rezervasyon Var mı?");
        tableModel.addColumn("Rezervasyon Yapıldığı Tarih");
        tableModel.addColumn("Rezervasyon Alındığı Tarih");


        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM musteri");
            while (rs.next()) {
                boolean rezervasyon_durum = rs.getBoolean("rezervasyon_durum");
                if ((rezervasyon_durum)==true)
                {
                    a = "Evet";
                }
                else
                {
                    a = "Hayır";
                }
                tableModel.addRow(new Object[]{
                        rs.getString("musteri_id"),
                        rs.getString("musteri_ad"),
                        rs.getString("musteri_soyad"),
                        rs.getString("musteri_telefon_no"),
                        rs.getString("musteri_mail"),
                        rs.getString("musteri_yas"),
                        rs.getString("musteri_cinsiyet"),
                        rs.getString("musteri_adres"),
                        rs.getString("musteri_kan_grubu"),
                        rs.getString("musteri_vatandaslik"),
                        a,
                        rs.getString("rezervasyonun_yapildigi_tarih"),
                        rs.getString("rezervasyonun_alindigi_tarih"),
                });
            }


            musteritablosu.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String i = textField1.getText().toUpperCase();
                String s = textField2.getText().toUpperCase();

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.musteri WHERE musteri_ad='" + i + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if ((rs.next())  == false) {
                        JOptionPane.showMessageDialog(null, "Müşteri Bulunamadı!");
                    }
                    else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        musteritablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(i));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.musteri WHERE musteri_soyad='" + s + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if ((rs.next())  == false) {
                        JOptionPane.showMessageDialog(null, "Müşteri Bulunamadı!");
                    }
                    else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        musteritablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField1.setText("");
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField2.setText("");
            }
        });
    }
}
