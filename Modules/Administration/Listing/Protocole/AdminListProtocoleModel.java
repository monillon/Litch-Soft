package Modules.Administration.Listing.Protocole;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Protocole;
import LITCH.Tools;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListProtocoleModel {

    private Main main;

    public AdminListProtocoleModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des protocoles dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllProtocoleRequest()) {
            theListView.getItems().add(new Protocole(i.getInt("ID_PROTOCOLE"), Tools.underscoreToSpace(i.getString("NOM_PROTOCOLE"))));
        }
    }


    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param leProtocole le protocole à supprimer
     */
    public void removeItemList(ListView theListView, Protocole leProtocole, Text errorText) throws IOException {
        DataBase.deleteProtocoleRequest(leProtocole.getIdProtocole());
        if (checkProtocoleDeleted(leProtocole.getIdProtocole())) {
            theListView.getItems().remove(leProtocole);

            //tracabilité
            main.tools.applyTraceability(leProtocole.getNameProtocole().toUpperCase() + " a été supprimé des protocoles");
        } else {
            errorText.setText("Élément non supprimé, ce protocole est encore lié à des modèles de manipulation");
        }
    }

    /**
     * Permet de vérifier si un protocole a bien été supprimé de la BDD en cas de clef étrangère
     * @param id_Protocole qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkProtocoleDeleted(int id_Protocole) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOneProtocole(id_Protocole);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }


}
