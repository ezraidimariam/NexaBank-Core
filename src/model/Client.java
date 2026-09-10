package model;

import exception.CompteInexistantException;
import exception.FichierException;
import exception.MontantNegatifException;
import exception.SoldeInsuffisantException;

import java.util.HashMap;

public class Client extends Utilisateur {
    private final String idClient;
    private final HashMap<String, CompteBancaire> comptes;

    public Client(String idClient, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient;
        this.comptes = new HashMap<>();
    }

    public void consulterSolde() {
        System.out.println("Comptes de " + nom + " " + prenom + " :");
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte trouve.");
        } else {
            for (CompteBancaire compte : comptes.values()) {
                System.out.println(" - Compte N: " + compte.getNumeroCompte() + " (" + compte.getTypeCompte() + ") : " + compte.getSolde() + " DH");
            }
        }
    }

    public void virement(String numSource, CompteBancaire compteDest, double montant)
            throws CompteInexistantException, MontantNegatifException, SoldeInsuffisantException, FichierException {

        CompteBancaire source = comptes.get(numSource);

        if (source == null || compteDest == null) {
            throw new CompteInexistantException("Compte introuvable");
        }

        source.retirer(montant);
        compteDest.deposer(montant);

        Transaction t = new Transaction("Virement", montant, source.getNumeroCompte(), compteDest.getNumeroCompte());
        source.getHistoriqueTransactions().add(t);
        compteDest.getHistoriqueTransactions().add(t);
    }

    public String getIdClient() { return idClient; }
    public HashMap<String, CompteBancaire> getComptes() { return comptes; }
}