package Modules.Menu;

import LITCH.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe MenuModel représente les méthodes derrière l'affichage
 */
public class MenuModel {

    private Main main;
    private MenuController menuController;

    public MenuModel(Main newMain, MenuController newMenuController){
        main = newMain;
        menuController = newMenuController;
    }

    /**
     Création des différents boutons du menu
     */
    public void loadMenuButton(String buttonID, String newTitle, Image imgPath, FlowPane menu) throws IOException {
        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/Menu/MenuProjectItem.fxml"));
        newFXML.setController(menuController);
        Pane pane = (Pane) newFXML.load();
        final Button button = (Button) pane.getChildren().get(0);
        button.setId(buttonID);
        button.setGraphic(new ImageView(imgPath));

        if (buttonID.equals("addProject")) {
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = (Stage) button.getScene().getWindow();
                        main.tools.switchScene(stage ,"Menu/AjoutProjet/AddProject.fxml", main.getAddProjectController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            button.setStyle("-fx-background-color: rgb(166,166,166); -fx-background-radius : 20px");
        }else if (buttonID.equals("admin")) {
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = (Stage) button.getScene().getWindow();
                        main.tools.switchScene(stage ,"Administration/AdminPage.fxml", main.getAdminPageController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            button.setStyle("-fx-background-color: rgb(255,87,87); -fx-background-radius : 20px");
        }else if (buttonID.equals("search")) {
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = (Stage) button.getScene().getWindow();
                        main.tools.switchScene(stage ,"Recherche/SearchPage.fxml", main.getSearchPageController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            button.setStyle("-fx-background-color: rgb(0,158,255); -fx-background-radius : 20px");
        }else if (buttonID.equals("fridge")) {
            button.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = (Stage) button.getScene().getWindow();
                        main.tools.switchScene(stage ,"ManagementFrigo/FridgeManagement.fxml", main.getFridgeManagementController());
                        main.getFridgeManagementController().setFridgeList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            button.setStyle("-fx-background-color: rgb(25,159,172); -fx-background-radius : 20px");
        }

        Text title = (Text) pane.getChildren().get(1);
        title.setText(newTitle);
        menu.getChildren().add(pane);
    }


    /**
     Chargement du menu, requete la base de données et renvoie tous les projets actuellement en cours
     */
    public void updateMenu(Text welcome, FlowPane menu) throws SQLException, IOException {

        Image image = null;
        for (JSONObject object : DataBase.getAllProjectRequest()){
            if (!object.has("message")) {
                if (object.get("IMG_PROJET").toString() != "null") {
                    try {
                        image = new Image(object.getString("IMG_PROJET"), 121, 95, true, true);
                    } catch (Exception e) {
                        // gestion de l'erreur
                    }
                    loadMenuButton(object.getString("NOM_PROJET"), main.tools.underscoreToSpace(object.getString("CODE_PROJET")), image, menu);
                } else {
                    loadMenuButton(object.getString("NOM_PROJET"), main.tools.underscoreToSpace(object.getString("CODE_PROJET")), null, menu);
                }
            }

        }

        //vérification du role de l'utilisateur, si l'utilisateur est un administrateur alors ces 2 boutons seront créés
        if (main.getCurrentUser().getRole().equals(Main.roles[0])) {
            loadMenuButton("addProject", "AJOUTER UN PROJET", new Image("/IMG/" + "plus.png", 121, 95, true, true), menu);
            loadMenuButton("admin", "ADMINISTRATION LIMS", new Image("/IMG/" + "system.png", 121, 95, true, true), menu);
            loadMenuButton("fridge", "GESTION DU FRIGO", new Image("/IMG/" + "fridge.png", 121, 95, true, true), menu);
        }else if (main.getCurrentUser().getRole().equals(Main.roles[1])){
            loadMenuButton("addProject", "AJOUTER UN PROJET", new Image("/IMG/" + "plus.png", 121, 95, true, true), menu);
            loadMenuButton("fridge", "GESTION DU FRIGO", new Image("/IMG/" + "fridge.png", 121, 95, true, true), menu);
        }
        loadMenuButton("search", "RECHERCHE",new Image("/IMG/" + "search.png", 121, 95, true , true), menu);

        welcome.setText("Bienvenue " + main.getCurrentUser().getUserName());

    }



    /**
     Creation de l'objet Project et redirection vers l'écran projet
     */
    public void loadProject(ActionEvent e) throws IOException, ParseException {
        Button button = (Button) e.getSource();

        buildProject(button.getId());

        Stage stage = (Stage) button.getScene().getWindow();
        main.tools.switchScene(stage,"Projet/ProjectScreen.fxml", main.getProjectScreenController());

        main.getProjectScreenController().loadProjectView(main.getCurrentProject());

    }

    /**
     * Creation d'un nouveau projet
     * @param projectName nom du projet
     * @throws IOException
     * @throws ParseException
     */
    public void buildProject(String projectName) throws IOException, ParseException {

        Project currentProject = null;
        for (JSONObject object : DataBase.getAllProjectRequest()) {
            if (object.getString("NOM_PROJET").equals(projectName)) {
                main.setCurrentProject(null);
                currentProject = new Project(object.getInt("ID_PROJET"), object.getString("CODE_PROJET"), Tools.underscoreToSpace(object.getString("NOM_PROJET")), object.get("IMG_PROJET").toString());
                main.setCurrentProject(currentProject);
            }
        }

        //Creation of all groups of the Project
        allGroupsRequest(currentProject);

        //Creation of all Subjects for all groups
        for (Group group : currentProject.getListOfGroup()) {
            for (JSONObject object : DataBase.getAllSubjectRequest(group)) {
                if (object.has("message") == false){
                    Subject subject = new Subject(object.getInt("ID_SUJET"), object.getString("CODE_SUJET"), object.getInt("AGE_SUJET"), new Unit(object.getInt("UNI_ID_UNITE"), null, null),main.tools.intToBoolean(object.getInt("SEXE_SUJET")), object.getInt("POIDS_SUJET"), new Unit(object.getInt("ID_UNITE"), null, null),object.getString("COMMENTAIRE_SUJET"));
                    group.addSubject(subject);
                    requestSubjectPhenotype(subject);
                    requestSubjectSample(subject);
                }
            }
        }
        main.setCurrentProject(currentProject);
    }

    /**
     * Requete tous les organism de la base de données et les retourne dans une liste
     */
    public ArrayList<Organism> requestGroupOrganism() throws IOException {
        ArrayList<Organism> organism = new ArrayList<Organism>();
        for (JSONObject object : DataBase.getGroupOrganismRequest()) {
            organism.add(new Organism(object.getInt("ID_ORGANISME"), object.getString("NOM_ORGANISME")));
        }
        return organism;
    }

    /**
     * Prend un subject en paramètre et requete tous les prélèvements liés à ce sujet
     * @param subject objet Subject
     * @throws IOException
     * @throws ParseException
     */
    public void requestSubjectSample(Subject subject) throws IOException, ParseException {

        for (JSONObject object : DataBase.getSubjectSampleRequest(subject.getIdSubject())) {
            if (object.has("message") == false) {
                SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(object.getString("DATE_HEURE_PRELEVEMENT"));
                subject.addSample(new Sample(object.getInt("ID_PRELEVEMENT"), object.getString("NOM_TISSU"), object.getString("NOM_ORGANE"), date, main.tools.checkStringIsNull(object,"COMMENTAIRE_PRELEVEMENT")));
            }
        }
    }


    /**
     * Prend un paramètre un subject et requete son phenotype
     * @param subject objet Subject
     * @throws IOException
     */
    public void requestSubjectPhenotype(Subject subject) throws IOException {

        for (JSONObject object : DataBase.getPhenoSubject(subject.getIdSubject())) {
            if (object.has("message") == false) {
                Phenotype phenotype = new Phenotype(object.getFloat("VALEUR_PREOP"), main.tools.searchForUnit(object.getString("NOM_UNITE")), null, null, null, new PreopData(object.getInt("ID_PREOP"), object.getString("NOM_PREOP")));

                phenotype.setPathology(new Pathology(object.getInt("ID_PATHOLOGIE"), object.getString("NOM_PATHOLOGIE")));
                if (!object.isNull("CLASSE")) {
                    phenotype.setMutation(new Mutation(object.getInt("ID_MUTATION"), object.getString("NOM_MUTATION"), object.getString("CLASSE")));
                }else{
                    phenotype.setMutation(new Mutation(object.getInt("ID_MUTATION"), object.getString("NOM_MUTATION"), null));
                }

                phenotype.setPrescription(new Prescription(object.getInt("ID_PRESCRIPTION"), object.getString("NOM_PRESCRIPTION")));
                subject.setPhenotype(phenotype);
            }
        }
    }


    /**
     * prend un paramètre un objet Project et requete tous les groupes associé à ce projet
     * @param currentProject objet Project
     * @throws IOException
     */
    public void allGroupsRequest(Project currentProject) throws IOException {

        Organism human = null;
        Organism rat = null;
        Organism cell = null;

        for (Organism organism : requestGroupOrganism()){
            if (organism.getIdOrganism() == 1){
                human = organism;
            }else if (organism.getIdOrganism() == 2) {
                rat = organism;
            }else if (organism.getIdOrganism() == 3) {
                cell = organism;
            }
        }

        //Human groups
        for (JSONObject object : DataBase.getHumanGroupsProjectRequest(currentProject)) {
            currentProject.addGroup(object.getInt("ID_GRP"), object.getString("CODE_GRP"), object.getString("NOM_GRP"), main.tools.intToBoolean(object.getInt("GRP_PATHO")), main.tools.intToBoolean(object.getInt("GRP_TRAITE")), object.getInt("NB_MEMBRE"), human);
        }

        //Rat groups
        for (JSONObject object : DataBase.getRatGroupsProjectRequest(currentProject)) {
            currentProject.addGroup(object.getInt("ID_GRP"), object.getString("CODE_GRP"), object.getString("NOM_GRP"), main.tools.intToBoolean(object.getInt("GRP_PATHO")), main.tools.intToBoolean(object.getInt("GRP_TRAITE")), object.getInt("NB_MEMBRE"), rat);
        }

        //Cell groups
        for (JSONObject object : DataBase.getCellGroupsProjectRequest(currentProject)) {
            currentProject.addGroup(object.getInt("ID_GRP"), object.getString("CODE_GRP"), object.getString("NOM_GRP"), main.tools.intToBoolean(object.getInt("GRP_PATHO")), main.tools.intToBoolean(object.getInt("GRP_TRAITE")), object.getInt("NB_MEMBRE"), cell);
        }
    }
}
