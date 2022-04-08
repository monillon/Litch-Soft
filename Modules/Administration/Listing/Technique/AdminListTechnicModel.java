package Modules.Administration.Listing.Technique;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListTechnicModel {

    private Main main;

    public AdminListTechnicModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des techniques dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllTechniqueRequest()) {
            theListView.getItems().add(new Technic(i.getInt("ID_TECHNIQUE"), Tools.underscoreToSpace(i.getString("NOM_TECHNIQUE")),Tools.underscoreToSpace(i.getString("DESCRIPTION_TECHNIQUE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param laTechnique la technique à supprimer
     */
    public void removeItemList(ListView theListView, Technic laTechnique, Text errorText) throws IOException {
        DataBase.deleteTechnicRequest(laTechnique.getIdTechnic());
        if (checkTechnicDeleted(laTechnique.getIdTechnic())) {
            theListView.getItems().remove(laTechnique);
        } else {
            errorText.setText("Élément non supprimé, cette technique est encore liée à des modèles de manipulation");
        }
    }

    /**
     * Permet de vérifier si une technique a bien été supprimé de la BDD en cas de clef étrangère
     * @param id_Technic qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkTechnicDeleted(int id_Technic) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneTechnic(id_Technic);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }


}
