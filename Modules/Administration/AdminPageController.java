package Modules.Administration;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminPageController {

    private Main main;
    @FXML private Button home;


    public AdminPageController(Main newMain){
        main = newMain;
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void goToOrgan(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminOrgan.fxml", main.getAdminOrganController());
    }

    public void goToPathology(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminPathology.fxml", main.getAdminPathologyController());
        main.getAdminPathologyController().setPathoList();
    }

    public void goToPreopData(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminPreopData.fxml", main.getAdminPreopDataController());
    }

    public void goToPrescri(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminPrescription.fxml", main.getAdminPrescriptionController());
    }

    public void goToUser(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminUser.fxml", main.getAdminUserController());
        main.getAdminUserController().setRoles();
    }

    public void goToManipulation(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        main.tools.switchScene((Stage) button.getScene().getWindow(), "Administration/AdminManipulation.fxml", main.getAdminManipulationController());
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

}
