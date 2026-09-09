package model;

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
            System.out.println("Aucun compte associé.");
        } else {
            for (CompteBancaire compte : comptes.values()) {
                System.out.println(" - Compte N° " + compte.getNumeroCompte() + " (" + compte.getTypeCompte() + ") : " + compte.getSolde() + " DH");
            }
        }
    }

    public void effectuerDepot(String numeroCompte, double montant) {
        CompteBancaire compte = comptes.get(numeroCompte);
        if (compte != null) {
            compte.deposer(montant);
            System.out.println("Dépôt de " + montant + " DH effectué sur le compte " + numeroCompte);
        } else {
            System.out.println("Compte introuvable.");
        }
    }

    public void effectuerRetrait(String numeroCompte, double montant) {
        CompteBancaire compte = comptes.get(numeroCompte);
        if (compte != null) {
            compte.retirer(montant);
            System.out.println("Retrait de " + montant + " DH effectué sur le compte " + numeroCompte);
        } else {
            System.out.println("Compte introuvable.");
        }
    }

    public void effectuerVirement(String numSource, CompteBancaire compteDest, double montant) {
        CompteBancaire source = comptes.get(numSource);
        if (source != null && compteDest != null) {
            if (source.getSolde() >= montant) {
                source.retirer(montant);
                compteDest.deposer(montant);
                source.ajouterTransaction(new Transaction("VIREMENT_SORTANT", montant));
                compteDest.ajouterTransaction(new Transaction("VIREMENT_ENTRANT", montant));
                System.out.println("Virement de " + montant + " DH effectué avec succès.");
            } else {
                System.out.println("Solde insuffisant pour le virement.");
            }
        } else {
            System.out.println("Compte source ou destination introuvable.");
        }
    }

    public String getIdClient() { return idClient; }
    public HashMap<String, CompteBancaire> getComptes() { return comptes; }
}