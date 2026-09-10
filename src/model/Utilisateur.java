package model;

public abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;

    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public boolean login(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }

    public void logout() {
        System.out.println("Déconnexion réussie pour : " + nom + " " + prenom);
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
}
