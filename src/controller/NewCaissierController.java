package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import modeles.Caissier;
import services.SimulationData;
import utils.Utiles;

public class NewCaissierController {

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField txtTempsMoyen;

    @FXML
    private Button btnSaveCaissier;

    /**
     * Verification des champs puis enregistrement du nouveau caissier
     *
     * @param event
     */
    @FXML
    void NewCaissier(ActionEvent event) {
        String tempsMoyen = txtTempsMoyen.getText().trim();

        if (tempsMoyen.equals("")) {
            Utiles.showMessage("SIMULATION BANQUE", "NOUVEAU Caissier", "Veuillez remplir les champs !!");
        } else {
            int temps = Utiles.convertToInt(tempsMoyen);
            try {
                if (temps == Integer.MIN_VALUE) {
                    Utiles.showMessage("SIMULATION BANQUE", "NOUVEAU Caissier", "Veuillez saisir un nombre entier (min) !!!");
                } else {
                    SimulationData.addCaissier(new Caissier(temps));
                    Utiles.showMessage("SIMULATION BANQUE", "NOUVEAU Caissier", "Ajout avec succes !!!");
                    initialise();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Utiles.showMessage("VICH SHOP", "NOUVEL Profil", "Echec d'jout !!!");
            }

        }
    }

    /**
     * initialise tous les champs du caissier
     */
    void initialise() {
        txtTempsMoyen.setText("");
    }


}
