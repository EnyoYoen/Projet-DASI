/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author tlafondela
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Intervenant extends Personne {

    private String numTel;
    private Boolean[] niveaux;
    private Integer nbSoutiens;
    private Boolean enSoutien;

    protected Intervenant() {
    }

    public Intervenant(String numTel, Boolean[] niveaux, String nom, String prenom, String mail, String mdp) {
        super(nom, prenom, mail, mdp);
        this.numTel = numTel;
        this.niveaux = niveaux;
        this.nbSoutiens = 0;
        this.enSoutien = false;
    }

    public String getNumTel() {
        return numTel;
    }

    public Boolean[] getNiveaux() {
        return niveaux;
    }

    public Integer getNbSoutiens() {
        return nbSoutiens;
    }

    public Boolean getEnSoutien() {
        return enSoutien;
    }

    public void addNbSoutiens() {
        ++this.nbSoutiens;
    }

    public void setEnSoutien(Boolean enSoutien) {
        this.enSoutien = enSoutien;
    }

}
