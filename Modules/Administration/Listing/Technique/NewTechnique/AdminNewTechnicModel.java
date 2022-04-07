package Modules.Administration.Listing.Technique.NewTechnique;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewTechnicModel {

    private Main main;

    public AdminNewTechnicModel(Main newMain){
        main = newMain;
    }


    public void addNewTechnic(TextField newTechnicName, TextField newTechnicDescription, Button addTechnicButton, Text errorTechnic) throws IOException {
        if (!newTechnicName.getText().isEmpty()) {
            DataBase.createTechniqueRequest(main.tools.spaceToUnderscore(newTechnicName.getText().toUpperCase()),main.tools.spaceToUnderscore(newTechnicDescription.getText().toUpperCase()));
            main.tools.switchScene((Stage) addTechnicButton.getScene().getWindow(), "Administration/Listing/Technique/AdminListTechnic.fxml", main.getAdminListTechnicController());
            main.getAdminListTechnicController().addElementList();

        }else{
            errorTechnic.setText("Veuillez renseigner le nom d'une technique");
        }
    }

}
