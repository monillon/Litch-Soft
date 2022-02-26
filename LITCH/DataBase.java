package LITCH;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Cette classe regroupe toutes les requêtes possible dans la base de donnée
 *
 * @author ben14
 */
public class DataBase {


    /**
     * Method splitResultQuery
     * Création des tableaux après une requête dans la bdd
     */
    public static ArrayList<JSONObject> splitResultQuery(String objectToSplit) {

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


    /**
     * Method sendRequest
     * Permet d'envoyer les méthode contenant les requêtes à la bdd
     */
    public static ArrayList<JSONObject> sendRequest(String request) throws IOException {
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

    /**
     * Method insertRequest
     * Permet de charger un lien de requête pour l'envoyer dans à la bdd
     */
    public static void insertRequest(String request) throws IOException {
        URL url = new URL(request);
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

    /**
     * Method getLocationRequest
     * Requête qui permet de chercher dans la bdd l'emplacement d'un echantillon grâce à son identifiant
     */
    public static ArrayList<JSONObject> getLocationRequest(int sampleId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/sample_localisation.php?id_p=" + sampleId;
        return sendRequest(request);
    }

    /**
     * Method connexionRequest
     * Requête qui permet de chercher dans la bdd un utilsateur avec son identifiant et son mot de passe
     */
    public static ArrayList<JSONObject> connexionRequest(String identity, String password) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/utilisateur/read_one_with_role.php?username=" + identity + "&password=" + password;
        return sendRequest(request);
    }

    /**
     * Method getAllProjectRequest
     * Requête qui permet de chercher dans la bdd tous les projets
     */
    public static ArrayList<JSONObject> getAllProjectRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/projet/readAll.php";
        return sendRequest(request);
    }

    /**
     * Method getAllSubjectRequest
     * Requête qui permet de chercher dans la bdd tous les sujets d'un groupe de sujet
     */
    public static ArrayList<JSONObject> getAllSubjectRequest(Group group) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/sujet/read.php?s=" + group.getIdGroup();
        return sendRequest(request);
    }

    /**
     * Method getAllModeleRequest
     * Requête qui permet de chercher dans la bdd tous les modèles de manipulation
     */
    public static ArrayList<JSONObject> getAllModeleRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/modele_manip/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllUserRequest
     * Requête qui permet de chercher dans la bdd tous les utlisateurs
     */
    public static ArrayList<JSONObject> getAllUserRequest() throws IOException {
        String request = "https://litch-dev.geniephy.net/API/utilisateur/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getGroupOrganismRequest
     * Requête qui permet de chercher dans la bdd tous les organismes
     */
    public static ArrayList<JSONObject> getGroupOrganismRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/organisme/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllPreopDataRequest
     * Requête qui permet de chercher dans la bdd toutes les données préopératoires
     */
    public static ArrayList<JSONObject> getAllPreopDataRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/donnees_preop/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllPathoRequest
     * Requête qui permet de chercher dans la bdd toutes les pathologies
     */
    public static ArrayList<JSONObject> getAllPathoRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/pathologie/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllUniteRequest
     * Requête qui permet de chercher dans la bdd toutes les unités
     */
    public static ArrayList<JSONObject> getAllUniteRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/unite/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getLocationRequest
     * Requête qui permet de chercher dans la bdd tous les prélèvement d'un sujet
     */
    public static ArrayList<JSONObject> getSubjectSampleRequest(int subjectId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prelevement/read_sujet.php?s=" + subjectId;
        return sendRequest(request);
    }

    /**
     * Method getSubjectPhenotypeRequest
     * Requête qui permet de chercher dans la bdd toutes les données préopératoires d'un sujet
     */
    public static ArrayList<JSONObject> getSubjectPhenotypeRequest(Subject subject) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/donnees_preop/read.php?s=" + subject.getIdSubject();
        return sendRequest(request);
    }

    /**
     * Method getPhenotypePathologyRequest
     * Requête qui permet de chercher dans la bdd toutes les pathologies d'un sujet
     */
    public static ArrayList<JSONObject> getPhenotypePathologyRequest(int subjectId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/pathologie/read.php?s=" + subjectId;
        return sendRequest(request);
    }

    /**
     * Method getPhenotypeMutationRequest
     * Requête qui permet de chercher dans la bdd toutes les mutations d'un sujet
     */
    public static ArrayList<JSONObject> getPhenotypeMutationRequest(int subjectId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/mutation/read.php?s=" + subjectId;
        return sendRequest(request);
    }

    /**
     * Method getMutationRequest
     * Requête qui permet de chercher dans la bdd les mutations liées à une pathologie
     */
    public static ArrayList<JSONObject> getMutationRequest(int idPatho) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/mutation/read_patho.php?s=" + idPatho;
        return sendRequest(request);
    }

    /**
     * Method getGroupRequest
     * Requête qui permet de chercher dans la bdd tous les groupes d'une même espèce dans un projet
     */
    public static ArrayList<JSONObject> getGroupRequest(int projectId, int species) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/groupe/read.php?a=" + projectId + "&b=" + species;
        return sendRequest(request);
    }

    /**
     * Method getPhenotypePrescriptionRequest
     * Requête qui permet de chercher dans la bdd toutes les prescriptions d'un sujet
     */
    public static ArrayList<JSONObject> getPhenotypePrescriptionRequest(int subjectId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prescription/read.php?s=" + subjectId;
        return sendRequest(request);
    }

    /**
     * Method getSubjectFromGroupRequest
     * Requête qui permet de chercher dans la bdd tous les sujets d'un groupe de sujet
     */
    public static ArrayList<JSONObject> getSubjectFromGroupRequest(int idGroup) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/sujet/read.php?s=" + idGroup;
        return sendRequest(request);
    }

    /**
     * Method getHumanGroupsProjectRequest
     * Requête qui permet de chercher dans la bdd tous les groupes humains d'un projet avec son identifiant
     */
    public static ArrayList<JSONObject> getHumanGroupsProjectRequest(Project currentProject) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/groupe/read.php?a=" + currentProject.getidProject() + "&b=" + "1";
        return sendRequest(request);
    }

    /**
     * Method getRatGroupsProjectRequest
     * Requête qui permet de chercher dans la bdd tous les groupes de rat d'un projet avec son identifiant
     */
    public static ArrayList<JSONObject> getRatGroupsProjectRequest(Project currentProject) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/groupe/read.php?a=" + currentProject.getidProject() + "&b=" + "2";
        return sendRequest(request);
    }

    /**
     * Method getCellGroupsProjectRequest
     * Requête qui permet de chercher dans la bdd tous les groupes de cellule d'un projet avec son identifiant
     */
    public static ArrayList<JSONObject> getCellGroupsProjectRequest(Project currentProject) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/groupe/read.php?a=" + currentProject.getidProject() + "&b=" + "3";
        return sendRequest(request);
    }

    /**
     * Method getLocationListRequest
     * Requête qui permet de chercher dans la bdd tous les emplacements
     */
    public static ArrayList<JSONObject> getLocationListRequest(int id_etagere, int id_boite, String id_rack) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/read_libre.php?id_etagere=" + id_etagere + "&id_boite=" + id_boite + "&id_rack=" + id_rack;
        return sendRequest(request);
    }

    /**
     * Method getAllOrganRequest
     * Requête qui permet de chercher dans la bdd tous les types d'organ
     */
    public static ArrayList<JSONObject> getAllOrganRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/organe/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllTissuRequest
     * Requête qui permet de chercher dans la bdd tous les types de tissu
     */
    public static ArrayList<JSONObject> getAllTissuRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/tissu/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method createProjectRequest
     * Requête qui permet de créer dans la bdd un projet avec ses attributs
     */
    public static void createProjectRequest(String projectCode, String fullNameProject, String selectedFileName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/projet/create.php?code_projet=" + projectCode + "&nom_projet=" + fullNameProject + "&img_projet=" + "/IMG/" + selectedFileName;
        insertRequest(request);
    }

    /**
     * Method createHumanGroupRequest1
     * Requête qui permet de créer dans la bdd le 1 er groupe humain
     */
    public static void createHumanGroupRequest1(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=1&&id_projet=" + idProject + "&&code_grp=HNPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Humains_non_pathologique_non_traités&&grp_patho=0&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createHumanGroupRequest2
     * Requête qui permet de créer dans la bdd le 2 eme groupe humain
     */
    public static void createHumanGroupRequest2(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=1&&id_projet=" + idProject + "&&code_grp=HPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Humains_pathologique_non_traités&&grp_patho=1&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createHumanGroupRequest3
     * Requête qui permet de créer dans la bdd le 3 eme groupe humain
     */
    public static void createHumanGroupRequest3(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=1&&id_projet=" + idProject + "&&code_grp=HNPT" + idProject + "&&nom_grp=" + projectCode + "_:_Humains_non_pathologique_traités&&grp_patho=0&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method createHumanGroupRequest4
     * Requête qui permet de créer dans la bdd le 4 eme groupe humain
     */
    public static void createHumanGroupRequest4(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=1&&id_projet=" + idProject + "&&code_grp=HPT" + idProject + "&&nom_grp=" + projectCode + "_:_Humains_pathologique_traités&&grp_patho=1&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method createRatGroupRequest1
     * Requête qui permet de créer dans la bdd le 1 er groupe de rat
     */
    public static void createRatGroupRequest1(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=2&&id_projet=" + idProject + "&&code_grp=RNPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Rats_non_pathologique_non_traités&&grp_patho=0&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createRatGroupRequest2
     * Requête qui permet de créer dans la bdd le 2 eme groupe de rat
     */
    public static void createRatGroupRequest2(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=2&&id_projet=" + idProject + "&&code_grp=RPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Rats_pathologique_non_traités&&grp_patho=1&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createRatGroupRequest3
     * Requête qui permet de créer dans la bdd le 3 eme groupe de rat
     */
    public static void createRatGroupRequest3(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=2&&id_projet=" + idProject + "&&code_grp=RNPT" + idProject + "&&nom_grp=" + projectCode + "_:_Rats_non_pathologique_traités&&grp_patho=0&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method createRatGroupRequest4
     * Requête qui permet de créer dans la bdd le 4 eme groupe de rat
     */
    public static void createRatGroupRequest4(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=2&&id_projet=" + idProject + "&&code_grp=RPT" + idProject + "&&nom_grp=" + projectCode + "_:_Rats_pathologique_traités&&grp_patho=1&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method createCellGroupRequest1
     * Requête qui permet de créer dans la bdd le 1 er groupe de cellule
     */
    public static void createCellGroupRequest1(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=3&&id_projet=" + idProject + "&&code_grp=CNPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Cellules_non_pathologique_non_traités&&grp_patho=0&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createCellGroupRequest2
     * Requête qui permet de créer dans la bdd le 2 eme groupe de cellule
     */
    public static void createCellGroupRequest2(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=3&&id_projet=" + idProject + "&&code_grp=CPNT" + idProject + "&&nom_grp=" + projectCode + "_:_Cellules_pathologique_non_traités&&grp_patho=1&&grp_traite=0";
        insertRequest(request);
    }

    /**
     * Method createCellGroupRequest3
     * Requête qui permet de créer dans la bdd le 3 eme groupe de cellule
     */
    public static void createCellGroupRequest3(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=3&&id_projet=" + idProject + "&&code_grp=CNPT" + idProject + "&&nom_grp=" + projectCode + "_:_Cellules_non_pathologique_traités&&grp_patho=0&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method createCellGroupRequest4
     * Requête qui permet de créer dans la bdd le 4 eme groupe de cellule
     */
    public static void createCellGroupRequest4(int idProject, String projectCode) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/groupe/insert.php?id_organisme=3&&id_projet=" + idProject + "&&code_grp=CPT" + idProject + "&&nom_grp=" + projectCode + "_:_Cellules_pathologique_traités&&grp_patho=1&&grp_traite=1";
        insertRequest(request);
    }

    /**
     * Method getLocationRequest
     * Requête qui permet de créer dans la bdd une manipulation avec ses attributs
     */
    public static void createManipComRequest(int userId, int modeleId, int sampleId, String dateFinale, String com) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/manipulation/create.php?id_utilisateur=" + userId
                + "&id_modele_manip=" + modeleId
                + "&id_prelevement=" + sampleId
                + "&date_heure_manipulation=" + dateFinale
                + "&commentaire_manipulation=" + com;
        insertRequest(request);
    }

    /**
     * Method createPhenoRequest
     * Requête qui permet de créer dans la bdd un phénotype avec tous ses attributs
     */
    public static void createPhenoRequest(int unitId, int preopId, int subjectId, int pathoId, int prescrId, int mutId, float preopVal) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/phenotype/create_pheno_preop.php?id_unite=" + unitId +
                "&id_preop=" + preopId +
                "&id_sujet=" + subjectId +
                "&id_pathologie=" + pathoId +
                "&id_prescription=" + prescrId +
                "&id_mutation=" + mutId +
                "&valeur_preop=" + preopVal;

        insertRequest(request);
    }

    /**
     * Method createPhenoRequest
     * Requête qui permet de créer dans la bdd un phénotype sans pathologie ni mutation et prescription
     */
    public static void createPhenoNoPathoRequest(int unitId, int preopId, int subjectId, float preopVal) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/phenotype/create_pheno_no_patho.php?id_unite=" + unitId +
                "&id_preop=" + preopId +
                "&id_sujet=" + subjectId +
                "&valeur_preop=" + preopVal;

        insertRequest(request);
    }


    /**
     * Method createSubjectRequest
     * Requête qui permet de créer dans la bdd un sujet humain/rat avec tous ses attributs
     */
    public static void createSubjectRequest(String codeSubject, int idGroup, int subjectAge, boolean subjectSexe, int subjectWeight, String com, int UnitWId, int UnitAId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/sujet/create.php?code_sujet=" + codeSubject +
                "&id_grp=" + idGroup +
                "&age_sujet=" + subjectAge +
                "&sexe_sujet=" + subjectSexe +
                "&poids_sujet=" + subjectWeight +
                "&commentaire_sujet=" + com +
                "&uni_id_unite=" + UnitAId +
                "&id_unite=" + UnitWId;
        insertRequest(request);
    }

    /**
     * Method createSubjectCellRequest
     * Requête qui permet de créer dans la bdd un sujet de type cellule avec tous ses attributs
     */
    public static void createSubjectCellRequest(String codeSubject, int idGroup, int subjectAge, String com, int UnitAId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/sujet/create_cell.php?code_sujet=" + codeSubject +
                "&id_grp=" + idGroup +
                "&age_sujet=" + subjectAge +
                "&commentaire_sujet=" + com +
                "&uni_id_unite=" + UnitAId;
        insertRequest(request);
    }

    /**
     * Method createOrganRequest
     * Requête qui permet de créer dans la bdd un type d'organe grâce à un nom
     */
    public static void createOrganRequest(String organName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/organe/insert.php?nom_organe=" + organName;
        insertRequest(request);
    }

    /**
     * Method createTissueRequest
     * Requête qui permet de créer dans la bdd un type de tissu grâce à un nom
     */
    public static void createTissueRequest(String tissueName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/tissu/insert.php?nom_tissu=" + tissueName;
        insertRequest(request);
    }

    /**
     * Method createPathoRequest
     * Requête qui permet de créer dans la bdd une pathologie grâce à un nom
     */
    public static void createPathoRequest(String pathoName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/pathologie/insert.php?nom_pathologie=" + pathoName;
        insertRequest(request);
    }

    /**
     * Method createMutationRequest
     * Requête qui permet de créer dans la bdd une mutation avec son nom et de la relié à un identifiant pathologie
     */
    public static void createMutationRequest(int idPatho, String newMutationName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/mutation/insert.php?id_pathologie=" + idPatho + "&&nom_mutation=" + newMutationName;
        insertRequest(request);
    }

    /**
     * Method createPreopDataRequest
     * Requête qui permet de créer dans la bdd une donnée préopératoire avec son nom
     */
    public static void createPreopDataRequest(String preopName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/donnees_preop/insert.php?nom_preop=" + preopName;
        insertRequest(request);
    }

    /**
     * Method createUniteRequest
     * Requête qui permet de créer dans la bdd une unité avec son nom et sa description
     */
    public static void createUniteRequest(String unitName, String unitDescription) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/unite/insert.php?nom_unite=" + unitName + "&&description_unite=" + unitDescription;
        insertRequest(request);
    }

    /**
     * Method createPrescriRequest
     * Requête qui permet de créer dans la bdd une prescription grâce a un nom de prescription
     */
    public static void createPrescriRequest(String prescriName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/prescription/insert.php?nom_prescription=" + prescriName;
        insertRequest(request);
    }

    /**
     * Method createUserRequest
     * Requête qui permet de créer dans la bdd un utilisateur avec son identifiant et son mot de passe
     */
    public static void createUserRequest(String userName, String userPassword, int id_role) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/utilisateur/insert.php?nom_u=" + userName + "&mdp_u=" + userPassword + "&id_role=" + id_role;
        insertRequest(request);
    }

    /**
     * Method getAllTechniqueRequest
     * Requête qui permet de chercher dans la bdd toutes les techniques utilisables
     */
    public static ArrayList<JSONObject> getAllTechniqueRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/technique/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllProtocoleRequest
     * Requête qui permet de chercher dans la bdd tous les protocoles utilisables
     */
    public static ArrayList<JSONObject> getAllProtocoleRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/protocole/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method createModeleManipRequest
     * Requête qui permet de créer un modèle de manipulation avec tous ses attributs
     */
    public static void createModeleManipRequest(String nom_modele_manip, int id_technique, int id_protocole) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/modele_manip/create.php?nom_modele_manip=" + nom_modele_manip + "&id_technique=" + id_technique + "&id_protocole=" + id_protocole;
        insertRequest(request);
    }

    /**
     * Method getAllFrigoRequest
     * Requête qui permet de chercher dans la bdd tous les frigos
     */
    public static ArrayList<JSONObject> getAllFrigoRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/frigo/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getAllEtagereRequest
     * Requête qui permet de chercher dans la bdd toutes les étagères
     */
    public static ArrayList<JSONObject> getAllEtagereRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/etagere/read_all.php";
        return sendRequest(request);
    }

    /**
     * Method getRackExistantRequest
     * Requête qui permet de chercher dans la bdd tous les racks d'une meme étagère grâce a l'idrentifiant de l'étagere
     */
    public static ArrayList<JSONObject> getRackExistantRequest(int getShelfIDAdd) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/read_rack_existant.php?id_etagere=" + getShelfIDAdd;
        return sendRequest(request);
    }

    /**
     * Method getRackExistantRequest
     * Requête qui permet de chercher dans la bdd tous les racks d'une meme étagère grâce a l'idrentifiant de l'étagere
     *
     * @param getShelfIDAdd
     * @param getRackId
     */
    public static ArrayList<JSONObject> getBoxExistantRequest(int getShelfIDAdd, String getRackId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/read_box_existant.php?id_etagere=" + getShelfIDAdd + "&id_rack=" + getRackId;
        return sendRequest(request);
    }

    /**
     * Method createAddSampleToBddRequest
     * Requête qui permet de créer dans la bdd un emplacement de boite dans une certaine étagère
     */
    public static void createAddSampleToBddRequest() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/create_slot.php";
        insertRequest(request);
    }

    /**
     * Method deleteSlotRequest
     * Requête qui permet de supprimer dans la bdd un emplacement d'une boite
     */
    public static void deleteSlotRequest(Object boxListRemove) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/delete_slot.php?id_boite=" + boxListRemove;
        insertRequest(request);
    }

    /**
     * Method createSampleRequest
     * Requête qui permet de  créer dans la bdd un identifiant avec tous ses attributs de base
     */
    public static void createSampleRequest(int subjectId, int organId, int tissueId, String sampleDate, String sampleHour) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prelevement/create.php?id_sujet=" + subjectId + "&date_heure_prelevement=" + sampleDate + "%" + sampleHour + "&id_organe=" + organId + "&id_tissu=" + tissueId + "&commentaire_prelevement=";
        insertRequest(request);
    }

    /**
     * Method updateLocationDateRequest
     * Requête qui permet de mettre à jour dans la bdd la localisation d'un échantillon grâce a son identifiant et sa nouvelle localisation
     */
    public static void updateLocationDateRequest(int locationId, int libre, String sampleId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/emplacement/update.php?id_emplacement=" + locationId + "&libre=" + libre + "&id_prelevement=" + sampleId;
        insertRequest(request);
    }

    /**
     * Method getIdPrelevementByBoxRequest
     * Requête qui permet de chercher dans la bdd l'emplacement d'un prélèvement grâce à son étagère et son numéro de boîte
     */
    public static ArrayList<JSONObject> getIdPrelevementByBoxRequest(int getShelfIDAdd, Object boxListRemove) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/read_id_prelevement_by_box.php?id_etagere=" + getShelfIDAdd + "&id_boite=" + boxListRemove;
        return sendRequest(request);
    }

    /**
     * Method deleteManipRequest
     * Requête qui permet de supprimer de la bdd une manipulaion grâce à l'identifiant d'un prélèvement
     */
    public static void deleteManipRequest(int prelevementId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/manipulation/delete_manip.php?id_prelevement=" + prelevementId;
        insertRequest(request);
    }

    /**
     * Method deletePrelevRequest
     * Requête qui permet de supprimer de la bdd un prélèvement grâce à son identifiant
     */
    public static void deletePrelevRequest(int prelevementId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/prelevement/delete_prelev.php?id_prelevement=" + prelevementId;
        insertRequest(request);
    }

    /**
     * Method getManipulationRequest
     * Requête qui permet de chercher dans la bdd une manipulation grâce à un identifiant de groupe
     */
    public static ArrayList<JSONObject> getManipulationRequest(int idGroup) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/manipulation/read_grp.php?id_grp=" + idGroup;
        return sendRequest(request);
    }

    /**
     * Method getManipRequest
     * Requête qui permet de chercher dans la bdd une manipulation grâce à son identifiant
     */
    public static ArrayList<JSONObject> getManipRequest(int idManip) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/manipulation/read_manip.php?id_manip=" + idManip;
        return sendRequest(request);
    }


    /**
     * Method createProtocoleRequest
     * Requête qui permet d'insérer à la bdd un protocole grâce à un nom
     */
    public static void createProtocoleRequest(String newProtocoleName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/protocole/create.php?nom_protocole=" + newProtocoleName;
        insertRequest(request);
    }

    /**
     * Method createTechniqueRequest
     * Requête qui permet d'insérer à la bdd une technique grâce à un nom
     */
    public static void createTechniqueRequest(String newTechniqueName) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/technique/create.php?nom_technique=" + newTechniqueName;
        insertRequest(request);
    }


    public static ArrayList<JSONObject> searchSubjectRequest(int id_projet, String search) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/sujet/read_search.php?id_projet=" + id_projet + "&search=" + search;
        return sendRequest(request);
    }


    public static ArrayList<JSONObject> searchSampleRequest(int id_projet, String search) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prelevement/read_search.php?id_projet=" + id_projet + "&search=" + search;
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> searchManipRequest(int id_projet, String search) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/manipulation/read_search.php?id_projet=" + id_projet + "&search=" + search;
        return sendRequest(request);
    }


    public static ArrayList<JSONObject> getNbManipGroupRequest(int id_grp) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/manipulation/read_grp_count.php?id_grp=" + id_grp;
        return sendRequest(request);
    }

    public static void createSingleSlot(int id_etagere, String id_rack, int id_prelevement) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/create_single_slot.php?id_etagere=" + id_etagere + "&id_rack=" + id_rack + "&id_prelevement=" + id_prelevement;
        insertRequest(request);
    }

    public static ArrayList<JSONObject> getAllRoles() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/role/read_all.php";
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getOneRoles(String role_name) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/role/read_one.php?nom_role=" + role_name;
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getShelfByFridge(int fridgeId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/etagere/read_by_fridge.php?id_frigo=" + fridgeId;
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getBoxSlot(int fridgeId, int shelfId, String drawerId, int boxId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/emplacement/box_slot.php?id_frigo=" + fridgeId + "&id_etagere=" + shelfId + "&id_rack=" + drawerId + "&id_boite=" + boxId;
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getSample(int sampleId) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prelevement/read_sample.php?id_p=" + sampleId;
        return sendRequest(request);
    }


    public static ArrayList<JSONObject> getTemporaryBox() throws IOException {
        String request = "https://litch-dev.geniephy.net/API/emplacement/read_temporary_box.php";
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getLocationById(int id_emplacement) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/emplacement/read_one_location.php?id_emplacement=" + id_emplacement;
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getLocationIdRequest(int sampleId) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/emplacement/sample_localisation_id.php?id_p=" + sampleId;
        return sendRequest(request);
    }

    public static void insertTrace(int idUser, Date date, String action) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/tracabilite/create.php?id_user=" + idUser + "&date=" + date + "&action=" + action;
        insertRequest(request);
    }

    public static ArrayList<JSONObject> getAllMutation() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/mutation/read_all.php";
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getAllPrescription() throws IOException {
        String request = "http://litch-dev.geniephy.net/API/precription/read_all.php";
        return sendRequest(request);
    }

    public static ArrayList<JSONObject> getPhenoSubject(int id_sujet) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/phenotype/read.php?id_s=" + id_sujet;
        return sendRequest(request);
    }

    /**
     * Method updateCommentaireManipulation
     * Requête qui permet de mettre à jour un commentaire d'une manipulation
     */
    public static void updateCommentaireManipulation(String commentaire_manipulation, int id_manipulation) throws IOException {
        String request = "http://litch-dev.geniephy.net/API/manipulation/update_commentaire.php?commentaire_manipulation=" + commentaire_manipulation + "&id_manipulation=" + id_manipulation;
        insertRequest(request);
    }

    /**
     * Method deleteTissuRequest
     * Requête qui permet de supprimer dans la bdd un tissu
     * @param id_tissu id de la bdd correspondant au tissu
     */
    public static void deleteTissuRequest(int id_tissu) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/tissu/delete_tissu.php?id_tissu=" + id_tissu;
        insertRequest(request);
    }

    /**
     * Permet de faire une requête pour récupérer un tissu grâce à son id
     * @param id_tissu l'id du tissu
     * @return l'objet tissu de BDD
     */
    public static ArrayList<JSONObject> getOneTissu(int id_tissu) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/tissu/read_one.php?id_tissu=" + id_tissu;
        return sendRequest(request);
    }

    /**
     * Permet de faire une requête pour récupérer les prélèvements liés à un tissu grâce à son id
     * @param id_tissu l'id du tissu
     * @return l'objet prélèvement de BDD
     */
    public static ArrayList<JSONObject> getPrelevementLieTissu(int id_tissu) throws IOException {
        String request = "https://litch-dev.geniephy.net/API/prelevement/read_tissu.php?id_tissu=" + id_tissu;
        return sendRequest(request);
    }


}