/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.UserBean;
import Models.Message;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author casier
 */
public class SendMessage extends JPanel implements ActionListener {
    
    private JButton returnHome;

    public SendMessage(int index) {
        setLayout(null);
        setPreferredSize(new Dimension(500, 400));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        System.out.println(index);
        ArrayList<Message> msg = UserBean.getInstance().getUser().getPrivateMessage();
        
        System.out.println(msg.get(index).getMessage());
// System.out.println(msg.getAuteur() + " hahaha " + msg.getMessage());
        returnHome = new JButton("Return");
        returnHome.setBounds(200, 300, 100, 50);
        returnHome.addActionListener(this);
        
        p1.add(returnHome);
        p1.setBounds(0, 0, 1000, 800);
        add(p1);
    }

    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Return")) {
            try {
                UserBean.getInstance().updateUserFriendInfo(UserBean.getInstance().getUser());
                MyFrame.getInstance().changeFrame(new Home());
            } catch (Exception err) {
                System.out.println(err);
            }
        }
    }
}
