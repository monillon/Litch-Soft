package LITCH;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by ben14 on 21/02/2020.
 */
public class Tools {

    private Main main;
    private Scene previousScene;

    public Tools(Main newMain) {
        main = newMain;
    }

    public void switchScene(Stage stage, String nameFXML, Object controller) throws IOException {
        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/" + nameFXML));
        newFXML.setController(controller);
        Parent pane = (Parent) newFXML.load();
        previousScene = stage.getScene();
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public void previous(Stage stage) {
        stage.setScene(previousScene);
        stage.show();
    }


    public void goHome(Button button) throws IOException, SQLException {
        Stage stage = (Stage) button.getScene().getWindow();
        switchScene(stage, "Menu/Menu.fxml", main.getMenuController());
        main.getMenuController().updateMenu();
    }


    public static String spaceToUnderscore(String textToTransform) {
        String textTransform = textToTransform;
        for (int i = 0; i < textToTransform.length(); i++) {

            if (textToTransform.charAt(i) == ' ') {
                textTransform = textTransform.substring(0, i) + '_' + textTransform.substring(i + 1);
            }
        }
        return textTransform;
    }


    public static String underscoreToSpace(String textToTransform) {
        String textTransform = textToTransform;
        for (int i = 0; i < textToTransform.length(); i++) {

            if (textToTransform.charAt(i) == '_') {
                textTransform = textTransform.substring(0, i) + ' ' + textTransform.substring(i + 1);
            }
        }
        return textTransform;
    }

    public ArrayList<JSONObject> splitResultQuery(String objectToSplit) {

        ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < objectToSplit.length(); i++) {

            JSONObject newJsonObject = null;

            if (objectToSplit.charAt(i) == '{') {
                pos1 = i;
            } else if (objectToSplit.charAt(i) == '}') {
                pos2 = i + 1;
                newJsonObject = new JSONObject(objectToSplit.substring(pos1, pos2));
                jsonList.add(newJsonObject);
            }
        }


        return jsonList;
    }


    public ArrayList<JSONObject> sendRequest(String request) throws IOException {
        ArrayList<JSONObject> listOfResults = null;

        URL url = new URL(request);
        URLConnection conn = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            String output = inputLine.replace("[", "").replace("]", "");
            ;
            listOfResults = splitResultQuery(output);
        }
        return listOfResults;
    }

    public void insertRequest(String request) throws IOException {
        URL url = new URL(request);
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }


    public boolean intToBoolean(int newInt) {
        Boolean result = null;
        if (newInt == 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public static String sexBooleanToString(boolean sex) {
        String sexString = "";
        if (sex) {
            sexString = "Femelle";
        } else {
            sexString = "Mâle";
        }
        return sexString;
    }


    /**
     * It return an empty string if the entered JsonObject's field is null
     *
     * @param object
     * @param key
     * @return
     */
    public String checkStringIsNull(JSONObject object, String key) {
        String result = "";
        if (!object.isNull(key)) {
            result = object.getString(key);
        }
        return result;
    }

    public boolean checkIfInt(String s) {
        boolean amIValid = false;
        try {
            Integer.parseInt(s);
            amIValid = true;
        } catch (NumberFormatException e) {

        }
        return amIValid;
    }


    public Unit searchForUnit(String unitName) throws IOException {
        Unit unit = null;
        String request = "http://litch-dev.geniephy.net/API/unite/read_all.php";
        for (JSONObject object : main.tools.sendRequest(request)) {
            if (object.getString("NOM_UNITE").equals(unitName))
                unit = new Unit(object.getInt("ID_UNITE"), object.getString("NOM_UNITE"), object.getString("DESCRIPTION_UNITE"));
        }
        return unit;
    }


    public Unit searchForUnitById(int unitid) throws IOException {
        Unit unit = null;
        String request = "http://litch-dev.geniephy.net/API/unite/read_all.php";
        for (JSONObject object : main.tools.sendRequest(request)) {
            if (object.getInt("ID_UNITE") == unitid)
                unit = new Unit(object.getInt("ID_UNITE"), object.getString("NOM_UNITE"), object.getString("DESCRIPTION_UNITE"));
        }
        return unit;
    }

    public ArrayList<Pathology> getAllPatho() throws IOException {
        ArrayList<Pathology> listOfPatho = new ArrayList<Pathology>();

        String request = "http://litch-dev.geniephy.net/API/pathologie/read_all.php";
        for (JSONObject object : main.tools.sendRequest(request)) {
            listOfPatho.add(new Pathology(object.getInt("ID_PATHOLOGIE"), object.getString("NOM_PATHOLOGIE")));
        }
        return listOfPatho;
    }


    public ArrayList<Prescription> getAllPrescription() throws IOException {
        ArrayList<Prescription> listPrescri = new ArrayList<Prescription>();

        String request = "http://litch-dev.geniephy.net/API/prescription/read_all.php";
        for (JSONObject object : main.tools.sendRequest(request)) {
            listPrescri.add(new Prescription(object.getInt("ID_PRESCRIPTION"), object.getString("NOM_PRESCRIPTION")));
        }
        return listPrescri;
    }


    /**
     * les lignes suivantes permettent d'appliquer les modifications des interfaces liées aux droits de l'utilisateur.
     *
     * @param pane
     * @param button
     */
    public void applyRoles(Pane pane, Button button) {
        pane.getChildren().remove(button);
    }


    public void applyTraceability(String action) throws IOException {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new Date(utilDate.getTime());
        Traceability traceability = new Traceability(main.getCurrentUser(), sqlDate, action);
        traceability.send();
    }


}
