/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.UserBean;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sofian
 */
public class Connection extends JPanel implements ActionListener {

    static JTextField TFPseudo;
    static JTextField TFPassword;
    JButton boutonConnection;

    static String Pseudo;
    static String Password;

    public Connection() {
        setLayout(null);
        setPreferredSize(new Dimension(500, 300));
        boutonConnection = new JButton("Se Connecter");
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        TFPseudo = new JTextField("Pseudo");
        TFPassword = new JTextField("Password");
        TFPseudo.setBackground(new Color(255, 255, 255));
        TFPseudo.setForeground(new Color(0, 0, 0));
        TFPseudo.setBounds(150, 50, 200, 30);
        TFPassword.setBounds(150, 100, 200, 30);

        p1.add(boutonConnection);
        boutonConnection.setBounds(150, 180, 150, 20);
        boutonConnection.addActionListener(this);

        p1.add(boutonConnection);
        p1.add(TFPassword);
        p1.add(TFPseudo);
        p1.setBounds(0, 0, 500, 300);
        add(p1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Se Connecter")) {
            Pseudo = TFPseudo.getText();
            Password = TFPassword.getText();
            UserBean.getInstance().connectUser(Pseudo, Password);
//            MyFrame.getInstance().changeFrame(new ChoixConnection());
        }
    }

}
