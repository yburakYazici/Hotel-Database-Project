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

public class OrganizasyonListele extends JFrame{
    private JPanel organizasyonListeleForm;
    private JTextField textField1;
    private JButton araButton;
    private JTable organizasyontablosu;

    public OrganizasyonListele(){
        this.setTitle("Organizasyon Listesi");
        this.setContentPane(organizasyonListeleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Adı");
        tableModel.addColumn("Tür ID");
        tableModel.addColumn("Saati");
        tableModel.addColumn("Türü");

        try {
            Connection connection = dbAccess.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("USE HOTEL");
            ResultSet rs = st.executeQuery("SELECT * FROM ORGANİZASYON INNER JOIN ORGANİZASYON_TURLERİ  ON ORGANİZASYON.organizasyon_tur_id = ORGANİZASYON_TURLERİ.organizasyon_tur_id");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("organizasyon_id"),
                        rs.getString("organizasyon_isim"),
                        rs.getString("organizasyon_tur_id"),
                        rs.getString("organizasyon_saati"),
                        rs.getString("organizasyon_tur_isim")
                });
            }
            organizasyontablosu.setModel(tableModel);

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
                    String stat = ("SELECT * FROM HOTEL.organizasyon WHERE organizasyon_isim='" + a + "'");
                    ResultSet rs = statement.executeQuery(stat);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(null, "Organizasyon Bulunamadı!");
                    } else {
                        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter(tableModel);
                        organizasyontablosu.setRowSorter(tableRowSorter);
                        tableRowSorter.setRowFilter(RowFilter.regexFilter(a));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
