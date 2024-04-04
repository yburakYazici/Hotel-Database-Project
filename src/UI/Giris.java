package UI;


import DB.DBSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Giris extends JFrame{
    private JTextField userl;
    private JPanel girispanel;
    private JButton girisButton;
    private JPasswordField pwl;
    DBSettings dbAccess = new DBSettings();

    public Giris(){
        this.setTitle("Giris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(girispanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();


        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u_n = userl.getText();
                String u_p = pwl.getText();
                if(u_n.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"ID boş bırakılamaz!");
                }
                else if(u_p.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Şifre boş bırakılamaz!");
                }
                else
                {
                    try
                    {
                        Connection connection = dbAccess.getConnection();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("USE HOTEL");
                        String stat=("SELECT * FROM HOTEL.calisanlar WHERE calisan_id='"+u_n+"' AND calisan_parola='"+u_p+"'");
                        ResultSet rs = statement.executeQuery(stat);
                        if(rs.next()==false)
                        {
                            JOptionPane.showMessageDialog(null,"ID veya şifre yanlış!");
                        }
                        else
                        {

                            dispose();
                            String check_admin = rs.getString("calisan_kontrol_admin");
                            String calisan_id = rs.getString("calisan_id");
                                if(check_admin.equals("1"))
                                {
                                   new Yonetici();
                                    JOptionPane.showMessageDialog(null, "ID: "+rs.getString("calisan_id")+"\n"+rs.getString("calisan_ad")+" "+rs.getString("calisan_soyad")+" "+"Olarak Yonetici Girişi Yaptınız!", "Hoşgeldiniz!", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else
                                {
                                    new Calisan(calisan_id);
                                    JOptionPane.showMessageDialog(null, "ID: "+rs.getString("calisan_id")+"\n"+rs.getString("calisan_ad")+" "+rs.getString("calisan_soyad")+" "+"Olarak Giriş Yaptınız!", "Hoşgeldiniz!", JOptionPane.INFORMATION_MESSAGE);
                                }
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }
}
