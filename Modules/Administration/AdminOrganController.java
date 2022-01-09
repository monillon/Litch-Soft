package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminOrganModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminOrganController {

    private Main main;
    private AdminOrganModel adminOrganModel;
    @FXML private Button home;
    @FXML private TextField newOrganName;
    @FXML private Button addOrganButton;
    @FXML private Button addTissueButton;
    @FXML private Text errorOrgan;
    @FXML private Text errorTissue;
    @FXML private TextField newTissueName;

    public AdminOrganController(Main newMain){
        main = newMain;
        adminOrganModel = new AdminOrganModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewOrgan() throws IOException {
        adminOrganModel.addNewOrgan(newOrganName, addOrganButton, errorOrgan);
    }


    public void addNewTissue() throws IOException {
        adminOrganModel.addNewTissue(newTissueName, addTissueButton, errorTissue);
    }

}
