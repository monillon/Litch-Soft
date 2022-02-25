package Modules.Administration.NewTissu;

import LITCH.Main;
import Modules.Administration.AdminOrganModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewTissuController {

    private Main main;
    private AdminNewTissuModel adminNewTissuModel;
    @FXML private Button home;
    @FXML private Button addTissueButton;
    @FXML private Text errorTissue;
    @FXML private TextField newTissueName;

    public AdminNewTissuController(Main newMain){
        main = newMain;
        adminNewTissuModel = new AdminNewTissuModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewTissue() throws IOException {
        adminNewTissuModel.addNewTissue(newTissueName, addTissueButton, errorTissue);
    }

}
