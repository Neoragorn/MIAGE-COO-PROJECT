
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
            Class.forName(co.getJDBC_DRIVER());
            co.startConnection("casier", "C&?1+mur");
            User user = new User(1, "Neor", "123456789", "b@g.com");  
            UserBdd.insertUser(user);
                        
            /* test de connection à la bdd réussi
            String req = "delete from Bureau;";
            pss = conn.prepareStatement(req);
            pss.executeUpdate();*/
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
