package console;

import dao.JpaUtil;
import java.util.Date;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Matiere;
import metier.service.Service;
import util.EducNetApi;

public class Main {

    public static void main(String[] args) {
        //JpaUtil.desactiverLog();
        Service service = new Service();

        JpaUtil.creerFabriquePersistance();
        testerInscrireEleve();

        Eleve eleve = service.authentification("lafontim@gmail.com", "1234");
        testerCreerSoutien(eleve);
        JpaUtil.fermerFabriquePersistance();
    }

    public static void testerInscrireEleve() {
        Service service = new Service();
        Eleve eleveTest = new Eleve(new Date(), 6, "Lafon", "Tim", "lafontim@gmail.com", "1234");
        String codeEtablissement = "0691664J";
        service.inscrireEleve(eleveTest, codeEtablissement);

    }

    public static void testerCreerSoutien(Eleve eleve) {
        Service service = new Service();
        service.creerSoutien(eleve, "Test des details", Matiere.ARTS_PLASTIQUES_COLLEGE);

    }

    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }

}
