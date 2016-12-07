/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.UserBean;
import Models.Friend;
import Models.User;
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

    static JTextField TFPseudo;
    static JTextField TFMail;
    private JList friends;
    private JList users;

    private JButton returnHome;
    private JButton addFriend;
    private JButton removeFriend;

    static String Password;
    private DefaultListModel listFriend;
    private DefaultListModel listUsers;

    class AddFriendListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = users.getSelectedIndex();
            
            for (User user : UserBean.getInstance().getAllUser()) {
                String pseudoMail = user.getPseudo() + " " + user.getMail();
                String userchosen = listUsers.elementAt(index).toString();                                
                if (pseudoMail.equals(userchosen)) {
                	try {
                        UserBean.getInstance().addFriend(user);
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                    break;
                }
            }
            listUsers.remove(index);
            int size = listUsers.getSize();
            if (size == 0) { //Nobody's left, disable firing.
                addFriend.setEnabled(false);
            } else { //Select an index.
                if (index == listUsers.getSize()) {
                    //removed item in last position
                    index--;
                }
                users.setSelectedIndex(index);
                users.ensureIndexIsVisible(index);
            }
        }
    }

    class RemoveFriendListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = friends.getSelectedIndex();
            for (Friend f : UserBean.getInstance().getUser().getFriends()) {
                String pseudoMail = f.getPseudo() + "//Mail : " + f.getMail();
                if (pseudoMail.equals(listFriend.elementAt(index))) {
                    try {
                        UserBean.getInstance().removeFriend(f.getPseudo(), f.getMail());
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                    break;
                }
            }
            listFriend.remove(index);
            int size = listFriend.getSize();
            if (size == 0) { //Nobody's left, disable firing.
                removeFriend.setEnabled(false);
            } else { //Select an index.
                if (index == listFriend.getSize()) {
                    //removed item in last position
                    index--;
                }
                friends.setSelectedIndex(index);
                friends.ensureIndexIsVisible(index);
            }
        }
    }

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
        addFriend.setBounds(760, 200, 200, 50);
        addFriend.addActionListener(new AddFriendListener());

        removeFriend = new JButton("remove Friend");
        removeFriend.setBounds(290, 200, 200, 50);
        removeFriend.addActionListener(new RemoveFriendListener());

        TFPseudo.setBackground(new Color(100, 100, 100));
        TFPseudo.setForeground(new Color(255, 255, 255));
        TFPseudo.setBounds(30, 10, 200, 30);
        TFMail.setBounds(30, 90, 200, 30);

        listUsers = new DefaultListModel();
        ArrayList<User> usersList = UserBean.getInstance().getAllUser();
        for (User user : usersList) {
            listUsers.addElement(user.getPseudo() + " " + user.getMail());
        }

        users = new JList(listUsers);
        users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        users.setSelectedIndex(0);
        users.addListSelectionListener(this);
        users.setVisibleRowCount(5);

        JScrollPane scrollUser = new JScrollPane(users);
        scrollUser.setBounds(500, 200, 250, 400);

        listFriend = new DefaultListModel();
        ArrayList<Friend> userFriends = UserBean.getInstance().getUser().getFriends();

        for (Friend friend : userFriends) {
            listFriend.addElement(friend.getPseudo() + "//Mail : " + friend.getMail());
        }

        friends = new JList(listFriend);
        friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friends.setSelectedIndex(0);
        friends.addListSelectionListener(this);
        friends.setVisibleRowCount(5);

        JScrollPane scrollFriend = new JScrollPane(friends);
        scrollFriend.setBounds(30, 200, 250, 400);

        p1.add(scrollFriend);
        p1.add(scrollUser);
        p1.add(returnHome);
        p1.add(removeFriend);
        p1.add(addFriend);
        p1.add(TFMail);
        p1.add(TFPseudo, BorderLayout.PAGE_END);
        p1.setBounds(0, 0, 1000, 800);
        add(p1);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (friends.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeFriend.setEnabled(false);
            } else {
                //Selection, enable the fire button.
                removeFriend.setEnabled(true);
            }
            if (users.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                addFriend.setEnabled(false);
            } else {
                //Selection, enable the fire button.
                addFriend.setEnabled(true);
            }
        }
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
