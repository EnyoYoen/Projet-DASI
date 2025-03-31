package console;

import dao.JpaUtil;
import java.util.List;
import metier.modele.Client;
import metier.service.Service;

public class Main {

    public static void main(String[] args) {
        //JpaUtil.desactiverLog();
        JpaUtil.creerFabriquePersistance();
        testerInscrireClients();
        testerConsulterListeClients();
        JpaUtil.fermerFabriquePersistance();
    }

    private static void testerInscrireClients() {
        Service service = new Service();

        printlnConsoleIHM("Inscription Client C1");
        Client c1 = new Client("Hugo", "Victor", "victor.hugo@gmail.com", "esmeralda");
        Boolean resultat1 = service.inscrireClient(c1);
        printlnConsoleIHM(resultat1 + " -> Inscription client C1 " + c1);

        printlnConsoleIHM("Inscription Client C2");
        Client c2 = new Client("Zola", "Emile", "emile.zola@gmail.com", "nana");
        Boolean resultat2 = service.inscrireClient(c2);
        printlnConsoleIHM(resultat2 + " -> Inscription client C2 " + c2);

        printlnConsoleIHM("Inscription Client C3");
        Client c3 = new Client("Ada", "Lovelace", "a.lovelace@gmail.com", "augusta");
        Boolean resultat3 = service.inscrireClient(c3);
        printlnConsoleIHM(resultat3 + " -> Inscription client C3 " + c3);

        // Cas d'un doublon de mail -> le service doit échouer
        printlnConsoleIHM("Inscription Client C4");
        Client c4 = new Client("Arliss", "Lovelace", "a.lovelace@gmail.com", "toto");
        Boolean resultat4 = service.inscrireClient(c4);
        printlnConsoleIHM(resultat4 + " -> Inscription client C4 " + c4);
    }

    private static void testerConsulterListeClients(){
        Service service = new Service();
        List<Client> clients = service.listerClients();

        if (clients == null) {
            printlnConsoleIHM("ERREUR du Service listerClients");
        } else {
            printlnConsoleIHM("Liste des Clients (" + clients.size() + ")");
            for (Client c: clients) {
                printlnConsoleIHM("#" + c.getId() + " " + c.getNom().toUpperCase() + " " + c.getPrenom());
            }
            printlnConsoleIHM("----");
        }
    }

    public static void printlnConsoleIHM(Object o) {
        String BG_CYAN = "\u001b[46m";
        String RESET = "\u001B[0m";

        System.out.print(BG_CYAN);
        System.out.println(String.format("%-80s", o));
        System.out.print(RESET);
    }
    

}
