
import Persistence.PersistenceConnection;
import Persistence.UserBdd;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Frame.ChoixConnection;
import Frame.MyFrame;
import Models.Admin;
import Models.User;

import javax.swing.JPanel;

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

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {

        PersistenceConnection co = new PersistenceConnection();        
        try {
            co.startConnection("casier", "C&?1+mur");
        } catch (Exception e) {
            System.out.println(e);
        }
        //creation de l'admin si besoin
       /* User admin = new Admin("admin", "admin", "admin@admin.com"); 
		UserBdd.insertUser(admin);
		System.out.println("admin created"); */
        MyFrame myF = new MyFrame();
        MyFrame.setInst(myF);        
        JPanel jp = new ChoixConnection();
        MyFrame.getInstance().setActualPanel(jp);
        //Ecran pour soit se connecter, soit s'inscrire
        myF.startPoint(jp);
       
    }
}
