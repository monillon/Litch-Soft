package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminPathologyModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminPathologyController {

    private Main main;
    private AdminPathologyModel adminPathologyModel;
    @FXML private Button home;
    @FXML private TextField newPathoName;
    @FXML private TextField newMutationName;
    @FXML private Text errorPatho;
    @FXML private Text errorMutation;
    @FXML private Button addPathoButton;
    @FXML private Button addMutationButton;
    @FXML private ChoiceBox pathoList;


    public AdminPathologyController(Main newMain){
        main = newMain;
        adminPathologyModel = new AdminPathologyModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPatho() throws IOException {
        adminPathologyModel.addNewPatho(newPathoName, addPathoButton, errorPatho);
    }

    public void setPathoList() throws IOException {
        adminPathologyModel.setPathoList(pathoList);
    }


    public void addNewMutation() throws IOException {
        adminPathologyModel.addNewMutation(newMutationName, pathoList, addMutationButton, errorMutation);
    }


}
