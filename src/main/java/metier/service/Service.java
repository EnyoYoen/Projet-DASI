/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.EleveDao;
import util.Message;
import dao.JpaUtil;
import java.util.List;
import metier.modele.Eleve;
import util.EducNetApi;

/**
 *
 * @author lmarnas
 */
public class Service {

    public List<String> verifierEtablissement(String codeEtablissement) {
        EducNetApi educNetApi = new EducNetApi();
        List<String> infos = null;

        try {
            infos = educNetApi.getInformationEtablissement(codeEtablissement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return infos;
    }

    public Boolean inscrireEleve(Eleve eleve, String codeEtablissement) {
        EleveDao eleveDao = new EleveDao();

        Message unMessage = new Message();

        List<String> infos = verifierEtablissement(codeEtablissement);

        Boolean result;

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            if (infos != null) {
                eleveDao.create(eleve);
                JpaUtil.validerTransaction();
                unMessage.envoyerMail("nepasrepondre.auto@service.fr", eleve.getMail(), "Succès Création Client", "Bienvenue, le client a été créé avec succès.");
                result = true;
            } else {
                result = false;
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

}
