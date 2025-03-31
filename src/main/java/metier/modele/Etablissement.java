/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ypeyrot
 */
@Entity
public class Etablissement {
    @Id 
    private Long id;
    private String uai;
    private String nom;
    private Float ips;
    @ManyToOne
    private Coordonnees coords;
}
