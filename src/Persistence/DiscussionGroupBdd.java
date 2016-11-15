/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

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

    public static void createDiscussionGroupBdd(int idCreator, String title) throws SQLException {
        try {
            String req = "INSERT INTO DiscussionGroup VALUES (?, ?, ?)";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, 0);
            pss.setInt(2, idCreator);
            pss.setString(3, title);
            pss.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<String> getDiscussionGroupBdd() throws SQLException {
        try {
            ArrayList<String> discussionGroups = new ArrayList();

            String req = "SELECT title FROM DiscussionGroup";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.executeQuery();
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                discussionGroups.add(rs.getString(1));
            }
            return discussionGroups;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
