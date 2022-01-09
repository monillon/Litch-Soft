package Modules.Projet.Manipulations;


import LITCH.*;
import Modules.Projet.Manipulations.ManipulationViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class ManipulationViewController {

    @FXML private Button home;
    @FXML private VBox vboxSubject;
    @FXML private Text manipField;
    @FXML private Text prelevField;
    @FXML private Text manipulateurField;
    @FXML private Pane paneSubjectItem;
    @FXML private Button selectManip;
    @FXML private Text currentProjectNameView;

    @FXML private Text codePatientField;
    @FXML private Text sexeField;
    @FXML private Text ageField;
    @FXML private Text prelevDetailsField;
    @FXML private Text modeleManipField;
    @FXML private TextArea commentairesField;
    @FXML private Text utilisateurField;
    @FXML private Text date;
    @FXML private Text donneesBrutesField;
    @FXML private Text resultatField;
    @FXML private Button add_commentaire;
    @FXML private Button add_brute;
    @FXML private Button add_analyse;
    @FXML private ComboBox preopDataList;
    @FXML private ComboBox pathoList;
    @FXML private ComboBox prescriList;
    @FXML private Pane paneSampleDetails;

    @FXML private Button previousButton;

    @FXML private Text localisation;

    private Main main;
    private ManipulationViewModel manipulationViewModel;

    public ManipulationViewController(Main newMain){
        main = newMain;
        manipulationViewModel = new ManipulationViewModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    /**
     * It allows to update the groupView screen
     * @param groupID
     * @throws IOException
     */
    public void updateManipulationView(int groupID) throws IOException {
        applyRoles();
        manipulationViewModel.updateManipulationView(groupID, currentProjectNameView, vboxSubject);
    }


    /**
     * It show details of the selected subject
     * @param e
     * @throws IOException
     */
    public void loadManipDetails(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        manipulationViewModel.loadManipDetails(Integer.parseInt(button.getId()),
                codePatientField,
                sexeField,
                ageField,
                prelevDetailsField,
                modeleManipField,
                commentairesField,
                utilisateurField,
                preopDataList,
                pathoList,
                prescriList);
    }

    public void updateCommentaireManipulation(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        manipulationViewModel.updateCommentaireManipulation(commentairesField);
    }


    public void retour(){
        Stage stage = (Stage) previousButton.getScene().getWindow();
        main.tools.previous(stage);
    }

    public Text getManipField() {
        return manipField;
    }
    public Text getPrelevField() {
        return prelevField;
    }
    public Text getManipulateurField() {
        return manipulateurField;
    }
    public Button getSelectManip() {
        return selectManip;
    }

    public void applyRoles(){
        if (main.getCurrentUser().getRole().equals(Main.roles[3])) {
            main.tools.applyRoles(paneSampleDetails, add_commentaire);
        }
    }
}
