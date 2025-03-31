/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import metier.modele.Etablissement;

/**
 *
 * @author tlafondela
 */
public class Outils {

    public Etablissement obtenirEtablissement(String codeEtablissement) {
        EducNetApi educNetApi = new EducNetApi();
        List<String> infos = null;

        try {
            infos = educNetApi.getInformationEtablissement(codeEtablissement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Etablissement etablissement = null;

        if (infos != null) {
            etablissement = new Etablissement(codeEtablissement, infos.get(1), Float.parseFloat(infos.get(8)), infos.get(4));
        }

        return etablissement;
    }
}
