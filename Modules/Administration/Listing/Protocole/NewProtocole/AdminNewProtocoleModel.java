package Modules.Administration.Listing.Protocole.NewProtocole;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewProtocoleModel {

    private Main main;

    public AdminNewProtocoleModel(Main newMain){
        main = newMain;
    }


    public void addNewProtocole(TextField newProtocoleName,Button addProtocoleButton, Text errorProtocole) throws IOException {
        if (!newProtocoleName.getText().isEmpty()) {
            DataBase.createProtocoleRequest(main.tools.spaceToUnderscore(newProtocoleName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addProtocoleButton.getScene().getWindow(), "Administration/Listing/Protocole/AdminListProtocole.fxml", main.getAdminListProtocoleController());
            main.getAdminListProtocoleController().addElementList();

            //traçabilité
            main.tools.applyTraceability(newProtocoleName.getText().toUpperCase() + " a été ajouté aux protocoles");

        }else{
            errorProtocole.setText("Veuillez renseigner le nom d'un protocole");
        }
    }

}
