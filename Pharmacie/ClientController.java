package Pharmacie;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController {

    @FXML private TextField txtCin, txtNom, txtPrenom, txtCredit;
    @FXML private ListView<ClientFidele> listClient;
    
    @FXML private TableView<ClientFidele> tableClient;
    @FXML private TableColumn<ClientFidele, Integer> colCin;
    @FXML private TableColumn<ClientFidele, String> colNom;
    @FXML private TableColumn<ClientFidele, String> colPrenom;
    @FXML private TableColumn<ClientFidele, Double> colCredit;

    
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
            gc.ajouterClient(c);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.erreur("Erreur de saisie client");
        }
    }

    
    @FXML
    public void supprimer() {
        try {
            gc.supprimerClient(Integer.parseInt(txtCin.getText()));
            refresh();
        } catch (Exception e) {
            AlertUtil.erreur("Client inexistant");
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
        tableClient.setItems(
            FXCollections.observableArrayList(gc.getAllClient())
        );
    }

}
