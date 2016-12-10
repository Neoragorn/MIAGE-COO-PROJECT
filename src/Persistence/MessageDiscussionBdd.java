/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.DiscussionGroup;
import Models.Message;
import Models.MessageDiscussion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class MessageDiscussionBdd {

    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static ArrayList<MessageDiscussion> getMessageFromDiscussion(DiscussionGroup discussion) {
        try {
            ArrayList<MessageDiscussion> msgList = new ArrayList();
            String req = "SELECT msg.idMessage, u.pseudo, msg.message, msg.heure FROM Message msg "
                    + "JOIN User u on u.idUser = msg.idUser "
                    + "JOIN DiscussionGroup disc on disc.idGroup = msg.idGroup "
                    + "WHERE disc.idGroup = ? "
                    + "ORDER by msg.heure DESC";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, discussion.getIdDiscussion());
            ResultSet rs = pss.executeQuery();
            while (rs.next())
            {
                MessageDiscussion msg = new MessageDiscussion(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getDate(4));
                msgList.add(msg);
            }
            return msgList;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
