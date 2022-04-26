package Modules.Administration.Listing.Traceability;

import LITCH.*;
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
     * Permet de faire la requête des tracability dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException, ParseException {
        // récupérer les users
        HashMap<Integer, User> dicoUser = new HashMap<Integer, User>();

        for (JSONObject i : DataBase.getUsersWithRoles()) {
            dicoUser.put(i.getInt("ID_UTILISATEUR"), new User(i.getInt("ID_UTILISATEUR"), i.getString("NOM_UTILISATEUR"), i.getString("NOM_ROLE")));
        }

        // récupérer les traces
        SimpleDateFormat formatBDD = new SimpleDateFormat("yyyy-MM-dd");

        for (JSONObject i : DataBase.getAllTraces()) {

            // date casting
            String dateString = i.getString("DATE");
            Date dateAction = new Date(formatBDD.parse(dateString).getTime());

            theListView.getItems().add(new Traceability(i.getInt("ID_TRACE"), dicoUser.get(i.getInt("ID_UTILISATEUR")), dateAction, Tools.underscoreToSpace(i.getString("ACTION"))));
        }

    }

}
