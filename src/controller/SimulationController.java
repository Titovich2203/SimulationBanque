package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import modeles.Caissier;
import modeles.Client;
import services.SimulationData;
import utils.LoadView;
import utils.Utiles;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    @FXML
    private Button btnStartSimulation;

    @FXML
    private Button btnAccelererSimulation;

    @FXML
    private HBox donneesHbox;

    @FXML
    private ImageView imgSimulation;

    @FXML
    private Label txtCaissiersDisponible;

    @FXML
    private Label txtNbClientEnTrainDetreServi;

    @FXML
    private Label txtClientEnAttente;

    @FXML
    private Label txtDureeTotale;

    @FXML
    private Label txtNbClientsServis;

    @FXML
    private Label txtTempsEcoule;

    @FXML
    private Label txtTempsRestant;


    @FXML
    private TableView<Caissier> tableCaissier;

    @FXML
    private TableColumn<Caissier, String> tdNumCaissier;

    @FXML
    private TableColumn<Caissier, String> tdNumClient;

    @FXML
    private TableColumn<Caissier, Integer> tdNumClientServi;

    @FXML
    private TableColumn<Caissier, String> tdTempsMoyenService;


    int dureeSimulationSecondes = SimulationData.dureeSimulation * 60;
    int tempsMoyenEntreDeuxArrivesSecondes = SimulationData.tempsMoyenEntreDeuxArrives * 60;
    int tempsEcoule = 0;
    int tempsRestant = dureeSimulationSecondes;
    List<Integer> nbClientsEnFileDattente = new ArrayList<>();

    int vitesseSimulation = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/view/img/load.gif");
        Image image = new Image(file.toURI().toString());
        imgSimulation.setImage(image);
        btnAccelererSimulation.setVisible(false);
        txtDureeTotale.setText(intToHour(dureeSimulationSecondes));
        refreshTab();
    }

    /**
     * lancement de la simulation proprement dit grace à un thread
     *
     * @param event
     * @throws InterruptedException
     */
    @FXML
    void lancerSimulation(ActionEvent event) throws InterruptedException {
        btnStartSimulation.setVisible(false);
        btnAccelererSimulation.setText("ACCELERER LA SIMULATION " + vitesseSimulation + "x");
        btnAccelererSimulation.setVisible(true);


        Thread th = new Thread() {
            @Override
            public void run() {
                while (tempsRestant > 0 || SimulationData.getNbClientsEnAttente() > 0 || SimulationData.getNbClientsEnService() > 0) {
                    tempsRestant--;
                    tempsEcoule++;

                    //Ajout d'un nouveau client chaque temps moyen entre deux arrivées successives de clients
                    if (tempsEcoule % tempsMoyenEntreDeuxArrivesSecondes == 0 && tempsRestant > 0) {
                        if (SimulationData.addClient(new Client(tempsEcoule), tempsEcoule)) refreshTab();
                    }

                    //Verifier si la duree du temps moyen de service d'un caissier est arrivee
                    if (SimulationData.verifierFinService(tempsEcoule)) refreshTab();

                    //Si un caissier est dispo, on lui attribue un des clients en attente
                    if (SimulationData.getNbCaissiersDispo() > 0) {
                        if (SimulationData.traiterFileDattente(tempsEcoule)) refreshTab();
                    }

                    // Mise à jour des données en temps réel
                    Platform.runLater(() -> txtTempsRestant.setText(intToHour(tempsRestant)));
                    Platform.runLater(() -> txtTempsEcoule.setText(intToHour(tempsEcoule)));
                    Platform.runLater(() -> txtNbClientsServis.setText(SimulationData.getNbClientsServi() + ""));
                    Platform.runLater(() -> txtClientEnAttente.setText(SimulationData.getNbClientsEnAttente() + ""));
                    Platform.runLater(() -> txtNbClientEnTrainDetreServi.setText(SimulationData.getNbClientsEnService() + ""));
                    Platform.runLater(() -> txtCaissiersDisponible.setText(SimulationData.getStrCaissiersDispo()));
                    try {
                        Thread.sleep(1000 / vitesseSimulation);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SimulationData.tempsEcoule = tempsEcoule;
                SimulationData.calculerMoyenneFileDattente(nbClientsEnFileDattente);
                Platform.runLater(() -> Utiles.showMessage("SIMULATION BANQUE", "Simulation terminée", "La simulation s'est déroulée avec succès !!!"));
                Platform.runLater(() -> LoadView.showView("SIMULATION TERMINEE", "FormAfterSimulation.fxml", 1));

            }
        };
        th.start();
    }

    /**
     * rafraichit la table des caissiers en y affichant les données en temps reel
     */
    void refreshTab() {
        tableCaissier.getItems().clear();
        tdNumCaissier.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>("CAISSIER " + cellData.getValue().getId()));
        tdNumClient.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getClientActuel() != null ? "CLIENT " + cellData.getValue().getClientActuel().getId() : "AUCUN CLIENT"));
        tdNumClientServi.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNbCientServi()));
        tdTempsMoyenService.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTempsMoyenService() + " min"));
        try {
            tableCaissier.setItems(FXCollections.observableArrayList(SimulationData.getCaissiers()));
        } catch (Exception e) {
            Utiles.showMessage("Gestion des caissiers", "LISTE DES CAISSIERS", "ERREUR " + e);
        }
        nbClientsEnFileDattente.add(SimulationData.getNbClientsEnAttente());
    }

    /**
     * permet d'augmenter la vitesse de la simulation ( 20 x plus vite au maximum)
     * @param event
     */
    @FXML
    void accelererSimulation(ActionEvent event) {
        if (vitesseSimulation < 20) {
            vitesseSimulation++;
        } else {
            vitesseSimulation = 1;
        }
        btnAccelererSimulation.setText("ACCELERER LA SIMULATION " + vitesseSimulation + "x");
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
}
