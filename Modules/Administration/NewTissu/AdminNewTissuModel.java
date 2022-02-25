package Modules.Administration.NewTissu;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewTissuModel {

    private Main main;

    public AdminNewTissuModel(Main newMain){
        main = newMain;
    }


    public void addNewTissue(TextField newTissueName, Button addTissueButton, Text errorTissue) throws IOException {
        if (!newTissueName.getText().isEmpty()) {
            DataBase.createTissueRequest(main.tools.spaceToUnderscore(newTissueName.getText()));
            main.tools.switchScene((Stage) addTissueButton.getScene().getWindow(), "Administration/AdminListTissu.fxml", main.getAdminListTissuController());
            main.getAdminListTissuController().addElementList();

        }else{
            errorTissue.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
