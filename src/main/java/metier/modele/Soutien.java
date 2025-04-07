/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.security.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ypeyrot
 */
@Entity
public class Soutien {

    protected Soutien() {
    }

    public Soutien(Matiere matiere, Eleve eleve, String details) {
        //this.dateDemande = new Timestamp(System.currentTimeMillis());
        this.matiere = matiere;
        this.eleve = eleve;
        this.details = details;
    }

    public Timestamp getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Timestamp dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
    

    public String getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(String compteRendu) {
        this.compteRendu = compteRendu;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
<<<<<<< HEAD
    @Temporal(TemporalType.DATE)
    private Timestamp dateDemande;
    @Temporal(TemporalType.DATE)
    private Timestamp dateFin;
=======
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateDemande;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateDebut;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateFin;
    private String etat;
>>>>>>> 14656a14a8a14e0320d77abdd736ec50874c5046
    private String compteRendu;
    private String details;
    private double note;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Intervenant intervenant;
    @ManyToOne
    private Matiere matiere;
}
