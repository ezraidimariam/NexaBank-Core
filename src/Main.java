import model.*;
import exception.*;

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
        Compte compteCourant = new Compte("CB001", 2000.0, "Courant");
        Compte compteEpargne = new Compte("CB002", 5000.0, "Épargne");

        gestionnaire.creerCompte(client, compteCourant);
        gestionnaire.creerCompte(client, compteEpargne);

        System.out.println("\n=== CONSULTATION DU SOLDE INITIAL ===");
        client.consulterSolde();

        System.out.println("\n=== TEST CLIENT : OPÉRATIONS BANCAIRES ===");
        try {
            compteCourant.deposer(500.0);
            compteCourant.retirer(300.0);
            client.virement("CB001", compteEpargne, 400.0);
        } catch (MontantNegatifException | SoldeInsuffisantException | CompteInexistantException | FichierException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n=== CONSULTATION DU SOLDE FINAL ===");
        client.consulterSolde();

        System.out.println("\n=== HISTORIQUE ET RELEVÉ DU FICHIER TXT ===");
        try {
            compteCourant.afficherReleveDepuisFichier();
        } catch (FichierException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n=== TEST GESTIONNAIRE : CLÔTURE DE COMPTE ===");
        try {
            gestionnaire.cloturerCompte(client, "CB002");
        } catch (CompteInexistantException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        client.consulterSolde();
    }
}