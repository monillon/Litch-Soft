package Modules.Administration;

import LITCH.Main;
import LITCH.Tissue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListTissuController {

    private Main main;
    private AdminListTissuModel adminListTissuModel;
    @FXML private Button home;
    @FXML private ListView laListe;

    public AdminListTissuController(Main newMain){
        main = newMain;
        adminListTissuModel = new AdminListTissuModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addElementList() throws IOException {
        adminListTissuModel.addItemList(laListe);
    }

    public void delSelectedItem() throws IOException {
        // TODO:erreur quand rien sélection
        adminListTissuModel.removeItemList(laListe, (Tissue) laListe.getSelectionModel().getSelectedItem());
    }




}
