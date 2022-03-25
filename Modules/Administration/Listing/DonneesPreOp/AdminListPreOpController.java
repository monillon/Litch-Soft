package Modules.Administration.Listing.DonneesPreOp;

import LITCH.Main;
import LITCH.PreopData;
//import LITCH.Tissue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListPreOpController {

    private Main main;
    private AdminListPreOpModel adminListPreOpModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorPreOp;

    public AdminListPreOpController(Main newMain){
        main = newMain;
        adminListPreOpModel = new AdminListPreOpModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListPreOpModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorPreOp.setText("");
        adminListPreOpModel.removeItemList(laListe, (PreopData) laListe.getSelectionModel().getSelectedItem(), errorPreOp);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorPreOp.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewPreOp(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/DonneesPreOp/NewPreOp/AdminNewPreOp.fxml", main.getAdminNewPreOpController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }

}
