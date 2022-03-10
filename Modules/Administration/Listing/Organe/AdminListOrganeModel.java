package Modules.Administration.Listing.Organe;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListOrganeModel {

    private Main main;

    public AdminListOrganeModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des organes dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllOrganRequest()) {
            theListView.getItems().add(new Organ(i.getInt("ID_ORGANE"), Tools.underscoreToSpace(i.getString("NOM_ORGANE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param leOrgane le tissu à supprimer
     * @param errorText endroit pour afficher l'erreur
     */
    public void removeItemList(ListView theListView, Organ leOrgane, Text errorText) throws IOException {
        DataBase.deleteOrganeRequest(leOrgane.getIdOrgan());
        if (checkTissuDeleted(leOrgane.getIdOrgan())) {
            theListView.getItems().remove(leOrgane);
        } else {
            errorText.setText("Elément non supprimé, ce tissu est encore lié à des prélèvements");
        }
    }

    /**
     * Permet de vérifier si un organe à bien été supprimé de la BDD en cas de clef étrangère
     * @param id_organe qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkTissuDeleted(int id_organe) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneOrgane(id_organe);
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
     * @param leOrgane le tissu à rechercher
     * @param affichage le lieu de l'affichage pour le message d'erreur
     */
    public void showDetails(Organ leOrgane, Text affichage) throws IOException {
        ArrayList<JSONObject> retour = DataBase.getPrelevementLieOrgane(leOrgane.getIdOrgan());

        if (retour.get(0).isNull("message")) {
            // Le tissu est lié à des prélèvements afficher les prélèvements
            affichage.setText("Le tissu est lié à " + retour.size() + " prélèvements, il ne peut pas être supprimé");
        } else {
            // pas de prélèvement lié
            affichage.setText("Ce tissu n'est pas lié à des prélèvements, il peut être supprimé");
        }
    }

}
