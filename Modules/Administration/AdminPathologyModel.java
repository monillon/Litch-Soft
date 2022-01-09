package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Pathology;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class AdminPathologyModel {

    private Main main;

    public AdminPathologyModel(Main newMain){
        main = newMain;
    }


    public void addNewPatho(TextField newPathoName, Button addPathoButton, Text errorPatho) throws IOException {
        if (!newPathoName.getText().isEmpty()) {
            DataBase.createPathoRequest(main.tools.spaceToUnderscore(newPathoName.getText()));
            main.tools.switchScene((Stage) addPathoButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorPatho.setText("Veuillez renseigner le nom d'une pathologie");
        }
    }

    public void setPathoList(ChoiceBox pathoList) throws IOException {
        for (Pathology patho : main.tools.getAllPatho()){
            pathoList.getItems().add(patho.getNamePathology());
        }
    }


    public void addNewMutation(TextField newMutationName, ChoiceBox pathoList, Button addMutationButton, Text errorMutation) throws IOException {
        if (!newMutationName.getText().isEmpty() && pathoList.getValue() != null) {
            int id_patho = 0;
            for (JSONObject object : DataBase.getAllPathoRequest()) {
                if (object.getString("NOM_PATHOLOGIE").equals(pathoList.getValue())){
                    id_patho = object.getInt("ID_PATHOLOGIE");
                }
            }
            DataBase.createMutationRequest(id_patho,main.tools.spaceToUnderscore(newMutationName.getText()));
            main.tools.switchScene((Stage) addMutationButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorMutation.setText("Veuillez renseigner le nom d'une mutation");
        }
    }

}
