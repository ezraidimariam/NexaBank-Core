package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private String idTransaction;
    private String type; // Dépôt, Retrait, Virement
    private double montant;
    private LocalDate date;
    private String compteSource;
    private String compteDestination;

    public Transaction(String type, double montant, String compteSource, String compteDestination) {
        this.idTransaction = "TX-" + UUID.randomUUID().toString().substring(0, 8);
        this.type = type;
        this.montant = montant;
        this.date = LocalDate.now();
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public String formatFichier() {
        String src = (compteSource != null) ? compteSource : "null";
        String dest = (compteDestination != null) ? compteDestination : "null";
        return src + " | " + dest + " | " + date + " | " + type + " | " + montant + " €";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(idTransaction, that.idTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction);
    }

    public String getIdTransaction() { return idTransaction; }
    public String getType() { return type; }
    public double getMontant() { return montant; }
    public LocalDate getDate() { return date; }
    public String getCompteSource() { return compteSource; }
    public String getCompteDestination() { return compteDestination; }
}