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

public class CalisanListele extends JFrame{
    private JPanel calisanListeleForm;
    private JTextField textField1;
    private JButton araButton;
    private JTable calisantablosu;
    private JTextField textField2;
    String a;

    public CalisanListele() {
        this.setTitle("Çalışan Listesi");
        this.setContentPane(calisanListeleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Adı");
        tableModel.addColumn("Soyadı");
        tableModel.addColumn("Cinsiyeti");
        tableModel.addColumn("Alanı");
        tableModel.addColumn("Telefon No");
        tableModel.addColumn("Doğum Tarihi");
        tableModel.addColumn("Tecrübesi");
        tableModel.addColumn("Maaşı");
        tableModel.addColumn("Kan Grubu");
        tableModel.addColumn("Yönetici mi?");

        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM CALİSANLAR");
            while (rs.next()) {
                boolean calisan_kontrol_admin = rs.getBoolean("calisan_kontrol_admin");
                if ((calisan_kontrol_admin)==true)
                {
                    a = "Evet";
                }
                else
                {
                    a = "Hayır";
                }
                tableModel.addRow(new Object[]{
                        rs.getString("calisan_id"),
                        rs.getString("calisan_ad"),
                        rs.getString("calisan_soyad"),
                        rs.getString("calisan_cinsiyet"),
                        rs.getString("calisan_alan"),
                        rs.getString("calisan_telefon_no"),
                        rs.getString("calisan_dogum_tarihi"),
                        rs.getString("calisan_tecrube_sene"),
                        rs.getString("calisan_maas"),
                        rs.getString("calisan_kan_grubu"),
                        a
                });
            }


            calisantablosu.setModel(tableModel);


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
                    String stat = ("SELECT * FROM HOTEL.calisanlar WHERE calisan_ad='" + i + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if ((rs.next())  == false) {
                        JOptionPane.showMessageDialog(null, "Çalışan Bulunamadı!");
                    }
                    else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        calisantablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(i));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.calisanlar WHERE calisan_soyad='" + s + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if ((rs.next())  == false) {
                        JOptionPane.showMessageDialog(null, "Çalışan Bulunamadı!");
                    }
                    else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        calisantablosu.setRowSorter(tableRowSorter);
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
