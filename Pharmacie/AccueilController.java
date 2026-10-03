package Pharmacie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    public void openMedicament(ActionEvent event) throws Exception {
        naviguerVers(event, "/view/Medicament.fxml", "Gestion des Médicaments", 900, 620);
    }

    @FXML
    public void openClient(ActionEvent event) throws Exception {
        naviguerVers(event, "/view/Client.fxml", "Gestion des Clients Fidèles", 900, 620);
    }

    private void naviguerVers(ActionEvent event, String fxml, String titre, double largeur, double hauteur) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, largeur, hauteur));
        stage.setTitle(titre);
        stage.setResizable(false);
        stage.centerOnScreen();
    }
}
