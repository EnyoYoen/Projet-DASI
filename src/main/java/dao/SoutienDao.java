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
import metier.modele.Soutien;

/**
 *
 * @author ypeyrot
 */
public class SoutienDao {
   public void create (Soutien soutien)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(soutien);
    }
   
    public List<Soutien> findSoutiensNonNotes() {
         String jpql = "SELECT s FROM Soutien s WHERE etat <> 'Termine' AND :date - dateDebut > 10800000";
         TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Soutien.class);
         query.setParameter("date", Timestamp.valueOf(LocalDateTime.now()));
         return query.getResultList();
    }
}
