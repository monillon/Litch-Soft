package Modules.Projet.Manipulations.AjoutManipulation;

import LITCH.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddManipulationController {
    private Main main;
    private int sampleId;
    private AddManipulationModel addManipulationModel;
    @FXML private Button home;
    @FXML private DatePicker dateManip;
    @FXML private TextField sampleHour;
    @FXML private ChoiceBox modeleList;
    @FXML private ChoiceBox userList;
    @FXML private TextArea com;
    @FXML private Button addManipButton;
    @FXML private Text error;



    public AddManipulationController(Main newMain){
        main = newMain;
        addManipulationModel = new AddManipulationModel(newMain);
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getSampleId() {
        return sampleId;
    }


    public void initialize() throws IOException {
        setModeleList();
        setUserList();
    }

    public void setModeleList() throws IOException {
        addManipulationModel.setModeleList(modeleList);
    }

    public void setUserList() throws IOException {
        addManipulationModel.setUserList(userList);
    }

    public void addManipToBdd() throws IOException, SQLException, ParseException {

        if (userList.getValue() != null &&
                modeleList.getValue() != null &&
                dateManip.getValue() != null &&
                !sampleHour.getText().isEmpty()){

            addManipulationModel.addManipToBdd(userList.getValue().toString(), modeleList.getValue().toString(), dateManip.getValue().toString(), sampleHour.getText(), sampleId, com.getText(), addManipButton);

        }else {
            error.setText("Veuillez remplir tous les champs");
        }
    }
}

