
import Models.User;
import Persistence.PersistenceConnection;
import Persistence.UserBdd;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;

import Frame.ChampsSaisie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in     the editor.
 */
/**
 *
 * @author sofian
 */
public class Main {

    public static void main(String[] args) {

        PersistenceConnection co = new PersistenceConnection();

        try {
            co.startConnection("casier", "C&?1+mur");
//            User user = new User(3, "Neor", "123456789", "b@g.com");  
            //          UserBdd.insertUser(user);
            User user = UserBdd.getUser("Neor","12345789");
            if (user != null)
            System.out.println(user.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        JFrame f = new JFrame("Messenger");
        f.getContentPane().add(new ChampsSaisie(), BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }
}
