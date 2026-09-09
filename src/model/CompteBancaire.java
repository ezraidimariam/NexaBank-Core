package model;

import exception.BankException;
import java.util.HashSet;

public class CompteBancaire {
    private String numeroCompte;
    private double solde;
    private String typeCompte;
    private HashSet<Transaction> historiqueTransactions;

    public CompteBancaire(String numeroCompte, double solde, String typeCompte) throws BankException {
        if (solde < 0) {
            throw new BankException("Le solde initial ne peut pas être négatif.");
        }
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.historiqueTransactions = new HashSet<>();
    }

    public void retirer(double montant) throws BankException {
        if (montant <= 0) {
            throw new BankException("Le montant du retrait doit être positif.");
        }
        if (montant > solde) {
            throw new BankException("Solde insuffisant.");
        }
        this.solde -= montant;
    }

    public void deposer(double montant) throws BankException {
        if (montant <= 0) {
            throw new BankException("Le montant du dépôt doit être positif.");
        }
        this.solde += montant;
    }

    public void ajouterTransaction(Transaction t) {
        this.historiqueTransactions.add(t);
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public String getTypeCompte() { return typeCompte; }
    public HashSet<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
}