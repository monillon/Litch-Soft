package Modules.Administration.Listing.Unit;

import LITCH.Main;
import LITCH.Unit;
import Modules.Administration.Listing.Unit.AdminListUnitModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListUnitController {

    private Main main;
    private AdminListUnitModel adminListUnitModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorUnit;
    @FXML private Text textDetails, textDetailsPrelev;

    public AdminListUnitController(Main newMain){
        main = newMain;
        adminListUnitModel = new AdminListUnitModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListUnitModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorUnit.setText("");
        textDetails.setText("");
        textDetailsPrelev.setText("");
        adminListUnitModel.removeItemList(laListe, (Unit) laListe.getSelectionModel().getSelectedItem(), errorUnit);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorUnit.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewUnit(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Unit/NewUnit/AdminNewUnit.fxml", main.getAdminNewUnitController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


    public void getDetails() throws IOException {
        textDetails.setText("");
        textDetailsPrelev.setText("");
        if (laListe.getSelectionModel().getSelectedItem() != null) {
            adminListUnitModel.showDetails((Unit) laListe.getSelectionModel().getSelectedItem(), textDetails, textDetailsPrelev);
        } else {
            textDetails.setText("Aucun élément n'a été sélectionné");
        }

    }

}
