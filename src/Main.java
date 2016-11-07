
import Persistence.PersistenceConnection;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Frame.ChoixConnection;
import Frame.MyFrame;
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

    public static void main(String[] args) {

        PersistenceConnection co = new PersistenceConnection();        
        try {
            co.startConnection("casier", "C&?1+mur");
/*            User user = UserBdd.getUser("Neor", "123456789");
            if (user != null) {
                System.out.println(user.toString());
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }

        MyFrame myF = new MyFrame();
        MyFrame.setInst(myF);                
        JPanel jp = new ChoixConnection();

        //Ecran pour soit se connecter, soit s'inscrire
        myF.changeFrame(jp);
        
        //Ecran pour s'inscrire
//        f.getContentPane().add(new ChampsSaisie(), BorderLayout.SOUTH);
        
        //Ecran pour se connecter
    }
}
