package model;

import java.time.LocalDateTime;

public class Transaction {
    private final String type;
    private final double montant;
    private final LocalDateTime date;

    public Transaction(String type, double montant) {
        this.type = type;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getMontant() { return montant; }
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return type + " de " + montant + " DH le " + date.getDayOfMonth() + "/" + date.getMonthValue();
    }
}