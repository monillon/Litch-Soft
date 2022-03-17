package Modules.Administration.Listing.Pathologie.NewPathologie;

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

public class AdminNewPathologieController {

    private Main main;
    private AdminNewPathologieModel adminNewTissuModel;
    @FXML private Button home;
    @FXML private Button addPathologyButton;
    @FXML private Text errorPathology;
    @FXML private TextField newPathologyName;

    public AdminNewPathologieController(Main newMain){
        main = newMain;
        adminNewTissuModel = new AdminNewPathologieModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewPathology() throws IOException {
        adminNewTissuModel.addNewPathology(newPathologyName, addPathologyButton, errorPathology);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewPathology();
        }
    }

}
