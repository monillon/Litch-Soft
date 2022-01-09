package Modules.ManagementFrigo;


import LITCH.Main;
import Modules.ManagementFrigo.FridgeManagementModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.*;
import javafx.scene.layout.*;



import java.io.IOException;
import java.sql.SQLException;


public class FridgeManagementController {


    @FXML FlowPane abscisse;
    @FXML FlowPane ordonnee;
    @FXML FlowPane pane;
    @FXML Pane pane1;
    @FXML ComboBox fridgeList;
    @FXML ComboBox shelfList;
    @FXML ComboBox drawerList;
    @FXML ComboBox boxList;


    private Main main;
    private FridgeManagementModel fridgeManagementModel;

    @FXML private Button home;

    public FridgeManagementController(Main newMain){
        main = newMain;
        fridgeManagementModel = new FridgeManagementModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }


    public void setup() throws IOException {
        fridgeManagementModel.setup(pane1, pane, fridgeList.getValue().toString(), shelfList.getValue().toString(), drawerList.getValue().toString(), boxList.getValue().toString(), abscisse, ordonnee);
    }

    public void setupFromSample(String fridge, String shelf, String drawer, String box) throws IOException {
        fridgeManagementModel.setup(pane1, pane, fridge, shelf, drawer, box, abscisse, ordonnee);
    }

    public void setFridgeList() throws IOException {
        fridgeManagementModel.setFridgeList(fridgeList);
    }

    public void setShelfList() throws IOException {
        fridgeManagementModel.setShelfList(fridgeList, shelfList);
    }


    public void setDrawerList() throws IOException {
        fridgeManagementModel.setDrawerList(shelfList, drawerList, boxList);
    }


    public void setBoxList() throws IOException {
        fridgeManagementModel.setBoxList(boxList, shelfList, drawerList);
    }


    public void handleSampleDragOver(DragEvent e){
        fridgeManagementModel.handleSampleDragOver(e);
    }

    public void handleSampleDrop(DragEvent e) throws IOException {
        fridgeManagementModel.handleSampleDrop(e, pane);
        setup();
    }


}
