package console;

import dao.JpaUtil;
import java.util.Date;
import java.util.List;
import metier.modele.Autre;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Personne;
import metier.modele.Soutien;
import metier.service.Service;
import util.EducNetApi;

public class Main {

    public static void main(String[] args) {
        Service service = new Service();

        JpaUtil.creerFabriquePersistance();
        //JpaUtil.desactiverLog();
        try {
            service.init();
            testerInscrireEleve();

            System.out.println(service.recupererMdp("lafontim2@gmail.com"));
            Personne personne = service.authentification("lafontim2@gmail.com", "1234");
            System.out.println(personne);
            List<Soutien> soutiensEleve = null;
            if (personne instanceof Eleve) {
                System.out.println(service.recupererMatieres());
                testerCreerSoutien((Eleve) personne);
                soutiensEleve = service.recupererHistorique(personne);
                System.out.println(soutiensEleve);
                System.out.println("Test créer soutien");
            } else if (personne instanceof Intervenant) {
                System.out.println("Pas un elève");
                throw new Exception("Pas un élève");
            }
            System.out.println("\n\nFIN PARTIE ELEVE\n\n");

            Personne intervenant = service.authentification("pierrelafon1@gmail.com", "mdp");
            System.out.println(intervenant);
            List<Soutien> soutiensIntervenant = null;
            if (intervenant instanceof Intervenant) {
                Intervenant i = (Intervenant)(intervenant);
                System.out.println(service.recupererStatsMatiere(i));
                System.out.println(service.recupererStatsIPS(i));
                System.out.println(service.recupererStatsDurees(i));
                System.out.println(service.recupererStatsNotes(i));
                System.out.println(service.recupererHistoriqueEtablissements(i));
                soutiensIntervenant = service.recupererHistorique(intervenant);
                System.out.println(soutiensIntervenant);
                System.out.println(service.obtenirProfil(personne.getMail()));
                System.out.println(service.recupererHistoriqueIntervenantEleve(i, (Eleve)personne));
            } else if (personne instanceof Intervenant) {
                System.out.println("Pas un intervenant");
            }
            System.out.println("\n\nFIN PARTIE INTERVENANT\n\n");
            
            System.out.println(service.ajouterCompteRendu(soutiensIntervenant.get(0), "tro bi1"));
            System.out.println(service.noterSoutien(soutiensEleve.get(0), 5));
            
            System.out.println("\n\nFIN SOUTIEN\n\n");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerFabriquePersistance();
        }
    }

    public static void testerInscrireEleve() {
        Service service = new Service();
        Eleve eleveTest = new Eleve(new Date(), 6, "Lafon", "Tim", "lafontim2@gmail.com", "1234");
        String codeEtablissement = "0691664J";
        service.inscrireEleve(eleveTest, codeEtablissement);

    }

    public static void testerCreerSoutien(Eleve eleve) {
        Service service = new Service();
        service.creerSoutien(eleve, "Test des details", Matiere.ARTS_PLASTIQUES_COLLEGE);

    }

    //public static void
    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }

}
