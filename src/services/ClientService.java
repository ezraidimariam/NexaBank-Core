package services;

import model.Client;
import model.Personne;
import java.util.ArrayList;

public class ClientService {
    private ArrayList<Personne> clientsDb = LoginService.users;

    // عرض جميع العملاء
    public void afficherClients() {
        for (Personne p : clientsDb) {
            if (p instanceof Client) {
                Client client = (Client) p;
                System.out.println("ID: " + client.getIdClient()
                        + " | Nom: " + client.getNom()
                        + " " + client.getPrenom()
                        + " | Email: " + client.getEmail());
            }
        }
    }

    // البحث عن العميل بالـ ID بأمان تام
    public Client trouverParId(String idClient) {
        for (Personne p : clientsDb) {
            if (p instanceof Client) {
                Client client = (Client) p;
                if (client.getIdClient().equalsIgnoreCase(idClient)) {
                    return client;
                }
            }
        }
        return null; // إذا لم يجد العميل
    }

    // تحديث البيانات بأمان
    public void mettreAJourInfos(String idClient, String nouveauNom, String nouveauPrenom, String nouveauEmail) {
        Client client = trouverParId(idClient);
        if (client != null) {
            client.setNom(nouveauNom);
            client.setPrenom(nouveauPrenom);
            client.setEmail(nouveauEmail);
        }
    }
}