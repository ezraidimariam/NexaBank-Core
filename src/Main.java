import exception.BankException;
import java.util.Date;
import model.Client;
import model.CompteBancaire;
import model.Gestionnaire;
import model.Transaction;

public class Main {
    public static void main(String[] args) {
        try {
            Client client = new Client("C001", "Doe", "John", "john@example.com", "1234");
            CompteBancaire compte = new CompteBancaire("CB001", 1000.0, "Courant");
            Gestionnaire gestionnaire = new Gestionnaire("G001", "Smith", "Alice", "alice@example.com", "admin");

            gestionnaire.creerCompte(client, compte);
            client.effectuerDepot("CB001", 250.0);
            client.consulterSolde();

            Transaction transaction = new Transaction(
                    1,
                    "Dépôt",
                    250.0,
                    new Date(),
                    null,
                    "CB001"
            );
            transaction.genererFichierTxt();

            System.out.println("Projet NexaBank initialisé avec succès.");
        } catch (BankException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
