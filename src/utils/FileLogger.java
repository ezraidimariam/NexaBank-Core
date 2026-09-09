package utils;

import exception.BankException;
import model.Transaction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;

public class FileLogger {
    private static final String RELEVE_DIR = "releves";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public static void enregistrerTransaction(String compte, Transaction transaction) throws BankException {
        try {
            Path dir = Path.of(RELEVE_DIR);
            Files.createDirectories(dir);
            Path file = dir.resolve(TRANSACTIONS_FILE);

            String line = String.format(
                "%s | %s | %.2f | %s | %s%n",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getDate()),
                transaction.getType(),
                transaction.getMontant(),
                compte,
                transaction.getCompteDestination() != null ? transaction.getCompteDestination() : "-"
            );

            Files.writeString(file, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new BankException("Impossible d'enregistrer la transaction : " + e.getMessage());
        }
    }

    public static void afficherReleve(String numCompte) throws BankException {
        try {
            Path file = Path.of(RELEVE_DIR, numCompte + ".txt");
            if (!Files.exists(file)) {
                throw new BankException("Relevé introuvable pour le compte " + numCompte);
            }
            System.out.println(Files.readString(file));
        } catch (IOException e) {
            throw new BankException("Erreur de lecture du relevé : " + e.getMessage());
        }
    }
}
