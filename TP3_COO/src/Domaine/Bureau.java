/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casier
 */
public class Bureau {

    private int idBureau;
    private String descr;
    private List<Personne> personnes = new ArrayList();

    public Bureau(int id, String description) {
        this.idBureau = id;
        this.descr = description;
    }

    public Bureau(String description) {
        this.descr = description;
    }

    public int getIdBureau() {
        return idBureau;
    }

    public void setIdBureau(int idBureau) {
        this.idBureau = idBureau;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public void addPersonne(Personne p) {
        this.personnes.add(p);
    }

    public void removePersonne(Personne p) {
        this.personnes.remove(p);
    }

    public void displayPersonne() {
        for (Personne p : this.personnes) {
            System.out.println(p.getNom() + " is in Bureau : " + this.descr);
        }
    }
}
