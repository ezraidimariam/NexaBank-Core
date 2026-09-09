package model;

public class Gestionnaire extends Utilisateur {
    private final String idGestionnaire;

    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }

    public void creerCompte(Client client, CompteBancaire compte) {
        if (client != null && compte != null) {
            client.getComptes().put(compte.getNumeroCompte(), compte);
            System.out.println("Compte " + compte.getNumeroCompte() + " créé et attribué au client " + client.getIdClient());
        } else {
            System.out.println("Erreur : Client ou Compte invalide.");
        }
    }

    public String getIdGestionnaire() { return idGestionnaire; }
}