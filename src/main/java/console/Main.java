package console;

import dao.JpaUtil;
import java.util.Date;
import java.util.List;
import metier.modele.Eleve;
import metier.service.Service;
import util.EducNetApi;

public class Main {

    public static void main(String[] args) {
        //JpaUtil.desactiverLog();
        JpaUtil.creerFabriquePersistance();
        testerInscrireEleve();
        JpaUtil.fermerFabriquePersistance();
    }

    public static void testerInscrireEleve() {
        Service service = new Service();
        Eleve eleveTest = new Eleve(new Date(), 6, "Lafon", "Tim2", "lafontim255@gmail.com", "1234");
        String codeEtablissement = "0440256P";
        service.inscrireEleve(eleveTest, codeEtablissement);

    }

    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }

}
