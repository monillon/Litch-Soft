package Modules.Administration.Listing.Traceability;

import LITCH.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminListTraceabilityModel {

    private Main main;

    public AdminListTraceabilityModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des traceability dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView, User selectedUser) throws IOException, ParseException {
        // récupérer les users
        HashMap<Integer, User> dicoUser = new HashMap<Integer, User>();

        for (JSONObject i : DataBase.getUsersWithRoles()) {
            dicoUser.put(i.getInt("ID_UTILISATEUR"), new User(i.getInt("ID_UTILISATEUR"), i.getString("NOM_UTILISATEUR"), i.getString("NOM_ROLE")));
        }

        // récupérer les traces
        SimpleDateFormat formatBDD = new SimpleDateFormat("yyyy-MM-dd");
        theListView.getItems().clear();
        for (JSONObject i : DataBase.getAllTraces()) {
            if (selectedUser == null || selectedUser.getIdUser() == i.getInt("ID_UTILISATEUR")) {
                // date casting
                String dateString = i.getString("DATE");
                Date dateAction = new Date(formatBDD.parse(dateString).getTime());

                theListView.getItems().add(new Traceability(i.getInt("ID_TRACE"), dicoUser.get(i.getInt("ID_UTILISATEUR")), dateAction, Tools.underscoreToSpace(i.getString("ACTION"))));
            }
        }

    }

    public void addUsers(ComboBox choixUser) throws IOException {
        choixUser.getItems().clear();
        choixUser.getItems().add("Tous les utilisateurs");

        for (JSONObject i : DataBase.getUsersWithRoles()) {
            choixUser.getItems().add(new User(i.getInt("ID_UTILISATEUR"), i.getString("NOM_UTILISATEUR"), i.getString("NOM_ROLE")));
        }
        choixUser.getSelectionModel().select(0);
    }

}
