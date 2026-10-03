package Pharmacie;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

public class AlertUtil {

    public static void erreur(String message) {
        afficher(Alert.AlertType.ERROR, "alert-error", "Une erreur est survenue", message);
    }

    public static void info(String message) {
        afficher(Alert.AlertType.INFORMATION, "alert-info", "Opération réussie", message);
    }

    private static void afficher(Alert.AlertType type, String variante, String entete, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Erreur" : "Succès");
        alert.setHeaderText(entete);
        alert.setContentText(message);
        alert.setGraphic(null);

        // même feuille de style que l'application, pour une charte cohérente
        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(AlertUtil.class.getResource("/view/style.css").toExternalForm());
        pane.getStyleClass().addAll("app-alert", variante);

        // la boîte s'ouvre centrée sur la fenêtre active
        Window fenetre = Window.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
        alert.initOwner(fenetre);

        alert.showAndWait();
    }
}
