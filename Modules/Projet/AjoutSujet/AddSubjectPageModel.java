package Modules.Projet.AjoutSujet;

import LITCH.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe AddSubjectPageModel est défini par un main, un id d'espèce, un subject, un ou plusieurs phenotype, un id groupe,
 * Elle contient les méthodes permettant d'ajouter un sujet à la base de données
 */
public class AddSubjectPageModel {

    private Main main;
    private int species; //1 = human, 2 = rat, 3 = cell
    private Subject subjectToAdd;
    private Phenotype pheno1;
    private Phenotype pheno2;
    private int idGroup;

    //second page
    private ArrayList<Pathology> listOfPatho;
    private ArrayList<Mutation> listMutations;
    private ArrayList<Prescription> listPrescri;
    //third page
    private ArrayList<PreopData> listPreopData;
    private ArrayList<Unit> listUnit;

    /**
     * Constructeur de la classe AddSubjectPageModel
     * @param newMain objet Main
     */
    public AddSubjectPageModel(Main newMain){
        main = newMain;
        listMutations = new ArrayList<Mutation>();
        listPreopData = new ArrayList<PreopData>();
        listUnit = new ArrayList<Unit>();
    }

    /**
     * Cette méthode récupère tous les éléments de la première page afin de créer un sujet
     * @param sexValue string genre
     * @param buttonId string id du bouton
     * @param newPatientCode string code du patient
     * @param newPatientAge int age du patient
     * @param newPatientWeight int poids du patient
     * @param newPatientCommentary string commentaire
     * @param stage objet stage
     * @param menuButtonGroup objet ChoiceBox
     * @return
     * @throws IOException
     */
    public int getPageOneElements(String sexValue, String buttonId, String newPatientCode, int newPatientAge, int newPatientWeight, String newPatientCommentary, Stage stage, ChoiceBox menuButtonGroup) throws IOException {
        subjectToAdd = null;
        pheno1 = null;
        pheno2 = null;
        int result = 0; // si 0 est retourné alors non patho, si 1 retourné alors patho
        boolean sex = false;
        if (sexValue.equals("Male")) {
            sex = false;
        } else if (sexValue.equals("Femelle")) {
            sex = true;
        }

        if (buttonId.equals("addRatButton")){
            subjectToAdd = new Subject(newPatientCode, newPatientAge, main.tools.searchForUnit("SEMAINES"), sex, newPatientWeight, main.tools.searchForUnit("g"),newPatientCommentary);
            species = 2;
        }else if (buttonId.equals("addCellButton")){
            subjectToAdd = new Subject(newPatientCode, newPatientAge, main.tools.searchForUnit("PASSAGES"), sex, 1, null,newPatientCommentary);
            species = 3;
        }else if (buttonId.equals("addHumanButton")){
            subjectToAdd = new Subject(newPatientCode, newPatientAge, main.tools.searchForUnit("ANS"), sex, newPatientWeight, main.tools.searchForUnit("kg"),newPatientCommentary);
            species = 1;
        }

        idGroup = 0;
        for (JSONObject object : DataBase.getGroupRequest(main.getCurrentProject().getidProject(),species)) {
            if (object.getInt("GRP_PATHO") == 1 && object.getInt("GRP_TRAITE") == 1 && menuButtonGroup.getValue().equals("PATHOLOGIQUES | TRAITES")){
                idGroup = object.getInt("ID_GRP");
                result = 1;
                main.tools.switchScene(stage, "Projet/AjoutSujet/AddSubjectPage2_3.fxml", main.getAddSubjectPageController());
            }else if (object.getInt("GRP_PATHO") == 0 && object.getInt("GRP_TRAITE") == 1 && menuButtonGroup.getValue().equals("NON PATHOLOGIQUES | TRAITES")){
                idGroup = object.getInt("ID_GRP");
                pheno1 = new Phenotype(0, null, null, null, null, null);
                pheno2 = new Phenotype(0, null, null, null, null, null);
                main.tools.switchScene(stage, "Projet/AjoutSujet/AddSubjectPage3_3.fxml", main.getAddSubjectPageController());
            }else if (object.getInt("GRP_PATHO") == 0 && object.getInt("GRP_TRAITE") == 0 && menuButtonGroup.getValue().equals("NON PATHOLOGIQUES | NON TRAITES")){
                idGroup = object.getInt("ID_GRP");
                pheno1 = new Phenotype(0, null, null, null, null, null);
                pheno2 = new Phenotype(0, null, null, null, null, null);
                main.tools.switchScene(stage, "Projet/AjoutSujet/AddSubjectPage3_3.fxml", main.getAddSubjectPageController());
            }else if (object.getInt("GRP_PATHO") == 1 && object.getInt("GRP_TRAITE") == 0 && menuButtonGroup.getValue().equals("PATHOLOGIQUES | NON TRAITES")){
                idGroup = object.getInt("ID_GRP");
                result = 1;
                main.tools.switchScene(stage, "Projet/AjoutSujet/AddSubjectPage2_3.fxml", main.getAddSubjectPageController());
            }
        }

        return result;


    }

    /**
     * Cette méthode requete la base de données afin de charger les différentes listes de la page deux
     * @param menuPatho1 Liste déroulante des pathologies
     * @param menuPrescri1 liste déroulante des prescriptions
     * @param menuPrescri2 liste déroulante des prescriptions 2
     * @throws IOException
     */
    public void loadPageTwoList(ComboBox menuPatho1, ComboBox menuPrescri1, ComboBox menuPrescri2) throws IOException {
        //it creates the list of pathology
        listOfPatho = main.tools.getAllPatho();
        for (Pathology patho : listOfPatho){
            menuPatho1.getItems().add(patho.getNamePathology());

        }

        //it creates the list of prescriptions
        listPrescri = main.tools.getAllPrescription();
        for (Prescription prescri : listPrescri){
            menuPrescri1.getItems().add(prescri.getPrescriptionName());
            menuPrescri2.getItems().add(prescri.getPrescriptionName());
        }
    }


    /**
     * Cette méthode, en fonction de la pathologie selectionnée, affiche les mutations en lien avec cette pathologie
     * @param box Objet ComboBox
     * @param menuPatho1 liste déroulante de spathologies
     * @param menuMutation1 liste déroulante des mutations 1
     * @param menuMutation2 liste déroulante des mutations 2
     * @throws IOException
     */
    public void updateMutations(ComboBox box, ComboBox menuPatho1, ComboBox menuMutation1, ComboBox menuMutation2) throws IOException {

        for (Pathology patho : listOfPatho){
            if (patho.getNamePathology().equals(menuPatho1.getValue())){
                //String request = ;
                for (JSONObject object : DataBase.getMutationRequest(patho.getIdPathology())) {
                    if (!object.has("message")) {
                        listMutations.add(new Mutation(object.getInt("ID_MUTATION"), object.getString("NOM_MUTATION"), main.tools.checkStringIsNull(object, "CLASSE_MUTATION")));
                        if (box.getId().equals("menuPatho1")) {
                            menuMutation1.getItems().add(object.getString("NOM_MUTATION"));
                            menuMutation2.getItems().add(object.getString("NOM_MUTATION"));
                        }
                    }else{
                        if (box.getId().equals("menuPatho1")){
                            menuMutation1.getItems().clear();
                            menuMutation2.getItems().clear();
                        }
                    }
                }
            }
        }
    }



    /**
     * Cette méthode créé un phenotype avec la pathologie, les mutations et les prescriptions saisi précédemment
     * @param pathoBox liste déroulante de pathologies
     * @param mutaBox liste déroulante de mutations
     * @param prescriBox liste déroulante de prescriptions
     * @return objet Phenotype
     */
    public Phenotype getPathoInfo(ComboBox pathoBox, ComboBox mutaBox, ComboBox prescriBox){
        Phenotype pheno = null;
        if (pathoBox.getValue() != null){

            Pathology patho = null;
            for (Pathology p : listOfPatho){
                if (p.getNamePathology().equals(pathoBox.getValue())){
                    patho = p;
                }
            }

            if (mutaBox.getValue() != null){

                Mutation mutation = null;
                for (Mutation m : listMutations){
                    if (m.getMutationName().equals(mutaBox.getValue())){
                        mutation = m;
                    }
                }
                if (prescriBox.getValue() != null){

                    Prescription prescription = null;
                    for (Prescription pre : listPrescri){
                        if (pre.getPrescriptionName().equals(prescriBox.getValue())){
                            prescription = pre;
                        }
                    }
                    pheno = new Phenotype(0, null, prescription, mutation, patho, null);
                }else{
                    pheno = new Phenotype(0, null, null, mutation, patho, null);
                }
            }else if(prescriBox.getValue() != null){

                Prescription prescription = null;
                for (Prescription pre : listPrescri){
                    if (pre.getPrescriptionName().equals(prescriBox.getValue())){
                        prescription = pre;
                    }
                }
                pheno = new Phenotype(0, null, prescription, null, patho, null);
            }else {
                pheno = new Phenotype(0, null, null, null, patho, null);
            }
        }
        return pheno;
    }


    /**
     * Récupere toutes les informations saisi en page deux
     * @param menuPatho1 objet combobox
     * @param menuMutation1 objet combobox
     * @param menuPrescri1 objet combobox
     * @param menuMutation2 objet combobox
     * @param menuPrescri2 objet combobox
     * @param stage objet stage
     * @throws IOException
     */
    public boolean getPageTwoElements(ComboBox menuPatho1, ComboBox menuMutation1, ComboBox menuPrescri1, ComboBox menuMutation2, ComboBox menuPrescri2, Stage stage, Text error) throws IOException {

        boolean done = false;
        pheno1 = getPathoInfo(menuPatho1, menuMutation1, menuPrescri1);
        //sachant que la pathologie ne peut etre selectionner qu'une fois
        //on vérifie si une seconde mutation est renseigner ou non (sinon le pheno2 ne sera pas null)
        if (menuMutation2.getValue() != null){
            pheno2 = getPathoInfo(menuPatho1, menuMutation2, menuPrescri2);
        }

        if (pheno1 != null || pheno2 != null) {
            main.tools.switchScene(stage, "Projet/AjoutSujet/AddSubjectPage3_3.fxml", main.getAddSubjectPageController());
            done = true;
        }else{
            error.setText("Veuillez remplir au moins la première ligne.");
        }
        return done;
    }

    /**
     * Cette méthode requete la base de données et charge les différentes information à completer dans la page 3 d'ajout de sujet
     * @param menuDataType1 liste déroulante des données
     * @param menuDataType2 liste déroulante des données 2
     * @param menuUnit1 liste déroulante des unités
     * @param menuUnit2 liste déroulante des unités 2
     * @throws IOException
     */
    public void loadPageThreeList(ComboBox menuDataType1, ComboBox menuDataType2, ComboBox menuUnit1, ComboBox menuUnit2, TextField dataValue1, TextField dataValue2, int result) throws IOException {
        //set up for the ChoiceBoxes of the next page
        System.out.print("1");
        if (result == 1) {
            System.out.print("2");
            if (pheno1 == null) {
                System.out.print("3");
                menuDataType1.setDisable(true);
                menuUnit1.setDisable(true);
                dataValue1.setDisable(true);
            }
            if (pheno2 == null) {
                System.out.print("4");
                menuDataType2.setDisable(true);
                menuUnit2.setDisable(true);
                dataValue2.setDisable(true);
            }
        }
        //get all preop data types

        for (JSONObject object : DataBase.getAllPreopDataRequest()) {
            menuDataType1.getItems().add(object.getString("NOM_PREOP"));
            menuDataType2.getItems().add(object.getString("NOM_PREOP"));
            listPreopData.add(new PreopData(object.getInt("ID_PREOP"), object.getString("NOM_PREOP")));
        }

        //get all unit of the data base

        for (JSONObject object : DataBase.getAllUniteRequest()) {
            menuUnit1.getItems().add(object.getString("NOM_UNITE"));
            menuUnit2.getItems().add(object.getString("NOM_UNITE"));
            listUnit.add(new Unit(object.getInt("ID_UNITE"), object.getString("NOM_UNITE"), object.getString("DESCRIPTION_UNITE")));
        }
    }


    /**
     * Cette méthode permet de modifier les données préopératoire d'un phenotype donné
     * @param dataTypeBox objet combobox
     * @param unitBox objet combobox
     * @param valueData objet Text
     * @param pheno objet phenotype
     * @param error objet text
     */
    public void getPreopDataInfo(ComboBox dataTypeBox, ComboBox unitBox, TextField valueData, Phenotype pheno, Text error) {

            if (dataTypeBox.getValue() == null && unitBox.getValue() == null && valueData.getText().isEmpty()){
                //do nothing
            }else if (dataTypeBox.getValue() != null && unitBox.getValue() != null && !valueData.getText().isEmpty()) {
                error.setText("");
                Unit unit = null;
                for (Unit u : listUnit) {
                    if (unitBox.getValue().equals(u.getNameUnit())) {
                        unit = u;
                    }
                }

                PreopData preopD = null;
                for (PreopData p : listPreopData) {
                    if (dataTypeBox.getValue().equals(p.getNamePreop())) {
                        preopD = p;
                    }
                }

                pheno.setUnitPreopValue(unit);
                pheno.setPreopData(preopD);
                pheno.setPreopValue(Float.valueOf(valueData.getText()));


            } else if (dataTypeBox.getValue() != null && unitBox.getValue() == null || valueData.getText().isEmpty()) {
                error.setText("Ligne incomplète");
            } else if (!valueData.getText().isEmpty() && dataTypeBox.getValue() == null || unitBox.getValue() == null) {
                error.setText("Ligne incomplète");
            } else if (unitBox.getValue() != null && dataTypeBox.getValue() == null || valueData.getText().isEmpty()) {
                error.setText("Ligne incomplète");
            }
    }

    /**
     * cette méthode récupère toutes les saisi de la page 3 afin de creer le sujet
     * @param menuDataType1 objet ComboBox
     * @param menuUnit1 objet ComboBox
     * @param dataValue1 objet Text
     * @param menuDataType2 objet ComboBox
     * @param menuUnit2 objet ComboBox
     * @param dataValue2 objet Text
     * @param error objet Text
     * @throws IOException
     * @throws SQLException
     */
    public void getPageThreeElements(ComboBox menuDataType1, ComboBox menuUnit1, TextField dataValue1, ComboBox menuDataType2, ComboBox menuUnit2, TextField dataValue2, Text error) throws IOException, SQLException {
        getPreopDataInfo(menuDataType1, menuUnit1, dataValue1, pheno1, error);
        if (pheno2 != null) {
            getPreopDataInfo(menuDataType2, menuUnit2, dataValue2, pheno2, error);
        }

        addSubjectToBdd(subjectToAdd);
    }


    /**
     *  cette méthode permet d'ajouter un phenotype à la base de données en fonction d'un id sujet et d'un objet phenotype
     * @param subjectId id sujet
     * @param pheno objet Phenotype
     * @throws IOException
     */
    public void addPhenoToBdd(int subjectId, Phenotype pheno) throws IOException {
        if (pheno != null){
            if (pheno.getPathology() != null) {
                DataBase.createPhenoRequest(pheno.getUnitPreopValue().getIdUnit(), pheno.getPreopData().getIdPreop(), subjectId, pheno.getPathology().getIdPathology(), pheno.getPrescription().getIdPresciption(), pheno.getMutation().getIdMutation(), pheno.getPreopValue());
            } else if (pheno.getPathology() == null & pheno.getUnitPreopValue() != null) {
                DataBase.createPhenoNoPathoRequest(pheno.getUnitPreopValue().getIdUnit(), pheno.getPreopData().getIdPreop(), subjectId, pheno.getPreopValue());
            }
        }
    }


    /**
     * Cette méthode récupère toutes les informations saisi dans 3 pages d'ajout de sujet et l'ajoute à la base de données
     * @param subject objet Subject
     * @throws IOException
     */
    public void addSubjectToBdd(Subject subject) throws IOException, SQLException {

        if (species == 3){
            DataBase.createSubjectCellRequest(subject.getCodeSubject(), idGroup, subject.getAgeSubject(), main.tools.spaceToUnderscore(subject.getCommentSubject()), subject.getAgeUnit().getIdUnit());
        }else {
            DataBase.createSubjectRequest(subject.getCodeSubject(), idGroup, subject.getAgeSubject(), subject.getSexeSubject(), subject.getWeightSubject(), main.tools.spaceToUnderscore(subject.getCommentSubject()), subject.getWeightUnit().getIdUnit(), subject.getAgeUnit().getIdUnit());
        }
        for (JSONObject object : DataBase.getSubjectFromGroupRequest(idGroup)) {
            if (object.getString("CODE_SUJET").equals(subject.getCodeSubject()) &&
                    object.getInt("AGE_SUJET") == subject.getAgeSubject() &&
                    main.tools.intToBoolean(object.getInt("SEXE_SUJET")) == subject.getSexeSubject() &&
                    object.getInt("POIDS_SUJET") == subject.getWeightSubject()
            ){
                subject.setIdSubject(object.getInt("ID_SUJET"));
            }
        }


        addPhenoToBdd(subject.getIdSubject(), pheno1);
        addPhenoToBdd(subject.getIdSubject(), pheno2);

        //tracabilité
        Date maDate = new Date();
        String action = main.getCurrentUser().getUserName() +
                " a rajouté un patient qui a pour code : " + subject.getCodeSubject()
                + " le " + maDate;
        main.tools.applyTraceability(Tools.spaceToUnderscore(action));

    }

}
