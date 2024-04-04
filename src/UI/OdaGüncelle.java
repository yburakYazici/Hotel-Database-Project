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

public class OdaGüncelle extends JFrame{
    private JPanel odagüncelleForm;
    private JTable odalistesi;
    private JPanel OdaEkleForm;
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton evetRadioButton;
    private JRadioButton hayırRadioButton;
    private JButton güncelleButton;
    private JTextField textField1;


    public OdaGüncelle(){
        this.setTitle("Oda Bilgilerini Giriniz");
        this.setContentPane(odagüncelleForm);
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
            Statement stt = connection.createStatement();
            stt.executeUpdate("USE HOTEL");
            ResultSet rs = stt.executeQuery("SELECT * FROM oda INNER JOIN oda_turleri INNER JOIN oda_durum ON oda.oda_turu_id = oda_turleri.oda_turu_id AND oda.oda_durum_id= oda_durum.oda_durum_id");
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


            odalistesi.setModel(tableModel);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        odalistesi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = odalistesi.getSelectedRow();
                textField1.setText(tableModel.getValueAt(selectedRow,0).toString());
                textField2.setText(tableModel.getValueAt(selectedRow,1).toString());
                textField3.setText(tableModel.getValueAt(selectedRow,2).toString());
                String b = tableModel.getValueAt(selectedRow,3).toString();
                if(b=="Evet")
                {
                    evetRadioButton.setSelected(true);
                }
                else if(b=="Hayır")
                {
                    hayırRadioButton.setSelected(true);
                }

            }
        });
        güncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                String tur = textField2.getText();
                String durum = textField3.getText();
                int tur_int = Integer.parseInt(tur);
                int durum_int = Integer.parseInt(durum);


                Boolean satis=true;
                if (hayırRadioButton.isSelected()){
                    satis=false;
                }
                try {
                    Connection connection = dbAccess.getConnection();
                    Statement st = connection.createStatement();
                    st.executeUpdate("USE HOTEL");
                    st.executeUpdate("UPDATE ODA SET oda_turu_id="+tur_int+",oda_durum_id="+durum_int+", satis_durum="+satis+" WHERE oda_id='"+id+"'");
                    JOptionPane.showMessageDialog(null,"Oda Güncellendi!","Onaylandı",JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
