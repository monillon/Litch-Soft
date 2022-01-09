package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminPreopDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminPreopDataController {

    private Main main;
    private AdminPreopDataModel adminPreopDataModel;
    @FXML private Button home;
    @FXML private TextField newPreopDataName;
    @FXML private TextField newUniteName;
    @FXML private TextField newUniteDescription;
    @FXML private Button addPreopDataButton;
    @FXML private Button addNewUniteButton;
    @FXML private Text errorPreopData;
    @FXML private Text errorUnite;


    public AdminPreopDataController(Main newMain){
        main = newMain;
        adminPreopDataModel = new AdminPreopDataModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPreopData() throws IOException {
        adminPreopDataModel.addNewPreopData(newPreopDataName, addPreopDataButton, errorPreopData);
    }

    public void addNewUnite() throws IOException {
        adminPreopDataModel.addNewUnite(newUniteName, newUniteDescription, addNewUniteButton, errorUnite);
    }
}
