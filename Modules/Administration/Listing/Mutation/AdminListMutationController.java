package Modules.Administration.Listing.Mutation;

import LITCH.Main;
import LITCH.Mutation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListMutationController {

    private Main main;
    private AdminListMutationModel adminListMutationModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorMutation;

    public AdminListMutationController(Main newMain){
        main = newMain;
        adminListMutationModel = new AdminListMutationModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListMutationModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorMutation.setText("");
        adminListMutationModel.removeItemList(laListe, (Mutation) laListe.getSelectionModel().getSelectedItem(), errorMutation);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorMutation.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewMutation(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Mutation/NewMutation/AdminNewMutation.fxml", main.getAdminNewMutationController());
        main.getAdminNewMutationController().fillPatho();
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }

}
