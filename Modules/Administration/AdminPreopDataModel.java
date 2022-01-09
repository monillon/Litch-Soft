package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPreopDataModel {

    private Main main;

    public AdminPreopDataModel(Main newMain){
        main = newMain;
    }

    public void addNewPreopData(TextField newPreopDataName, Button addPreopDataButton, Text errorPreopData) throws IOException {
        if (!newPreopDataName.getText().isEmpty()) {
            DataBase.createPreopDataRequest(main.tools.spaceToUnderscore(newPreopDataName.getText()));
            main.tools.switchScene((Stage) addPreopDataButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorPreopData.setText("Veuillez renseigner le nom du type de donnée");
        }
    }

    public void addNewUnite(TextField newUniteName, TextField newUniteDescription,Button addNewUniteButton, Text errorUnite) throws IOException {
        if (!newUniteName.getText().isEmpty() && !newUniteDescription.getText().isEmpty()) {
            DataBase.createUniteRequest(newUniteName.getText(),main.tools.spaceToUnderscore(newUniteDescription.getText()));
            main.tools.switchScene((Stage) addNewUniteButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorUnite.setText("Veuillez renseigner le nom et la description de l'unité");
        }
    }
}
