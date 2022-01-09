package Modules.Connexion;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.User;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Classe ConnexionModel : Permet, selon les informations saisi, d'accéder au reste de l'application
 */
public class ConnexionModel {

    private Main main;

    public ConnexionModel(Main newMain) {
        main = newMain;
    }

    /**
     * Compare les informations saisi par l'utilisateur avec les données de la BDD, si les informations correspondent alors le menu est initialisé
     * @param identity identifiant
     * @param password Mot de passe
     * @param stage Objet Stage
     * @param verif objet Text
     * @throws IOException
     * @throws SQLException
     */
    public void connexion(String identity, String password, Stage stage, Text verif) throws IOException, SQLException {
        for (JSONObject object : DataBase.connexionRequest(identity, password)) {
            if (object.has("message") == false) {
                main.tools.switchScene(stage, "Menu/Menu.fxml", main.getMenuController());
                main.setCurrentUser(new User(object.getInt("ID_UTILISATEUR"), object.getString("NOM_UTILISATEUR"), object.getString("NOM_ROLE")));
                main.getMenuController().updateMenu();
            } else {
                verif.setText("Données entrées erronées!");
            }
        }
    }


}
