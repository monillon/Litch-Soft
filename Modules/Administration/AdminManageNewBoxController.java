package Modules.Administration;

import LITCH.Main;
import Modules.Administration.AdminManageNewBoxModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;

public class AdminManageNewBoxController {

    private Main main;
    private AdminManageNewBoxModel adminManageNewBoxModel;
    @FXML private Button home;
    @FXML private Text errorAdd;
    @FXML private ChoiceBox locationListAdd;
    @FXML private ComboBox fridgeListAdd;
    @FXML private ComboBox shelfListAdd;
    @FXML private ComboBox boxListAdd;
    @FXML private Button addBoxButton;
    @FXML private Text errorRemove;
    @FXML private ChoiceBox locationListRemove;
    @FXML private ComboBox fridgeListRemove;
    @FXML private ComboBox shelfListRemove;
    @FXML private ComboBox boxListRemove;
    @FXML private Button removeBoxButton;
    char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public AdminManageNewBoxController(Main newMain){
        main = newMain;
        adminManageNewBoxModel = new AdminManageNewBoxModel(newMain);
    }
    public ArrayList<Integer> listIdPrelevement;


    public void goHome() throws IOException, SQLException
    {
        main.tools.goHome(home);
    }

    public void initialize() throws IOException
    {
        setFridgeList();
        listIdPrelevement = new ArrayList<Integer>();
    }
    //Code la fonctionnalité d'ajout sur la gauche de la fenêtre ------------------------------------------------------------------------------------------------------------------------------------
    //Permet de créer une liste avec les frigos présents dans la bdd
    public void setFridgeList() throws IOException
    {
        adminManageNewBoxModel.setFridgeList(fridgeListAdd, fridgeListRemove);
    }
    //Permet de créer une liste avec les étagères présentes dans le frigo sélectionné
    public void setShelfListAdd() throws IOException
    {
        adminManageNewBoxModel.setShelfListAdd(shelfListAdd, fridgeListAdd);
    }
    //Permet de créer une liste d'emplacements de boites disponibles dans l'étagère sélectionnée
    public void setBoxListAdd() throws IOException
    {
        adminManageNewBoxModel.setBoxListAdd(boxListAdd, shelfListAdd);
    }
    //Permet d'ajouter une boite dans l'emplacement sélectionné et donc d'ajouter 100 emplacements dans la BDD correspondant aux emplacements de la boite
    public void addSampleToBdd() throws IOException, SQLException
    {
        adminManageNewBoxModel.addSampleToBdd(boxListAdd, shelfListAdd, fridgeListAdd, fridgeListRemove, shelfListRemove, boxListRemove, errorRemove, errorAdd);
    }
    //Code la fonctionnalité de retrait sur la droite de la fenêtre ------------------------------------------------------------------------------------------------------------------------------------
    public void setShelfListRemove() throws IOException
    {
        adminManageNewBoxModel.setShelfListRemove(shelfListRemove, fridgeListRemove);
    }

    public void setBoxListRemove() throws IOException
    {
        adminManageNewBoxModel.setBoxListRemove(boxListRemove, shelfListRemove);
    }

    public void removeSampleToBdd() throws IOException, SQLException {
        adminManageNewBoxModel.removeSampleToBdd(boxListRemove, shelfListRemove, fridgeListRemove, listIdPrelevement, boxListAdd, fridgeListAdd, shelfListAdd, errorRemove, errorAdd);
    }

    public void popUpRemove() throws IOException, SQLException {
        adminManageNewBoxModel.popUpRemove(boxListRemove, errorAdd, errorRemove, shelfListRemove, fridgeListRemove, listIdPrelevement, boxListAdd, fridgeListAdd, shelfListAdd);
    }


}