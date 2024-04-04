package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DB.DBSettings;

public class OdaEkle extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton evetRadioButton;
    private JRadioButton hayırRadioButton;
    private JButton ekleButton;
    private JPanel OdaEkleForm;
    private JTextField textField3;

    public OdaEkle(){
        this.setTitle("Oda Bilgilerini Giriniz");
        this.setContentPane(OdaEkleForm);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            DBSettings dbAccess = new DBSettings();
            String oda_turu = textField1.getText();
            String oda_durum = textField2.getText();
            String oda_id = textField3.getText();
            int odaturu_int = Integer.parseInt(oda_turu);
            int odadurum_int = Integer.parseInt(oda_durum);
            int odaid_int = Integer.parseInt(oda_id);

            ButtonGroup grup = new ButtonGroup();
                grup.add(evetRadioButton);
                grup.add(hayırRadioButton);

            Boolean hazir = true;
                if (hayırRadioButton.isSelected()){
                hazir=false;
            }
                int input = JOptionPane.showConfirmDialog(null, "Oda eklemek istediğinize emin misiniz? (Oda sadece inşaat aşamalarından sonra eklenir)", "UYARI!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if(input==JOptionPane.YES_OPTION) {
                        try {
                            Connection connection = dbAccess.getConnection();
                            Statement st = connection.createStatement();
                            String sql = "(SELECT * FROM HOTEL.ODA WHERE oda_id='"+odaid_int+"');";
                            ResultSet up = st.executeQuery(sql);
                            boolean var = up.next();
                            if(var)
                            {
                                JOptionPane.showMessageDialog(null,"Oda zaten var!");
                            }
                            else {
                                st.executeUpdate("USE HOTEL");
                                st.executeUpdate("INSERT INTO ODA(oda_id,oda_turu_id, oda_durum_id, satis_durum) VALUES ('" + odaid_int + "','" + odaturu_int + "','" + odadurum_int + "'," + hazir + ")");
                                JOptionPane.showMessageDialog(null, "Oda Eklendi!", "Onaylandı", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, e1);
                        }
                    }
        }
    });
    }
}
