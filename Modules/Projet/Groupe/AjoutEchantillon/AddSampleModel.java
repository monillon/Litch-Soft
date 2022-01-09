package Modules.Projet.Groupe.AjoutEchantillon;

import LITCH.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe AddSampleModel est défini par un main, une liste d'organes et une liste de tissus
 * Elle contient les méthodes permettant d'ajouter un prélèvement dans la base de données
 */
public class AddSampleModel {

    private Main main;
    private ArrayList<Organ> organListLocal;
    private ArrayList<Tissue> tissueListLocal;

    /**
     * Constructeur de la classe AddSampleModel
     * @param newMain objet Main
     */
    public AddSampleModel(Main newMain){
        main = newMain;
        organListLocal = new ArrayList<Organ>();
        tissueListLocal = new ArrayList<Tissue>();
    }


    /**
     * Cette méthode permet d'afficher la liste des organes dans une liste déroulante
     * @param organList Objet ChoiceBox
     * @throws IOException
     */
    public void setOrganList(ChoiceBox organList) throws IOException {
        for (JSONObject object : DataBase.getAllOrganRequest()) {
            organList.getItems().add(object.getString("NOM_ORGANE"));
            organListLocal.add(new Organ(object.getInt("ID_ORGANE"), object.getString("NOM_ORGANE")));
        }
    }

    /**
     * Cette méthode permet d'afficher la liste des tissus dans une liste déroulante
     * @param tissueList Objet ChoiceBox
     * @throws IOException
     */
    public void setTissueList(ChoiceBox tissueList) throws IOException {
        for (JSONObject object : DataBase.getAllTissuRequest()) {
            tissueList.getItems().add(object.getString("NOM_TISSU"));
            tissueListLocal.add(new Tissue(object.getInt("ID_TISSU"), object.getString("NOM_TISSU")));
        }
    }

    /**
     * Cette méthode permet d'afficher la liste des frigos dans une liste déroulante
     * @param fridgeList objet ComboBox
     * @throws IOException
     */
    public void setFridgeList(ComboBox fridgeList) throws IOException {
        fridgeList.getItems().clear();
        for (JSONObject object : DataBase.getAllFrigoRequest()) {
            fridgeList.getItems().add(object.getString("NOM_FRIGO"));
        }
    }

    /**
     * Cette méthode permet d'afficher la liste des étagères dans une liste déroulante
     * @param shelfList objet ComboBox
     * @throws IOException
     */
    public void setShelfList(ComboBox shelfList) throws IOException {
        shelfList.getItems().clear();
        for (JSONObject object : DataBase.getAllEtagereRequest()) {
            shelfList.getItems().add(object.getString("NOM_ETAGERE"));
        }
    }

    /**
     * Cette méthode permet d'afficher la liste des boîtes dans une liste déroulante
     * @param boxList objet ComboBox
     * @throws IOException
     */
    public void setRackList(ComboBox rackList,ComboBox boxList,ComboBox locationList,String fridgeListValue, String shelfListValue) throws IOException {
        rackList.getItems().clear();
        if (getShelfId(fridgeListValue, shelfListValue) == 2) {
            boxList.setDisable(true);
            locationList.setDisable(true);
            rackList.getItems().add('A');
            rackList.getItems().add('B');
            rackList.getItems().add('C');
        }
        else {
            boxList.setDisable(false);
            locationList.setDisable(false);
            for (JSONObject object : DataBase.getRackExistantRequest(getShelfId(fridgeListValue, shelfListValue))) {
                rackList.getItems().add(object.getString("ID_RACK"));
            }
        }
    }

    public void setBoxList(ComboBox boxList, ComboBox shelfList, ComboBox rackList,String fridgeListValue, String shelfListValue) throws IOException {
        boxList.getItems().clear();
        for (JSONObject object : DataBase.getBoxExistantRequest(getShelfId(fridgeListValue, shelfListValue), rackList.getValue().toString())) {
            boxList.getItems().add(object.getString("ID_BOITE"));
        }
    }

    /**
     * Cette méthode permet d'afficher la liste des emplacements dans une liste déroulante
     * @param locationList objet ComboBox
     * @throws IOException
     */
    public void setLocationList(ComboBox rackList, ComboBox boxList, ComboBox locationList, String shelfListValue, String fridgeListValue) throws IOException {
        locationList.getItems().clear();
        for (JSONObject object : DataBase.getLocationListRequest(getShelfId(fridgeListValue, shelfListValue), Integer.parseInt(boxList.getValue().toString()), rackList.getValue().toString())) {
            //only those corresponding to the previous choices are shown.
            if (object.has("message") == false) {
                if (object.getString("NOM_FRIGO").equals(fridgeListValue)) {
                    locationList.getItems().add(object.getString("COORDONNEE_X") + object.getString("COORDONNEE_Y"));
                }
            }
        }
    }

    /**
     * Cette méthode permet de récuperer toutes les informations saisi afin de les ajouter dans la base de données
     * @param organListValue string nom de l'organe
     * @param tissueListValue string nom du tissu
     * @param fridgeListValue string nom du frigo
     * @param shelfListValue string nom de l'étagère
     * @param boxListValue string nom de la boîte
     * @param locationListValue string nom de l'emplacement
     * @param sampleDateValue string date
     * @param sampleHourValue string heure
     * @param subjectId id sujet
     * @param addSampleButton objet Button
     * @throws IOException
     * @throws SQLException
     */
    public void addSampleToBdd(ComboBox rackList, ComboBox boxList, String organListValue, String tissueListValue, String fridgeListValue, String shelfListValue, ComboBox locationList, String sampleDateValue, String sampleHourValue, int subjectId, Button addSampleButton) throws IOException, SQLException {


            //get the organ ID
            int organId = 0;
            for (Organ organ : organListLocal) {
                if (organ.getNameOrgan().equals(organListValue)){
                    organId = organ.getIdOrgan();
                }
            }

            //get the tissu ID
            int tissueId = 0;
            for (Tissue tissue : tissueListLocal) {
                if (tissue.getNameTissue().equals(tissueListValue)){
                    tissueId = tissue.getIdTissue();
                }
            }

            //create the sample
            DataBase.createSampleRequest(subjectId, organId, tissueId, sampleDateValue,sampleHourValue);

            //get the sample ID
            int sampleId = 0;
            for (JSONObject object : DataBase.getSubjectSampleRequest(subjectId)) {

                if (object.getString("NOM_ORGANE").equals(organListValue) & object.getString("NOM_TISSU").equals(tissueListValue)){
                    int max = -1;
                    if (object.getInt("ID_PRELEVEMENT") > max){
                        max = object.getInt("ID_PRELEVEMENT");
                    }
                    sampleId = object.getInt("ID_PRELEVEMENT");
                }
            }

            String coordonneeX = "";
            String coordonneeY = "";

            if (getShelfId(fridgeListValue, shelfListValue) != 2) {

                //get the location ID
                int locationId = 0;

                for (JSONObject object : DataBase.getLocationListRequest(getShelfId(fridgeListValue, shelfListValue), Integer.parseInt(boxList.getValue().toString()), rackList.getValue().toString())) {
                    //only those corresponding to the previous choices are shown.
                    if (locationList.getValue().toString().equals(object.getString("COORDONNEE_X") + object.getString("COORDONNEE_Y"))) {
                        coordonneeX = object.getString("COORDONNEE_X");
                        coordonneeY = object.getString("COORDONNEE_Y");
                        locationId = object.getInt("ID_EMPLACEMENT");
                    }
                }
                //update the location data
                DataBase.updateLocationDateRequest(locationId, 0,String.valueOf(sampleId));
            }
            else {

                DataBase.createSingleSlot(getShelfId(fridgeListValue, shelfListValue), rackList.getValue().toString(), sampleId);
            }

            //tracabilité
            Date maDate = new Date();
            String action = main.getCurrentUser().getUserName() +
                    " a rajouté un prélèvement qui a pour id : " + sampleId
                    + " le " + maDate;
            main.tools.applyTraceability(Tools.spaceToUnderscore(action));

            main.tools.goHome(addSampleButton);

    }


    /**
     * Cette méthode sélectionne l'id d'une étagère
     * @param fridgeListValue string nom du frigo
     * @param shelfListValue string nom de l'étagère
     * @return
     * @throws IOException
     */
    public int getShelfId(String fridgeListValue, String shelfListValue) throws IOException {

        //get the fridge ID
        int fridgeId = 0;
        for (JSONObject object : DataBase.getAllFrigoRequest()) {
            if (object.getString("NOM_FRIGO").equals(fridgeListValue)){
                fridgeId = object.getInt("ID_FRIGO");
            }
        }


        //get the shelf ID
        int shelfId = 0;
        for (JSONObject object : DataBase.getAllEtagereRequest()) {
            if (object.getString("NOM_ETAGERE").equals(shelfListValue) & object.getInt("ID_FRIGO") == fridgeId){
                shelfId = object.getInt("ID_ETAGERE");
            }
        }
        return shelfId;


    }



}
