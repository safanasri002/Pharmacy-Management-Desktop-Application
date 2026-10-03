package Pharmacie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    public void openMedicament() throws Exception {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/view/Medicament.fxml")),
                900, 620
        ));
        stage.setTitle("Gestion des Médicaments");
        stage.setMinWidth(760);
        stage.setMinHeight(480);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void openClient() throws Exception {
        Stage stage = new Stage();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/view/Client.fxml")),
                900, 620
        ));
        stage.setTitle("Gestion des Clients Fidèles");
        stage.setMinWidth(760);
        stage.setMinHeight(480);
        stage.centerOnScreen();
        stage.show();
    }
}
