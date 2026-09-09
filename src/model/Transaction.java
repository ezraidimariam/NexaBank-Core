package model;

public class Transaction {
    package model;

import utils.FileLogger;
import exception.BankException;
import java.util.Date;

    public class Transaction {
        private int id_transaction;
        private String type;
        private double montant;
        private Date date;
        private String compteSource;
        private String compteDestination;

        public Transaction(int id_transaction, String type, double montant, Date date, String compteSource, String compteDestination) {
            this.id_transaction = id_transaction;
            this.type = type;
            this.montant = montant;
            this.date = date;
            this.compteSource = compteSource;
            this.compteDestination = compteDestination;
        }

        public void genererFichierTxt() {
            try {
                FileLogger.enregistrerTransaction(compteSource != null ? compteSource : compteDestination, this);
            } catch (BankException e) {
                System.out.println("Erreur génération TXT : " + e.getMessage());
            }
        }

        public int getId_transaction() { return id_transaction; }
        public String getType() { return type; }
        public double getMontant() { return montant; }
        public Date getDate() { return date; }
        public String getCompteSource() { return compteSource; }
        public String getCompteDestination() { return compteDestination; }
    }
}
