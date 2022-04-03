package Modules.Administration.Listing.CatDeManip;

import LITCH.Main;
import LITCH.ManipCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListCategorieManipController {

    private Main main;
    private AdminListCategorieManipModel AdminListCategorieManipModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorCatDeManip;

    public AdminListCategorieManipController(Main newMain){
        main = newMain;
        AdminListCategorieManipModel = new AdminListCategorieManipModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        AdminListCategorieManipModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorCatDeManip.setText("");
        AdminListCategorieManipModel.removeItemList(laListe, (ManipCategory) laListe.getSelectionModel().getSelectedItem(), errorCatDeManip);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorCatDeManip.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewCatDeManip(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/CatDeManip/NewCatManip/AdminNewCategorieManip.fxml", main.getAdminNewCategorieManipController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }
    

}
