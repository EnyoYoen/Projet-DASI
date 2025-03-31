/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

/**
 *
 * @author tlafondela
 */
public class Intervenant extends Personne {
    String numTel;
    Boolean[] niveaux;

    public Intervenant(String numTel, Boolean[] niveaux, String nom, String prenom, String mail, String mdp) {
        super(nom, prenom, mail, mdp);
        this.numTel = numTel;
        this.niveaux = niveaux;
    }

    public String getNumTel() {
        return numTel;
    }

    public Boolean[] getNiveaux() {
        return niveaux;
    }
}
