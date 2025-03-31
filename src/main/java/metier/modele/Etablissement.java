/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ypeyrot
 */
@Entity
public class Etablissement {

    public Etablissement() {
    }

    public Etablissement(String uai, String nom, Float ips, String adresse) {
        this.uai = uai;
        this.nom = nom;
        this.ips = ips;
        this.adresse = adresse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uai;
    private String nom;
    private Float ips;
    private String adresse;
    @ManyToOne
    private Coordonnees coords;

    public String getUai() {
        return uai;
    }

    public String getNom() {
        return nom;
    }

    public Float getIps() {
        return ips;
    }

    public Coordonnees getCoords() {
        return coords;
    }
}
