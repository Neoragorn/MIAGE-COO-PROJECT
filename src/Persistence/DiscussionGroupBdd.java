/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.DiscussionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class DiscussionGroupBdd {

    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static void createDiscussionGroupBdd(int idCreator, String title, String description) throws SQLException {
        try {
            String req = "INSERT INTO DiscussionGroup VALUES (?, ?, ?, ?)";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, 0);
            pss.setInt(2, idCreator);
            pss.setString(3, title);
            pss.setString(4, description);
            pss.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<DiscussionGroup> getDiscussionGroupBdd() throws SQLException {
        try {
            ArrayList<DiscussionGroup> discussionGroups = new ArrayList();

            String req = "SELECT title, description FROM DiscussionGroup";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.executeQuery();
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                DiscussionGroup discussion = new DiscussionGroup();
                discussion.setTitle(rs.getString(1));
                discussion.setDescription(rs.getString(2));
                discussionGroups.add(discussion);
            }
            return discussionGroups;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
