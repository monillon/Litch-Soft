package Modules.Projet.Groupe;

import LITCH.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  Un GroupViewModel est défini par Un main, un grouViewController et un group
 *  Cette classe fait le lien entre le main et les controller, elle permet entre autre de stocker des donnée patient
 *  à travers des identifiant de boutons
 * @author ben14
 */
public class GroupViewModel {

    /**
     * Main application
     */
    private Main main;


    /**
     * GroupView du groupModel
     */
    private Group currentGroup;


    /**
     * GroupViewModel Constructor
     *
     * @param newMain Permet de stocker le main de l'application
     * @param newgroupViewController Le nom du controller de ce groupview
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public GroupViewModel(Main newMain){
        main = newMain;
    }

    /**
     * Allow to load addManipulation screen
     * @param e lié à l'action de l'utilisateur
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public void addManip (Button button) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        main.tools.switchScene(stage,"Projet/Manipulations/AjoutManipulation/AddManipulation.fxml",main.getAddManipulationController());
        main.getAddManipulationController().setSampleId(Integer.parseInt(button.getId()));
    }



    /**
     * It allows to update the groupView screen
     * @param groupID identifiant du groupe
     * @throws IOException
     */
    public void updateGroupView(int groupID, Text currentGroupNameView) throws IOException {
        currentGroup = main.getCurrentProject().searchGroupById(groupID);
        currentGroupNameView.setText(currentGroup.typeGroupToString());

        int color = 0;
        for (Subject subject : currentGroup.getSubjectList()){
            loadSubjectPanel(subject, color);
            if (color == 0){
                color = 1;
            }else{
                color = 0;
            }
        }


    }


    /**
     * It allows to load and to add a panel of a subject in a VBox
     * @param subject objet de la classe Subject
     * @param backgroundColor integer de la couleur d'arrière plan
     * @throws IOException
     */
    public void loadSubjectPanel(Subject subject, int backgroundColor) throws IOException {

        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/Projet/Groupe/SubjectListItem.fxml"));
        newFXML.setController(main.getGroupViewController());
        Pane pane = (Pane) newFXML.load();

        //Selection de la couleur de fond du panel
        if (backgroundColor == 0){
            pane.setStyle("-fx-background-color: grey;");
        }else{
            pane.setStyle("-fx-background-color:" + " rgb(217,217,217);");
        }


        //remplissage des champs de texte du panel avec les informations du sujet
        main.getGroupViewController().getPatientCodeField().setText("Patient : " + subject.getCodeSubject());
        main.getGroupViewController().getSexAgeField().setText(Tools.sexBooleanToString(subject.getSexeSubject()) + ", " + subject.getAgeSubject() + " " + main.tools.searchForUnitById(subject.getAgeUnit().getIdUnit()).getNameUnit());
        if (subject.getPhenotype() != null) {
            int n = 0;
            for (Phenotype pheno : subject.getPhenotype()){
                if (pheno.getPathology() != null) {
                    if (n == 0) {
                        main.getGroupViewController().getMutationField().setText(pheno.getMutation().getMutationName());
                    } else if (n == 1) {
                        main.getGroupViewController().getMutationField1().setText(pheno.getMutation().getMutationName());
                    }
                    n++;
                }
            }
        }else {
            main.getGroupViewController().getMutationField().setText("Mutation : ");
        }

        main.getGroupViewController().getSelectSubject().setId(String.valueOf(subject.getIdSubject()));

        main.getGroupViewController().getVboxSubject().getChildren().add(pane);
    }


    /**
     * It show details of the selected subject
     * @param e
     * @throws IOException
     */
    public void loadSubjectDetails(int buttonId, Text subjectCodeView, Text subjectSexView, Text subjectAgeView, ComboBox preopDataList, ComboBox pathoList, ComboBox prescriList, Text subjectPreopDataView, Text subjectPathoView, Text subjectPrescriView, FlowPane sampleListPane, Button addSampleButton, Text sampleOrgLoc, Text localisation, Button addManipButton) throws IOException {
        Subject subjectToShow = currentGroup.getSubjectById(buttonId);


        subjectCodeView.setText("Code patient : " + subjectToShow.getCodeSubject());
        subjectSexView.setText("Sexe : " + Tools.sexBooleanToString(subjectToShow.getSexeSubject()));
        subjectAgeView.setText("Age : " + subjectToShow.getAgeSubject() + " " + main.tools.searchForUnitById(subjectToShow.getAgeUnit().getIdUnit()).getNameUnit());
        if (subjectToShow.getPhenotype() != null) {
            preopDataList.getItems().clear();
            pathoList.getItems().clear();
            prescriList.getItems().clear();
            if (subjectToShow.getPhenotype() != null) {
                for (Phenotype pheno : subjectToShow.getPhenotype()) {

                        System.out.println(subjectToShow.getPhenotype());
                        preopDataList.getItems().add(pheno.getPreopValue() + pheno.getUnitPreopValue().getNameUnit() + " " + pheno.getPreopData().getNamePreop());
                        preopDataList.getSelectionModel().select(0);
                    if (pheno.getPathology() != null) {
                        pathoList.getItems().add(pheno.getPathology().getNamePathology());
                        pathoList.getSelectionModel().select(0);
                        prescriList.getItems().add(pheno.getPrescription().getPrescriptionName());
                        prescriList.getSelectionModel().select(0);
                    }
                }
                preopDataList.getSelectionModel().select(0);
                pathoList.getSelectionModel().select(0);
                prescriList.getSelectionModel().select(0);
            }
        }else{
            subjectPreopDataView.setText("Données pré-opératoires : ");
            subjectPathoView.setText("Co-morbidité : " );
            subjectPrescriView.setText("Prescription : ");
        }


        loadSubjectSample(subjectToShow, sampleListPane);
    }


    /**
     * Build the list of sample of a subject in a VBox
     * @param subject objet de la classe Subject
     * @throws IOException
     */
    public void loadSubjectSample(Subject subject, FlowPane sampleListPane) throws IOException {
        sampleListPane.getChildren().clear();

        ArrayList<Sample> list = subject.getSampleList();
        main.getGroupViewController().getAddSampleButton().setId(String.valueOf(subject.getIdSubject()));
        for (Sample sample : list){
            FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/Projet/Groupe/SubjectSampleItem.fxml"));
            newFXML.setController(main.getGroupViewController());
            Pane pane = (Pane) newFXML.load();
            main.getGroupViewController().getSampleOrgLoc().setText(sample.getSampleOrgan() + " - " + sample.getSampleLocalization());

            for (JSONObject object : DataBase.getLocationRequest(sample.getIdSample())) {
                if (!object.has("message")) {
                    if (object.getString("NOM_ETAGERE").equals("ETAGERE 2"))
                    {
                        main.getGroupViewController().getLocalisation().setText(object.getString("NOM_FRIGO") + "/" + object.getString("NOM_ETAGERE") + "/RACK " + object.getString("ID_RACK"));
                        main.getGroupViewController().getModifLocation().setId(object.getString("NOM_FRIGO") + "/" + object.getString("NOM_ETAGERE") + "/" + object.getString("ID_RACK") + "/" + object.getString("ID_BOITE") );
                    }
                    else {
                        main.getGroupViewController().getLocalisation().setText(object.getString("NOM_FRIGO") + "/" + object.getString("NOM_ETAGERE") + "/RACK " + object.getString("ID_RACK") + "/BOITE " + object.getString("ID_BOITE") + "/" + object.getString("COORDONNEE_X") + object.getString("COORDONNEE_Y"));
                        main.getGroupViewController().getModifLocation().setId(object.getString("NOM_FRIGO") + "/" + object.getString("NOM_ETAGERE") + "/" + object.getString("ID_RACK") + "/" + object.getString("ID_BOITE") );
                    }
                }
            }
            //les stagiaires n'ont pas accès à l'ajout de manipulation, on fait donc disparaitre le bouton le permettant
            if (main.getCurrentUser().getRole().equals(Main.roles[3])) {
                main.tools.applyRoles(pane, main.getGroupViewController().getAddManipButton());
            }else {
                main.getGroupViewController().getAddManipButton().setId(String.valueOf(sample.getIdSample()));
            }
            main.getGroupViewController().getSampleListPane().getChildren().add(pane);
        }

    }


    /**
     * Fait la transition d'affichage quand le bouton ajouter un echantillon a été pressé
     * @param e
     * @throws IOException
     */
    public void goToAddSample(Button button) throws IOException {

        if (button.getId().equals("addSampleButton")){
            //no subject selected
        }else{
            main.tools.switchScene((Stage) button.getScene().getWindow(), "Projet/Groupe/AjoutEchantillon/AddSample.fxml", main.getAddSampleController());
            main.getAddSampleController().setSubjectId(Integer.parseInt(button.getId()));
            Subject subjectToShow = currentGroup.getSubjectById(Integer.valueOf(button.getId()));
            main.getAddSampleController().setSubjectCodeToModify(subjectToShow.getCodeSubject());
        }

    }



    public void goToSampleLocation(Button button){
        String idButton = button.getId();
        int i = 0;
        String fridge = "";
        String shelf = "";
        String drawer = "";
        String box = "";

        for (char c : idButton.toCharArray()){
            if (c != '/'){
                if (i == 0){
                    fridge += c;
                }else if (i == 1){
                    shelf += c;
                }else if (i == 2){
                    drawer += c;
                }else if (i == 3){
                    box += c;
                }
            }else{
                i++;
            }
        }

        try {
            Stage stage = (Stage) button.getScene().getWindow();
            main.tools.switchScene(stage ,"ManagementFrigo/FridgeManagement.fxml", main.getFridgeManagementController());
            main.getFridgeManagementController().setFridgeList();
            main.getFridgeManagementController().setupFromSample(fridge, shelf, drawer, box);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
