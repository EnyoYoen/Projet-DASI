/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Intervenant;
import metier.modele.Matiere;

/**
 *
 * @author ypeyrot
 */
public class IntervenantDao {

    public void create(Intervenant intervenant) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(intervenant);
    }

    public List<Intervenant> findIntervenantsDisponibles(Integer classe) {
        String jpql = "SELECT i FROM Intervenant i WHERE enSoutien = false AND niveauMin <= :classe AND niveauMax >= :classe AND nbSoutiens = MIN(SELECT nbSoutiens FROM Intervenant)";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Intervenant.class);
        query.setParameter("classe", classe);
        List<Intervenant> listeIntervenants = query.getResultList();
        return listeIntervenants;
    }
}
