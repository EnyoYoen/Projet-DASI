/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Etablissement;

/**
 *
 * @author tlafondela
 */
public class EtablissementDao {

    public void create(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }

    public List<Etablissement> findByCode(String uai) {
        String jpql = "SELECT e FROM Etablissement e WHERE e.uai = :unUai ORDER BY e.nom DESC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Etablissement.class);
        query.setParameter("unUai", uai);
        return query.getResultList();
    }
}
