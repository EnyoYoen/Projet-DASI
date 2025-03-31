/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ypeyrot
 */
@Entity
public class Coordonnees {

    @Id
    private Long id;
    private Float lat;
    private Float lng;

    protected Coordonnees() {
    }

    public Coordonnees(Float lat, Float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

}
