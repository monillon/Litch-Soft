package Modules.Projet.Manipulations;

import LITCH.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;


public class ManipulationViewModel {


    private Main main;
    private Group currentGroup;
    private int idManipulation;

    public ManipulationViewModel(Main newMain){
        main = newMain;
    }


    /**
     * It allows to update the groupView screen
     * @param groupID
     * @throws IOException
     */
    public void updateManipulationView(int groupID, Text currentProjectNameView, VBox vboxSubject) throws IOException {
        currentGroup = main.getCurrentProject().searchGroupById(groupID);
        currentProjectNameView.setText(currentGroup.typeGroupToString());

        int color = 0;
        for (JSONObject object : DataBase.getManipulationRequest(groupID)){
            if (object.has("message") == false) {
                loadManipPanel(object, color, vboxSubject);
                if (color == 0) {
                    color = 1;
                } else {
                    color = 0;
                }
            }
        }


    }

    /**
     * It allows to load and to add a panel of a subject in a VBox
     * @param subject
     * @param backgroundColor
     * @throws IOException
     */
    public void loadManipPanel(JSONObject object, int backgroundColor, VBox vboxSubject) throws IOException {

        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/Projet/Manipulations/ManipulationListItem.fxml"));
        newFXML.setController(main.getManipulationViewController());
        Pane pane = (Pane) newFXML.load();

        //Selection de la couleur de fond du panel
        if (backgroundColor == 0){
            pane.setStyle("-fx-background-color: grey;");
        }else{
            pane.setStyle("-fx-background-color:" + " rgb(217,217,217);");
        }

        //remplissage des champs de texte du panel avec les informations du sujet
        main.getManipulationViewController().getManipField().setText(object.getString("NOM_MODELE_MANIP"));
        main.getManipulationViewController().getPrelevField().setText(object.getString("NOM_ORGANE")+" "+object.getString("CODE_SUJET"));
        main.getManipulationViewController().getManipulateurField().setText(object.getString("NOM_UTILISATEUR"));

        main.getManipulationViewController().getSelectManip().setId(String.valueOf(object.getString("ID_MANIPULATION")));

        vboxSubject.getChildren().add(pane);
    }


    /**
     * It show details of the selected subject
     * @param e
     * @param pathoList
     * @param prescriList
     * @throws IOException
     */
    public void loadManipDetails(int buttonId,
                                 Text codePatientField,
                                 Text sexeField, Text ageField,
                                 Text prelevDetailsField,
                                 Text modeleManipField,
                                 TextArea commentairesField,
                                 Text utilisateurField,
                                 ComboBox preopDataList,
                                 ComboBox pathoList,
                                 ComboBox prescriList) throws IOException {
        preopDataList.getItems().clear();
        pathoList.getItems().clear();
        prescriList.getItems().clear();
        idManipulation = buttonId;


        for (JSONObject object : DataBase.getManipRequest(buttonId)){
            Subject subject = currentGroup.getSubjectById(Integer.parseInt(object.getString("ID_SUJET")));
            for(Phenotype phenotype : subject.getPhenotype()) {

                String NamePreop = phenotype.getPreopData().getNamePreop();
                float PreopValue = phenotype.getPreopValue();
                String NameUnit = phenotype.getUnitPreopValue().getNameUnit();
                preopDataList.getItems().add(NamePreop+" "+PreopValue+" "+NameUnit);
                preopDataList.getSelectionModel().selectFirst();

                String NamePathology = phenotype.getPathology().getNamePathology();
                pathoList.getItems().add(NamePathology);
                pathoList.getSelectionModel().selectFirst();

                String PrescriptionName = phenotype.getPrescription().getPrescriptionName();
                prescriList.getItems().add(PrescriptionName);
                prescriList.getSelectionModel().selectFirst();
            }
            codePatientField.setText(object.getString("CODE_SUJET"));
            sexeField.setText(main.tools.sexBooleanToString(main.tools.intToBoolean(object.getInt("SEXE_SUJET"))));
            ageField.setText(Integer.toString(object.getInt("AGE_SUJET"))+" "+main.tools.searchForUnitById(object.getInt("UNI_ID_UNITE")).getNameUnit());
            prelevDetailsField.setText(object.getString("NOM_ORGANE")+" "+object.getString("CODE_SUJET"));
            modeleManipField.setText(object.getString("NOM_MODELE_MANIP"));
            commentairesField.setText(Tools.underscoreToSpace(main.tools.checkStringIsNull(object,"COMMENTAIRE_MANIPULATION")));
            utilisateurField.setText(object.getString("NOM_UTILISATEUR")+", "+object.getString("DATE_HEURE_MANIPULATION"));

        }
    }

    public void updateCommentaireManipulation(TextArea commentairesField) throws IOException {
        DataBase.updateCommentaireManipulation(Tools.spaceToUnderscore(commentairesField.getText()), idManipulation);
    }
}
