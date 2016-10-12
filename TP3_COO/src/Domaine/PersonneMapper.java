/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

import static Domaine.BureauMapper.inst;
import Persistance.DataMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javax.swing.text.html.HTML.Attribute.ID;

/**
 *
 * @author casier
 */
public class PersonneMapper {

    static PersonneMapper inst;
    private Connection conn = DataMapper.getConn();

    public PersonneMapper() {
        IDPersonne = 1;
    }

    static public PersonneMapper getInstance() {
        if (inst == null) {
            inst = new PersonneMapper();
        }
        return inst;
    }
    int IDPersonne;

    /*
    * Méthode pour lier une personne à un bureau + mise en BDD de la Personne uniquement.
    * Le lien est fait dans la classe BureauMapper
     */
    public void linkPersonneBureau(Bureau b, Personne p) {
        p.setBureau(b);
        try {
            this.insert(p);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void insert(Personne b) throws SQLException {
        String req = "INSERT INTO Personne VALUES(?,?,?, ?)";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, PersonneMapper.getInstance().IDPersonne);
        ps.setString(2, b.getNom());
        ps.setString(3, b.getTelephone());
        if (b.getFonction() != null) {
            ps.setString(4, b.getFonction());
        } else {
            ps.setString(4, "no function");
        }
        ps.executeUpdate();
        PersonneMapper.getInstance().IDPersonne++;
    }

    public void delete(Personne b) throws SQLException {
        String req = "DELETE FROM Personne WHERE idPersonne = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, b.getIdPersonne());
        ps.executeUpdate();
    }

    public void update(Personne b) throws SQLException {
        String req = "UPDATE Personne SET telephone=? WHERE idPersonne=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, b.getTelephone());
        ps.setInt(2, b.getIdPersonne());
        ps.executeUpdate();
    }

    public void updateTelephone(Personne b, String nouveautel) throws SQLException {
        String req = "UPDATE Personne SET telephone=? WHERE idPersonne=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, nouveautel);
        ps.setInt(2, b.getIdPersonne());
        ps.executeUpdate();
    }

    public Personne findById(int id) throws SQLException {
        String req = "SELECT nom FROM Personne WHERE idPersonne=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, BureauMapper.getInstance().ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Personne b = new Personne(id, rs.getString(1));
        return b;
    }

    public Personne findByTel(String tel) throws SQLException {
        String req = "SELECT p.idPersonne, nom, telephone FROM Personne p WHERE telephone=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setString(1, tel);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Personne b = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3));
        return b;
    }
}
