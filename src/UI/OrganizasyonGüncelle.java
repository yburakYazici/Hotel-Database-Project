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

public class OrganizasyonGüncelle extends JFrame{
    private JPanel organizasyonListeleForm;
    private JTable organizasyontablosu;
    private JPanel organizasyonekleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton güncelleButton;
    private JTextField textField0;
    private JPanel organizasyonguncelle;

    public OrganizasyonGüncelle(){
        this.setTitle("Organizasyon Bilgilerini Giriniz");
        this.setContentPane(organizasyonguncelle);
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
        organizasyontablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = organizasyontablosu.getSelectedRow();
                textField0.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField1.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,3).toString());
            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField0.getText();
                String ad = textField1.getText().toUpperCase();
                String tur_id = textField2.getText();
                String saati = textField3.getText();

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE organizasyon SET  organizasyon_isim='"+ad+"', organizasyon_tur_id  ='"+tur_id+"',organizasyon_saati='"+saati+"' WHERE organizasyon_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Organizasyon Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
