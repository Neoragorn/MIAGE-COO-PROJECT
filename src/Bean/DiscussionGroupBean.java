/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Models.DiscussionGroup;
import Models.User;
import Persistence.DiscussionGroupBdd;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class DiscussionGroupBean {

    private DiscussionGroup discussion;
    
      public static DiscussionGroupBean inst;

    static public DiscussionGroupBean getInstance() {
        if (inst == null) {
            inst = new DiscussionGroupBean();
        }
        return inst;
    }
    
    public static void createDiscussion(User user, String title, String description) throws SQLException {
        DiscussionGroupBdd.createDiscussionGroupBdd(user.getIdUser(), title, description);
    }

    public ArrayList<DiscussionGroup> getDiscussionGroups() throws SQLException {
        try {
            return DiscussionGroupBdd.getDiscussionGroupBdd();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public DiscussionGroup getDiscussion() {
        return discussion;
    }

    public void setDiscussion(DiscussionGroup discussion) {
        this.discussion = discussion;
    }
    
    
}
