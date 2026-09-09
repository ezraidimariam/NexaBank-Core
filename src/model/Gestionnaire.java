package model;

import exception.BankException;
import utils.FileLogger;

public class Gestionnaire extends Utilisateur {
    private String idGestionnaire;

    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }

    public void creerCompte(Client client, CompteBancaire compte) {
        client.getComptes().put(compte.getNumeroCompte(), compte);
        System.out.println("Compte " + compte.getNumeroCompte() + " créé et attribué à " + client.getIdClient());
    }

    public void modifierCompte(CompteBancaire compte) {
        System.out.println("Compte " + compte.getNumeroCompte() + " mis à jour.");
    }

    public boolean consulterLogs() {
        System.out.println("Consultation des logs système...");
        return true;
    }

    public float consulter_solde(CompteBancaire compte) {
        return (float) compte.getSolde();
    }

    public void consulterReleveClient(String numCompte) {
        try {
            FileLogger.afficherReleve(numCompte);
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getIdGestionnaire() { return idGestionnaire; }
}
