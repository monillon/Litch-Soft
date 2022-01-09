package Modules.Recherche;

import LITCH.Main;
import Modules.Recherche.SearchPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.sql.SQLException;
import java.util.List;



public class SearchPageController {

    @FXML private Button home;
    @FXML private TextField searchText;
    @FXML private ComboBox typeSearch;
    @FXML private ComboBox projectSearch;
    @FXML private Button search;
    @FXML private TableView<List<String>> tableView;
    @FXML private Button download;


    private Main main;
    private SearchPageModel searchPageModel;


    public SearchPageController(Main newMain){
        main = newMain;
        searchPageModel = new SearchPageModel(newMain);
    }


    public void initialize() throws IOException {
        setProjectList();
        setTypeList();
    }

    public void setProjectList() throws IOException {
        searchPageModel.setProjectList(projectSearch);
    }

    public void setTypeList(){
        searchPageModel.setTypeList(typeSearch);
    }


    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void onEnter(KeyEvent ke) throws SQLException, IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.searchFunction();
        }
    }


    public void searchFunction() throws IOException {
        searchPageModel.searchFunction(projectSearch, tableView, searchText, typeSearch, download);
    }


    /**
     * Cette methode permet de sauvegarder le contenue du TableView dans un fichier excel
     * @param event
     * @throws IOException
     */
    public void save() throws IOException {
        searchPageModel.save(tableView);
    }

}
