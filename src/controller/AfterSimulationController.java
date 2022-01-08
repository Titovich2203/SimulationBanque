package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import modeles.Caissier;
import modeles.Client;
import services.SimulationData;
import utils.Utiles;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class AfterSimulationController implements Initializable {

    @FXML
    private HBox donneesHbox;

    @FXML
    private HBox donneesHbox1;

    @FXML
    private TableView<Caissier> tableCaissier;

    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Caissier, String> tdNbClientServi;

    @FXML
    private TableColumn<Caissier, String> tdNumCaissier;

    @FXML
    private TableColumn<Client, String> tdNumClient;

    @FXML
    private TableColumn<Caissier, String> tdTauxOccupation;

    @FXML
    private TableColumn<Client, String> tdTempsAttente;

    @FXML
    private TableColumn<Client, String> tdTempsDevantCaissier;

    @FXML
    private TableColumn<Caissier, String> tdTempsMoyenService;

    @FXML
    private TableColumn<Caissier, String> tdTempsOccupation;

    @FXML
    private Label txtDureePrevue;

    @FXML
    private Label txtDureeTotale;

    @FXML
    private Label txtLongueurMaximaleFile;

    @FXML
    private Label txtLongueurMoyenneFile;

    @FXML
    private Label txtNbTotalClient;

    @FXML
    private Label txtTempsMoyenAttente;

    @FXML
    private Label txtTempsOccupation;

    /**
     * initialise tous les tableaux et labels avec les résultats de la simulation
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final DecimalFormat df = new DecimalFormat("0.00");

        txtDureePrevue.setText(intToHour(SimulationData.dureeSimulation * 60));
        txtDureeTotale.setText(intToHour(SimulationData.tempsEcoule));
        txtTempsOccupation.setText(intToHour((int) SimulationData.getTempsTotalOccupation()));
        txtLongueurMaximaleFile.setText(SimulationData.longueurMaximaleFileDattente + " Clients");
        txtLongueurMoyenneFile.setText(df.format(SimulationData.longueurMoyenneFileDattente) + " Clients");
        txtNbTotalClient.setText(SimulationData.getClients().size() + " Clients");
        txtTempsMoyenAttente.setText(intToHour(SimulationData.getTempsMoyenAttente()));

        refreshTabCaissier();
        refreshTabClients();

    }

    /**
     * permet de convertir un entier (Secondes) en heure (H : min : s)
     *
     * @param secondes
     * @return
     */
    public String intToHour(int secondes) {
        return (secondes / 3600) + "H : " + ((secondes % 3600) / 60) + "min : " + ((secondes % 3600) % 60) + " s";
    }

    /**
     * rafraichit la table des caissiers en y affichant les données actuelles
     */
    void refreshTabCaissier() {
        tableCaissier.getItems().clear();
        tdNumCaissier.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>("CAISSIER " + cellData.getValue().getId()));
        tdTempsMoyenService.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTempsMoyenService() + " min"));
        tdNbClientServi.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNbCientServi() + " Clients"));
        tdTempsOccupation.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(intToHour(cellData.getValue().getTempsOccupation())));
        tdTauxOccupation.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTauxOccupation() + " %"));
        try {
            tableCaissier.setItems(FXCollections.observableArrayList(SimulationData.getCaissiers()));
        } catch (Exception e) {
            Utiles.showMessage("Gestion des caissiers", "LISTE DES CAISSIERS", "ERREUR " + e);
        }
    }

    /**
     * rafraichit la table des clients en y affichant les données actuelles
     */
    void refreshTabClients() {
        tableClient.getItems().clear();
        tdNumClient.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>("CLIENT " + cellData.getValue().getId()));
        tdTempsAttente.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(intToHour(cellData.getValue().getHeureDebutService() - cellData.getValue().getHeureArrivee())));
        tdTempsDevantCaissier.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(intToHour(cellData.getValue().getHeureSortie() - cellData.getValue().getHeureDebutService())));
        try {
            tableClient.setItems(FXCollections.observableArrayList(SimulationData.getClients()));
        } catch (Exception e) {
            Utiles.showMessage("Gestion des caissiers", "LISTE DES CLIENTS", "ERREUR " + e);
        }
    }
}
