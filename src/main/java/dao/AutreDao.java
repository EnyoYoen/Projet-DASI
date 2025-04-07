/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import metier.modele.Autre;

/**
 *
 * @author ypeyrot
 */
public class AutreDao {
    
    
   public void create (Autre autre)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(autre);
    }
}
