/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;

/**
 *
 * @author tlafondela
 */
public class Eleve extends Personne {

    public Eleve(Date dateNaissance, String codeEtablissement, String classe, String nom, String prenom, String mail, String mdp) {
        super(nom, prenom, mail, mdp);
        this.dateNaissance = dateNaissance;
        this.codeEtablissement = codeEtablissement;
        this.classe = classe;
    }

    
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getCodeEtablissement() {
        return codeEtablissement;
    }

    public String getClasse() {
        return classe;
    }
    
    private Date dateNaissance;
    private String codeEtablissement;
    private String classe;
}
