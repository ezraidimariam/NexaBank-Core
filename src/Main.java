
import model.Client;
import model.CompteBancaire;
import model.Gestionnaire;

public class Main {
    public static void main(String[] args) {
        Gestionnaire gestionnaire = new Gestionnaire("G001", "El Amrani", "Youssef", "youssef@bank.ma", "admin123");
        Client client = new Client("C101", "Benali", "Karim", "karim@email.com", "pass123");

        if (client.login("karim@email.com", "pass123")) {
            System.out.println("Connexion réussie pour le client : " + client.getNom());
        }

        System.out.println("Gestionnaire actif: " + gestionnaire.getNom() + " (ID: " + gestionnaire.getIdGestionnaire() + ")");

        CompteBancaire compteCourant = new CompteBancaire("CB001", 2000.0, "Courant");
        CompteBancaire compteEpargne = new CompteBancaire("CB002", 5000.0, "Épargne");

        gestionnaire.creerCompte(client, compteCourant);
        gestionnaire.creerCompte(client, compteEpargne);

        System.out.println("\n--- Solde initial ---");
        client.consulterSolde();

        System.out.println("\n--- Opérations ---");
        client.effectuerDepot("CB001", 500.0);
        client.effectuerRetrait("CB001", 300.0);
        client.effectuerVirement("CB001", compteEpargne, 400.0);

        System.out.println("\n--- Solde final ---");
        client.consulterSolde();

        System.out.println("\n--- Historique des transactions ---");
        compteCourant.afficherHistorique();
        System.out.println("Nombre total d'opérations enregistrées: " + compteCourant.getHistoriqueTransactions().size());

        System.out.println("\n--- Déconnexion ---");
        client.logout();
    }
}