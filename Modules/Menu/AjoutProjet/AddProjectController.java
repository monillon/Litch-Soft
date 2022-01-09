package Modules.Menu.AjoutProjet;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AddProjectController {

    @FXML private Button home;
    @FXML private TextField projectCode;
    @FXML private TextField fullNameProject;
    @FXML private Button addProjectButton;
    @FXML private Button addImg;
    @FXML private Text verif;

    private Main main;
    private AddProjectModel addProjectModel;
    private String selectedFileName;


    public AddProjectController(Main newMain){
        main = newMain;
        addProjectModel = new AddProjectModel(newMain);
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }


    public void addNewProjectBDD() throws SQLException, IOException {
        if (!projectCode.getText().isEmpty() && !fullNameProject.getText().isEmpty()) {
            if (projectCode.getText().length() <= 8) {

                addProjectModel.addNewProjectBDD(projectCode.getText(), fullNameProject.getText(), selectedFileName, addImg.getText(), addProjectButton, verif);
            }else {
                verif.setText("Le code du projet entré est trop long (8 caractères maximum)");
            }
        }else {
            verif.setText("Veuillez remplir tous les champs");
        }
    }

    public void uploadImg(ActionEvent event) throws IOException {
        addProjectModel.uploadImg(addImg, selectedFileName);
    }


}
