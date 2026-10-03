package Pharmacie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	System.out.println("start");
        Scene scene = new Scene(
            FXMLLoader.load(getClass().getResource("/view/Accueil.fxml")),
            520, 420
        );

        stage.setTitle("Gestion de Pharmacie");
        stage.setScene(scene);
        stage.setMinWidth(480);
        stage.setMinHeight(380);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // lance JavaFX
    }
}
