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
public class Enseignant extends Intervenant {

    protected Enseignant() {
    }

    public Enseignant(String typeEtablissement, String numTel, Boolean[] niveaux, String nom, String prenom, String mail, String mdp) {
        super(numTel, niveaux, nom, prenom, mail, mdp);
        this.typeEtablissement = typeEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }
    
    private String typeEtablissement;
}
