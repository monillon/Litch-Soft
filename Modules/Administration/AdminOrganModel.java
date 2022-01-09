package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminOrganModel {

    private Main main;

    public AdminOrganModel(Main newMain){
        main = newMain;
    }


    public void addNewOrgan(TextField newOrganName, Button addOrganButton, Text errorOrgan) throws IOException {
        if (!newOrganName.getText().isEmpty()) {
            DataBase.createOrganRequest(main.tools.spaceToUnderscore(newOrganName.getText()));
            main.tools.switchScene((Stage) addOrganButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorOrgan.setText("Veuillez renseigner le nom d'un organe");
        }
    }


    public void addNewTissue(TextField newTissueName, Button addTissueButton, Text errorTissue) throws IOException {
        if (!newTissueName.getText().isEmpty()) {
            DataBase.createTissueRequest(main.tools.spaceToUnderscore(newTissueName.getText()));
            main.tools.switchScene((Stage) addTissueButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorTissue.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
