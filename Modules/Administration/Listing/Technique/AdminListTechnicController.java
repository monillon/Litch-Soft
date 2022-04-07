package Modules.Administration.Listing.Technique;

import LITCH.Main;
import LITCH.Technic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListTechnicController {

    private Main main;
    private AdminListTechnicModel adminListTechnicModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorTechnic;
    @FXML private Text textDetails, textDetailsPrelev;

    public AdminListTechnicController(Main newMain){
        main = newMain;
        adminListTechnicModel = new AdminListTechnicModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListTechnicModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorTechnic.setText("");
        adminListTechnicModel.removeItemList(laListe, (Technic) laListe.getSelectionModel().getSelectedItem(), errorTechnic);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorTechnic.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewTechnic(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Technique/NewTechnique/AdminNewTechnic.fxml", main.getAdminNewTechnicController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


}
