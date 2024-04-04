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

public class OdaListele extends JFrame{
    private JPanel odaListeleForm;
    private JTextField textField1;
    private JButton araButton;
    private JTable odatablosu;

    public OdaListele(){
        this.setTitle("Oda Listesi");
        this.setContentPane(odaListeleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        String a;

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Oda ID");
        tableModel.addColumn("Tür ID");
        tableModel.addColumn("Durum ID");
        tableModel.addColumn("Satışa Hazır Mı?");
        tableModel.addColumn("Durum");
        tableModel.addColumn("Fiyat");
        tableModel.addColumn("Yatak Sayısı");
        tableModel.addColumn("Ekstra Yatak Koyulabilir");
        tableModel.addColumn("Tür İsmi");
        tableModel.addColumn("Kişi Sayısı");


        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM oda INNER JOIN oda_turleri INNER JOIN oda_durum ON oda.oda_turu_id = oda_turleri.oda_turu_id AND oda.oda_durum_id= oda_durum.oda_durum_id");
            while (rs.next()) {
                boolean satis_durum = rs.getBoolean("satis_durum");
                if ((satis_durum)==true)
                {
                    a = "Evet";
                }
                else
                {
                    a = "Hayır";
                }
                tableModel.addRow(new Object[]{
                        rs.getString("oda_id"),
                        rs.getString("oda_turu_id"),
                        rs.getString("oda_durum_id"),
                        a,
                        rs.getString("oda_durum"),
                        rs.getString("oda_fiyat"),
                        rs.getString("yatak_sayisi"),
                        rs.getString("ekstra_yatak"),
                        rs.getString("tur_ismi"),
                        rs.getString("kisi_sayisi")

                });
            }


            odatablosu.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField1.getText().toUpperCase();

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE HOTEL");
                    String stat = ("SELECT * FROM HOTEL.ODA WHERE oda_id='" + a + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Oda Bulunamadı!");
                    } else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        odatablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(a));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
