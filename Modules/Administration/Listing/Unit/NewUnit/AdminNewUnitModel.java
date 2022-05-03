package Modules.Administration.Listing.Unit.NewUnit;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewUnitModel {

    private Main main;

    public AdminNewUnitModel(Main newMain){
        main = newMain;
    }


    public void addNewUnit(TextField newUnitName, TextField newUnitDescription, Button addUnitButton, Text errorUnit) throws IOException {
        if (!newUnitName.getText().isEmpty()) {
            DataBase.createUniteRequest(main.tools.spaceToUnderscore(newUnitName.getText().toUpperCase()),main.tools.spaceToUnderscore(newUnitDescription.getText().toUpperCase()));
            main.tools.switchScene((Stage) addUnitButton.getScene().getWindow(), "Administration/Listing/Unit/AdminListUnit.fxml", main.getAdminListUnitController());
            main.getAdminListUnitController().addElementList();

            //traçabilité
            main.tools.applyTraceability(newUnitName.getText().toUpperCase() + " a été ajouté aux unités");

        }else{
            errorUnit.setText("Veuillez renseigner le nom d'une unité");
        }
    }

}
