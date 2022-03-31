package Modules.Administration.Listing.Mutation;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListMutationModel {

    private Main main;

    public AdminListMutationModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des mutations dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllMutationRequest()) {
            theListView.getItems().add(new Prescription(i.getInt("ID_MUTATION"), Tools.underscoreToSpace(i.getString("NOM_MUTATION"))));
        }
    }

    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param laMutation la prescription à supprimer
     */
    public void removeItemList(ListView theListView, Mutation laMutation, Text errorText) throws IOException {
        DataBase.deleteMutationRequest(laMutation.getIdMutation());
        if (checkMutationDeleted(laMutation.getIdMutation())) {
            theListView.getItems().remove(laMutation);
        } else {
            errorText.setText("Elément non supprimé, cette mutation est encore liée à des choses");
        }
    }

    /**
     * Permet de vérifier si une mutation a bien été supprimée de la BDD en cas de clef étrangère
     * @param id_mutation qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkMutationDeleted(int id_mutation) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneMutation(id_mutation);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément a bien été supprimé de la BDD
            return true;
        }
    }









//    /**
//     * Permet d'aller chercher en BDD si la prescription est liée à un ou plusieurs prélèvements et d'afficher un message.
//     * @param leTissu la prescription à rechercher
//     * @param affichage le lieu de l'affichage pour le message d'erreur
//     * @param detailsPrelev le lieu d'affiche de la liste des prélèvements
//     */
//    public void showDetails(Tissue leTissu, Text affichage, Text detailsPrelev) throws IOException {
//        ArrayList<JSONObject> retour = DataBase.getPrelevementLieTissu(leTissu.getIdTissue());
//
//        if (retour.get(0).isNull("message")) {
//            // La prescription est liée à des prélèvements afficher les prélèvements
//            affichage.setText("Le tissu est lié à " + retour.size() + " prélèvements, il ne peut pas être supprimé");
//            ArrayList<JSONObject> details = DataBase.getSujetLiePrelevment(leTissu.getIdTissue());
//
//            // permet d'afficher les 5 premiers sujets où le tissu est utilisé.
//            int parcours;
//            if (details.size() >= 5 ) {
//                parcours = 5;
//            } else {
//                parcours = details.size();
//            }
//
//            String texte = "";
//            for (int i = 0; i < parcours; i++) {
//                texte += details.get(i).getString("CODE_SUJET") + " ";
//            }
//            detailsPrelev.setText("Ce tissu est utilisé pour les sujets : " + texte);
//
//        } else {
//            // pas de prélèvement lié
//            affichage.setText("Ce tissu n'est pas lié à des prélèvements, il peut être supprimé");
//            detailsPrelev.setText("");
//        }
//
//    }

}
