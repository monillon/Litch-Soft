package Modules.Administration.Listing.Unit.NewUnit;

import LITCH.Main;
import Modules.Administration.Listing.Unit.NewUnit.AdminNewUnitModel;
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

public class AdminNewUnitController {

    private Main main;
    private AdminNewUnitModel adminNewUnitModel;
    @FXML private Button home;
    @FXML private Button addUnitButton;
    @FXML private Text errorUnit;
    @FXML private TextField newUnitName;
    @FXML private TextField newUnitDescription;

    public AdminNewUnitController(Main newMain){
        main = newMain;
        adminNewUnitModel = new AdminNewUnitModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewUnit() throws IOException {
        adminNewUnitModel.addNewUnit(newUnitName, newUnitDescription, addUnitButton, errorUnit);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewUnit();
        }
    }

}
