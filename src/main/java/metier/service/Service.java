/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import dao.EleveDao;
import util.Message;
import dao.JpaUtil;
import java.util.List;
import metier.modele.Eleve;

/**
 *
 * @author lmarnas
 */
public class Service {
    
    
  public Boolean inscrireEleve (Eleve eleve)
   {
       EleveDao eleveDao = new EleveDao();

       Message unMessage = new Message();
       
       try{
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();
           eleveDao.create(eleve);
           JpaUtil.validerTransaction();
           
           unMessage.envoyerMail("nepasrepondre.auto@service.fr", eleve.getMail(),"Succès Création Client", "Bienvenue, le client a été créé avec succès.");
          
           return true;
       }
       
       catch (Exception ex)
        {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            
            unMessage.envoyerMail("nepasrepondre.auto@service.fr", eleve.getMail(),"Echec Création Client", "Le client n'a pas pu être crée. Veuillez vérifier vos informations et réessayer dans quelques instants. Si le problèmes persiste, n'hésitez pas à contacter l'entreprise.");
            
            return false;
                   
        }
       
       
       finally{
           JpaUtil.fermerContextePersistance();
       }
      
   }
  
}
       
   
 
