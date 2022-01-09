package Modules.Projet.Manipulations.AjoutManipulation;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tools;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.json.JSONObject;

import java.sql.CallableStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * La classe AddmanipulationModel est défini par un main
 * Elle contient les méthodes permettant d'ajouter une manipulation dans la bdd
 */
public class AddManipulationModel {

    private Main main;

    /**
     * Constructeur de la classe AddManipulationModel
     * @param newMain objet Main
     */
    public AddManipulationModel(Main newMain){
        main = newMain;
    }

    /**
     * Cette méthode requete la base de données pour avoir tous les modele et recupere l'item java fx choicebox modeleList
     * @param modeleList Item java fx
     * @throws IOException
     */
    public void setModeleList(ChoiceBox modeleList) throws IOException {
        for (JSONObject object : DataBase.getAllModeleRequest()) {
            modeleList.getItems().add(object.getString("NOM_MODELE_MANIP"));
        }
    }

    /**
     * Cette méthode requete la base de données pour avoir tous les users et recupere l'item java fx choicebox userList
     * @param userList
     * @throws IOException
     */
    public void setUserList(ChoiceBox userList) throws IOException {
        for (JSONObject object : DataBase.getAllUserRequest()) {
            userList.getItems().add(object.getString("NOM_UTILISATEUR"));
        }
    }

    /**
     * Methode addManipToBdd : permet de récuperer les informations d'un utilisateur et d'un modèle afin d'ajouter une manipulation à la base de données
     * @param userListValue string de l'utilisateur
     * @param modelListValue string du modèle
     * @param dateManipValue string de la date
     * @param sampleHourValue string de l'heure du sample
     * @param sampleId identifiant du sample
     * @param com string d'un commentaire
     * @param addManipButton bouton
     * @throws IOException
     * @throws SQLException
     */
    public void addManipToBdd(String userListValue, String modelListValue, String dateManipValue, String sampleHourValue, int sampleId, String com, Button addManipButton) throws IOException, SQLException, ParseException {

            //get the user ID
            int userId = 0;
            for (JSONObject object : DataBase.getAllUserRequest()) {
                if (object.getString("NOM_UTILISATEUR").equals(userListValue)){
                    userId = object.getInt("ID_UTILISATEUR");
                }
            }

            //get the model ID
            int modeleId = 0;
            for (JSONObject object : DataBase.getAllModeleRequest()) {
                if (object.getString("NOM_MODELE_MANIP").equals(modelListValue)){
                    modeleId = object.getInt("ID_MODELE_MANIP");
                }
            }

            //transformer la date et l'heure en une seule variable
            //il faut mettre un underscore pour que le lien http ne pète pas, et un .0 pour que SQL comprenne le format "timestamp"
            String dateFinale2 = dateManipValue + "_" + sampleHourValue +".0";

            //create the manipulation
            DataBase.createManipComRequest(userId,modeleId,sampleId,dateFinale2,main.tools.spaceToUnderscore(com));

            //récuperer l'id de la manipulation créée
/*            for (JSONObject object : DataBase.getSubjectFromGroupRequest(idGroup)) {
                if (object.getString("CODE_SUJET").equals(subject.getCodeSubject()) &&
                        object.getInt("AGE_SUJET") == subject.getAgeSubject() &&
                        main.tools.intToBoolean(object.getInt("SEXE_SUJET")) == subject.getSexeSubject() &&
                        object.getInt("POIDS_SUJET") == subject.getWeightSubject()
                ){
                    subject.setIdSubject(object.getInt("ID_SUJET"));
                }
            }*/

            //tracabilité
            Date maDate = new Date();
            String action = main.getCurrentUser().getUserName() +
                    " a rajouté la manipulation qui a pour id : "
                    + " le " + maDate;
            main.tools.applyTraceability(Tools.spaceToUnderscore(action));

            //retourner au menu principal
            main.tools.goHome(addManipButton);
    }
}
