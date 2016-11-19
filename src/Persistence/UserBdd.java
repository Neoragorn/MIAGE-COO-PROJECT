/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Bean.UserBean;
import Models.Friend;
import Models.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class UserBdd {

    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static void insertUser(User user) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String req = "INSERT INTO User VALUES (?, ?, ?, ?)";
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(user.getPwd().getBytes());
        StringBuilder sb = new StringBuilder();
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
        String req = "DELETE FROM User WHERE idUser = ? ";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setInt(1, user.getIdUser());
        pss.executeUpdate();
    }

    public static ArrayList<User> getAllUser() throws SQLException {
        ArrayList<User> userList = new ArrayList();

        String req = "Select idUser, pseudo, mail "
                + "FROM User "
                + "WHERE idUser not in "
                + "(SELECT u.idUser "
                + "FROM Friend f "
                + "JOIN User u ON f.idFriend = u.idUser "
                + "WHERE f.idUser = ?) AND idUser != ?";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setInt(1, UserBean.getInstance().getUser().getIdUser());
        pss.setInt(2, UserBean.getInstance().getUser().getIdUser());
        System.out.println(UserBean.getInstance().getUser().getIdUser());
        ResultSet rs = pss.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            userList.add(user);
        }
        return userList;
    }

    public static User getUser(String pseudo, String pwd) throws SQLException, NoSuchAlgorithmException {
        try {
            User user = new User();
            String req = "SELECT * FROM User WHERE pseudo = ? AND password = ? ";
            PreparedStatement pss = conn.prepareStatement(req);

            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            pss.setString(1, pseudo);
            pss.setString(2, sb.toString());
            ResultSet rs = pss.executeQuery();
            rs.next();
            user.setIdUser(rs.getInt(1));
            user.setPseudo(rs.getString(2));
            user.setMail(rs.getString(4));
            return user;
        } catch (SQLException | NoSuchAlgorithmException e) {
            if (e instanceof SQLException) {
                System.out.println("Erreur dans le login et/ou le mot de passe");
                return null;
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<Friend> getFriends(User user) throws SQLException {
        ArrayList<Friend> friends = new ArrayList();
        String req = "SELECT u.idUser, u.pseudo, u.mail "
                + "FROM Friend f "
                + "JOIN User u ON f.idFriend = u.idUser "
                + "WHERE f.idUser = ?;";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setInt(1, user.getIdUser());
        ResultSet rs = pss.executeQuery();
        while (rs.next()) {
            Friend friend = new Friend();
            friend.setIdFriend(rs.getInt(1));
            friend.setPseudo(rs.getString(2));
            friend.setMail(rs.getString(3));
            friends.add(friend);
        }
        return friends;
    }

    public static void removeFriend(User user, Friend friend) throws SQLException {
        String req = "DELETE FROM Friend WHERE idUser = ? AND idFriend = ? ";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setInt(1, user.getIdUser());
        pss.setInt(2, friend.getIdFriend());
        pss.executeUpdate();
    }

    /*    public static void updateUser(User user) {
        
    }*/
}
