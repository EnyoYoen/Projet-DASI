/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Intervenant;

/**
 *
 * @author ypeyrot
 */
public class IntervenantDao {

    public void create(Intervenant intervenant) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(intervenant);
    }

    public void update(Intervenant intervenant) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.merge(intervenant);
    }

    public List<Intervenant> findIntervenantsDisponibles(Integer classe) {
        String jpqlMin = "SELECT MIN(i.nbSoutiens) FROM Intervenant i WHERE i.enSoutien = false AND i.niveauMin >= :classe AND i.niveauMax <= :classe";
        TypedQuery<Integer> queryMin = JpaUtil.obtenirContextePersistance().createQuery(jpqlMin, Integer.class);
        queryMin.setParameter("classe", classe);
        Integer minNbSoutiens = queryMin.getSingleResult();

        String jpql = "SELECT i FROM Intervenant i WHERE i.enSoutien = false AND i.niveauMin >= :classe AND i.niveauMax <= :classe AND i.nbSoutiens = :minNbSoutiens";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Intervenant.class);
        query.setParameter("classe", classe);
        query.setParameter("minNbSoutiens", minNbSoutiens);
        return query.getResultList();
    }
}
