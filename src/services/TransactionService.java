package services;

import exception.CompteInexistantException;
import exception.MontantNegatifException;
import exception.SoldeInsuffisantException;
import model.*;

import java.io.*;

public class TransactionService {

    public void effectuerDepot(Compte compte, double montant) throws MontantNegatifException, IOException {
        if (montant <= 0) {
            throw new MontantNegatifException("Le montant du dépôt doit être supérieur à zéro.");
        }
        compte.setSolde(compte.getSolde() + montant);

        Transaction t = new Transaction(
                Transaction.count++,
                TypeTransaction.DEPOT,
                montant,
                null,
                compte.getNumeroCompte()
        );

        compte.getHistoriqueTransactions().add(t);
        ecrireTransactionFichier(compte.getNumeroCompte(), t);
    }

    public void effectuerRetrait(Compte compte, double montant) throws MontantNegatifException, SoldeInsuffisantException, IOException {
        if (montant <= 0) {
            throw new MontantNegatifException("Le montant du retrait doit être supérieur à zéro.");
        }
        if (compte.getSolde() < montant) {
            throw new SoldeInsuffisantException("Solde insuffisant pour effectuer ce retrait.");
        }
        compte.setSolde(compte.getSolde() - montant);

        Transaction t = new Transaction(
                Transaction.count++,
                TypeTransaction.RETRAIT,
                montant,
                compte.getNumeroCompte(),
                null
        );

        compte.getHistoriqueTransactions().add(t);
        ecrireTransactionFichier(compte.getNumeroCompte(), t);
    }

    public void effectuerVirement(Compte source, Compte destination, double montant) throws MontantNegatifException, SoldeInsuffisantException, IOException {
        if (montant <= 0) {
            throw new MontantNegatifException("Le montant du virement doit être supérieur à zéro.");
        }
        if (source.getSolde() < montant) {
            throw new SoldeInsuffisantException("Solde insuffisant pour effectuer le virement.");
        }

        source.setSolde(source.getSolde() - montant);
        destination.setSolde(destination.getSolde() + montant);

        Transaction t = new Transaction(
                Transaction.count++,
                TypeTransaction.VIREMENT,
                montant,
                source.getNumeroCompte(),
                destination.getNumeroCompte()
        );

        source.getHistoriqueTransactions().add(t);
        destination.getHistoriqueTransactions().add(t);

        ecrireTransactionFichier(source.getNumeroCompte(), t);
        ecrireTransactionFichier(destination.getNumeroCompte(), t);
    }

    private void ecrireTransactionFichier(String numeroCompte, Transaction t) throws IOException {
        String filename = numeroCompte + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String source = (t.getCompteSource() != null) ? t.getCompteSource() : "null";
            String dest = (t.getCompteDestination() != null) ? t.getCompteDestination() : "null";

            String ligne = t.getDate().toString() + " " + t.getTypeTransaction().name() + " " + (int)t.getMontant() + " € " + source + " " + dest;
            writer.write(ligne);
            writer.newLine();
        }
    }

    public void afficherReleveFichier(String numeroCompte) throws IOException, CompteInexistantException {
        String filename = numeroCompte + ".txt";
        File file = new File(filename);

        if (!file.exists()) {
            throw new CompteInexistantException("Aucun relevé trouvé pour le compte " + numeroCompte);
        }

        System.out.println("\n--- RELEVÉ BANCAIRE (Compte " + numeroCompte + ") ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}