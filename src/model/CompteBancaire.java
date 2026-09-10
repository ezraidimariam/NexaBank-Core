package model;

import exception.FichierException;
import exception.MontantNegatifException;
import exception.SoldeInsuffisantException;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CompteBancaire {
    private String numeroCompte;
    private double solde;
    private String typeCompte;
    private Set<Transaction> historiqueTransactions;

    public CompteBancaire(String numeroCompte, double solde, String typeCompte) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.historiqueTransactions = new HashSet<>();
    }

    public void deposer(double montant) throws MontantNegatifException, FichierException {
        if (montant <= 0) {
            throw new MontantNegatifException("Montant invalide");
        }
        this.solde += montant;
        Transaction t = new Transaction("Depot", montant, this.numeroCompte, null);
        ajouterTransaction(t);
    }

    public void retirer(double montant) throws MontantNegatifException, SoldeInsuffisantException, FichierException {
        if (montant <= 0) {
            throw new MontantNegatifException("Montant invalide");
        }
        if (montant > solde) {
            throw new SoldeInsuffisantException("Solde insuffisant");
        }
        this.solde -= montant;
        Transaction t = new Transaction("Retrait", montant, this.numeroCompte, null);
        ajouterTransaction(t);
    }

    public void ajouterTransaction(Transaction transaction) throws FichierException {
        this.historiqueTransactions.add(transaction);
        ecrireDansFichier(transaction.formatFichier());
    }

    private void ecrireDansFichier(String ligne) throws FichierException {
        try (FileWriter fw = new FileWriter(this.numeroCompte + ".txt", true)) {
            fw.write(ligne + "\n");
        } catch (Exception e) {
            throw new FichierException("Erreur fichier");
        }
    }

    public void afficherReleveDepuisFichier() throws FichierException {
        System.out.println("Releve de compte " + numeroCompte + ":");
        try (Scanner sc = new Scanner(new File(this.numeroCompte + ".txt"))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            throw new FichierException("Fichier introuvable");
        }
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public String getTypeCompte() { return typeCompte; }
    public Set<Transaction> getHistoriqueTransactions() { return historiqueTransactions; }
}