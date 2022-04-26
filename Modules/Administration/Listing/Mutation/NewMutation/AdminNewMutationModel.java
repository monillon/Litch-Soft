package Modules.Administration.Listing.Mutation.NewMutation;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Pathology;
import LITCH.Tools;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class AdminNewMutationModel {

    private Main main;

    public AdminNewMutationModel(Main newMain){
        main = newMain;
    }

    public void addNewMuta(ComboBox newPathoName, TextField newMutaName, TextField newClassName,  Button addMutaButton, Text errorMuta) throws IOException {
        if (!newMutaName.getText().isEmpty() && newPathoName.getSelectionModel().getSelectedItem() != null && !newClassName.getText().isEmpty()) {
            Pathology laPatho = (Pathology) newPathoName.getSelectionModel().getSelectedItem();
            DataBase.createMutationRequest(laPatho.getIdPathology(), main.tools.spaceToUnderscore(newMutaName.getText().toUpperCase()), main.tools.spaceToUnderscore(newClassName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addMutaButton.getScene().getWindow(),"Administration/Listing/Mutation/AdminListMutation.fxml",main.getAdminListMutationController());
            main.getAdminListMutationController().addElementList();

            //tracabilité
            main.tools.applyTraceability(newMutaName.getText().toUpperCase() + " à été ajouté aux mutations lié à la pathologie " + newPathoName.getSelectionModel().getSelectedItem().toString().toUpperCase());



        }else{
            errorMuta.setText("Veuillez renseigner une mutation, une pathologie et une classe");
        }
    }


    public void fillPatho(ComboBox lesPathos) throws IOException{
        for (JSONObject i : DataBase.getAllPathoRequest()) {
            lesPathos.getItems().add(new Pathology(i.getInt("ID_PATHOLOGIE"), Tools.underscoreToSpace(i.getString("NOM_PATHOLOGIE"))));
        }
    }
}
