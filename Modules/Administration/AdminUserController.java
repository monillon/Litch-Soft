package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import Modules.Administration.AdminUserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class AdminUserController {


    private Main main;
    private AdminUserModel adminUserModel;
    @FXML private Button home;
    @FXML private TextField newUserName;
    @FXML private TextField newUserPassword;
    @FXML private Button addUserButton;
    @FXML private Text errorUser;
    @FXML private ChoiceBox roles;


    public AdminUserController(Main newMain){
        main = newMain;
        adminUserModel = new AdminUserModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void setRoles() throws IOException {
        for (JSONObject object : DataBase.getAllRoles()){
            roles.getItems().add(object.getString("NOM_ROLE"));
        }
    }

    public void addNewUser() throws IOException, SQLException {
        adminUserModel.addNewUser(newUserName, newUserPassword, roles, addUserButton, errorUser);
    }

}
