/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import metier.modele.Etablissement;
import com.google.maps.model.LatLng;
import metier.modele.Coordonnees;
import static util.GeoNetApi.getLatLng;

/**
 *
 * @author tlafondela
 */
public class Outils {

    public Etablissement obtenirEtablissement(String codeEtablissement) {
        EducNetApi educNetApi = new EducNetApi();
        GeoNetApi geoNetApi = new GeoNetApi();
        List<String> infos = null;

        try {
            infos = educNetApi.getInformationEtablissement(codeEtablissement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Etablissement etablissement = null;

        if (infos != null) {
            String nom = infos.get(1);
            String adresse = infos.get(4);
            LatLng latlng = getLatLng(nom + ", " + adresse);
            double lat = latlng.lat;
            double lng = latlng.lng;
            Coordonnees coords = new Coordonnees(lat, lng);
            etablissement = new Etablissement(codeEtablissement, infos.get(1), Float.parseFloat(infos.get(8)), infos.get(4), coords);
            
        }

        return etablissement;
    }
}
