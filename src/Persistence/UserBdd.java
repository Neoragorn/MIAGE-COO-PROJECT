/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Models.User;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sofian
 */
public class UserBdd {

    private static Connection conn = PersistenceConnection.getInstance().getConn();

    public static void insertUser(User user) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String req = "INSERT INTO User VALUES (?, ?, ?, ?)";
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(user.getPwd().getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        user.setPwd(sb.toString());
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
