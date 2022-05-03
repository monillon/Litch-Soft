package Modules.Administration.Listing.Pathologie;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListPathologieModel {

    private Main main;

    public AdminListPathologieModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des pathologies dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllPathoRequest()) {
            theListView.getItems().add(new Pathology(i.getInt("ID_PATHOLOGIE"), Tools.underscoreToSpace(i.getString("NOM_PATHOLOGIE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param laPathologie la pathologie à supprimer
     */
    public void removeItemList(ListView theListView, Pathology laPathologie, Text errorText) throws IOException {
        DataBase.deletePathologie(laPathologie.getIdPathology());
        if (checkPathologyDeleted(laPathologie.getIdPathology())) {
            theListView.getItems().remove(laPathologie);

            //tracabilité
            main.tools.applyTraceability(laPathologie.getNamePathology().toUpperCase() + " a été supprimé des pathologies");
        } else {
            errorText.setText("Elément non supprimé, cette pathologie est encore liée à des mutations ou phénotypes");
        }
    }

    /**
     * Permet de vérifier si une pathologie à bien été supprimée de la BDD en cas de clef étrangère
     * @param id_patho qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkPathologyDeleted(int id_patho) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOnePathologie(id_patho);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }

}
