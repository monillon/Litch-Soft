package Modules.Administration.Listing.Mutation.NewMutation;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewMutationController {

    private Main main;
    private AdminNewMutationModel adminMutationModel;
    @FXML private Button home;
    @FXML private TextField newClassName;
    @FXML private TextField newMutaName;
    @FXML private ComboBox newPathoName;
    @FXML private Button addMutaButton;
    @FXML private Text errorMuta;

    public AdminNewMutationController(Main newMain){
        main = newMain;
        adminMutationModel = new AdminNewMutationModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewMuta() throws IOException {
        adminMutationModel.addNewMuta(newPathoName, newMutaName, newClassName, addMutaButton, errorMuta);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewMuta();
        }
    }

    public void fillPatho() throws IOException {
        adminMutationModel.fillPatho(newPathoName);
    }

}
