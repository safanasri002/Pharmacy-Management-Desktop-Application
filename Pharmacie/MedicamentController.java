package Pharmacie;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicamentController {

    @FXML private TextField txtCode, txtNom, txtCategorie, txtType, txtPrix, txtQuantite;
    @FXML private TableView<Medicament> tableMedicament;
    @FXML private TableColumn<Medicament, Long> colCode;
    @FXML private TableColumn<Medicament, String> colNom;
    @FXML private TableColumn<Medicament, Double> colPrix;
    @FXML private TableColumn<Medicament, Integer> colQuantite;

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

        refresh();
    }


    @FXML
    public void ajouter() {
        try {
            Medicament m = new MedicamentChimique(
                Long.parseLong(txtCode.getText()),
                txtCategorie.getText(),
                txtNom.getText(),
                txtType.getText(),
                Double.parseDouble(txtPrix.getText()),
                0L,
                java.time.LocalDate.now().plusMonths(6),
                Integer.parseInt(txtQuantite.getText())
            );
            gm.ajouterMedicament(m);
            refresh();
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
            gm.supprimerMedicament(m);
            refresh();
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
        tableMedicament.setItems(
                FXCollections.observableArrayList(gm.getAllMedicaments())
        );
    }
}
