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


public class MüşteriGüncelle extends JFrame{
    private JPanel musteriListeleForm;
    private JTable musteritablosu;
    private JPanel musteriekleForm;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton evetRadioButton;
    private JRadioButton hayırRadioButton;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton güncelleButton;
    private JPanel musteriguncelForm;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField0;
    String a;

    public MüşteriGüncelle(){
        this.setTitle("Müşteri Bilgilerini Giriniz");
        this.setContentPane(musteriguncelForm);
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
        musteritablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = musteritablosu.getSelectedRow();
                textField0.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField1.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,2).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,3).toString());
                textField4.setText(tableModel.getValueAt(selectedRow,4).toString());
                textField5.setText(tableModel.getValueAt(selectedRow,5).toString());
                textField6.setText(tableModel.getValueAt(selectedRow,6).toString());
                textField7.setText(tableModel.getValueAt(selectedRow,7).toString());
                textField8.setText(tableModel.getValueAt(selectedRow,8).toString());
                textField9.setText(tableModel.getValueAt(selectedRow,9).toString());
                String b = tableModel.getValueAt(selectedRow,10).toString();
                if(b=="Evet")
                {
                    evetRadioButton.setSelected(true);
                }
                else if(b=="Hayır")
                {
                    hayırRadioButton.setSelected(true);
                }
                textField10.setText(tableModel.getValueAt(selectedRow,11).toString());
                textField11.setText(tableModel.getValueAt(selectedRow,12).toString());
            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField0.getText();
                String ad = textField1.getText().toUpperCase();
                String soyad = textField2.getText().toUpperCase();
                String telefon = textField3.getText();
                String mail = textField4.getText().toUpperCase();
                String yas = textField5.getText();
                String cinsiyet = textField6.getText().toUpperCase();
                String adres = textField7.getText();
                String kangrubu = textField8.getText().toUpperCase();
                String vatandaslik = textField9.getText().toUpperCase();
                String rezerve_tarih = textField10.getText();
                String rezervea_tarih = textField11.getText();

                if((rezerve_tarih).equals(""))
                    rezerve_tarih = ("0000-00-00");

                if((rezervea_tarih).equals(""))
                    rezervea_tarih = ("0000-00-00");

                Boolean rezerv = false;
                if (evetRadioButton.isSelected()){
                    rezerv=true;
                }

                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE musteri SET  musteri_ad='"+ad+"', musteri_soyad='"+soyad+"',musteri_telefon_no='"+telefon+"', musteri_mail='"+mail+"', musteri_yas='"+yas+"',musteri_cinsiyet='"+cinsiyet+"',musteri_adres='"+adres+"',musteri_kan_grubu='"+kangrubu+"',musteri_vatandaslik='"+vatandaslik+"',rezervasyonun_yapildigi_tarih='"+rezerve_tarih+"',rezervasyonun_alindigi_tarih='"+rezervea_tarih+"' WHERE musteri_id="+id+"");
                    JOptionPane.showMessageDialog(null,"Müşteri Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(evetRadioButton.isSelected())
                {
                    textField10.setEditable(true);
                    textField11.setEditable(true);
                }
                else if(hayırRadioButton.isSelected())
                {
                    textField10.setEditable(false);
                    textField11.setEditable(false);
                }
            }
        };
        evetRadioButton.addActionListener(listener);
        hayırRadioButton.addActionListener(listener);
    }
}
