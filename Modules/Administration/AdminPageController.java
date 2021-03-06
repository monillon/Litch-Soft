package Modules.Administration;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AdminPageController {

    private Main main;
    @FXML private Button home;


    public AdminPageController(Main newMain){
        main = newMain;
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void goToListTissu(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Tissu/AdminListTissu.fxml", main.getAdminListTissuController());
        main.getAdminListTissuController().addElementList();
    }

    public void goToListOrgan(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Organe/AdminListOrgane.fxml", main.getAdminListOrganeController());
        main.getAdminListOrganeController().addElementList();
    }

    public void goToListPreopData(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/DonneesPreOp/AdminListPreOp.fxml", main.getAdminListPreOpController());
        main.getAdminListPreOpController().addElementList();
    }
  
    public void goToListUnit(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Unit/AdminListUnit.fxml", main.getAdminListUnitController());
        main.getAdminListUnitController().addElementList();
    }

    public void goToListPathology(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Pathologie/AdminListPathologie.fxml", main.getAdminListPathologieController());
        main.getAdminListPathologieController().addElementList();
    }

    public void goToListPrescription(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Prescription/AdminListPrescription.fxml", main.getAdminListPrescriptionController());
        main.getAdminListPrescriptionController().addElementList();
    }

    public void goToListCatDeManip(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/CatDeManip/AdminListCategorieManip.fxml", main.getAdminListCategorieManipController());
        main.getAdminListCategorieManipController().addElementList();
    }

    public void goToListMutation(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Mutation/AdminListMutation.fxml", main.getAdminListMutationController());
        main.getAdminListMutationController().addElementList();
    }

    public void goToListTechnic(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Technique/AdminListTechnic.fxml", main.getAdminListTechnicController());
        main.getAdminListTechnicController().addElementList();
    }

    public void goToListUser(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Users/AdminListUsers.fxml", main.getAdminListUserController());
        main.getAdminListUserController().addElementList();
    }

    public void goToListProtocole(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Protocole/AdminListProtocole.fxml", main.getAdminListProtocoleController());
        main.getAdminListProtocoleController().addElementList();
    }

    public void goToTraceability(ActionEvent e) throws IOException, ParseException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/Listing/Traceability/AdminListTraceability.fxml", main.getAdminListTraceabilityController());
        main.getAdminListTraceabilityController().addElementList();
    }
}
