package Modules.Administration.Listing.Users.NewUser;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewUserModel {

    private Main main;


    public AdminNewUserModel(Main newMain){
        main = newMain;
    }

    public void addNewUser(TextField newUserName, TextField newUserPassword, ChoiceBox roles, Button addUserButton, Text errorUser) throws IOException, SQLException {
        if (!newUserName.getText().isEmpty() && !newUserPassword.getText().isEmpty() && roles.getSelectionModel().getSelectedItem() != null) {
            boolean verif = true;
            for (JSONObject object : DataBase.getAllUserRequest()) {
                if (newUserName.getText().equals(object.getString("NOM_UTILISATEUR"))){
                    verif = false;
                }
            }
            if (verif){
                int id_role = 0;
                for(JSONObject object : DataBase.getOneRoles(roles.getValue().toString())){
                    id_role = object.getInt("ID_ROLE");
                }
                DataBase.createUserRequest(newUserName.getText(),newUserPassword.getText(), id_role);

                //traçabilité
                main.tools.applyTraceability(newUserName.getText().toUpperCase() + " à été ajouté aux utilisateurs avec le role" + roles.getValue().toString().toUpperCase());

                main.tools.switchScene((Stage) addUserButton.getScene().getWindow(),"Administration/AdminPage.fxml",main.getAdminPageController());
            }else{
                errorUser.setText("Nom d'utilisateur déjà utilisé");
            }
        }else{
            errorUser.setText("Veuillez remplir tous les champs");
        }
    }
}
