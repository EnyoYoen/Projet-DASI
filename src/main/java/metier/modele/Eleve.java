/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tlafondela
 */
@Entity
public class Eleve extends Personne {

    protected Eleve() {
    }

    public Eleve(Date dateNaissance, Integer classe, String nom, String prenom, String mail, String mdp) {
        super(nom, prenom, mail, mdp);
        this.dateNaissance = dateNaissance;
        this.classe = classe;
        this.etablissement = null;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Integer getClasse() {
        return classe;
    }
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Integer classe;
    @ManyToOne
    private Etablissement etablissement;
}
