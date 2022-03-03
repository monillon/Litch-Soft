package Modules.Administration.Listing.Tissu;

import LITCH.Main;
import LITCH.Tissue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListTissuController {

    private Main main;
    private AdminListTissuModel adminListTissuModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorTissu;
    @FXML private Text textDetails;

    public AdminListTissuController(Main newMain){
        main = newMain;
        adminListTissuModel = new AdminListTissuModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListTissuModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorTissu.setText("");
        textDetails.setText("");
        adminListTissuModel.removeItemList(laListe, (Tissue) laListe.getSelectionModel().getSelectedItem(), errorTissu);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorTissu.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewTissu(ActionEvent e) throws IOException {
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
            adminListTissuModel.showDetails((Tissue) laListe.getSelectionModel().getSelectedItem(), textDetails);
        } else {
            textDetails.setText("Aucun élément n'a été sélectionné");
        }

    }

}
