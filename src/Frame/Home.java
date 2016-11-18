package Frame;

import Bean.DiscussionGroupBean;
import Bean.UserBean;
import Models.DiscussionGroup;
import Models.Friend;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Home extends JPanel implements ActionListener, ListSelectionListener {

    private JButton quitter;
    private JButton createDiscussion;
    private JButton profile;
    private JButton sendMessage;

    private JList discussionGroup;
    private JList friends;

    private DefaultListModel listDiscussion;
    private DefaultListModel listFriend;

    private JLabel pseudo;

    public Home() {
        setLayout(null);
        setPreferredSize(new Dimension(1500, 800));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        pseudo = new JLabel("Pseudo : " + UserBean.getInstance().getUser().getPseudo());
        pseudo.setOpaque(true);
        pseudo.setBounds(20, 10, 300, 20);

        quitter = new JButton("Quit");
        quitter.setBounds(100, 700, 100, 50);

        sendMessage = new JButton("Send Message");
        sendMessage.setBounds(100, 500, 200, 50);

        profile = new JButton("Profile");
        profile.setBounds(200, 10, 80, 30);

        createDiscussion = new JButton("Create discussion");
        createDiscussion.setBounds(1000, 700, 200, 50);
        createDiscussion.addActionListener(this);

        listDiscussion = new DefaultListModel();

        try {
            ArrayList<DiscussionGroup> discussionGroups = DiscussionGroupBean.getDiscussionGroups();
            for (DiscussionGroup discusionGroup : discussionGroups) {
                listDiscussion.addElement(discusionGroup.getTitle());
                listDiscussion.addElement(discusionGroup.getDescription());
                listDiscussion.addElement("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        listFriend = new DefaultListModel();
        ArrayList<Friend> userFriends = UserBean.getInstance().getUser().getFriends();
        for (Friend friend : userFriends) {
            listFriend.addElement(friend.getPseudo() + "    \t\t\tMail : " + friend.getMail());
        }

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
        scrollFriend.setBounds(50, 50, 500, 400);

        quitter.addActionListener(this);

        add(pseudo);
        add(scrollDiscussion);
        add(scrollFriend);
        add(sendMessage);
        add(quitter, BorderLayout.PAGE_END);
        add(profile, BorderLayout.PAGE_END);
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
        if (e.getActionCommand().equals("Create discussion")) {
            try {
                DiscussionGroupBean.createDiscussion(UserBean.getInstance().getUser(), "premier test de groupe", "premier description");
                MyFrame.getInstance().getFrame().setVisible(false);
                MyFrame createDiscussion = new MyFrame("Create Discussion");
                MyFrame.getInstance().setSecondMyFrame(createDiscussion);
                createDiscussion.startPoint(new CreateDiscussion());
//                MyFrame.getInstance().changeFrame(new Home());
            } catch (Exception err) {
                System.out.println(err);
            }
        }
    }

}
