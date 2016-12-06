package Frame;

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

import Bean.UserBean;
import Models.Friend;
import Models.User;

public class Manage extends JPanel implements ActionListener, ListSelectionListener {
	
    private JList users;
    
	private JButton removeUser;
	private JButton addUser;
	private JButton modifyUser;
	
    private DefaultListModel listUsers;
	
	public Manage(){
		setLayout(null);
        setPreferredSize(new Dimension(1000, 800));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        
        p1.setBounds(0, 0, 1000, 800);
        add(p1);
        
        listUsers = new DefaultListModel();
        ArrayList<User> usersList = UserBean.getInstance().getAllUser();
        for (User user : usersList) {
            listUsers.addElement(user.getPseudo() + "    \t\t\tMail : " + user.getMail());
        }

        users = new JList(listUsers);
        users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        users.setSelectedIndex(0);
        users.addListSelectionListener(this);
        users.setVisibleRowCount(5);

        JScrollPane scrollUser = new JScrollPane(users);
        scrollUser.setBounds(500, 200, 250, 400);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	 public void valueChanged(ListSelectionEvent e) {
	        if (e.getValueIsAdjusting() == false) {

	            if (users.getSelectedIndex() == -1) {
	                //No selection, disable fire button.
	                removeUser.setEnabled(false);

	            } else {
	                //Selection, enable the fire button.
	                removeUser.setEnabled(true);
	            }
	        }
	    }

}
