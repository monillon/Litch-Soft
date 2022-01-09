package Modules.Menu;


import LITCH.Main;
import Modules.Menu.MenuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class MenuController {

    @FXML private Text welcome;
    @FXML private FlowPane menu;
    @FXML private TextField projectCode;
    @FXML private TextField fullNameProject;
    @FXML private Button addProjectButton;
    private Main main;
    private MenuModel menuModel;





    public MenuController(Main newMain){
        main = newMain;
        menuModel = new MenuModel(newMain, this);
    }


    public void updateMenu() throws IOException, SQLException {
        menuModel.updateMenu(welcome, menu);
    }

    public void loadProject(ActionEvent e) throws IOException, ParseException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chargement");
        alert.setContentText("Chargement du projet en cours");
        alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
        alert.show();
        menuModel.loadProject(e);
        alert.close();
    }

}
