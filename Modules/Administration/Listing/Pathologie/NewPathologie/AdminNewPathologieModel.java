package Modules.Administration.Listing.Pathologie.NewPathologie;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewPathologieModel {

    private Main main;

    public AdminNewPathologieModel(Main newMain){
        main = newMain;
    }


    public void addNewPathology(TextField newPathologyName, Button addPathologyButton, Text errorPathology) throws IOException {
        if (!newPathologyName.getText().isEmpty()) {
            DataBase.createPathoRequest(main.tools.spaceToUnderscore(newPathologyName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addPathologyButton.getScene().getWindow(), "Administration/Listing/Pathologie/AdminListPathologie.fxml", main.getAdminListPathologieController());
            main.getAdminListPathologieController().addElementList();

        }else{
            errorPathology.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
