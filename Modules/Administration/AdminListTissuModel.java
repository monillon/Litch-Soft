package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tissue;
import LITCH.Tools;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListTissuModel {

    private Main main;

    public AdminListTissuModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des tissus dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllTissuRequest()) {
            theListView.getItems().add(new Tissue(i.getInt("ID_TISSU"), Tools.underscoreToSpace(i.getString("NOM_TISSU"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param leTissu le tissu à supprimer
     */
    public void removeItemList(ListView theListView, Tissue leTissu, Text errorText) throws IOException {
        DataBase.deleteTissuRequest(leTissu.getIdTissue());
        if (checkTissuDeleted(leTissu.getIdTissue())) {
            theListView.getItems().remove(leTissu);
        } else {
            errorText.setText("Elément non supprimé, ce tissu est encore lié à des prélèvements");
        }
    }

    /**
     * Permet de vérifier si un tissu à bien été supprimé de la BDD en cas de clef étrangère
     * @param id_tissu qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkTissuDeleted(int id_tissu) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneTissu(id_tissu);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }

    /**
     * Permet d'aller chercher en BDD si le tissu est lié à un ou plusieurs prélèvements et d'afficher un message.
     * @param leTissu le tissu à rechercher
     * @param affichage le lieu de l'affichage pour le message d'erreur
     */
    public void showDetails(Tissue leTissu, Text affichage) throws IOException {
        ArrayList<JSONObject> retour = DataBase.getPrelevementLieTissu(leTissu.getIdTissue());

        if (retour.get(0).isNull("message")) {
            // Le tissu est lié à des prélèvements afficher les prélèvements
            affichage.setText("Le tissu est lié à " + retour.size() + " prélèvements, il ne peut pas être supprimé");
        } else {
            // pas de prélèvement lié
            affichage.setText("Ce tissu n'est pas lié à des prélèvements, il peut être supprimé");
        }

    }

}
