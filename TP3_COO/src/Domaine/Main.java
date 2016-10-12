/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

import Persistance.DataMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author casier
 */
public class Main {

    public static void main(String[] args) {

        DataMapper dataMap = new DataMapper();
        //Bloc pour tester la connection
        try {
            Class.forName(dataMap.getJDBC_DRIVER());
            Connection conn = DriverManager.getConnection(dataMap.getDB_URL(), "", "");
            dataMap.setConn(conn);

            //Nettoyage de la BDD
            String req = "DELETE from Bureau;";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.executeUpdate();
            req = "DELETE from bureauLinkage;";
            ps = conn.prepareStatement(req);
            ps.executeUpdate();
            req = "Delete from Personne";
            ps = conn.prepareStatement(req);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        ResultSet rs;

        //Creation des classes pour tester le programme
        try {
            PersonneMapper pm = new PersonneMapper();
            BureauMapper bm = new BureauMapper();

            Bureau bur = new Bureau(BureauMapper.getInstance().ID, "Administration");
            bm.insert(bur);
            BureauMapper.getInstance().ID++;
            Bureau bur2 = new Bureau(BureauMapper.getInstance().ID, "Science");
            bm.insert(bur2);
            BureauMapper.getInstance().ID++;

            Personne p = new Personne(PersonneMapper.getInstance().IDPersonne, "bob", bur);
            pm.linkPersonneBureau(bur, p);
            Personne p3 = new Personne(PersonneMapper.getInstance().IDPersonne, "John", bur);
            pm.linkPersonneBureau(bur, p3);
            //Je lie le bureau et la personne dans les classes et dans la BDD        

            bm.linkBureauPersonne(bur, p);
            BureauMapper.getInstance().IDLINK++;
            bm.linkBureauPersonne(bur, p3);
            BureauMapper.getInstance().IDLINK++;

            p.displayInfo();
            //lister les personnes d'un bureau   
            Bureau test1 = bm.findById(1);
            test1.displayPersonne();

            //Numero de telephone actuel 
            p.displayTel();

            //Changer le numero de telephone de la personne
            pm.updateTelephone(p, "0658991421");

            //Retrouver la personne via le nouveau numéro de telephone
            Personne p2 = pm.findByTel("0658991421");
            p2.displayTel();

            //Séparer la personne du bureau
            bm.deLinkBureauPersonne(p);
            p.displayInfo();
            //Création d'un chercheur
            Chercheur chercheur = new Chercheur(PersonneMapper.getInstance().IDPersonne, "jack", bur, "chercheur");
            pm.linkPersonneBureau(bur, chercheur);
            bm.linkBureauPersonne(bur, chercheur);
            chercheur.displayInfo();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
