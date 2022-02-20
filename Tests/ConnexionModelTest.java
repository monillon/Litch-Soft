package Tests;

import Modules.Connexion.ConnexionModel;
import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tools;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Tests unitaires de la classe ConnexionModel
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
public class ConnexionModelTest {
    private Tools tools;
    private Button button;
    private Main main;
    private ConnexionModel connexion;
    private Stage stage;
    private Text text;

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testBonIds
     * Vérifie qu'en envoyant les bons ids à la base de données, la connexion s'effectue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testBonIds() throws IOException, SQLException {
        // un utilisateur "test" doit être présent dans la BDD pour que cela fonctionne
       for( JSONObject object :DataBase.connexionRequest("TESTCO", "TESTCO")) {
            if (object.has("message") == false) {
                assertTrue(true);
            } else assertTrue(false);
        }
    }

    /**
     * Methode testBadIds
     * vérifie qu'en envoyant des strings vide à la base de données, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testBadIds() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("", "")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Methode test IdvidebonMdp
     * vérifie qu'en envoyant un id string vide et le bon mot de passe, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdvidebonMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("", "TEST")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Methode testIdbadbonMdp
     * vérifie qu'en envoyant un mauvais id et un bon mot de passe, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdbadbonMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("125", "TEST")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Méthode testIdbonvideMdp
     * vérifie qu'en envoyant un bon id et un mot de passe string vide, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdbonvideMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("ALA", "")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Methode testIdbonbadMdp
     * vérifie qu'en envoyant un bon id et un mauvais mot de passe, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdbonbadMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("ALA", "TES")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }


    /**
     * Methode testIdbonnullMdp
     * vérifie qu'en envoyant un bon id et un mot de passe null, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdbonnullMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest("ALA", null)) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Methode testidsnull
     * vérifie qu'en envoyant un id null et un mot de passe null, la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testidsnull() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest(null, null)) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

    /**
     * Methode testIdnullbonMdp
     * vérifie qu'en envoyant un id null et un bon mot de passe , la connexion échoue
     * @throws IOException
     * @throws SQLException
     */
    @Test
    public void testIdnullbonMdp() throws IOException, SQLException {
        for( JSONObject object :DataBase.connexionRequest(null, "TEST")) {
            if (object.has("message") == false) {
                assertTrue(false);
            } else assertTrue(true);
        }
    }

}