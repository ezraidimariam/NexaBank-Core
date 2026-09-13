package model;

import java.util.HashSet;

public class Compte {
    private String numeroCompte;
    private double solde;
    private String typeCompte; // Courant / Épargne
    private HashSet<Transaction> historiqueTransactions;

    public Compte(String numeroCompte, double solde, String typeCompte) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.historiqueTransactions = new HashSet<>();
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public String getTypeCompte() { return typeCompte; }
    public HashSet<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
}