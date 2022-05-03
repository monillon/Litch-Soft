package Modules.Administration.Listing.Tissu.NewTissu;

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
            DataBase.createTissueRequest(main.tools.spaceToUnderscore(newTissueName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addTissueButton.getScene().getWindow(), "Administration/Listing/Tissu/AdminListTissu.fxml", main.getAdminListTissuController());
            main.getAdminListTissuController().addElementList();

            //traçabilité
            main.tools.applyTraceability(newTissueName.getText().toUpperCase() + " a été ajouté aux tissus");

        }else{
            errorTissue.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
