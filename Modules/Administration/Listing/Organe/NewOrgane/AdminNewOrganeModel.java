package Modules.Administration.Listing.Organe.NewOrgane;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewOrganeModel {

    private Main main;

    public AdminNewOrganeModel(Main newMain){
        main = newMain;
    }


    public void addNewOrgane(TextField newOrganeName, Button addOrganeButton, Text errorOrgane) throws IOException {
        if (!newOrganeName.getText().isEmpty()) {
            DataBase.createOrganRequest(main.tools.spaceToUnderscore(newOrganeName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addOrganeButton.getScene().getWindow(), "Administration/Listing/Organe/AdminListOrgane.fxml", main.getAdminListOrganeController());
            main.getAdminListOrganeController().addElementList();

            //tracabilité
            main.tools.applyTraceability(newOrganeName.getText().toUpperCase() + " à été ajouté aux organes");


        }else{
            errorOrgane.setText("Veuillez renseigner le nom d'un tissu");
        }
    }

}
