package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-04-07T17:52:39")
@StaticMetamodel(Intervenant.class)
public abstract class Intervenant_ extends Personne_ {

    public static volatile SingularAttribute<Intervenant, Integer> nbSoutiens;
    public static volatile SingularAttribute<Intervenant, Boolean[]> niveaux;
    public static volatile SingularAttribute<Intervenant, Boolean> enSoutien;
    public static volatile SingularAttribute<Intervenant, String> numTel;

}