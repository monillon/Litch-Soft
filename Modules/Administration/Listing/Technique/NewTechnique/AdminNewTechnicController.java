package Modules.Administration.Listing.Technique.NewTechnique;

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

public class AdminNewTechnicController {

    private Main main;
    private AdminNewTechnicModel adminNewTechnicModel;
    @FXML private Button home;
    @FXML private Button addTechnicButton;
    @FXML private Text errorTechnic;
    @FXML private TextField newTechnicName;
    @FXML private TextField newTechnicDescription;

    public AdminNewTechnicController(Main newMain){
        main = newMain;
        adminNewTechnicModel = new AdminNewTechnicModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewTechnic() throws IOException {
        adminNewTechnicModel.addNewTechnic(newTechnicName, newTechnicDescription, addTechnicButton, errorTechnic);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewTechnic();
        }
    }

}
