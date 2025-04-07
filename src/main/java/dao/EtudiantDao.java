/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import metier.modele.Etudiant;

/**
 *
 * @author ypeyrot
 */
public class EtudiantDao {
    
    
   public void create (Etudiant etudiant)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(etudiant);
    }
}
