package Modules.Connexion;

import LITCH.Main;
import Modules.Connexion.ConnexionModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ConnexionController {

    @FXML private PasswordField passwordEntered;
    @FXML private TextField identity;
    @FXML private Text verif;
    @FXML private Button submit;


    private Main main;
    private ConnexionModel connexionModel;

    public ConnexionController(Main newMain){
        main = newMain;
        connexionModel = new ConnexionModel(newMain);
    }

    /**
     * Compares the information entered by the user with the information stored in the database and initializes the menu if the information entered is correct.
     * @throws IOException
     * @throws SQLException
     */
    public void connexion() throws IOException, SQLException {
        Stage stage = (Stage) submit.getScene().getWindow();
        connexionModel.connexion(identity.getText(), passwordEntered.getText(), stage, verif);
    }


    public void onEnter(KeyEvent ke) throws SQLException, IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.connexion();
        }
    }

}
