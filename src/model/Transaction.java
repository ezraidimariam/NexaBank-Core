package model;

import java.time.LocalDate;

public class Transaction {
    private String idTransaction;
    private String type;
    private double montant;
    private LocalDate date;
    private String compteSource;
    private String compteDestination;

    // Constructeur complet
    public Transaction(String idTransaction, String type, double montant, LocalDate date, String compteSource, String compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    // Constructeur simple pour faciliter l'utilisation dans Client
    public Transaction(String type, double montant) {
        this.idTransaction = "TX" + (int)(Math.random() * 1000);
        this.type = type;
        this.montant = montant;
        this.date = LocalDate.now();
        this.compteSource = "N/A";
        this.compteDestination = "N/A";
    }

    public String formatFichier() {
        String src = (compteSource != null) ? compteSource : "null";
        String dest = (compteDestination != null) ? compteDestination : "null";
        return src + " | " + dest + " | " + date + " | " + type + " | " + montant + " €";
    }

    public String getIdTransaction() { return idTransaction; }
    public String getType() { return type; }
    public double getMontant() { return montant; }
    public LocalDate getDate() { return date; }
    public String getCompteSource() { return compteSource; }
    public String getCompteDestination() { return compteDestination; }
}