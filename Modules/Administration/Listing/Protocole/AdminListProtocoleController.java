package Modules.Administration.Listing.Protocole;

import LITCH.Main;
import LITCH.Protocole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListProtocoleController {

    private Main main;
    private AdminListProtocoleModel adminListProtocoleModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorProtocole;

    public AdminListProtocoleController(Main newMain){
        main = newMain;
        adminListProtocoleModel = new AdminListProtocoleModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListProtocoleModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorProtocole.setText("");
        adminListProtocoleModel.removeItemList(laListe, (Protocole) laListe.getSelectionModel().getSelectedItem(), errorProtocole);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorProtocole.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewProtocole(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Protocole/NewProtocole/AdminNewProtocole.fxml", main.getAdminNewProtocoleController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }



}
