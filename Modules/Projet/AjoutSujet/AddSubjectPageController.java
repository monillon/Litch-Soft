package Modules.Projet.AjoutSujet;

import LITCH.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddSubjectPageController {

    @FXML private Button home;

    //common to all page 1/3
    @FXML private TextField newPatientCode;
    @FXML private ChoiceBox menuButtonGroup;
    @FXML private TextField newPatientAge;
    @FXML private ChoiceBox menuButtonSex;
    @FXML private TextField newPatientWeight;
    @FXML private TextArea newPatientCommentary;
    @FXML private Text error;

    //page 2/3
    @FXML private ComboBox menuPatho1;


    @FXML private ComboBox menuMutation1;
    @FXML private ComboBox menuMutation2;


    @FXML private ComboBox menuPrescri1;
    @FXML private ComboBox menuPrescri2;

    @FXML private Button addSubjectPathoButton;


    //page 3/3
    @FXML private ComboBox menuDataType1;
    @FXML private ComboBox menuDataType2;

    @FXML private ComboBox menuUnit1;
    @FXML private ComboBox menuUnit2;


    @FXML private TextField dataValue1;
    @FXML private TextField dataValue2;


    @FXML private Button finalFormularButton;



    private Main main;
    private AddSubjectPageModel addSubjectPageModel;


    public AddSubjectPageController(Main newMain){
        main = newMain;
        addSubjectPageModel = new AddSubjectPageModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public ChoiceBox getMenuButtonGroup() {
        return menuButtonGroup;
    }


    public ChoiceBox getMenuButtonSex() {
        return menuButtonSex;
    }


    /**
     * get information from the first page
     */
    public void getPageOneElements(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        if (button.getId().toString().equals("addCellButton")){
            menuButtonSex.setValue("Mâle");
        }

        //we check that all mandatory fields are filled in
        if (!newPatientCode.getText().isEmpty() &&
                menuButtonGroup.getValue() != null &&
                !newPatientAge.getText().isEmpty() &&
                menuButtonSex.getValue() != null &&
                !newPatientWeight.getText().isEmpty()){

            Stage stage = (Stage) button.getScene().getWindow();

            int result = addSubjectPageModel.getPageOneElements(menuButtonSex.getValue().toString(),
                            button.getId().toString(),
                            newPatientCode.getText(),
                            Integer.parseInt(newPatientAge.getText()),
                            Integer.parseInt(newPatientWeight.getText()),
                            newPatientCommentary.getText(),
                            stage,
                            menuButtonGroup);
            if (result == 1) {
                addSubjectPageModel.loadPageTwoList(menuPatho1, menuPrescri1, menuPrescri2);
            }else if (result == 0){
                addSubjectPageModel.loadPageThreeList(menuDataType1, menuDataType2, menuUnit1, menuUnit2, dataValue1, dataValue2, result);
            }

        }else{
            error.setText("Un champ n'a pas été renseigné");
        }
    }

    /**
     * Update the Mutation Combobox about the selected pathology
     * @param e
     * @throws IOException
     */
    public void updateMutations(ActionEvent e) throws IOException {
        ComboBox box = (ComboBox) e.getSource();
        addSubjectPageModel.updateMutations(box, menuPatho1, menuMutation1, menuMutation2);
    }



    /**
     * get all the information entered by the user on the second page and set up the third page Combobox
     * @throws IOException
     */
    public void getPageTwoElements() throws IOException {
        Stage stage = (Stage) addSubjectPathoButton.getScene().getWindow();
        if(addSubjectPageModel.getPageTwoElements(menuPatho1, menuMutation1, menuPrescri1, menuMutation2, menuPrescri2, stage, error)){
            addSubjectPageModel.loadPageThreeList(menuDataType1, menuDataType2, menuUnit1, menuUnit2, dataValue1, dataValue2,1);
        }
    }

    /**
     *
     * @throws IOException
     * @throws SQLException
     */
    public void getPageThreeElements(ActionEvent e) throws IOException, SQLException {
        Button button = (Button) e.getSource();
        addSubjectPageModel.getPageThreeElements(menuDataType1, menuUnit1, dataValue1, menuDataType2, menuUnit2, dataValue2, error);
        main.tools.goHome(button);
    }



}
