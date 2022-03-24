package Modules.Administration.Listing.DonneesPreOp;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListPreOpModel {

    private Main main;

    public AdminListPreOpModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des tissus dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllPreopDataRequest()) {
            theListView.getItems().add(new Tissue(i.getInt("ID_PREOP"), Tools.underscoreToSpace(i.getString("NOM_PREOP"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param laDonnee la donnée à supprimer
     */
    public void removeItemList(ListView theListView, PreopData laDonnee, Text errorText) throws IOException {
        DataBase.deletePreopDataRequest(laDonnee.getIdPreop());

        if (checkPreopDataDeleted(laDonnee.getIdPreop())) {
            theListView.getItems().remove(laDonnee);
        } else {
            errorText.setText("Elément non supprimé, ce tissu est encore lié à des prélèvements");
        }
    }

    /**
     * Permet de vérifier si une donnée préopératoire à bien été supprimée de la BDD en cas de clef étrangère
     * @param id_preopData qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkPreopDataDeleted(int id_preopData) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOnePreopData(id_preopData);
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
     * @param detailsPrelev le lieu d'affiche de la liste des prélèvements
     */
    public void showDetails(Tissue leTissu, Text affichage, Text detailsPrelev) throws IOException {
        ArrayList<JSONObject> retour = DataBase.getPrelevementLieTissu(leTissu.getIdTissue());

        if (retour.get(0).isNull("message")) {
            // Le tissu est lié à des prélèvements afficher les prélèvements
            affichage.setText("Le tissu est lié à " + retour.size() + " prélèvements, il ne peut pas être supprimé");
            ArrayList<JSONObject> details = DataBase.getSujetLiePrelevment(leTissu.getIdTissue());

            // permet d'afficher les 5 premiers sujets où le tissu est utilisé.
            int parcours;
            if (details.size() >= 5 ) {
                parcours = 5;
            } else {
                parcours = details.size();
            }

            String texte = "";
            for (int i = 0; i < parcours; i++) {
                texte += details.get(i).getString("CODE_SUJET") + " ";
            }
            detailsPrelev.setText("Ce tissu est utilisé pour les sujets : " + texte);

        } else {
            // pas de prélèvement lié
            affichage.setText("Ce tissu n'est pas lié à des prélèvements, il peut être supprimé");
            detailsPrelev.setText("");
        }

    }

}
