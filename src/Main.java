
import Models.User;
import Persistence.PersistenceConnection;
import Persistence.UserBdd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            User user = new User(2, "Neor", "123456789", "b@g.com");  
            UserBdd.insertUser(user);                        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
