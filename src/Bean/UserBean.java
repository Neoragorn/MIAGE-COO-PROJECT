package Bean;

import Models.User;
import Persistence.UserBdd;
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

    public boolean isUserConnected() {
        return connected;
    }

    public void connectUser() {
        this.connected = true;
    }

    public void disconnecttUser() {
        this.connected = false;
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
