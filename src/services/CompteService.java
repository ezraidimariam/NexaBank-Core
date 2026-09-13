package services;

import exception.CompteInexistantException;
import model.Client;
import model.Compte;
import model.TypeCompte;

public class CompteService {

    public Compte creerCompte(Client client, double soldeInitial, TypeCompte type) {
        Compte nouveauCompte = new Compte(soldeInitial, type);
        client.getComptes().put(nouveauCompte.getNumeroCompte(), nouveauCompte);
        return nouveauCompte;
    }

    public void cloturerCompte(Client client, String numeroCompte) throws CompteInexistantException {
        if (!client.getComptes().containsKey(numeroCompte)) {
            throw new CompteInexistantException("Le compte " + numeroCompte + " n'existe pas pour ce client.");
        }
        client.getComptes().remove(numeroCompte);
    }
}