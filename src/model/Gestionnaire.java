package model;

import exception.CompteInexistantException;

public class Gestionnaire extends Utilisateur {
    private String idGestionnaire;

    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }

    public void creerCompte(Client client, CompteBancaire compte) {
        if (client != null && compte != null) {
            client.getComptes().put(compte.getNumeroCompte(), compte);
            System.out.println("Compte " + compte.getNumeroCompte() + " créé pour " + client.getNom());
        }
    }

    public void cloturerCompte(Client client, String numeroCompte) throws CompteInexistantException {
        if (client == null || !client.getComptes().containsKey(numeroCompte)) {
            throw new CompteInexistantException("Impossible de clôturer : compte introuvable.");
        }
        client.getComptes().remove(numeroCompte);
        System.out.println("Compte " + numeroCompte + " clôturé avec succès.");
    }

    public void modifierClient(Client client, String nom, String prenom, String email) {
        if (client != null) {
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);
            System.out.println("Informations du client mises à jour.");
        }
    }

    public String getIdGestionnaire() { return idGestionnaire; }
}