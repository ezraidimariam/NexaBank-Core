package model;

import java.util.ArrayList;
import java.util.List;

public class CompteBancaire {
    private String numeroCompte;
    private double solde;
    private String typeCompte;
    private List<Transaction> historiqueTransactions;

    public CompteBancaire(String numeroCompte, double solde, String typeCompte) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.historiqueTransactions = new ArrayList<>();
    }

    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
            ajouterTransaction(new Transaction("DEPOT", montant));
        }
    }

    public void retirer(double montant) {
        if (montant > 0 && solde >= montant) {
            solde -= montant;
            ajouterTransaction(new Transaction("RETRAIT", montant));
        }
    }

    public void ajouterTransaction(Transaction transaction) {
        historiqueTransactions.add(transaction);
    }

    public void afficherHistorique() {
        System.out.println("Historique du compte " + numeroCompte + " :");
        for (Transaction t : historiqueTransactions) {
            System.out.println(" - " + t.formatFichier());
        }
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public String getTypeCompte() { return typeCompte; }
    public List<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
}