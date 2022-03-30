package Modules.Administration.Listing.Prescription;

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

public class AdminListPrescriptionController {

    private Main main;
    private AdminListPrescriptionModel adminListPrescriptionModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorPrescription;

    public AdminListPrescriptionController(Main newMain){
        main = newMain;
        adminListPrescriptionModel = new AdminListPrescriptionModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListPrescriptionModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        errorPrescription.setText("");
        adminListPrescriptionModel.removeItemList(laListe, (Tissue) laListe.getSelectionModel().getSelectedItem(), errorPrescription);
        laListe.getSelectionModel().clearSelection();
        } else {
            errorPrescription.setText("Aucun élément n'a été sélectionné");
        }
    }

    public void addNewPrescription(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Prescription/NewPrescription/AdminPrescription.fxml", main.getAdminPrescriptionController());
    }

    public void goBackSettings(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
    }

}
