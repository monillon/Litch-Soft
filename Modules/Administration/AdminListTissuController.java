package Modules.Administration;

import LITCH.Main;
import LITCH.Tissue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AdminListTissuController {

    private Main main;
    private AdminListTissuModel adminListTissuModel;
    @FXML private Button home;
    @FXML private ListView laListe;
    @FXML private Text errorTissu;

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
        if (laListe.getSelectionModel().getSelectedItem() != null ) {
        adminListTissuModel.removeItemList(laListe, (Tissue) laListe.getSelectionModel().getSelectedItem());
        errorTissu.setText("");
        } else {
            errorTissu.setText("Aucun élément n'a été sélectionné");
        }
    }




}
