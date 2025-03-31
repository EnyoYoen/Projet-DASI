/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
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

    public Eleve(Date dateNaissance, int classe, String nom, String prenom, String mail, String mdp) {
        super(nom, prenom, mail, mdp);
        this.dateNaissance = dateNaissance;
        this.classe = classe;
    }

    
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public int getClasse() {
        return classe;
    }
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private int classe;
}
