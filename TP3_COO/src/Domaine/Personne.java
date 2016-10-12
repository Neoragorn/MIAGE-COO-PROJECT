/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

/**
 *
 * @author casier
 */
public class Personne {

    private int idPersonne;
    private String nom;
    private String telephone;
    protected String fonction = null;
    private Bureau bureau = null;

    public Personne(String nom) {
        this.nom = nom;
    }

    public Personne(int id, String nom) {
        this.idPersonne = id;
        this.nom = nom;
    }

    public Personne(int id, String nom, String telephone) {
        this.idPersonne = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Personne(int id, String nom, Bureau b) {
        this.idPersonne = id;
        this.nom = nom;
        this.bureau = b;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Bureau getBureau() {
        return bureau;
    }

    public void setBureau(Bureau bureau) {
        this.bureau = bureau;
        //creer un num de tel aleatoire
        this.telephone = "00000000";
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    public void displayTel() {
        System.out.println("Telephone of " + this.nom + " is " + this.telephone);
    }

    public void displayInfo() {
        if (this.bureau != null) {
            System.out.println("Name : " + this.nom + " Tel :" + this.telephone + " Bureau : " + this.bureau.getDescr());
        } else {
            System.out.println("Name : " + this.nom + " Tel :" + this.telephone + " Bureau : n'a pas de bureau");
        }
    }
}
