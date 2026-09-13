import model.*;
import services.*;
import exception.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        ClientService clientService = new ClientService();
        CompteService compteService = new CompteService();
        TransactionService transactionService = new TransactionService();

        // 1. Initialisation des données de test
        Client client1 = new Client("C101", "Benali", "Karim", "karim@email.com", "pass123");
        Gestionnaire admin = new Gestionnaire("G001", "El Amrani", "Youssef", "admin@email.com", "admin123");

        AuthService.users.add(client1);
        AuthService.users.add(admin);

        Compte cCourant = compteService.creerCompte(client1, 2000.0, TypeCompte.COURANT);
        Compte cEpargne = compteService.creerCompte(client1, 5000.0, TypeCompte.EPARGNE);

        System.out.println("=================================");
        System.out.println("      BIENVENUE À NEXABANK       ");
        System.out.println("=================================");

        // 2. Authentification
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String pass = scanner.nextLine();

        Personne user = authService.authentifier(email, pass);

        if (user == null) {
            System.out.println("Email ou mot de passe incorrect !");
            return;
        }

        // 3. Espace Client
        if (user instanceof Client) {
            Client clientConnecte = (Client) user;
            System.out.println("\nConnexion réussie ! Bonjour " + clientConnecte.getPrenom() + " " + clientConnecte.getNom());

            boolean continuer = true;
            while (continuer) {
                System.out.println("\n--- MENU CLIENT ---");
                System.out.println("1. Consulter mes comptes");
                System.out.println("2. Effectuer un dépôt");
                System.out.println("3. Effectuer un retrait");
                System.out.println("4. Effectuer un virement");
                System.out.println("5. Consulter un relevé (.txt)");
                System.out.println("6. Quitter");
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choix) {
                        case 1:
                            for (Compte c : clientConnecte.getComptes().values()) {
                                System.out.println("Compte N° : " + c.getNumeroCompte() + " | Type : " + c.getTypeCompte() + " | Solde : " + c.getSolde() + " €");
                            }
                            break;

                        case 2:
                            System.out.print("Montant à déposer sur compte courant : ");
                            double depot = scanner.nextDouble();
                            transactionService.effectuerDepot(cCourant, depot);
                            System.out.println("Nouveau solde courant : " + cCourant.getSolde() + " €");
                            break;

                        case 3:
                            System.out.print("Montant à retirer du compte courant : ");
                            double retrait = scanner.nextDouble();
                            transactionService.effectuerRetrait(cCourant, retrait);
                            System.out.println("Nouveau solde courant : " + cCourant.getSolde() + " €");
                            break;

                        case 4:
                            System.out.print("Montant à transférer (Courant -> Épargne) : ");
                            double virement = scanner.nextDouble();
                            transactionService.effectuerVirement(cCourant, cEpargne, virement);
                            System.out.println("Virement réussi !");
                            break;

                        case 5:
                            System.out.print("Entrez le numéro du compte (ex: " + cCourant.getNumeroCompte() + ") : ");
                            String numCompte = scanner.nextLine();
                            transactionService.afficherReleveFichier(numCompte);
                            break;

                        case 6:
                            continuer = false;
                            System.out.println("Au revoir !");
                            break;

                        default:
                            System.out.println("Option invalide !");
                    }
                } catch (MontantNegatifException | SoldeInsuffisantException | CompteInexistantException | IOException e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            }
        }
        // 4. Espace Gestionnaire
        else if (user instanceof Gestionnaire) {
            System.out.println("\nConnexion réussie ! Espace Gestionnaire.");
            System.out.println("--- Liste des Clients ---");
            clientService.afficherClients();

            System.out.print("\nEntrez le numéro du compte client à consulter (.txt) : ");
            String numCompte = scanner.nextLine();

            try {
                transactionService.afficherReleveFichier(numCompte);
            } catch (CompteInexistantException | IOException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
    }
}