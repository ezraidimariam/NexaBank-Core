package model;

import exception.BankException;
import utils.FileLogger;
import java.util.HashMap;
public class Client extends Utilisateur {
    private String idClient;
    private HashMap<String, CompteBancaire> comptes;

    public Client(String idClient, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient;
        this.comptes = new HashMap<>();
    }

    public void consulterSolde() {
        System.out.println("Soldes des comptes pour le client " + idClient + " :");
        for (CompteBancaire c : comptes.values()) {
            System.out.println("- Compte " + c.getNumeroCompte() + " (" + c.getTypeCompte() + ") : " + c.getSolde() + " DH");
        }
    }

    public void effectuerDepot(String numCompte, double montant) throws BankException {
        CompteBancaire c = comptes.get(numCompte);
        if (c == null) throw new BankException("Compte " + numCompte + " introuvable.");
        c.deposer(montant);
    }

    public void effectuerRetrait(String numCompte, double montant) throws BankException {
        CompteBancaire c = comptes.get(numCompte);
        if (c == null) throw new BankException("Compte " + numCompte + " introuvable.");
        c.retirer(montant);
    }

    public void effectuerVirement(String srcNum, CompteBancaire dest, double montant) throws BankException {
        CompteBancaire src = comptes.get(srcNum);
        if (src == null) throw new BankException("Compte source introuvable.");
        if (dest == null) throw new BankException("Compte destination introuvable.");

        src.retirer(montant);
        dest.deposer(montant);
    }

    public void consulterReleve(String numCompte) {
        try {
            FileLogger.afficherReleve(numCompte);
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getIdClient() { return idClient; }
    public HashMap<String, CompteBancaire> getComptes() { return comptes; }
}