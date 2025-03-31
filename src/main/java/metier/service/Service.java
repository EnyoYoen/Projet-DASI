/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import dao.ClientDao;
import metier.modele.Client;
import util.Message;
import dao.JpaUtil;
import java.util.List;

/**
 *
 * @author lmarnas
 */
public class Service {
    
    
  public Boolean inscrireClient (Client client)
   {
       ClientDao clientDao = new ClientDao();

       Message unMessage = new Message();
       
       try{
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();
           clientDao.create(client);
           JpaUtil.validerTransaction();
           
           unMessage.envoyerMail("nepasrepondre.auto@service.fr", client.getMail(),"Succès Création Client", "Bienvenue, le client a été créé avec succès.");
          
          
           return true;
       }
       
       catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            
            unMessage.envoyerMail("nepasrepondre.auto@service.fr", client.getMail(),"Echec Création Client", "Le client n'a pas pu être crée. Veuillez vérifier vos informations et réessayer dans quelques instants. Si le problèmes persiste, n'hésitez pas à contacter l'entreprise.");
            
            return false;
                   
        }
       
       
       finally{
           JpaUtil.fermerContextePersistance();
       }
      
   }  
  
  
  public List<Client> listerClients()
  {
     ClientDao clientDao = new ClientDao();

      JpaUtil.creerContextePersistance();
      List<Client> clients = clientDao.findAll();
      JpaUtil.fermerContextePersistance();
      
      return clients;
  }
            
}
       
   
 
