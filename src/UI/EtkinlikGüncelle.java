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

public class EtkinlikGüncelle extends JFrame{
    private JTable etkinliktablosu;
    private JPanel etkinlikEkleForm;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton güncelleButton;
    private JTextField textField2;
    private JTextField textField0;
    private JPanel etkinlikguncelle;

    public EtkinlikGüncelle(){
        this.setTitle("Etkinlik Bilgilerini Giriniz");
        this.setContentPane(etkinlikguncelle);
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
        etkinliktablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = etkinliktablosu.getSelectedRow();
                textField0.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField1.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,3).toString());
                textField4.setText(tableModel.getValueAt(selectedRow,4).toString());
            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBSettings dbAccess = new DBSettings();
                String id = textField0.getText();
                String etkinlikad = textField1.getText().toUpperCase();
                String etkinliktarih = textField2.getText();
                String yas_siniri = textField3.getText();
                String kisi_siniri = textField4.getText();
                int yas_siniri_int = Integer.parseInt(yas_siniri);
                int kisi_siniri_int = Integer.parseInt(kisi_siniri);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE  ETKİNLİK SET etkinlik_isim='"+etkinlikad+"', etkinlik_zaman='"+etkinliktarih+"', yas_limit="+yas_siniri_int+", sinir_limit="+kisi_siniri_int+" WHERE etkinlik_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Etkinlik Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
