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

    private  Connection conn = PersistenceConnection.getConn();

    public static void insertUser(User user) throws SQLException {
        try {
            String req = "INSERT INTO USER VALUES (?, ?, ?, ?)";
            System.out.println("INSERT ! ");
            System.out.println("INSERT !1 ");
            PreparedStatement pss = conn.prepareStatement(req);
            System.out.println("INSERT ! 2");
            pss.setInt(1, user.getIdUser());
            System.out.println("INSERT ! 3");
            pss.setString(2, user.getPseudo());
            pss.setString(3, user.getPwd());
            pss.setString(4, user.getMail());
            pss.executeUpdate();
            System.out.println("INSERT ! DONE");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteUser() {

    }

    public static void updateUser() {

    }
}
