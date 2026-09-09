package model;

import java.util.HashSet;

public class CompteBancaire {
    private final String numeroCompte;
    private final String typeCompte;
    private double solde;
    private final HashSet<Transaction> historiqueTransactions;

    public CompteBancaire(String numeroCompte, double solde, String typeCompte) {
        this.numeroCompte = numeroCompte;
        this.typeCompte = typeCompte;
        if (solde >= 0) {
            this.solde = solde;
        } else {
            this.solde = 0;
            System.out.println("Le solde initial ne peut pas être négatif. Mis à 0 par défaut.");
        }
        this.historiqueTransactions = new HashSet<>();
    }

    public void retirer(double montant) {
        if (montant > 0 && montant <= solde) {
            this.solde -= montant;
            ajouterTransaction(new Transaction("RETRAIT", montant));
        } else {
            System.out.println("Opération impossible: montant invalide ou solde insuffisant.");
        }
    }

    public void deposer(double montant) {
        if (montant > 0) {
            this.solde += montant;
            ajouterTransaction(new Transaction("DEPOT", montant));
        } else {
            System.out.println("Le montant du dépôt doit être positif.");
        }
    }

    public void ajouterTransaction(Transaction t) {
        this.historiqueTransactions.add(t);
    }

    public void afficherHistorique() {
        System.out.println("Historique du compte " + numeroCompte + ":");
        for (Transaction t : historiqueTransactions) {
            // استدعينا Getters باش تحيد Warning ويبقى الكود منظم
            System.out.println(" - " + t.getType() + " de " + t.getMontant() + " DH (Date: " + t.getDate() + ")");
        }
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public String getTypeCompte() { return typeCompte; }
    public HashSet<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
}