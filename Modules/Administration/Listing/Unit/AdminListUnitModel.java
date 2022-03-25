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

    /**
     * Permet d'aller chercher en BDD si l'unité est liée à un ou plusieurs prélèvements et d'afficher un message.
     * @param lUnite l'unité à rechercher
     * @param affichage le lieu de l'affichage pour le message d'erreur
     * @param detailsPrelev le lieu d'affiche de la liste des prélèvements
     */
    public void showDetails(Unit lUnite, Text affichage, Text detailsPrelev) throws IOException {
        ArrayList<JSONObject> retour = DataBase.getUtilisationUnit(lUnite.getIdUnit());

        if (retour.get(0).isNull("message")) {
            // L'unité est liée à des prélèvements afficher les prélèvements
            affichage.setText("L'unité est liée à " + retour.size() + " prélèvements, il ne peut pas être supprimé");
            ArrayList<JSONObject> details = DataBase.getSujetLiePrelevment(lUnite.getIdUnit());

            // permet d'afficher les 5 premiers sujets où l'unité est utilisé.
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
            detailsPrelev.setText("Cette unité est utilisée pour les sujets : " + texte);

        } else {
            // pas de prélèvement lié
            affichage.setText("Cette unité n'est pas liée à des prélèvements, elle peut être supprimée");
            detailsPrelev.setText("");
        }

    }

}
