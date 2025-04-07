/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.EleveDao;
import dao.EtablissementDao;
import util.Message;
import dao.JpaUtil;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import util.EducNetApi;
import util.Outils;

/**
 *
 * @author lmarnas
 */
public class Service {

    public Boolean inscrireEleve(Eleve eleve, String codeEtablissement) {
        Outils outils = new Outils();
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
                Etablissement etablissement = outils.obtenirEtablissement(codeEtablissement);
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
    
    
    public Boolean creerSoutien() {
        
        
        return true;
    }

}
