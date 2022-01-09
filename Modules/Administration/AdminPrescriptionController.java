package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminPrescriptionModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminPrescriptionController {

    private Main main;
    private AdminPrescriptionModel adminPrescriptionModel;
    @FXML private Button home;
    @FXML private TextField newPrescriName;
    @FXML private Button addPrescriButton;
    @FXML private Text errorPrescri;

    public AdminPrescriptionController(Main newMain){
        main = newMain;
        adminPrescriptionModel = new AdminPrescriptionModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPrescri() throws IOException {
        adminPrescriptionModel.addNewPrescri(newPrescriName, addPrescriButton, errorPrescri);
    }


}
