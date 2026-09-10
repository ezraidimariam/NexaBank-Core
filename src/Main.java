import model.Client;
import model.CompteBancaire;
import model.Gestionnaire;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INITIALISATION DES DONNÉES ===");
        Gestionnaire gestionnaire = new Gestionnaire("G001", "El Amrani", "Youssef", "youssef@bank.ma", "admin123");
        Client client = new Client("C101", "Benali", "Karim", "karim@email.com", "pass123");

        System.out.println("\n=== TEST DE CONNEXION ===");
        if (client.login("karim@email.com", "pass123")) {
            System.out.println("Connexion réussie pour le client : " + client.getNom());
        }

        System.out.println("\n=== TEST GESTIONNAIRE : CRÉATION DE COMPTES ===");
        CompteBancaire compteCourant = new CompteBancaire("CB001", 2000.0, "Courant");
        CompteBancaire compteEpargne = new CompteBancaire("CB002", 5000.0, "Épargne");

        gestionnaire.creerCompte(client, compteCourant);
        gestionnaire.creerCompte(client, compteEpargne);

        System.out.println("\n=== CONSULTATION DU SOLDE INITIAL ===");
        client.consulterSolde();

        System.out.println("\n=== TEST CLIENT : OPÉRATIONS BANCAIRES ===");
        client.effectuerDepot("CB001", 500.0);
        client.effectuerRetrait("CB001", 300.0);
        client.effectuerVirement("CB001", compteEpargne, 400.0);

        System.out.println("\n=== CONSULTATION DU SOLDE FINAL ===");
        client.consulterSolde();

        System.out.println("\n=== HISTORIQUE DES TRANSACTIONS (COMPTE COURANT) ===");
        compteCourant.afficherHistorique();

        System.out.println("\n=== TEST GESTIONNAIRE : CLÔTURE DE COMPTE ===");
        gestionnaire.cloturerCompte(client, "CB002");
        client.consulterSolde();

        System.out.println("\n=== DÉCONNEXION ===");
        client.logout();
        gestionnaire.logout();
    }
}