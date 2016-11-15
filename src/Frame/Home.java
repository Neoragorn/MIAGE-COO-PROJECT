package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Home extends JPanel implements ActionListener, ListSelectionListener {

    private JButton quitter;
    private JButton createDiscussion;

    private JList discussionGroup;
    private JList friends;

    private DefaultListModel listDiscussion;
    private DefaultListModel listFriend;

    public Home() {
        setLayout(null);
        setPreferredSize(new Dimension(1500, 800));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        quitter = new JButton("Quit");
        quitter.setBounds(100, 700, 100, 50);

        createDiscussion = new JButton("Create discussion");
        createDiscussion.setBounds(1000, 700, 200, 50);

        listDiscussion = new DefaultListModel();
        listDiscussion.addElement("Discussion 1");
        listDiscussion.addElement("Discussion 2");
        listDiscussion.addElement("Discussion 3");

        listFriend = new DefaultListModel();
        listFriend.addElement("Jane Doe");
        listFriend.addElement("John Smith");
        listFriend.addElement("Kathy Green");

        discussionGroup = new JList(listDiscussion);
        discussionGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        discussionGroup.setSelectedIndex(0);
        discussionGroup.addListSelectionListener(this);
        discussionGroup.setVisibleRowCount(5);

        friends = new JList(listFriend);
        friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        friends.setSelectedIndex(0);
        friends.addListSelectionListener(this);
        friends.setVisibleRowCount(5);

        JScrollPane scrollDiscussion = new JScrollPane(discussionGroup);
        scrollDiscussion.setBounds(650, 10, 1000, 600);

        JScrollPane scrollFriend = new JScrollPane(friends);
        scrollFriend.setBounds(50, 10, 500, 400);

        quitter.addActionListener(this);

        add(scrollDiscussion);
        add(scrollFriend);
        add(quitter, BorderLayout.PAGE_END);
        add(createDiscussion, BorderLayout.PAGE_END);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (discussionGroup.getSelectedIndex() == -1) {
                //No selection, disable fire button.
//                fireButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                //              fireButton.setEnabled(true);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quit")) {
            MyFrame.getInstance().quit();
        }
    }

}
