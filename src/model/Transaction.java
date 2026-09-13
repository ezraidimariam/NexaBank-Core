package model;

import java.time.LocalDate;

public class Transaction {
    public static int count = 1;
    private int idTransaction;
    private TypeTransaction type;
    private double montant;
    private LocalDate date;
    private String compteSource;
    private String compteDestination;

    public Transaction(int idTransaction, TypeTransaction type, double montant, String compteSource, String compteDestination) {
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = LocalDate.now();
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
    }

    public int getIdTransaction() { return idTransaction; }
    public TypeTransaction getTypeTransaction() { return type; }
    public double getMontant() { return montant; }
    public LocalDate getDate() { return date; }
    public String getCompteSource() { return compteSource; }
    public String getCompteDestination() { return compteDestination; }
}