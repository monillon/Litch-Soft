package Modules.Administration.Listing.Users;

import LITCH.Main;
import LITCH.User;
import Modules.Administration.Listing.Users.NewUser.AdminNewUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListUserController {

    private Main main;
    private AdminListUserModel AdminListUserModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorUsers;
    @FXML private Text textDetails;

    public AdminListUserController(Main newMain){
        main = newMain;
        AdminListUserModel = new AdminListUserModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        AdminListUserModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
            errorUsers.setText("");
        textDetails.setText("");
        AdminListUserModel.removeItemList(laListe, (User) laListe.getSelectionModel().getSelectedItem(), errorUsers);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorUsers.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewUser(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Users/NewUser/AdminNewUser.fxml", main.getAdminNewUserController());
        main.getAdminNewUserController().applyRoles();
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }



}
