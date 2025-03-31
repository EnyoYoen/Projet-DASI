/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Client;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author lmarnas
 */
public class ClientDao {
    
    
   public void create (Client client)
    {  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }
   
   public List<Client> findAll()
   {
       EntityManager em = JpaUtil.obtenirContextePersistance();

      try{
       String jpql = "SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC";
       Query query = em.createQuery(jpql, Client.class);
        
       return query.getResultList();
   }
      
      catch (Exception ex)
      {
          ex.printStackTrace();
          return new ArrayList<>();
      }
    
   }
}
