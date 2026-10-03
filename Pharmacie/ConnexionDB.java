package Pharmacie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class ConnexionDB {
    // Ordre de priorité : variable d'environnement > fichier .env > valeur par défaut
    private static final Map<String, String> ENV = chargerEnv(".env");

    private static final String URL = valeur("DB_URL", "jdbc:postgresql://localhost:5432/pharmacie_db");
    private static final String USER = valeur("DB_USER", "postgres");
    private static final String PASSWORD = valeur("DB_PASSWORD", "postgres");

    private static String valeur(String cle, String parDefaut) {
        String env = System.getenv(cle);
        if (env != null) return env;
        return ENV.getOrDefault(cle, parDefaut);
    }

    private static Map<String, String> chargerEnv(String fichier) {
        Map<String, String> map = new HashMap<>();
        try {
            for (String ligne : Files.readAllLines(Paths.get(fichier))) {
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("#") || !ligne.contains("=")) continue;
                int i = ligne.indexOf('=');
                map.put(ligne.substring(0, i).trim(), ligne.substring(i + 1).trim());
            }
        } catch (IOException e) {
            // pas de fichier .env : on utilise les variables d'environnement ou les valeurs par défaut
        }
        return map;
    }

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
