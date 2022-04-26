package Modules.Administration.Listing.Traceability;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AdminListTraceabilityController {

    private Main main;
    private AdminListTraceabilityModel adminListTraceabilityModel;


    @FXML
    private Button home;
    @FXML private ListView laListe;

    public AdminListTraceabilityController(Main newMain){
        main = newMain;
        adminListTraceabilityModel = new AdminListTraceabilityModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException, ParseException {
        adminListTraceabilityModel.addItemList(laListe);
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


}
