package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Coordonnees;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-04-07T15:50:26")
@StaticMetamodel(Etablissement.class)
public class Etablissement_ { 

    public static volatile SingularAttribute<Etablissement, String> adresse;
    public static volatile SingularAttribute<Etablissement, Long> id;
    public static volatile SingularAttribute<Etablissement, String> uai;
    public static volatile SingularAttribute<Etablissement, String> nom;
    public static volatile SingularAttribute<Etablissement, Float> ips;
    public static volatile SingularAttribute<Etablissement, Coordonnees> coords;

}