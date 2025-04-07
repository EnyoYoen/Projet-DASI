/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;

/**
 *
 * @author tlafondela
 */
public class EleveDao {

    public void create(Eleve eleve) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(eleve);
    }

    public List<Eleve> find() {
        String jpql = "SELECT e FROM Eleve e";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Eleve.class);
        return query.getResultList();
    }

    public List<Eleve> findByMailMdp(String mail, String mdp) {
        String jpql = "SELECT e FROM Eleve e WHERE e.mail = :mail and e.mdp = :mdp";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Eleve.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        return query.getResultList();
    }
}
