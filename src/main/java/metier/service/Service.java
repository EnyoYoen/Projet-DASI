/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.EleveDao;
import dao.EtablissementDao;
import dao.IntervenantDao;
import util.Message;
import dao.JpaUtil;
import dao.SoutienDao;
import java.util.List;
import metier.modele.Coordonnees;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien;
import util.EducNetApi;
import util.GeoNetApi;
import static util.GeoNetApi.getLatLng;

/**
 *
 * @author lmarnas
 */
public class Service {

    public Boolean inscrireEleve(Eleve eleve, String codeEtablissement) {
        Message unMessage = new Message();
        EleveDao eleveDao = new EleveDao();
        EtablissementDao etablissementDao = new EtablissementDao();

        Boolean result;

        Boolean etablissementInDb;

        try {
            JpaUtil.creerContextePersistance();

            List<Etablissement> etablissements = etablissementDao.findByCode(codeEtablissement);

            etablissementInDb = !etablissements.isEmpty();

            JpaUtil.ouvrirTransaction();
            if (etablissementInDb) {
                eleveDao.create(eleve);
                JpaUtil.validerTransaction();
                unMessage.envoyerMail("nepasrepondre.auto@service.fr", eleve.getMail(), "Succès Création Client", "Bienvenue, le client a été créé avec succès.");
                result = true;
            } else {
                Etablissement etablissement = obtenirEtablissement(codeEtablissement);
                if (etablissement != null) {
                    eleve.setEtablissement(etablissement);
                    etablissementDao.create(etablissement);
                    eleveDao.create(eleve);
                    JpaUtil.validerTransaction();
                    result = true;
                } else {
                    JpaUtil.annulerTransaction();
                    result = false;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

            unMessage.envoyerMail("nepasrepondre.auto@service.fr", eleve.getMail(), "Echec Création Client", "Le client n'a pas pu être crée. Veuillez vérifier vos informations et réessayer dans quelques instants. Si le problèmes persiste, n'hésitez pas à contacter l'entreprise.");

            result = false;

        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return result;

    }

    public String creerSoutien(Eleve eleve, String details, Matiere matiere) {

        String result = "";

        SoutienDao soutienDao = new SoutienDao();
        IntervenantDao intervenantDao = new IntervenantDao();

        List<Intervenant> listeIntervenants = intervenantDao.findIntervenantsDisponibles(eleve.getClasse());

        if (listeIntervenants != null) {
            try {
                Intervenant intervenant = listeIntervenants.get(0);
                Soutien soutien = new Soutien(matiere, eleve, details, intervenant);
                intervenant.setEnSoutien(true);

                JpaUtil.creerContextePersistance();
                JpaUtil.ouvrirTransaction();

                soutienDao.create(soutien);

                JpaUtil.validerTransaction();

            } catch (Exception ex) {
                ex.printStackTrace();
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        } else {

            return result;
        }

        return "";
    }

    private Etablissement obtenirEtablissement(String codeEtablissement) {
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

    public void NoterSoutien(Soutien soutien) {

    }

}
