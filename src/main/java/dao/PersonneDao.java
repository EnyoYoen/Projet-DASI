/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Personne;
import metier.modele.Soutien;

/**
 *
 * @author tlafondela
 */
public class PersonneDao {

    public List<Soutien> recupererHistorique(Personne personne) {
        String jpql = "SELECT s FROM Soutien s WHERE s.eleve.mail = :unMail OR s.intervenant.mail = :unMail ORDER BY s.dateDemande DESC";
        TypedQuery<Soutien> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Soutien.class);
        query.setParameter("unMail", personne.getMail());
        List<Soutien> listeSoutiens = query.getResultList();
        return listeSoutiens;
    }

    public List<Personne> findByMailMdp(String mail, String mdp) {
        String jpql = "SELECT p FROM Personne p WHERE p.mail = :mail AND p.mdp = :mdp";
        TypedQuery<Personne> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Personne.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        return query.getResultList();
    }

    public List<Personne> findByMail(String mail) {
        String jpql = "SELECT p FROM Personne p WHERE p.mail = :mail";
        TypedQuery<Personne> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Personne.class);
        query.setParameter("mail", mail);
        return query.getResultList();
    }

}
