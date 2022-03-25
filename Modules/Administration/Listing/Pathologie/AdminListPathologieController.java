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
    @FXML private Text errorPatho;

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
        errorPatho.setText("");
        adminListTissuModel.removeItemList(laListe, (Pathology) laListe.getSelectionModel().getSelectedItem(), errorPatho);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorPatho.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewPatho(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Pathologie/NewPathologie/AdminNewPathologie.fxml", main.getAdminNewPathologieController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }

}
