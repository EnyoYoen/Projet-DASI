/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Soutien;

/**
 *
 * @author ypeyrot
 */
public class SoutienDao {

    public void create(Soutien soutien) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(soutien);
    }

    public void update(Soutien soutien) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(soutien);
    }

    public List<Soutien> recupererHistoriqueIntervenantEleve(Intervenant intervenant, Eleve eleve) {
        String jpql = "SELECT s FROM Soutien s WHERE s.eleve = :eleve AND s.intervenant = :intervenant ORDER BY s.dateDemande DESC";
        TypedQuery<Soutien> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Soutien.class);
        query.setParameter("eleve", eleve);
        query.setParameter("intervenant", intervenant);
        List<Soutien> listeSoutiens = query.getResultList();
        return listeSoutiens;
    }

    public List<Soutien> findSoutiensNonNotes() {
        String jpql = "SELECT s FROM Soutien s WHERE s.etat <> 'Termine' AND :date - s.dateDebut > 10800000";
        TypedQuery<Soutien> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Soutien.class);
        query.setParameter("date", Timestamp.valueOf(LocalDateTime.now()));
        return query.getResultList();
    }

    public List<Etablissement> findHistoriqueEtablissements(Intervenant intervenant) {
        String jpql = "SELECT s.etablissement FROM Soutien s WHERE s.intervenant = :intervenant";
        TypedQuery<Etablissement> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Etablissement.class);
        query.setParameter("intervenant", intervenant);
        List<Etablissement> listeEtablissements = query.getResultList();
        return listeEtablissements;
    }
}
