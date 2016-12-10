/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.Message;
import Models.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class UserDiscussionGroupVirtualProxy extends ArrayList<Message> {

    int id;
    ArrayList<User> users = new ArrayList();

    public UserDiscussionGroupVirtualProxy(int id) {
        this.id = id;
    }

    public ArrayList<User> initialize() throws SQLException
    {
        if (users.isEmpty())
        {
            System.out.println(id);
            users = DiscussionGroupBdd.getDiscussionGroupById(id);
        }
        return users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
