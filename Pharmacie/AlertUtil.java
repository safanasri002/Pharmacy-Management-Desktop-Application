package Pharmacie;

import javafx.scene.control.Alert;

public class AlertUtil {

    public static void erreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
