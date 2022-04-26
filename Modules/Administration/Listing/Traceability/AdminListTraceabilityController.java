package Modules.Administration.Listing.Traceability;

import LITCH.Main;
import LITCH.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AdminListTraceabilityController {

    private Main main;
    private AdminListTraceabilityModel adminListTraceabilityModel;


    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private ComboBox choixUser;

    public AdminListTraceabilityController(Main newMain){
        main = newMain;
        adminListTraceabilityModel = new AdminListTraceabilityModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException, ParseException {
        adminListTraceabilityModel.addUsers(choixUser);
        adminListTraceabilityModel.addItemList(laListe, null);
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


    public void filterSelection() throws IOException, ParseException {
        if (choixUser.getSelectionModel().getSelectedItem().toString().equals("Tous les utilisateurs")) {
            adminListTraceabilityModel.addItemList(laListe, null);
        } else {
            User theUser = (User) choixUser.getSelectionModel().getSelectedItem();
            adminListTraceabilityModel.addItemList(laListe, theUser);
        }
    }

}
