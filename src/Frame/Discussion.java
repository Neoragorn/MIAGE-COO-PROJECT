/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.DiscussionGroupBean;
import Models.User;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sofian
 */
public class Discussion extends JPanel implements ActionListener, ListSelectionListener {

    private DefaultListModel listMembers;
    private JList members;

    public Discussion() {
        setLayout(null);
        setPreferredSize(new Dimension(1600, 800));
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        listMembers = new DefaultListModel();
        try {
            ArrayList<User> users = DiscussionGroupBean.getInstance().getDiscussion().getMembers().initialize();
            for (User user : users) {
                listMembers.addElement(user.getPseudo());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        members = new JList(listMembers);
        members.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        members.setSelectedIndex(0);
        members.addListSelectionListener(this);
        members.setVisibleRowCount(5);

        JScrollPane scrollMembers = new JScrollPane(members);
        scrollMembers.setBounds(1000, 50, 500, 600);

        p1.setBounds(0, 0, 1600, 800);
        p1.add(scrollMembers);
        add(p1);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

        }
    }

    public void actionPerformed(ActionEvent e) {

    }
}
