package Frame;

import Bean.DiscussionGroupBean;
import Bean.UserBean;
import Models.DiscussionGroup;
import Models.Friend;
import Models.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
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
    private JButton answer;
    private JButton manage;

    private DefaultListModel listDiscussion;
    private DefaultListModel listFriend;
    private DefaultListModel boiteReception;

    private JList discussionGroup;
    private JList friends;
    private JList privateMessage;

    private JLabel pseudo;
    private JLabel reception;
    private JLabel yourFriends;

    public Home() {
        setLayout(null);
        setPreferredSize(new Dimension(1600, 800));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);

        pseudo = new JLabel("Pseudo : " + UserBean.getInstance().getUser().getPseudo());
        pseudo.setOpaque(true);
        pseudo.setBounds(20, 10, 150, 20);

        yourFriends = new JLabel("Your Friends");
        yourFriends.setOpaque(true);
        yourFriends.setBounds(70, 70, 100, 20);

        reception = new JLabel("Your private messages");
        reception.setOpaque(true);
        reception.setBounds(70, 380, 200, 20);

        quitter = new JButton("Quit");
        quitter.setBounds(100, 700, 100, 50);

        sendMessage = new JButton("Send Message");
        sendMessage.setBounds(80, 310, 200, 50);
        sendMessage.addActionListener(this);

        answer = new JButton("Answer");
        answer.setBounds(80, 620, 200, 50);
        answer.addActionListener(this);

        profile = new JButton("Profile");
        profile.setBounds(200, 10, 80, 30);
        profile.addActionListener(this);

        createDiscussion = new JButton("Create discussion");
        createDiscussion.setBounds(1000, 700, 200, 50);
        createDiscussion.addActionListener(this);

        listDiscussion = new DefaultListModel();
        boiteReception = new DefaultListModel();

        manage = new JButton("Manage");
        manage.setBounds(300, 10, 80, 30);
        manage.addActionListener(this);

        try {
            ArrayList<Message> privateMsg = UserBean.getInstance().getUser().getProxyMessage().initialize();
            for (Message msg : privateMsg) {
                boiteReception.addElement("[" + msg.getDate() + "] " + msg.getDestinataire().getPseudo() + " : " + msg.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

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

        privateMessage = new JList(boiteReception);
        privateMessage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        privateMessage.setSelectedIndex(0);
        privateMessage.addListSelectionListener(this);
        privateMessage.setVisibleRowCount(5);

        JScrollPane scrollDiscussion = new JScrollPane(discussionGroup);
        scrollDiscussion.setBounds(650, 10, 1000, 600);

        JScrollPane scrollFriend = new JScrollPane(friends);
        scrollFriend.setBounds(50, 100, 500, 200);

        JScrollPane scrollPrivateMessage = new JScrollPane(privateMessage);
        scrollPrivateMessage.setBounds(50, 410, 500, 200);

        quitter.addActionListener(this);

        add(yourFriends);
        add(reception);
        add(scrollPrivateMessage);
        add(pseudo);
        add(answer);
        add(scrollDiscussion);
        add(scrollFriend);
        add(sendMessage);
        add(quitter);
        add(profile);

        if (UserBean.getInstance().getUser().getPseudo().equals("admin")) {
            add(manage);
        }
        add(createDiscussion, BorderLayout.PAGE_END);
        p1.setBounds(0, 0, 1500, 800);
        add(p1);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (friends.getSelectedIndex() == -1) {
                sendMessage.setEnabled(false);

            } else {
                sendMessage.setEnabled(true);
            }
            if (privateMessage.getSelectedIndex() == -1) {
               answer.setEnabled(false);

            } else {
                answer.setEnabled(true);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Quit")) {
            MyFrame.getInstance().quit();
        }
        if (e.getActionCommand().equals("Manage")) {
            MyFrame.getInstance().changeFrame(new Manage());
        }
        if (e.getActionCommand().equals("Profile")) {
            MyFrame.getInstance().changeFrame(new Profile());
        }
        if (e.getActionCommand().equals("Send Message")) {
            MyFrame.getInstance().changeFrame(new SendMessage(friends.getSelectedIndex()));
        }
        if (e.getActionCommand().equals("Answer")) {
            MyFrame.getInstance().changeFrame(new AnswerMessage(privateMessage.getSelectedIndex()));
        }
        if (e.getActionCommand().equals("Create discussion")) {
            try {
//                DiscussionGroupBean.createDiscussion(UserBean.getInstance().getUser(), "premier test de groupe", "premier description");
                MyFrame.getInstance().getFrame().setVisible(false);
                MyFrame createDiscussion = new MyFrame("Create Discussion");
                MyFrame.getInstance().setSecondMyFrame(createDiscussion);
                createDiscussion.startPoint(new CreateDiscussion());
            } catch (Exception err) {
                System.out.println(err);
            }
        }
    }

}
