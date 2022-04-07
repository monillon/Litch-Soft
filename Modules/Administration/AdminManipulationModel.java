package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

public class AdminManipulationModel {

    private Main main;


    public AdminManipulationModel(Main newMain){
        main = newMain;
    }


    public void addNewProtocole(TextField newManipulationName, Button addProtocoleButton, Text errorProtocole) throws IOException {
        if (!newManipulationName.getText().isEmpty()) {
            DataBase.createProtocoleRequest(main.tools.spaceToUnderscore(newManipulationName.getText()));
            main.tools.switchScene((Stage) addProtocoleButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
        }else{
            errorProtocole.setText("Veuillez renseigner le nom du protocole");
        }
    }

//    public void addNewTechnique(TextField newTechniqueName, TextField newTechnicDescription, Button addTechniqueButton, Text errorTechnique) throws IOException {
//        if (!newTechniqueName.getText().isEmpty()) {
//            DataBase.createTechniqueRequest(main.tools.spaceToUnderscore(newTechniqueName.getText()),main.tools.spaceToUnderscore(newTechnicDescription.getText()));
//            main.tools.switchScene((Stage) addTechniqueButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
//        }else{
//            errorTechnique.setText("Veuillez renseigner le nom de la technique");
//        }
//    }


    public void setTechniqueList(ComboBox selectTechnique) throws IOException {
        selectTechnique.getItems().clear();
        for (JSONObject object : DataBase.getAllTechniqueRequest()) {
            selectTechnique.getItems().add(object.getString("NOM_TECHNIQUE"));
        }
    }

    public void setProtocoleList(ComboBox selectProtocole) throws IOException {
        selectProtocole.getItems().clear();
        for (JSONObject object : DataBase.getAllProtocoleRequest()) {
            selectProtocole.getItems().add(object.getString("NOM_PROTOCOLE"));
        }
    }


    public void addNewModele(ComboBox selectTechnique, ComboBox selectProtocole, Button addModeleButton, Text errorModele) throws IOException, SQLException {
        if (selectTechnique.getValue() != null &&
                selectProtocole.getValue() != null) {

            //get the id_technique
            int id_technique = 0;
            for (JSONObject object : DataBase.getAllTechniqueRequest()) {
                if (object.getString("NOM_TECHNIQUE").equals(selectTechnique.getValue().toString())){
                    id_technique = object.getInt("ID_TECHNIQUE");
                }
            }

            //get the id_protocole
            int id_protocole = 0;
            for (JSONObject object : DataBase.getAllProtocoleRequest()) {
                if (object.getString("NOM_PROTOCOLE").equals(selectProtocole.getValue().toString())){
                    id_protocole = object.getInt("ID_PROTOCOLE");
                }
            }

            String nom_modele_manip = selectProtocole.getValue().toString() + "-" + selectTechnique.getValue().toString();

            DataBase.createModeleManipRequest(nom_modele_manip,id_technique,id_protocole);


            main.tools.goHome(addModeleButton);
        } else {
            errorModele.setText("Veuillez selectionner les noms protocole et technique");
        }
    }
}
