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
public class Etudiant extends Intervenant {

    protected Etudiant() {
    }

    public Etudiant(String universite, String specialite, String numTel, Integer niveauMin, Integer niveauMax, Integer nbSoutiens, Boolean enSoutien, String nom, String prenom, String mail, String mdp) {
        super(numTel, niveauMin, niveauMax, nom, prenom, mail, mdp);
        this.universite = universite;
        this.specialite = specialite;
    }

    public String getUniversite() {
        return universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    private String universite;
    private String specialite;
}
