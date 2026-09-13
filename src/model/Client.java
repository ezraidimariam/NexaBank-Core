package model;

import java.util.HashMap;

public class Client extends Personne {
    private String idClient;
    private HashMap<String, Compte> comptes;

    public Client(String idClient, String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.idClient = idClient;
        this.comptes = new HashMap<>();
    }

    public String getIdClient() { return idClient; }
    public HashMap<String, Compte> getComptes() { return comptes; }
}