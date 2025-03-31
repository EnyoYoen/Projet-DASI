package metier.modele;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarnas
 */
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    
    private String prenom;
    
    @Column (unique = true)
    private String mail;
    
    private String motDePasse;

    // Constructeur par défaut sans paramètres
    public Client() {
    }

    // Constructeur par défaut avec paramètres (nom, prenom, mail, motDePasse)
    public Client(String nom, String prenom, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }
    
    
    // Getter id
    public long getId() {
        return id;
    }

    // Getter nom
    public String getNom() {
        return nom;
    }

    // Getter prenom
    public String getPrenom() {
        return prenom;
    }

    // Getter mail
    public String getMail() {
        return mail;
    }

    // Getter motDePasse
    public String getMotDePasse() {
        return motDePasse;
    }

    // Setter id
    public void setId(Long id) {
        this.id = id;
    }

    // Setter nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Setter prenom
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Setter mail
    public void setMail(String mail) {
        this.mail = mail;
    }

    // Setter motDePasse
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    // Méthode toString
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        return Objects.equals(this.id, other.id);
    }
    
      
}
