/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

import Persistance.DataMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casier
 */
class BureauMapper {

    static BureauMapper inst;
    private Connection conn = DataMapper.getConn();

    public BureauMapper() {
        ID = 1;
        IDLINK = 1;
    }

    static public BureauMapper getInstance() {
        if (inst == null) {
            inst = new BureauMapper();
        }
        return inst;
    }
    int ID;
    int IDLINK;

    /**
     * Méthode pour insérer le bureau dans la BDD + créer le lien entre la
     * personne et le bureau via la table d'association
     *
     * @param b bureau à lier
     * @param p personne à lier
     */
    public void linkBureauPersonne(Bureau b, Personne p) {
        b.addPersonne(p);
        try {
            this.createLinkage(b, p);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void createLinkage(Bureau b, Personne p) throws SQLException {
        String req = "Insert into bureauLinkage values (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, BureauMapper.getInstance().IDLINK);
        ps.setInt(2, b.getIdBureau());
        ps.setInt(3, p.getIdPersonne());
        ps.executeUpdate();
    }

    public void deLinkBureauPersonne(Personne p) {
        Bureau bur = p.getBureau();
        bur.removePersonne(p);
        p.setBureau(null);
        p.setTelephone(null);
        try {
            this.deleteLinkage(bur, p);

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void insert(Bureau b) throws SQLException {
        String req = "INSERT INTO Bureau VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, BureauMapper.getInstance().ID);
        ps.setString(2, b.getDescr());
        ps.executeUpdate();
    }

    public void delete(Bureau b) throws SQLException {
        String req = "DELETE FROM Bureau WHERE idBureau = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, b.getIdBureau());
        ps.executeUpdate();
    }

    public void update(Bureau b) throws SQLException {
        String req = "UPDATE bureau SET description=? WHERE idBureau=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, b.getDescr());
        ps.setInt(2, ID);
        ps.executeUpdate();
    }

    //Supprime le lien entre personne et bureau dans la table de linkage
    public void deleteLinkage(Bureau b, Personne p) throws SQLException {
        String req = "delete from bureauLinkage WHERE idBureau=? AND idPersonne=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, b.getIdBureau());
        ps.setInt(2, p.getIdPersonne());
        ps.executeUpdate();
    }

    public Bureau findById(int id) throws SQLException {
        String req = "SELECT description FROM Bureau WHERE idBureau=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String req2 = "Select p.idPersonne, nom from Personne p"
                + " join bureauLinkage bl on bl.idBureau = " + id
                + " join Bureau br on br.idBureau = " + id;
        PreparedStatement ps2 = conn.prepareStatement(req2);
        ResultSet rs2 = ps2.executeQuery();
        List<Personne> listP = new ArrayList();
        while (rs2.next()) {
            Personne p = new Personne(rs2.getInt(1), rs2.getString(2));
            listP.add(p);
        }
        //remplir la liste de personnes du bureau correctement
        Bureau b = new Bureau(id, rs.getString(1));
        b.setPersonnes(listP);
        return b;
    }

}
