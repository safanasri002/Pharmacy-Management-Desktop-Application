package Pharmacie;

import java.text.Normalizer;
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

public class MedicamentController {

    @FXML private TextField txtCode, txtNom, txtCategorie, txtType, txtPrix, txtQuantite;
    @FXML private TableView<Medicament> tableMedicament;
    @FXML private TableColumn<Medicament, Long> colCode;
    @FXML private TableColumn<Medicament, String> colNom;
    @FXML private TableColumn<Medicament, Double> colPrix;
    @FXML private TableColumn<Medicament, Integer> colQuantite;
    @FXML private Label lblCount;

    private final GestionMedicament gm = new GestionMedicament();

    
    /*
    @FXML
    public void initialize() {
        colCode.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCode()));
        colNom.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getNom()));
        colPrix.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getPrix()));
        colQuantite.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getQuantite()));

        refresh();
    }
    
	*/
    
    @FXML
    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        Label vide = new Label("Aucun médicament enregistré");
        vide.getStyleClass().add("muted");
        tableMedicament.setPlaceholder(vide);

        // clic sur une ligne : le formulaire se remplit pour modifier ou supprimer
        tableMedicament.getSelectionModel().selectedItemProperty().addListener((obs, ancien, sel) -> {
            if (sel != null) remplirFormulaire(sel);
        });

        refresh();
    }


    @FXML
    public void ajouter() {
        try {
            // le type doit être "chimique" ou "homeopathique" (accents et casse ignorés)
            String typeNormalise = Normalizer.normalize(txtType.getText().trim(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();
            if (!typeNormalise.equals("chimique") && !typeNormalise.equals("homeopathique")) {
                AlertUtil.erreur("Le type doit être « Chimique » ou « Homéopathique ».");
                return;
            }

            Medicament m = new MedicamentChimique(
                Long.parseLong(txtCode.getText()),
                txtCategorie.getText(),
                txtNom.getText(),
                typeNormalise,
                Double.parseDouble(txtPrix.getText()),
                0L,
                java.time.LocalDate.now().plusMonths(6),
                Integer.parseInt(txtQuantite.getText())
            );
            if (gm.ajouterMedicament(m)) {
                AlertUtil.info("Médicament « " + m.getNom() + " » enregistré.");
                viderFormulaire();
                refresh();
            } else {
                AlertUtil.erreur("Impossible d'enregistrer le médicament (voir la console).");
            }
        } catch (Exception e) {
            AlertUtil.erreur("Données invalides pour le médicament");
        }
    }

    @FXML
    public void supprimer() {
        try {
            Medicament m = gm.rechercherMedicament(
                    Long.parseLong(txtCode.getText())
            );
            if (gm.supprimerMedicament(m)) {
                AlertUtil.info("Médicament « " + m.getNom() + " » supprimé.");
                viderFormulaire();
                refresh();
            } else {
                AlertUtil.erreur("Impossible de supprimer le médicament (voir la console).");
            }
        } catch (Exception e) {
            AlertUtil.erreur("Médicament introuvable");
        }
    }

    @FXML
    public void rechercher() {
        try {
            Medicament m = gm.rechercherMedicament(
                    Long.parseLong(txtCode.getText())
            );
            tableMedicament.setItems(
                    FXCollections.observableArrayList(m)
            );
        } catch (Exception e) {
            AlertUtil.erreur("Aucun médicament trouvé");
        }
    }

    private void refresh() {
        List<Medicament> liste = gm.getAllMedicaments();
        tableMedicament.setItems(FXCollections.observableArrayList(liste));
        lblCount.setText(liste.size() + (liste.size() > 1 ? " médicaments" : " médicament"));
    }

    private void remplirFormulaire(Medicament m) {
        txtCode.setText(String.valueOf(m.getCode()));
        txtNom.setText(m.getNom());
        txtCategorie.setText(m.getCategorie());
        txtType.setText(m.getType());
        txtPrix.setText(String.valueOf(m.getPrix()));
        txtQuantite.setText(String.valueOf(m.getQuantite()));
    }

    private void viderFormulaire() {
        txtCode.clear();
        txtNom.clear();
        txtCategorie.clear();
        txtType.clear();
        txtPrix.clear();
        txtQuantite.clear();
        tableMedicament.getSelectionModel().clearSelection();
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
