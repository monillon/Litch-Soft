package Modules.Administration.Listing.DonneesPreOp.NewPreOp;

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

public class AdminNewPreOpController {

    private Main main;
    private AdminNewPreOpModel adminNewPreOpModel;
    @FXML private Button home;
    @FXML private Button addPreOpButton;
    @FXML private Text errorPreOp;
    @FXML private TextField newPreOpName;

    public AdminNewPreOpController(Main newMain){
        main = newMain;
        adminNewPreOpModel = new AdminNewPreOpModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPreOp() throws IOException {
        adminNewPreOpModel.addNewPreOp(newPreOpName, addPreOpButton, errorPreOp);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewPreOp();
        }
    }
}
