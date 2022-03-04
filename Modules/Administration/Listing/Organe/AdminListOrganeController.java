package Modules.Administration.Listing.Organe;

import LITCH.Main;
import LITCH.Organ;
import LITCH.Tissue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListOrganeController {

    private Main main;
    private AdminListOrganeModel adminListOrganModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorOrgane;
    @FXML private Text textDetails;

    public AdminListOrganeController(Main newMain){
        main = newMain;
        adminListOrganModel = new AdminListOrganeModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListOrganModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorOrgane.setText("");
        textDetails.setText("");
        adminListOrganModel.removeItemList(laListe, (Organ) laListe.getSelectionModel().getSelectedItem(), errorOrgane);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorOrgane.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewOrgan(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Tissu/NewTissu/AdminNewTissu.fxml", main.getAdminNewTissuController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


    public void getDetails() throws IOException {
        textDetails.setText("");
        if (laListe.getSelectionModel().getSelectedItem() != null) {
            adminListOrganModel.showDetails((Organ) laListe.getSelectionModel().getSelectedItem(), textDetails);
        } else {
            textDetails.setText("Aucun élément n'a été sélectionné");
        }

    }

}
