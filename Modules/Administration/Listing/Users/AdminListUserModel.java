package Modules.Administration.Listing.Users;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.User;
import LITCH.Tools;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListUserModel {

    private Main main;

    public AdminListUserModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des utilisateurs dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getUsersWithRoles()) {
            theListView.getItems().add(new User(i.getInt("ID_UTILISATEUR"), Tools.underscoreToSpace(i.getString("NOM_UTILISATEUR")), Tools.underscoreToSpace(i.getString("NOM_ROLE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param LeUser l'utilisateur à supprimer
     * @param errorText endroit pour afficher l'erreur
     */
    public void removeItemList(ListView theListView, User LeUser, Text errorText) throws IOException {
        DataBase.deleteUserRequest(LeUser.getIdUser());
        if (checkUserDeleted(LeUser.getIdUser())) {
            theListView.getItems().remove(LeUser);

            //tracabilité
            main.tools.applyTraceability(LeUser.getUserName().toUpperCase() + " à été supprimé des utilisateurs");

        } else {
            errorText.setText("Elément non supprimé, cet utilisateur est encore associé à un projet");
        }
    }

    /**
     * Permet de vérifier si un utilisateur à bien été supprimé de la BDD en cas de clef étrangère
     * @param id_user qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkUserDeleted(int id_user) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneUser(id_user);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }


}
