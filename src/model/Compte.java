    package model;

    import java.util.HashSet;

    public class Compte {
        private static int counter = 1001;
        private String numeroCompte;
        private double solde;
        private TypeCompte typeCompte;
        private HashSet<Transaction> historiqueTransactions;

        public Compte(double solde, TypeCompte typeCompte) {
            this.numeroCompte = "CB" + counter++;
            this.solde = solde;
            this.typeCompte = typeCompte;
            this.historiqueTransactions = new HashSet<>();
        }

        public String getNumeroCompte() { return numeroCompte; }
        public double getSolde() { return solde; }
        public void setSolde(double solde) { this.solde = solde; }
        public TypeCompte getTypeCompte() { return typeCompte; }
        public HashSet<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
    }