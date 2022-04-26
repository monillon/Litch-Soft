package Modules.Administration.Listing.Unit;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Unit;
import LITCH.Tools;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListUnitModel {

    private Main main;

    public AdminListUnitModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des unités dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllUniteRequest()) {
            theListView.getItems().add(new Unit(i.getInt("ID_UNITE"), Tools.underscoreToSpace(i.getString("NOM_UNITE")), Tools.underscoreToSpace(i.getString("DESCRIPTION_UNITE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param lUnite l'unité à supprimer
     */
    public void removeItemList(ListView theListView, Unit lUnite, Text errorText) throws IOException {
        DataBase.deleteUnitRequest(lUnite.getIdUnit());
        if (checkUnitDeleted(lUnite.getIdUnit())) {
            theListView.getItems().remove(lUnite);

            //tracabilité
            main.tools.applyTraceability(lUnite.getNameUnit().toUpperCase() + " à été supprimé des unités");
        } else {
            errorText.setText("Élément non supprimé, cette unité est encore liée à des phénotypes ou sujets");
        }
    }

    /**
     * Permet de vérifier si une unité a bien été supprimé de la BDD en cas de clef étrangère
     * @param id_unit qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkUnitDeleted(int id_unit) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneUnit(id_unit);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }

}
