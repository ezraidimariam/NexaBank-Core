package model;

public class Gestionnaire extends Utilisateur {
    private String idGestionnaire;

    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }

    public void creerCompte(Client client, CompteBancaire compte) {
        if (client != null && compte != null) {
            client.getComptes().put(compte.getNumeroCompte(), compte);
            System.out.println("Compte " + compte.getNumeroCompte() + " créé pour le client " + client.getNom());
        }
    }

    public void cloturerCompte(Client client, String numeroCompte) {
        if (client != null && client.getComptes().containsKey(numeroCompte)) {
            client.getComptes().remove(numeroCompte);
            System.out.println("Compte " + numeroCompte + " clôturé avec succès.");
        } else {
            System.out.println("Impossible de clôturer : compte introuvable.");
        }
    }

    public String getIdGestionnaire() { return idGestionnaire; }
}