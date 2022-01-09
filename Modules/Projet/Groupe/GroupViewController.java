package Modules.Projet.Groupe;


import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GroupViewController {

    @FXML private Button home;
    @FXML private VBox vboxSubject;
    @FXML private Text patientCodeField;
    @FXML private Text sexAgeField;
    @FXML private Text mutationField;
    @FXML private Text mutationField1;
    @FXML private Pane paneSubjectItem;
    @FXML private Button selectSubject;
    @FXML private Text currentGroupNameView;

    @FXML private Text sampleOrgLoc;
    @FXML private Button addManipButton;
    @FXML private FlowPane sampleListPane;
    @FXML private Pane samplePane;
    @FXML private Button addSampleButton;
    @FXML private Button modifLocation;

    @FXML private Text subjectCodeView;
    @FXML private Text subjectSexView;
    @FXML private Text subjectAgeView;
    @FXML private Text subjectPreopDataView;
    @FXML private Text subjectPathoView;
    @FXML private Text subjectPrescriView;
    @FXML private ComboBox preopDataList;
    @FXML private ComboBox pathoList;
    @FXML private ComboBox prescriList;
    @FXML private ComboBox mutationList;


    @FXML private Button previousButton;

    @FXML private Text localisation;

    private Main main;
    private GroupViewModel groupViewModel;



    public GroupViewController(Main newMain){
        main = newMain;
        groupViewModel = new GroupViewModel(newMain);
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }



    public void addManip (ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        groupViewModel.addManip(button);
    }


    /**
     * It show details of the selected subject
     * @param e
     * @throws IOException
     */
    public void loadSubjectDetails(ActionEvent e) throws IOException {
        addSampleButton.setDisable(false);
        Button button = (Button) e.getSource();
        groupViewModel.loadSubjectDetails(Integer.parseInt(button.getId()), subjectCodeView, subjectSexView, subjectAgeView, preopDataList, pathoList, prescriList, subjectPreopDataView, subjectPathoView, subjectPrescriView, sampleListPane, addSampleButton, sampleOrgLoc, localisation, addManipButton);
    }


    /**
     * It allows to update the groupView screen
     * @param groupID
     * @throws IOException
     */
    public void updateGroupView(int groupID) throws IOException {
        this.applyRoles();
        groupViewModel.updateGroupView(groupID, currentGroupNameView);
    }


    public void retour(){
        Stage stage = (Stage) previousButton.getScene().getWindow();
        main.tools.previous(stage);
    }

    public void goToAddSample(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        groupViewModel.goToAddSample(button);

    }

    public void applyRoles(){
        if (main.getCurrentUser().getRole().equals(Main.roles[3])) {
            main.tools.applyRoles(samplePane, addSampleButton);
        }
    }

    public void goToSampleLocation(ActionEvent e){
        Button button = (Button) e.getSource();
        groupViewModel.goToSampleLocation(button);
    }


    public VBox getVboxSubject() {
        return vboxSubject;
    }

    public Text getPatientCodeField() {
        return patientCodeField;
    }

    public Text getSexAgeField() {
        return sexAgeField;
    }

    public Text getMutationField() {
        return mutationField;
    }

    public Text getMutationField1() {
        return mutationField1;
    }

    public Button getSelectSubject() {
        return selectSubject;
    }

    public Text getSampleOrgLoc() {
        return sampleOrgLoc;
    }

    public Button getAddManipButton() {
        return addManipButton;
    }

    public FlowPane getSampleListPane() {
        return sampleListPane;
    }

    public Button getAddSampleButton() {
        return addSampleButton;
    }

    public Text getLocalisation() {
        return localisation;
    }

    public Button getModifLocation() {
        return modifLocation;
    }
}
