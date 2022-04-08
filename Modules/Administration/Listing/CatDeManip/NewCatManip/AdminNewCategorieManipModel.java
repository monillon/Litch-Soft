package Modules.Administration.Listing.CatDeManip.NewCatManip;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewCategorieManipModel {

    private Main main;

    public AdminNewCategorieManipModel(Main newMain){
        main = newMain;
    }


    public void addNewCatDeManip(TextField newNameCatDeManip, Button addCatDeManipButton, Text errorCatDeManip) throws IOException {
        if (!newNameCatDeManip.getText().isEmpty()) {
            DataBase.createCatDeManipRequest(main.tools.spaceToUnderscore(newNameCatDeManip.getText().toUpperCase()));
            main.tools.switchScene((Stage) addCatDeManipButton.getScene().getWindow(), "Administration/Listing/CatDeManip/AdminListCategorieManip.fxml", main.getAdminListCategorieManipController());
            main.getAdminListCategorieManipController().addElementList();

        }else{
            errorCatDeManip.setText("Veuillez renseigner le nom de la catégorie de manipulation");
        }
    }

}
