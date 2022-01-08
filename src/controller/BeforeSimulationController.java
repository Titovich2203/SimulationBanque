package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modeles.Caissier;
import services.SimulationData;
import utils.LoadView;
import utils.Utiles;

import java.io.IOException;

public class BeforeSimulationController {

    @FXML
    private Button btnNewCaissier;

    @FXML
    private Button btnStartSimulation;

    @FXML
    private TableView<Caissier> tableCaissier;

    @FXML
    private TableColumn<Caissier, Integer> tdIdCaissier;

    @FXML
    private TableColumn<Caissier, String> tdTempsMoyenService;

    @FXML
    private TextField txtDureeSimulation;

    @FXML
    private TextField txtTempsMoyen;

    /**
     * permet d'ouvrir un popup pour l'ajout de nouveaux caissiers avant la simulation
     * @param event
     * @throws IOException
     */
    @FXML
    void NewCaissier(ActionEvent event) throws IOException {
        Utiles.showPopup("NOUVEAU CAISSIER", "Cliquez sur ok apres l'ajout", "FormNewCaissier.fxml");
        actualiserTab();
    }

    /**
     * ouverture de la fenetre de lancement de la simulation proprement dite apres les verifications des donnees saisies
     * @param event
     */
    @FXML
    void StartSimulation(ActionEvent event) {

        if (txtDureeSimulation.getText().trim().equals("") || txtTempsMoyen.getText().trim().equals("")) {
            Utiles.showMessage("SIMULATION BANQUE", "Nouvelle Simulation", "Veuillez remplir les champs !!");
        } else {
            int dureeEstime = Utiles.convertToInt(txtDureeSimulation.getText().trim());
            int tempsMoyen = Utiles.convertToInt(txtTempsMoyen.getText().trim());
            if (tempsMoyen <= 0 || dureeEstime <= 0) {
                Utiles.showMessage("SIMULATION BANQUE", "Nouvelle Simulation", "Veuillez saisir uniquement des nombres entiers > 0 (min) !!!");
            } else {
                if (SimulationData.getCaissiers().size() <= 0) {
                    Utiles.showMessage("SIMULATION BANQUE", "Nouvelle Simulation", "Veuillez ajouter au moins un caissier !!!");
                } else {
                    SimulationData.dureeSimulation = dureeEstime;
                    SimulationData.tempsMoyenEntreDeuxArrives = tempsMoyen;
                    LoadView.showView("SIMULATION BANQUE", "FormSimulation.fxml", 1);
                }
            }
        }

    }

    /**
     * rafraichit la table des caissiers en y affichant les nouveaux caissiers ajoutés
     */
    public void actualiserTab() {
        tdIdCaissier.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        tdTempsMoyenService.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTempsMoyenService() + " min"));
        try {
            tableCaissier.setItems(FXCollections.observableArrayList(SimulationData.getCaissiers()));
        } catch (Exception e) {
            Utiles.showMessage("Gestion des caissiers", "LISTE DES CAISSIERS", "ERREUR " + e);
        }
    }
}
