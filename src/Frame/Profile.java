/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.UserBean;
import static Frame.ChampsSaisie.TFPseudo;
import Models.Friend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sofian
 */
public class Profile extends JPanel implements ActionListener, ListSelectionListener {

    private String pseudo;
    private String mail;
//    private String pwd;

    static JTextField TFPseudo;
    static JTextField TFMail;
    private JList friends;

    private JButton returnHome;
    private JButton addFriend;
    private JButton removeFriend;

    static String Password;
    private DefaultListModel listFriend;

    public Profile() {
        setLayout(null);
        setPreferredSize(new Dimension(1000, 800));

        TFPseudo = new JTextField("Pseudo");
        TFMail = new JTextField("Mail");
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        returnHome = new JButton("Return");
        returnHome.setBounds(30, 650, 100, 50);
        returnHome.addActionListener(this);

        addFriend = new JButton("Add Friend");
        addFriend.setBounds(550, 250, 200, 50);
        addFriend.addActionListener(this);

        removeFriend = new JButton("remove Friend");
        removeFriend.setBounds(300, 200, 200, 50);
        removeFriend.addActionListener(this);

        TFPseudo.setBackground(new Color(100, 100, 100));
        TFPseudo.setForeground(new Color(255, 255, 255));
        TFPseudo.setBounds(30, 10, 200, 30);
        TFMail.setBounds(30, 90, 200, 30);

        listFriend = new DefaultListModel();
        ArrayList<Friend> userFriends = UserBean.getInstance().getUser().getFriends();
        for (Friend friend : userFriends) {
            listFriend.addElement(friend.getPseudo() + "    \t\t\tMail : " + friend.getMail());
        }

        friends = new JList(listFriend);
        friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friends.setSelectedIndex(0);
        friends.addListSelectionListener(this);
        friends.setVisibleRowCount(5);

        JScrollPane scrollFriend = new JScrollPane(friends);
        scrollFriend.setBounds(30, 200, 250, 400);

        p1.add(scrollFriend);
        p1.add(returnHome);
        p1.add(removeFriend);
        p1.add(addFriend);
        p1.add(TFMail);
        p1.add(TFPseudo, BorderLayout.PAGE_END);
        p1.setBounds(0, 0, 1000, 800);
        add(p1);
    }

    public void valueChanged(ListSelectionEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Return")) {
            MyFrame.getInstance().changeFrame(new Home());
        }
    }
}
