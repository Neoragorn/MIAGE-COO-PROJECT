/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Models.User;
import Persistence.DiscussionGroupBdd;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class DiscussionGroupBean {

    public static void createDiscussion(User user, String title) throws SQLException {
        DiscussionGroupBdd.createDiscussionGroupBdd(user.getIdUser(), title);
    }

    public static ArrayList<String> getDiscussionGroups() throws SQLException {
        try {
            return DiscussionGroupBdd.getDiscussionGroupBdd();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
