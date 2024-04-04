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


public class CalisanGuncelle extends JFrame{
    private JPanel calisanEkleForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton güncelleButton;
    private JRadioButton evetRadioButton1;
    private JRadioButton hayırRadioButton1;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JPanel guncellepanel;
    private JTextField textField0;
    private JTable calisantablosu;
    String a;

    public CalisanGuncelle(){
        this.setTitle("Çalışan Bilgilerini Giriniz");
        this.setContentPane(guncellepanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();

        DBSettings dbAccess = new DBSettings();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Adı");
        tableModel.addColumn("Soyadı");
        tableModel.addColumn("Parola");
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
                        rs.getString("calisan_parola"),
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

        calisantablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = calisantablosu.getSelectedRow();
                textField0.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField1.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,3).toString());
                textField4.setText(tableModel.getValueAt(selectedRow,5).toString());
                textField5.setText(tableModel.getValueAt(selectedRow,6).toString());
                textField6.setText(tableModel.getValueAt(selectedRow,4).toString());
                textField7.setText(tableModel.getValueAt(selectedRow,7).toString());
                textField8.setText(tableModel.getValueAt(selectedRow,10).toString());
                textField9.setText(tableModel.getValueAt(selectedRow,9).toString());
                textField10.setText(tableModel.getValueAt(selectedRow,8).toString());
                String b = tableModel.getValueAt(selectedRow,11).toString();
                if(b=="Evet")
                {
                    evetRadioButton1.setSelected(true);
                }
                else if(b=="Hayır")
                {
                    hayırRadioButton1.setSelected(true);
                }

            }
        });

        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField0.getText();
                String ad = textField1.getText().toUpperCase();
                String soyad = textField2.getText().toUpperCase();
                String parola = textField3.getText();
                String alan = textField4.getText().toUpperCase();
                String telefon = textField5.getText();
                String cinsiyet = textField6.getText().toUpperCase();
                String doğum_tarihi = textField7.getText();
                String kangrubu = textField8.getText();
                String maas = textField9.getText();
                String tecrube = textField10.getText();
                int maas_int = Integer.parseInt(maas);
                int tecrube_int = Integer.parseInt(tecrube);

                Boolean admin=false;
                if (evetRadioButton1.isSelected()){
                    admin=true;
                }
                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE Calisanlar SET  calisan_ad='"+ad+"', calisan_soyad='"+soyad+"',calisan_parola='"+parola+"', calisan_cinsiyet='"+cinsiyet+"', calisan_alan='"+alan+"',calisan_telefon_no='"+telefon+"', calisan_dogum_tarihi='"+doğum_tarihi+"', calisan_tecrube_sene='"+tecrube_int+"', calisan_maas='"+maas_int+"',calisan_kan_grubu='"+kangrubu+"', calisan_kontrol_admin="+admin+" WHERE calisan_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Çalışan Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
