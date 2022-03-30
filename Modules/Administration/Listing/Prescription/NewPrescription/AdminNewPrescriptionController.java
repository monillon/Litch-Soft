package Modules.Administration.Listing.Prescription.NewPrescription;

import LITCH.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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


}
