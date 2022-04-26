package Modules.Administration.Listing.DonneesPreOp.NewPreOp;

import LITCH.DataBase;
import LITCH.Main;
import javafx.event.ActionEvent;
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


    public void addNewPreOp(TextField newPreOpName, Button addPreOpButton, Text errorPreOp) throws IOException {
        if (!newPreOpName.getText().isEmpty()) {
            DataBase.createPreopDataRequest(main.tools.spaceToUnderscore(newPreOpName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addPreOpButton.getScene().getWindow(), "Administration/Listing/DonneesPreOp/AdminListPreOp.fxml", main.getAdminListPreOpController());
            main.getAdminListPreOpController().addElementList();

            //tracabilité
            main.tools.applyTraceability(newPreOpName.getText().toUpperCase() + " à été ajouté des données pré-opératoire");


        }else{
            errorPreOp.setText("Veuillez renseigner le nom de la nouvelle donnée pré-opératoire");
        }
    }

}
