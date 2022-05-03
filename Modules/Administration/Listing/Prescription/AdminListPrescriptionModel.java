package Modules.Administration.Listing.Prescription;

import LITCH.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AdminListPrescriptionModel {

    private Main main;

    public AdminListPrescriptionModel(Main newMain){
        main = newMain;
    }

    /**
     * Permet de faire la requête des prescriptions dans la BDD et de l'ajouter dans la ListView
     * @param theListView
     */
    public void addItemList(ListView theListView) throws IOException {
        for (JSONObject i : DataBase.getAllPrescriptionRequest()) {
            theListView.getItems().add(new Prescription(i.getInt("ID_PRESCRIPTION"), Tools.underscoreToSpace(i.getString("NOM_PRESCRIPTION"))));
        }
    }

    /**
     * Permet d'envoyer la requête de suppression et de retirer l'élément de la ListView
     * @param theListView
     * @param laPrescription la prescription à supprimer
     */
    public void removeItemList(ListView theListView, Prescription laPrescription, Text errorText) throws IOException {
        DataBase.deletePrescriptionRequest(laPrescription.getIdPresciption());
        if (checkPrescriptionDeleted(laPrescription.getIdPresciption())) {
            theListView.getItems().remove(laPrescription);

            //tracabilité
            main.tools.applyTraceability(laPrescription.getPrescriptionName().toUpperCase() + " a été supprimé des prescriptions");
        } else {
            errorText.setText("Elément non supprimé, cette prescription est encore liée à des phénotypes");
        }
    }

    /**
     * Permet de vérifier si une prescription a bien été supprimée de la BDD en cas de clef étrangère
     * @param id_prescription qui est supprimé
     * @return un boolean true si l'élément n'est plus présent en BDD
     */
    public boolean checkPrescriptionDeleted(int id_prescription) throws IOException {
        ArrayList<JSONObject> retour =  DataBase.getOnePrescription(id_prescription);
        if (retour.get(0).isNull("message")) {
            // erreur lors de la suppression, l'élément toujours présent dans la BDD
            return false;
        } else {
            // l'élément à bien été supprimé de la BDD
            return true;
        }
    }


}
