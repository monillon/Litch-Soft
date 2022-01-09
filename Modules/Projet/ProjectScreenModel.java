package Modules.Projet;

import LITCH.Group;
import LITCH.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static LITCH.DataBase.getNbManipGroupRequest;

/**
 * Classe ProjectScreenModel représente gestion de l'affichage qui permet de gérer les groupes d'un projet
 */
public class ProjectScreenModel {

    private Main main;

    public ProjectScreenModel(Main newMain){
        main = newMain;
    }


    /**
     * Permet la gestion d'un groupe dans son Panel dans le fichier FXML "ProjectScreen"
     * @param pane
     * @param group objet groupe
     */
    public void buildGroupPanel(Pane pane, Group group) throws IOException {
        Text text1 = (Text) pane.getChildren().get(0);
        text1.setText("Nombre de sujet : " + group.getNbMembers());
        Text text2 = (Text) pane.getChildren().get(1);
        for (JSONObject object : getNbManipGroupRequest(group.getIdGroup())) {
            text2.setText("Nombre de manipulations : " + object.getString("NB_MANIP"));
        }
        Button buttonManipulation = (Button) pane.getChildren().get(3);
        buttonManipulation.setId("" + group.getIdGroup());
        Button buttonSubject = (Button) pane.getChildren().get(2);
        buttonSubject.setId("" + group.getIdGroup());
    }


    /**
    Permet d'ajouter un groupe dans le projet, en fonction du bouton cela affiche un écran dans les cas où l'on choisit d'ajouter soit, un humain, un rat, une cellule
     */
    public void addNewSubject(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();

        //Définit les choix possibles pour les menu buttons de la première page pour l'ajout d'un sujet humain à un groupe
        ObservableList<String> availableChoices = FXCollections.observableArrayList("PATHOLOGIQUES | NON TRAITES", "PATHOLOGIQUES | TRAITES", "NON PATHOLOGIQUES | NON TRAITES", "NON PATHOLOGIQUES | TRAITES");

        if (button.getId().equals("addHumanGroupButton")){

            Stage stage = (Stage) button.getScene().getWindow();
            main.tools.switchScene(stage, "Projet/AjoutSujet/AddHumanPage1_3.fxml", main.getAddSubjectPageController());
            main.getAddSubjectPageController().getMenuButtonGroup().getItems().addAll(availableChoices);
            availableChoices = FXCollections.observableArrayList("Mâle", "Femelle");
            main.getAddSubjectPageController().getMenuButtonSex().getItems().addAll(availableChoices);

        }else if (button.getId().equals("addRatGroupButton")){

            Stage stage = (Stage) button.getScene().getWindow();
            main.tools.switchScene(stage, "Projet/AjoutSujet/AddRatPage.fxml", main.getAddSubjectPageController());
            main.getAddSubjectPageController().getMenuButtonGroup().getItems().addAll(availableChoices);
            availableChoices = FXCollections.observableArrayList("Mâle", "Femelle");
            main.getAddSubjectPageController().getMenuButtonSex().getItems().addAll(availableChoices);

        }else if (button.getId().equals("addCellGroupButton")){

            Stage stage = (Stage) button.getScene().getWindow();
            main.tools.switchScene(stage, "Projet/AjoutSujet/AddCellPage.fxml", main.getAddSubjectPageController());
            main.getAddSubjectPageController().getMenuButtonGroup().getItems().addAll(availableChoices);
            availableChoices = FXCollections.observableArrayList("Mâle", "Femelle");
            main.getAddSubjectPageController().getMenuButtonSex().getItems().addAll(availableChoices);
        }



    }


    /**
     Permet de changer d'écran eet de passer sur un écran qui affiche les différentes informations d'un groupe et des sujets
     */
    public void goGroupView(ActionEvent e) throws IOException {

        Button button = (Button) e.getSource();

        Stage stage = (Stage) button.getScene().getWindow();
        main.tools.switchScene(stage, "Projet/Groupe/GroupView.fxml", main.getGroupViewController());

        main.getGroupViewController().updateGroupView(Integer.valueOf(button.getId()));
    }

    /**
     It allows to switch to the screen of a group and to see all manipulation's details of the group
     */
    public void goManipulationView(ActionEvent e) throws IOException {

        Button button = (Button) e.getSource();

        Stage stage = (Stage) button.getScene().getWindow();
        main.tools.switchScene(stage, "Projet/Manipulations/ManipulationView.fxml", main.getManipulationViewController());

        main.getManipulationViewController().updateManipulationView(Integer.valueOf(button.getId()));
    }

}
