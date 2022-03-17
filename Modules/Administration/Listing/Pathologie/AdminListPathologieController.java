package Modules.Administration.Listing.Pathologie;

import LITCH.Main;
import LITCH.Pathology;
import LITCH.Tissue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListPathologieController {

    private Main main;
    private AdminListPathologieModel adminListTissuModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorTissu;
    @FXML private Text textDetails, textDetailsPrelev;

    public AdminListPathologieController(Main newMain){
        main = newMain;
        adminListTissuModel = new AdminListPathologieModel(newMain);
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
        textDetailsPrelev.setText("");
        adminListTissuModel.removeItemList(laListe, (Pathology) laListe.getSelectionModel().getSelectedItem(), errorTissu);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorTissu.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewTissu(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Pathologie/NewPathologie/AdminNewPathologie.fxml", main.getAdminNewPathologieController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }


    public void getDetails() throws IOException {
        textDetails.setText("");
        textDetailsPrelev.setText("");
        if (laListe.getSelectionModel().getSelectedItem() != null) {
            adminListTissuModel.showDetails((Tissue) laListe.getSelectionModel().getSelectedItem(), textDetails, textDetailsPrelev);
        } else {
            textDetails.setText("Aucun élément n'a été sélectionné");
        }

    }

}
