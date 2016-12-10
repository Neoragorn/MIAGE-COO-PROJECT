/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.DiscussionGroup;
import Models.User;
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

            String req = "SELECT idGroup, title, description FROM DiscussionGroup";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.executeQuery();
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                DiscussionGroup discussion = new DiscussionGroup();
                discussion.setIdDiscussion(rs.getInt(1));
                discussion.setTitle(rs.getString(2));
                discussion.setDescription(rs.getString(3));
                discussionGroups.add(discussion);
            }
            return discussionGroups;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<User> getDiscussionGroupById(int id) throws SQLException {
        try {
            ArrayList<User> users = new ArrayList();

            String req = "SELECT u.idUser, u.pseudo, u.mail FROM User u "
                    + "join AssoGroupUser asso on asso.idUser = u.idUser "
                    + "join DiscussionGroup disc on disc.idGroup = asso.idGroup "
                    + "WHERE disc.idGroup = ?";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, id);
            pss.executeQuery();
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
