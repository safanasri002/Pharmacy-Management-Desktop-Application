package Pharmacie;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDB {
    // Valeurs par défaut pour le développement local ; surchargeables via
    // les variables d'environnement DB_URL / DB_USER / DB_PASSWORD
    // pour éviter de committer un vrai mot de passe.
    private static final String URL = System.getenv().getOrDefault(
            "DB_URL", "jdbc:postgresql://localhost:5432/pharmacie_db");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        if (ConnexionDB.getConnection() != null)
            System.out.println("Connexion etablie !");
        else
            System.out.println("Erreur de connexion !");
    }
}

