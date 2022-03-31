package Modules.Administration.Listing.CatDeManip;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListCategorieManipModel {

    private Main main;

    public AdminListCategorieManipModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des catégories de manipulation dans la BDD et de les ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllCatDeManipRequest()) {
            theListView.getItems().add(new ManipCategory(i.getInt("ID_CATEGORIE_MANIP"), Tools.underscoreToSpace(i.getString("NOM_CATEGORIE_MANIP"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer la catégorie de manipulation de la ListView
     * @param theListView
     * @param LaCatManip la catégorie de manipulation à supprimer
     */
    public void removeItemList(ListView theListView, ManipCategory LaCatManip, Text errorText) throws IOException {
        DataBase.deleteCatDeManipRequest(LaCatManip.getIdManipCategory());
        if (checkCatDeManipDeleted(LaCatManip.getIdManipCategory())) {
            theListView.getItems().remove(LaCatManip);
        } else {
            errorText.setText("Elément non supprimé, cette catégorie est encore liée à des prélèvements");
        }
    }

    /**
     * Permet de vérifier si une catégorie de manipulation à bien été supprimée de la BDD en cas de clef étrangère
     * @param idManipCategory qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkCatDeManipDeleted(int idManipCategory) throws IOException {
        ArrayList<JSONObject> retour = DataBase.getOneCatDeManip(idManipCategory);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, la catégorie de manipulation est toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }

}
