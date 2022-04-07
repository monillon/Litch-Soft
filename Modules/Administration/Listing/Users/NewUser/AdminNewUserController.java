package Modules.Administration.Listing.Users.NewUser;

import LITCH.DataBase;
import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewUserController {


    private Main main;
    private AdminNewUserModel adminUserModel;
    @FXML private Button home;
    @FXML private TextField newUserName;
    @FXML private TextField newUserPassword;
    @FXML private Button addUserButton;
    @FXML private Text errorUser;
    @FXML private ChoiceBox roles;


    public AdminNewUserController(Main newMain){
        main = newMain;
        adminUserModel = new AdminNewUserModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void applyRoles() throws IOException {
        for (JSONObject object : DataBase.getAllRoles()){
            roles.getItems().add(object.getString("NOM_ROLE"));
        }
    }

    public void addNewUser() throws IOException, SQLException {
        adminUserModel.addNewUser(newUserName, newUserPassword, roles, addUserButton, errorUser);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

}
