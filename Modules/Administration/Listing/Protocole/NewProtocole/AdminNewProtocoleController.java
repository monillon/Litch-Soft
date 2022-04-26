package Modules.Administration.Listing.Protocole.NewProtocole;

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

public class AdminNewProtocoleController {

    private Main main;
    private AdminNewProtocoleModel adminNewProtocoleModel;
    @FXML private Button home;
    @FXML private Button addProtocoleButton;
    @FXML private Text errorProtocole;
    @FXML private TextField newProtocoleName;

    public AdminNewProtocoleController(Main newMain){
        main = newMain;
        adminNewProtocoleModel = new AdminNewProtocoleModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewProtocole() throws IOException {
        adminNewProtocoleModel.addNewProtocole(newProtocoleName, addProtocoleButton, errorProtocole);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewProtocole();
        }
    }

}
