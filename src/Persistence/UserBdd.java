/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sofian
 */
public class UserBdd {

    private static Connection conn = PersistenceConnection.getInstance().getConn();

    public static void insertUser(User user) throws SQLException {
            String req = "INSERT INTO USER VALUES (?, ?, ?, ?)";
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, user.getIdUser());
            pss.setString(2, user.getPseudo());
            pss.setString(3, user.getPwd());
            pss.setString(4, user.getMail());
            pss.executeUpdate();        
    }

    public static void deleteUser(User user) throws SQLException {
        String req = "DELETE FROM USER WHERE idUser = ? ";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setInt(1, user.getIdUser());
        pss.executeUpdate();
    }

/*    public static void updateUser(User user) {
        
    }*/
}
