/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domaine;

/**
 *
 * @author sofian
 */
public class Administratif extends Personne {

    public Administratif(int id, String nom, Bureau b, String fonction) {
        super(id, nom, b);
        this.fonction = fonction;
    }

}
