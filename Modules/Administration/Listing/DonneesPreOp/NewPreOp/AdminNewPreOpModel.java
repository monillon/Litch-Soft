package Modules.Administration.Listing.DonneesPreOp.NewPreOp;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewPreOpModel {

    private Main main;

    public AdminNewPreOpModel(Main newMain){
        main = newMain;
    }


    public void addNewTissue(TextField newTissueName, Button addTissueButton, Text errorTissue) throws IOException {
        if (!newTissueName.getText().isEmpty()) {
            DataBase.createTissueRequest(main.tools.spaceToUnderscore(newTissueName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addTissueButton.getScene().getWindow(), "Administration/Listing/Tissu/AdminListTissu.fxml", main.getAdminListTissuController());
            main.getAdminListTissuController().addElementList();

        }else{
            errorTissue.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
