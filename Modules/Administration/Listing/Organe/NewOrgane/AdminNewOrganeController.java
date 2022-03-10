package Modules.Administration.Listing.Organe.NewOrgane;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewOrganeController {

    private Main main;
    private AdminNewOrganeModel adminNewOrganeModel;
    @FXML private Button home;
    @FXML private Button addOrganeButton;
    @FXML private Text errorOrgane;
    @FXML private TextField newOrganeName;

    public AdminNewOrganeController(Main newMain){
        main = newMain;
        adminNewOrganeModel = new AdminNewOrganeModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewOrgane() throws IOException {
        adminNewOrganeModel.addNewOrgane(newOrganeName, addOrganeButton, errorOrgane);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewOrgane();
        }
    }

}
