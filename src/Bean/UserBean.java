package Bean;

import Models.Friend;
import Models.User;
import Persistence.UserBdd;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sofian
 */
public class UserBean {

    private User user = null;
    private boolean connected = false;

    public UserBean() {

    }

    public static UserBean inst;

    static public UserBean getInstance() {
        if (inst == null) {
            inst = new UserBean();
        }
        return inst;
    }

    public ArrayList<User> getAllUser() {
        try {
            ArrayList<User> userList = UserBdd.getAllUser();
            return userList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean isUserConnected() {
        return connected;
    }

    public void removeFriend(String pseudo, String mail) throws SQLException {
        for (Friend f : this.user.getFriends()) {
            if (f.getPseudo().equals(pseudo) && f.getMail().equals(mail)) {
                UserBdd.removeFriend(user, f);
            }
        }
    }

    public boolean connectUser(String pseudo, String pwd) {
        try {
            User user = UserBdd.getUser(pseudo, pwd);
            if (user != null) {
                ArrayList<Friend> friend = UserBdd.getFriends(user);
                user.setFriends(friend);
                this.connected = true;
                this.user = user;
                System.out.println("You are connected!");
                return true;
            } else {
                System.out.println("Error in the login/pwd!");
                return false;
            }
        } catch (Exception err) {
            System.out.println(err);
        }
        return false;
    }

    public void updateUserFriendInfo(User user) throws SQLException {
        ArrayList<Friend> friend = UserBdd.getFriends(user);
        user.setFriends(friend);
    }

    public void disconnecttUser() {
        this.connected = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUserBdd(ArrayList<String> l) {
        User use = new User(l.get(0), l.get(2), l.get(1));
        try {
            UserBdd.insertUser(use);
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
