package model;

import exception.BankException;
import utils.FileLogger;
import java.util.Date;
import java.util.HashMap;

public class Client extends Utilisateur {
    private String idClient;
    private HashMap<String, CompteBancaire> comptes;

    public Client(String idClient, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient;
        this.comptes = new HashMap<>();
    }

    public void consulterSolde() {
        System.out.println("Soldes des comptes pour le client " + idClient + " :");
        for (CompteBancaire c : comptes.values()) {
            System.out.println("- Compte " + c.getNumeroCompte() + " (" + c.getTypeCompte() + ") : " + c.getSolde() + " DH");
        }
    }

    public void effectuerDepot(String numCompte, double montant) throws BankException {
        CompteBancaire c = comptes.get(numCompte);
        if (c == null) throw new BankException("Compte " + numCompte + " introuvable.");

        c.deposer(montant);

        Transaction transaction = new Transaction(
                (int) System.currentTimeMillis(),
                "Dépôt",
                montant,
                new Date(),
                null,
                numCompte
        );
        c.ajouterTransaction(transaction);
        transaction.genererFichierTxt();
    }

    public void effectuerRetrait(String numCompte, double montant) throws BankException {
        CompteBancaire c = comptes.get(numCompte);
        if (c == null) throw new BankException("Compte " + numCompte + " introuvable.");

        c.retirer(montant);

        Transaction transaction = new Transaction(
                (int) System.currentTimeMillis(),
                "Retrait",
                montant,
                new Date(),
                numCompte,
                null
        );
        c.ajouterTransaction(transaction);
        transaction.genererFichierTxt();
    }

    public void effectuerVirement(String srcNum, CompteBancaire dest, double montant) throws BankException {
        CompteBancaire src = comptes.get(srcNum);
        if (src == null) throw new BankException("Compte source introuvable.");
        if (dest == null) throw new BankException("Compte destination introuvable.");

        src.retirer(montant);
        dest.deposer(montant);

        Transaction transactionSource = new Transaction(
                (int) System.currentTimeMillis(),
                "Virement sortant",
                montant,
                new Date(),
                srcNum,
                dest.getNumeroCompte()
        );
        Transaction transactionDestination = new Transaction(
                (int) System.currentTimeMillis() + 1,
                "Virement entrant",
                montant,
                new Date(),
                srcNum,
                dest.getNumeroCompte()
        );

        src.ajouterTransaction(transactionSource);
        dest.ajouterTransaction(transactionDestination);
        transactionSource.genererFichierTxt();
        transactionDestination.genererFichierTxt();
    }

    public void consulterHistorique(String numCompte) {
        CompteBancaire c = comptes.get(numCompte);
        if (c == null) {
            System.out.println("Compte " + numCompte + " introuvable.");
            return;
        }

        System.out.println("Historique du compte " + numCompte + " :");
        for (Transaction t : c.getHistoriqueTransactions()) {
            System.out.println("- " + t.getType() + " | " + t.getMontant() + " DH | " + t.getDate());
        }
    }

    public void consulterReleve(String numCompte) {
        try {
            FileLogger.afficherReleve(numCompte);
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getIdClient() { return idClient; }
    public HashMap<String, CompteBancaire> getComptes() { return comptes; }
}