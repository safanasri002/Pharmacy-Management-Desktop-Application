package Pharmacie;

import java.util.List;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController {

    @FXML private TextField txtCin, txtNom, txtPrenom, txtCredit;
    @FXML private ListView<ClientFidele> listClient;
    
    @FXML private TableView<ClientFidele> tableClient;
    @FXML private TableColumn<ClientFidele, Integer> colCin;
    @FXML private TableColumn<ClientFidele, String> colNom;
    @FXML private TableColumn<ClientFidele, String> colPrenom;
    @FXML private TableColumn<ClientFidele, Double> colCredit;
    @FXML private Label lblCount;

    
    private final GestionClient gc = new GestionClient();

    /*@FXML
    public void initialize() {
        refresh();
    }*/
    
    @FXML
    public void initialize() {
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));

        Label vide = new Label("Aucun client enregistré");
        vide.getStyleClass().add("muted");
        tableClient.setPlaceholder(vide);

        // clic sur une ligne : le formulaire se remplit pour modifier ou supprimer
        tableClient.getSelectionModel().selectedItemProperty().addListener((obs, ancien, sel) -> {
            if (sel != null) remplirFormulaire(sel);
        });
        refresh(); }

    
/*
    @FXML
    public void ajouter() {
        try {
            ClientFidele c = new ClientFidele(
                Integer.parseInt(txtCin.getText()),
                txtNom.getText(),
                txtPrenom.getText(),
                Double.parseDouble(txtCredit.getText())
            );
            gc.ajouterClient(c);
            refresh();
        } catch (Exception e) {
            AlertUtil.erreur("Erreur de saisie du client");
        }
    }
    */
    
    @FXML
    public void ajouter() {
        try {
            ClientFidele c = new ClientFidele(
                Integer.parseInt(txtCin.getText()),
                txtNom.getText(),
                txtPrenom.getText(),
                Double.parseDouble(txtCredit.getText())
            );
            if (gc.ajouterClient(c)) {
                AlertUtil.info("Client « " + c.getPrenom() + " " + c.getNom() + " » enregistré.");
                viderFormulaire();
                refresh();
            } else {
                AlertUtil.erreur("Impossible d'enregistrer le client (CIN déjà existant ?).");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.erreur("Erreur de saisie client");
        }
    }


    @FXML
    public void supprimer() {
        try {
            int cin = Integer.parseInt(txtCin.getText());
            ClientFidele c = gc.rechercherClient(cin);
            if (c == null) {
                AlertUtil.erreur("Client inexistant");
                return;
            }
            if (gc.supprimerClient(cin)) {
                AlertUtil.info("Client « " + c.getPrenom() + " " + c.getNom() + " » supprimé.");
                viderFormulaire();
                refresh();
            } else {
                AlertUtil.erreur("Impossible de supprimer le client (voir la console).");
            }
        } catch (NumberFormatException e) {
            AlertUtil.erreur("Veuillez saisir un CIN valide");
        }
    }
    
    
    /*

    @FXML
    public void rechercher() {
        ClientFidele c = gc.rechercherClient(Integer.parseInt(txtCin.getText()));
        if (c == null)
            AlertUtil.erreur("Client non trouvé");
        else
            listClient.setItems(
                    FXCollections.observableArrayList(c)
            );
    }
    */

    @FXML
    public void rechercher() {
        try {
            int cin = Integer.parseInt(txtCin.getText());
            ClientFidele c = gc.rechercherClient(cin);

            if (c == null) {
                AlertUtil.erreur("Client non trouvé");
                tableClient.setItems(FXCollections.observableArrayList());
            } else {
                tableClient.setItems(
                    FXCollections.observableArrayList(c)
                );
            }
        } catch (NumberFormatException e) {
            AlertUtil.erreur("Veuillez saisir un CIN valide");
        }
    }

    
   /* private void refresh() {
        listClient.setItems(
                FXCollections.observableArrayList(gc.getAllClient())
        );
    }*/
    
    private void refresh() {
        List<ClientFidele> liste = gc.getAllClient();
        tableClient.setItems(FXCollections.observableArrayList(liste));
        lblCount.setText(liste.size() + (liste.size() > 1 ? " clients" : " client"));
    }

    private void remplirFormulaire(ClientFidele c) {
        txtCin.setText(String.valueOf(c.getCin()));
        txtNom.setText(c.getNom());
        txtPrenom.setText(c.getPrenom());
        txtCredit.setText(String.valueOf(c.getCredit()));
    }

    private void viderFormulaire() {
        txtCin.clear();
        txtNom.clear();
        txtPrenom.clear();
        txtCredit.clear();
        tableClient.getSelectionModel().clearSelection();
    }

    @FXML
    public void retour(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 520, 420));
        stage.setTitle("Gestion de Pharmacie");
        stage.setResizable(false);
        stage.centerOnScreen();
    }

}
