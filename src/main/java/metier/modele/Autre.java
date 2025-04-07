/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author ypeyrot
 */
@Entity
public class Autre extends Intervenant {

    protected Autre() {
    }

    public Autre(String activite, String numTel, Boolean[] niveaux, String nom, String prenom, String mail, String mdp) {
        super(numTel, niveaux, nom, prenom, mail, mdp);
        this.activite = activite;
    }

    public String getActivite() {
        return activite;
    }
    
    private String activite;
}
