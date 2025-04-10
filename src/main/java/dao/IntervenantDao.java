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
    
    
    public void create (Intervenant intervenant)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(intervenant);
    }
    
    public List<Intervenant> findIntervenantsDisponibles(Integer classe) {
         String jpql = "SELECT i FROM Intervenant i WHERE enSoutien = false AND nbSoutiens = MIN(SELECT nbSoutiens FROM Intervenant)";
         TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Intervenant.class);
         List<Intervenant> listeIntervenants = query.getResultList();
         List<Intervenant> listeFinale = new ArrayList();
         for(Intervenant i:listeIntervenants){
             if(i.getNiveaux()[classe]){
                 listeFinale.add(i);
             }
         }
         return listeFinale;
    }
}
