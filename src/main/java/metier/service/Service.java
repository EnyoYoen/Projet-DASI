/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.AutreDao;
import dao.EleveDao;
import dao.EtablissementDao;
import dao.IntervenantDao;
import util.Message;
import dao.JpaUtil;
import dao.PersonneDao;
import dao.SoutienDao;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import metier.modele.Autre;
import metier.modele.Coordonnees;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Personne;
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

        String result = null;
        SoutienDao soutienDao = new SoutienDao();
        IntervenantDao intervenantDao = new IntervenantDao();

        try {
            JpaUtil.creerContextePersistance();

            List<Intervenant> listeIntervenants = intervenantDao.findIntervenantsDisponibles(eleve.getClasse());

            if (listeIntervenants != null && !listeIntervenants.isEmpty()) {

                Intervenant intervenant = listeIntervenants.get(0);
                Soutien soutien = new Soutien(matiere, eleve, details, intervenant);
                intervenant.setEnSoutien(true);

                JpaUtil.ouvrirTransaction();

                soutienDao.create(soutien);
                intervenantDao.update(intervenant);

                result = soutien.getLien();

                JpaUtil.validerTransaction();
            } else {
                throw new Exception("Aucun intervenant disponible pour la classe " + eleve.getClasse());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return result;
    }

    public Personne authentification(String mail, String mdp) {
        PersonneDao personneDao = new PersonneDao();

        Personne personne = null;
        List<Personne> listePersonne = null;

        try {
            JpaUtil.creerContextePersistance();

            listePersonne = personneDao.findByMailMdp(mail, mdp);

            if (listePersonne != null && !listePersonne.isEmpty()) {
                personne = listePersonne.get(0);
            } else {
                throw new Exception("Mauvais identifiant ou mot de passe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return personne;
    }

    public void noterSoutien(Soutien soutien, double note) {
        SoutienDao soutienDao = new SoutienDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            soutien.setNote(note);
            if (soutien.getDateFin() == null) {
                soutien.setDateFin(new Date());
            }

            soutienDao.update(soutien);

            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void ajouterCompteRendu(Soutien soutien, String compteRendu) {
        SoutienDao soutienDao = new SoutienDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            soutien.setCompteRendu(compteRendu);
            if (soutien.getDateFin() == null) {
                soutien.setDateFin(new Date());
            }

            soutienDao.update(soutien);

            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /*public Personne obtenirProfil(String mail) {

    }*/
    public List<Soutien> recupererHistorique(Personne personne) {
        PersonneDao personneDao = new PersonneDao();

        List<Soutien> listeSoutiens = null;

        try {
            JpaUtil.creerContextePersistance();
            listeSoutiens = personneDao.recupererHistorique(personne);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return listeSoutiens;
    }

    public String accepterSoutien(Soutien soutien) {

        return soutien.getLien();
    }

    public List<Matiere> recupererMatiere() {
        List<Matiere> liste = Arrays.asList(Matiere.values());
        return liste;
    }

    private Etablissement obtenirEtablissement(String codeEtablissement) {
        EducNetApi educNetApi = new EducNetApi();
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
            LatLng latlng = GeoNetApi.getLatLng(nom + ", " + adresse);
            double lat = latlng.lat;
            double lng = latlng.lng;
            Coordonnees coords = new Coordonnees(lat, lng);
            etablissement = new Etablissement(codeEtablissement, nom, Float.parseFloat(infos.get(8)), adresse, coords);

        }

        return etablissement;
    }

    public void init() {
        AutreDao autreDao = new AutreDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            Autre autre1 = new Autre("Joueur", "0755624099", 6, 0, "Lafon", "Pierre", "pierrelafon1@gmail.com", "mdp");
            Autre autre2 = new Autre("Joueur", "0755624099", 5, 1, "Lafon", "Pierre", "pierrelafon2@gmail.com", "mdp");
            Autre autre3 = new Autre("Joueur", "0755624099", 4, 2, "Lafon", "Pierre", "pierrelafon3@gmail.com", "mdp");
            Autre autre4 = new Autre("Joueur", "0755624099", 3, 3, "Lafon", "Pierre", "pierrelafon4@gmail.com", "mdp");

            autreDao.create(autre1);
            autreDao.create(autre2);
            autreDao.create(autre3);
            autreDao.create(autre4);

            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

}
