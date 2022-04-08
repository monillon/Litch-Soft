package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminManipulationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminManipulationController {

    private Main main;
    private AdminManipulationModel adminManipulationModel;
    @FXML private Button home;
    @FXML private TextField newManipulationName;
    @FXML private Button addProtocoleButton;
    @FXML private TextField newTechniqueName;
    @FXML private Button addTechniqueButton;
    @FXML private ComboBox selectTechnique;
    @FXML private ComboBox selectProtocole;
    @FXML private Button addModeleButton;
    @FXML private Text errorModele;
    @FXML private Text errorTechnique;
    @FXML private Text errorProtocole;

    public AdminManipulationController(Main newMain){
        main = newMain;
        adminManipulationModel = new AdminManipulationModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }


    public void initialize() throws IOException {
        setTechniqueList();
        setProtocoleList();
    }

    public void addNewProtocole() throws IOException {
        adminManipulationModel.addNewProtocole(newManipulationName, addProtocoleButton, errorProtocole);
    }

//    public void addNewTechnique() throws IOException {
//        adminManipulationModel.addNewTechnique(newTechniqueName, addTechniqueButton, errorTechnique);
//    }


    public void setTechniqueList() throws IOException {
        adminManipulationModel.setTechniqueList(selectTechnique);
    }

    public void setProtocoleList() throws IOException {
        adminManipulationModel.setProtocoleList(selectProtocole);
    }


    public void addNewModele() throws IOException, SQLException {
        adminManipulationModel.addNewModele(selectTechnique, selectProtocole, addModeleButton, errorModele);
    }

}
