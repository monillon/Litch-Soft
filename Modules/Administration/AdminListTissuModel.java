package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tissue;
import javafx.scene.control.ListView;
import org.json.JSONObject;

import java.io.IOException;

public class AdminListTissuModel {

    private Main main;

    public AdminListTissuModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des tissus dans la BDD et de l'ajouter dans la liste
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllTissuRequest()) {
            theListView.getItems().add(new Tissue(i.getInt("ID_TISSU"), i.getString("NOM_TISSU")));
        }
    }


    public void removeItemList(ListView theListView, Tissue leTissu) throws IOException {
        DataBase.deleteTissuRequest(leTissu.getIdTissue());
        theListView.getItems().remove(leTissu);
    }

}
