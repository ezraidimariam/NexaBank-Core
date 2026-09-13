package services;

import model.Personne;
import java.util.ArrayList;

public class AuthService {
    public static ArrayList<Personne> users = new ArrayList<>();

    public Personne authentifier(String email, String motDePasse) {
        for (int i = 0; i < users.size(); i++) {
            Personne p = users.get(i);
            if (p.getEmail().equalsIgnoreCase(email) && p.getMotDePasse().equals(motDePasse)) {
                return p;
            }
        }
        return null;
    }
}