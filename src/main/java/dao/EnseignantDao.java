/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import metier.modele.Enseignant;

/**
 *
 * @author ypeyrot
 */
public class EnseignantDao {
    
    
   public void create (Enseignant enseignant)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(enseignant);
    }
}
