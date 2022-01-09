package Modules.Projet.Groupe.AjoutEchantillon;

import LITCH.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AddSampleController {

    private Main main;
    private AddSampleModel addSampleModel;
    private int subjectId;

    @FXML private TextField subjectCodeToModify;
    @FXML private Button home;
    @FXML private ChoiceBox organList;
    @FXML private ChoiceBox tissueList;
    @FXML private ComboBox fridgeList;
    @FXML private ComboBox shelfList;
    @FXML private ComboBox rackList;
    @FXML private ComboBox boxList;
    @FXML private ComboBox locationList;
    @FXML private DatePicker sampleDate;
    @FXML private TextField sampleHour;
    @FXML private Button addSampleButton;
    @FXML private Text error;

    public AddSampleController(Main newMain){
        main = newMain;
        addSampleModel = new AddSampleModel(newMain);

    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void initialize() throws IOException {
        setOrganList();
        setTissueList();
        setFridgeList();
        setShelfList();
    }



    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectCodeToModify(String subjectCodeToModify) {
        this.subjectCodeToModify.setText(subjectCodeToModify);
    }


    public void setOrganList() throws IOException {
        addSampleModel.setOrganList(organList);
    }

    public void setTissueList() throws IOException {
        addSampleModel.setTissueList(tissueList);
    }

    public void setFridgeList() throws IOException {
        addSampleModel.setFridgeList(fridgeList);
    }

    public void setShelfList() throws IOException {
        addSampleModel.setShelfList(shelfList);
    }

    public void setRackList() throws IOException {
        addSampleModel.setRackList(rackList, boxList, locationList, fridgeList.getValue().toString(), shelfList.getValue().toString());
    }

    public void setBoxList() throws IOException {
        addSampleModel.setBoxList(boxList, shelfList, rackList, fridgeList.getValue().toString(), shelfList.getValue().toString());
    }

    public void setLocationList() throws IOException {
        addSampleModel.setLocationList(rackList, boxList, locationList, shelfList.getValue().toString(), fridgeList.getValue().toString());
    }


    public void addSampleToBdd() throws IOException, SQLException {
        if (organList.getValue() != null &&
                tissueList.getValue() != null &&
                fridgeList.getValue() != null &&
                shelfList.getValue() != null &&
                rackList.getValue() != null &&
                sampleDate.getValue() !=  null &&
                !sampleHour.getText().isEmpty()) {

            addSampleModel.addSampleToBdd(rackList, boxList, organList.getValue().toString(), tissueList.getValue().toString(), fridgeList.getValue().toString(), shelfList.getValue().toString(), locationList, sampleDate.getValue().toString(), sampleHour.getText(), subjectId, addSampleButton);
        }else {
            error.setText("Veuillez remplir tous les champs");
        }
    }



}
