package Modules.Administration.Listing.Prescription.NewPrescription;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewPrescriptionController {

    private Main main;
    private AdminNewPrescriptionModel adminPrescriptionModel;
    @FXML private Button home;
    @FXML private TextField newPrescriName;
    @FXML private Button addPrescriButton;
    @FXML private Text errorPrescri;

    public AdminNewPrescriptionController(Main newMain){
        main = newMain;
        adminPrescriptionModel = new AdminNewPrescriptionModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPrescri() throws IOException {
        adminPrescriptionModel.addNewPrescri(newPrescriName, addPrescriButton, errorPrescri);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewPrescri();
        }
    }


}
