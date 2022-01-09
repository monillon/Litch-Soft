package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPrescriptionModel {

    private Main main;

    public AdminPrescriptionModel(Main newMain){
        main = newMain;
    }

    public void addNewPrescri(TextField newPrescriName, Button addPrescriButton, Text errorPrescri) throws IOException {
        if (!newPrescriName.getText().isEmpty()) {
            DataBase.createPrescriRequest(main.tools.spaceToUnderscore(newPrescriName.getText()));
            main.tools.switchScene((Stage) addPrescriButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorPrescri.setText("Veuillez renseigner le nom d'une prescription");
        }
    }
}
