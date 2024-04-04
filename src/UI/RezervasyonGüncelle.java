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

public class RezervasyonGüncelle extends JFrame{
    private JPanel güncelleme;
    private JTable rezervasyontablosu;
    private JPanel rezervekleForm;
    private JTextField textField4;
    private JTextField textField1;
    private JTextField textField3;
    private JButton güncelleButton;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField0;

    public RezervasyonGüncelle(){
        this.setTitle("Rezervasyon Bilgilerini Giriniz");
        this.setContentPane(güncelleme);
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
        rezervasyontablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = rezervasyontablosu.getSelectedRow();
                textField0.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField1.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,3).toString());
                textField4.setText(tableModel.getValueAt(selectedRow,4).toString());
                textField5.setText(tableModel.getValueAt(selectedRow,7).toString());
            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField0.getText();
                String id1 = textField4.getText();
                String kapora = textField1.getText();
                String giris = textField2.getText();
                String çıkış = textField3.getText();
                String oda = textField5.getText();
                int kapora_int = Integer.parseInt(kapora);

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE rezervasyon SET  musteri_id='"+id1+"', kapora='"+kapora_int+"', giris_tarih='"+giris+"', cikis_tarih='"+çıkış+"',oda_id='"+oda+"' WHERE rezervasyon_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Rezervasyon Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
