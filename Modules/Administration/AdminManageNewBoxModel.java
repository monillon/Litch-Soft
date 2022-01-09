package Modules.Administration;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminManageNewBoxModel
{
    private Main main;

    public AdminManageNewBoxModel(Main newMain)
    {
        main = newMain;
    }

    //Code la fonctionnalité d'ajout sur la gauche de la fenêtre ------------------------------------------------------------------------------------------------------------------------------------
    //Permet de créer une liste avec les frigos présents dans la bdd
    public void setFridgeList(ComboBox fridgeListAdd, ComboBox fridgeListRemove) throws IOException
    {
        fridgeListAdd.getItems().clear();
        for (JSONObject object : DataBase.getAllFrigoRequest())
        {
            if (object.has("message") == false) {
                fridgeListAdd.getItems().add(object.getString("NOM_FRIGO"));
                fridgeListRemove.getItems().add(object.getString("NOM_FRIGO"));
            }
        }
    }
    //Permet de créer une liste avec les étagères présentes dans le frigo sélectionné
    public void setShelfListAdd(ComboBox shelfListAdd, ComboBox fridgeListAdd) throws IOException
    {
        shelfListAdd.getItems().clear();
        for (JSONObject object : DataBase.getAllEtagereRequest())
        {
            if (getFridgeIDAdd(fridgeListAdd) == object.getInt("ID_FRIGO"))
            {
                if (object.has("message") == false) {
                    shelfListAdd.getItems().add(object.getString("NOM_ETAGERE"));
                }
            }
        }
    }
    //Permet de créer une liste d'emplacements de boites disponibles dans l'étagère sélectionnée
    public void setBoxListAdd(ComboBox boxListAdd, ComboBox shelfListAdd) throws IOException
    {
        boxListAdd.getItems().clear();
        int i;
        String x;
        String y = "A";
        if (getShelfIDAdd(shelfListAdd) == 1)
        {
            y = "A";
        }
        else if (getShelfIDAdd(shelfListAdd) == 2)
        {
            y = "B";
        }
        else if (getShelfIDAdd(shelfListAdd) == 3)
        {
            y = "C";
        }
        else if (getShelfIDAdd(shelfListAdd) == 4)
        {
            y = "D";
        }
        else if (getShelfIDAdd(shelfListAdd) == 5)
        {
            y = "E";
        }
        for (i = 1; i < 49; i++) {
            x = (y + i);
            boxListAdd.getItems().add(x);
        }
        for (JSONObject object : DataBase.getRackExistantRequest(getShelfIDAdd(shelfListAdd)))
        {
            if (object.has("message") == false) {
                boxListAdd.getItems().remove(object.getString("ID_BOITE"));
            }
        }
    }

    //Permet de return l'ID de l'étagère sélectionnée
    public int getShelfIDAdd(ComboBox shelfListAdd) throws IOException {
        //get the shelf ID
        int shelfIdAdd = 0;
        for (JSONObject object : DataBase.getAllEtagereRequest()) {
            if (object.getString("NOM_ETAGERE").equals(shelfListAdd.getValue())) {
                if (object.has("message") == false) {
                    shelfIdAdd = object.getInt("ID_ETAGERE");
                }
            }
        }
        return shelfIdAdd;
    }
    //Permet de return l'ID du frigo sélectionné
    public int getFridgeIDAdd(ComboBox fridgeListAdd) throws IOException {
        //get the shelf ID
        int fridgeIdAdd = 0;
        for (JSONObject object : DataBase.getAllFrigoRequest()) {
            if (object.getString("NOM_FRIGO").equals(fridgeListAdd.getValue().toString())) {
                if (object.has("message") == false) {
                    fridgeIdAdd = object.getInt("ID_FRIGO");
                }
            }
        }
        return fridgeIdAdd;
    }
    //Permet d'ajouter une boite dans l'emplacent sélectionné et donc d'ajouter 100 emplacements dans la BDD correspondant aux emplacements de la boite
    public void addSampleToBdd(ComboBox boxListAdd, ComboBox shelfListAdd, ComboBox fridgeListAdd, ComboBox fridgeListRemove, ComboBox shelfListRemove, ComboBox boxListRemove, Text errorRemove, Text errorAdd) throws IOException, SQLException
    {
//        if (boxListAdd.getValue() != null &&
//                shelfListAdd.getValue() != null &&
//                fridgeListAdd.getValue() != null)
//        {

            DataBase.createAddSampleToBddRequest();
//
//            fridgeListAdd.getSelectionModel().clearSelection();
//            shelfListAdd.getItems().clear();
//            boxListAdd.getItems().clear();
//            fridgeListRemove.getSelectionModel().clearSelection();
//            shelfListRemove.getItems().clear();
//            boxListRemove.getItems().clear();
//            errorRemove.setText("");
//            errorAdd.setText("La boîte a été ajoutée");
//        }
//        else{
//            errorAdd.setText("Un champ n'a pas été renseigné");
//        }
    }
    //Code la fonctionnalité de retrait sur la droite de la fenêtre ------------------------------------------------------------------------------------------------------------------------------------
    public void setShelfListRemove(ComboBox shelfListRemove, ComboBox fridgeListRemove) throws IOException
    {
        shelfListRemove.getItems().clear();
        for (JSONObject object : DataBase.getAllEtagereRequest())
        {
            if (getFridgeIDRemove(fridgeListRemove) == object.getInt("ID_FRIGO"))
            {
                if (object.has("message") == false) {
                    shelfListRemove.getItems().add(object.getString("NOM_ETAGERE"));
                }
            }
        }
    }

    public void setBoxListRemove(ComboBox boxListRemove, ComboBox shelfListRemove) throws IOException
    {
        boxListRemove.getItems().clear();
        for (JSONObject object : DataBase.getRackExistantRequest(getShelfIDRemove(shelfListRemove)))
        {
            if (object.has("message") == false) {
                boxListRemove.getItems().add(object.getString("ID_BOITE"));
            }
        }
    }

    //Permet de return l'ID du frigo sélectionné
    public int getFridgeIDRemove(ComboBox fridgeListRemove) throws IOException {
        //get the shelf ID
        int fridgeIdRemove = 0;
        for (JSONObject object : DataBase.getAllFrigoRequest()) {
            if (object.getString("NOM_FRIGO").equals(fridgeListRemove.getValue().toString())) {
                if (object.has("message") == false) {
                    fridgeIdRemove = object.getInt("ID_FRIGO");
                }
            }
        }
        return fridgeIdRemove;
    }

    //Permet de return l'ID de l'étagère sélectionnée
    public int getShelfIDRemove(ComboBox shelfListRemove) throws IOException {
        //get the shelf ID
        int shelfIdRemove = 0;
        for (JSONObject object : DataBase.getAllEtagereRequest ()) {
            if (object.getString("NOM_ETAGERE").equals(shelfListRemove.getValue())) {
                if (object.has("message") == false) {
                    shelfIdRemove = object.getInt("ID_ETAGERE");
                }
            }
        }
        return shelfIdRemove;
    }

    public void removeSampleToBdd(ComboBox boxListRemove, ComboBox shelfListRemove, ComboBox fridgeListRemove, ArrayList<Integer> listIdPrelevement, ComboBox boxListAdd, ComboBox fridgeListAdd, ComboBox shelfListAdd, Text errorRemove, Text errorAdd) throws IOException, SQLException {
        if (boxListRemove.getValue() != null &&
                shelfListRemove.getValue() != null &&
                fridgeListRemove.getValue() != null)
        {
            for (JSONObject object : DataBase.getIdPrelevementByBoxRequest(getFridgeIDRemove(fridgeListRemove),boxListRemove.getValue()))
            {
                if (object.has("message") == false) {
                    listIdPrelevement.add(object.getInt("ID_PRELEVEMENT"));
                }
            }
            DataBase.deleteSlotRequest(boxListRemove.getValue());
//            if (listIdPrelevement.size() != 0)
            //{
            for (int i = 0; i < listIdPrelevement.size(); i++) {
                DataBase.deleteManipRequest(listIdPrelevement.get(i));
                DataBase.deletePrelevRequest(listIdPrelevement.get(i));
            }
            // }
            fridgeListRemove.getSelectionModel().clearSelection();
            shelfListRemove.getItems().clear();
            boxListRemove.getItems().clear();
            fridgeListAdd.getSelectionModel().clearSelection();
            shelfListAdd.getItems().clear();
            boxListAdd.getItems().clear();
            errorAdd.setText("");
            errorRemove.setText("La boîte a été supprimée");
        }
        else
        {
            errorRemove.setText("Un champ n'a pas été renseigné");
        }
    }

    public void popUpRemove(ComboBox boxListRemove, Text errorAdd, Text errorRemove, ComboBox shelfListRemove, ComboBox fridgeListRemove, ArrayList<Integer> listIdPrelevement, ComboBox boxListAdd, ComboBox fridgeListAdd, ComboBox shelfListAdd) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmer la suppression");
        alert.setContentText("Etes-vous sur de vouloir supprimer le lot ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            for (JSONObject object : DataBase.getIdPrelevementByBoxRequest(getFridgeIDRemove(fridgeListRemove),boxListRemove.getValue()))
            {
                if (object.has("message") == true)
                {
                    removeSampleToBdd(boxListRemove, shelfListRemove, fridgeListRemove, listIdPrelevement, boxListAdd, fridgeListAdd, shelfListAdd, errorRemove, errorAdd);
                    errorAdd.setText("");
                    errorRemove.setText("La boîte a été supprimée");
                }
                else {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Attention");
                    alert2.setHeaderText("Présence de prélèvements et de manipulations dans la boîte");
                    alert2.setContentText("Êtes vous sur de vouloir la supprimer ?");
                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.get() == ButtonType.OK)
                    {
                        removeSampleToBdd(boxListRemove, shelfListRemove, fridgeListRemove, listIdPrelevement, boxListAdd, fridgeListAdd, shelfListAdd, errorRemove, errorAdd);
                        errorAdd.setText("");
                        errorRemove.setText("La boîte a été supprimée");
                    }
                }
            }
        } else {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("Suppression");
            alert3.setHeaderText("Suppression annulée.");
            alert3.setContentText("La suppression n'a pas été effectuée.");

            alert3.showAndWait();
            errorAdd.setText("");
            errorRemove.setText("La boîte n'a pas été supprimée");
        }

    }


}
