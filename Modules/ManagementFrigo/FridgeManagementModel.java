package Modules.ManagementFrigo;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tools;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class FridgeManagementModel {

    private Main main;
    double sizeButtonCurrentBox;//taille du bouton d'un échantillon en fonction de la boite actuellement regardé par l'utilisateur
    private char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


    public FridgeManagementModel(Main newMain){
        main = newMain;
    }



    /**
     * Permet de return l'ID du frigo sélectionné par rapport à son nom
     */
    public int getFridgeID(String fridgeName) throws IOException {
        //get the shelf ID
        int fridgeIdAdd = 0;
        for (JSONObject object : DataBase.getAllFrigoRequest()) {
            if (object.getString("NOM_FRIGO").equals(fridgeName)) {
                if (object.has("message") == false) {
                    fridgeIdAdd = object.getInt("ID_FRIGO");
                }
            }
        }
        return fridgeIdAdd;
    }

    /**
     * Permet de return l'ID de l'étagère sélectionnée par rapport à son nom
     * @param fridgeListAdd
     * @return
     * @throws IOException
     */
    public int getShelfID(String shelfName) throws IOException {
        //get the shelf ID
        int shelfId = 0;
        for (JSONObject object : DataBase.getAllEtagereRequest()) {
            if (object.getString("NOM_ETAGERE").equals(shelfName)) {
                if (object.has("message") == false) {
                    shelfId = object.getInt("ID_ETAGERE");
                }
            }
        }
        return shelfId;
    }

    /**
     * Etablit la liste des frigos et l'ajout à la combobox correspondante
     * @param fridgeList
     * @throws IOException
     */
    public void setFridgeList(ComboBox fridgeList) throws IOException {
        //setup de la liste des frigos
        for (JSONObject object : DataBase.getAllFrigoRequest()){
            fridgeList.getItems().add(object.getString("NOM_FRIGO"));
        }
    }


    /**
     * Ajoute le nom des étagères à la combobox en fonction d'un frigo donné
     * @param fridgeList
     * @param shelfList
     * @throws IOException
     */
    public void setShelfList(ComboBox fridgeList, ComboBox shelfList) throws IOException {
        shelfList.getItems().clear();
        for (JSONObject object : DataBase.getShelfByFridge(getFridgeID(fridgeList.getValue().toString()))){
            if (object.has("message") == false) {
                shelfList.getItems().add(object.getString("NOM_ETAGERE"));
            }
        }
    }


    /**
     * Ajoute le nom des racks à la combobox en fonction d'une étagère donnée
     * @param shelfList
     * @param drawerList
     * @throws IOException
     */
    public void setDrawerList(ComboBox shelfList, ComboBox drawerList, ComboBox boxList) throws IOException {
        drawerList.getItems().clear();
        boxList.getItems().clear();
        for (JSONObject object : DataBase.getRackExistantRequest(getShelfID(shelfList.getValue().toString()))){
            if (object.has("message") == false) {
                drawerList.getItems().add(object.getString("ID_RACK"));
            }
        }
    }

    /**
     * Définit la liste des boites pour une étagère et un rack donné
     * et complète la combobox correspondante de cette liste
     * @param boxList
     * @param shelfList
     * @param drawerList
     * @throws IOException
     */
    public void setBoxList(ComboBox boxList, ComboBox shelfList, ComboBox drawerList) throws IOException {
        boxList.getItems().clear();
        for (JSONObject object : DataBase.getBoxExistantRequest(getShelfID(shelfList.getValue().toString()), drawerList.getValue().toString())){
            if (object.has("message") == false) {
                boxList.getItems().add(object.getString("ID_BOITE"));
            }
        }
    }


    /**
     * Génère la représentation visuelle de la boite selectionnée
     * @param pane1
     * @param pane
     * @param fridgeList
     * @param shelfList
     * @param drawerList
     * @param boxList
     * @throws IOException
     */
    public void setup(Pane pane1, Pane pane, String fridge, String shelf, String drawer, String box, FlowPane abscisse, FlowPane ordonnee) throws IOException {
        pane1.getChildren().clear();
        pane.getChildren().clear();
        abscisse.getChildren().clear();
        ordonnee.getChildren().clear();
        ordonnee.setLayoutX(565);
        //setup du visuel de la boite

        ArrayList<JSONObject> emplacements = DataBase.getBoxSlot(getFridgeID(fridge),
                                            getShelfID(shelf),
                                            drawer,
                                            Integer.parseInt(box));

        int gap = 5;
        int nbEmplacements = emplacements.size();
        //calcul de la taille d'un bouton par rapport au nombre d'échantillon de la boite
        //400 représente la taille d'un coté du pane1 auquel on soustrait la taille de l'espace entre les boutons multiplié
        // par la racine carré du nombre d'emplacements dans la boite. On divise ce résultat par la racine carré du nombre
        //d'emplacement afin d'obtenir la taille d'un bouton.
        double size = ((400 - (gap * Math.ceil(Math.sqrt(nbEmplacements))))/Math.ceil(Math.sqrt(nbEmplacements)));
        sizeButtonCurrentBox = size;

        int numEmp = 0;
        for (int y = 0; y < Math.ceil(Math.sqrt(nbEmplacements)); y++) {
            for (int x = 0; x < Math.ceil(Math.sqrt(nbEmplacements)); x++) {
                if(numEmp < nbEmplacements) {
                    Pane emp = new Pane();
                    emp.setPrefSize(size + 3, size + 3);
                    emp.setId(emplacements.get(numEmp).getString("ID_EMPLACEMENT"));
                    emp.setLayoutX(1 + x * (size + gap));
                    emp.setLayoutY(1 + y * (size + gap));
                    emp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

                    emp.setOnDragDropped(event -> {
                        Pane destination = (Pane) event.getSource();
                        ObservableList<Node> children = destination.getChildren();
                        int size1 = children.size();
                        if (size1 < 1) {
                            try {
                                this.handleSampleDrop(event, pane);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    emp.setOnDragOver(event -> {
                        this.handleSampleDragOver(event);
                    });

                    //si un échantillon est présent alors on génère un bouton représentant cet échantillon
                    sampleButton(emplacements, numEmp, size, emp);

                    pane1.getChildren().add(emp);

                    numEmp++;
                }
            }
        }

        //setup des les noms des colonnes
        for (double i = 0; i < Math.ceil(Math.sqrt(nbEmplacements)); i++){
            Pane p = new Pane();
            p.setPrefWidth(size);
            p.setPrefHeight(50);

            Text nameColumn = new Text(String.valueOf(alphabet[(int) i]));
            nameColumn.setFont(new Font(size/2.5));
            nameColumn.setY(p.getPrefHeight() - 10);
            nameColumn.setX(size/2.3);

            p.getChildren().add(nameColumn);
            abscisse.setHgap(5);
            abscisse.getChildren().add(p);
        }

        //setup des les noms des lignes
        for (double i = 0; i < Math.ceil(Math.sqrt(nbEmplacements)); i++){
            Pane p = new Pane();
            p.setPrefHeight(size);
            p.setPrefWidth(size);

            Text nameColumn = new Text(String.valueOf(Integer.valueOf((int) i+1)));
            nameColumn.setFont(new Font(size/2.5));
            nameColumn.setX(size/2);
            nameColumn.setY(size/1.5);

            p.getChildren().add(nameColumn);

            ordonnee.setVgap(5);
            ordonnee.getChildren().add(p);
        }


        //on replace le flowpane ordonnée pour qu'il n'empiète pas sur la boite
        //lorsque sa wigth est supérieure à 50
        if (size > 50){
            ordonnee.setLayoutX(ordonnee.getLayoutX()-size/2.5);
        }


        //setup de la boite temporaire
        ArrayList<JSONObject> emplacements_temp = DataBase.getTemporaryBox();
        size = 52.5;
        for (int i = 0; i < emplacements_temp.size(); i++){
            Pane emp_temp = new Pane();
            emp_temp.setPrefSize(size, size);
            emp_temp.setId(emplacements_temp.get(i).getString("ID_EMPLACEMENT"));
            emp_temp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            emp_temp.setOnDragDropped(event -> {
                Pane destination = (Pane) event.getSource();
                ObservableList<Node> children = destination.getChildren();
                int size1 = children.size();
                if (size1 < 1) {
                    try {
                        this.handleSampleDrop(event, pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            emp_temp.setOnDragOver(event -> {
                this.handleSampleDragOver(event);
            });

            sampleButton(emplacements_temp, i, size, emp_temp);
            pane.getChildren().add(emp_temp);
        }


    }

    /**
     *
     * @param emplacements
     * @param numEmp
     * @param size
     * @param emp
     */
    public void sampleButton(ArrayList<JSONObject> emplacements, int numEmp, double size, Pane emp){
        if(emplacements.get(numEmp).getInt("LIBRE") == 0) {
            Button button = new Button();
            button.setPrefSize(size, size);
            button.setId(String.valueOf(emplacements.get(numEmp).get("ID_PRELEVEMENT")));
            button.setLayoutX(1);
            button.setLayoutY(1);

            button.setOnDragDetected(event -> {
                Dragboard db = button.startDragAndDrop(TransferMode.ANY);
                ClipboardContent cb = new ClipboardContent();
                cb.putString(button.getId());
                db.setContent(cb);
                event.consume();
            });


            button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echantillon");
                alert.headerTextProperty().setValue(null);
                try {
                    if (button.getId() != "null") {
                        for (JSONObject object : DataBase.getSample(Integer.parseInt(button.getId()))) {
                            alert.setContentText("Code sujet: " + object.getString("CODE_SUJET") + "\n" +
                                    "Organe: " + object.getString("NOM_ORGANE") + "\n" +
                                    "Tissu: " + object.getString("NOM_TISSU") + "\n" +
                                    "Date et heure du prélèvement:" + object.getString("DATE_HEURE_PRELEVEMENT"));
                        }
                    }else {
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.setContentText("Erreur : emplacement marqué complet mais aucun prélèvement lié !");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                alert.show();
            });

            emp.getChildren().add(button);
        }
    }

    /**
     * Action réalisée lorsque un bouton représentant un échantillon est relaché sur un pane appelant cette methode
     * Le bouton est redimentionné en conséquence
     * @param e
     * @param pane
     */
    public void handleSampleDrop(DragEvent e, Pane pane) throws IOException {
        //on récupère le pane dans lequel l'utilisateur souhaite déposer le bouton
        Pane destination = (Pane) e.getSource();
        ObservableList<Node> children = destination.getChildren();
        int size = 0;
        //on vérifie si il y a un bouton dans l'emplacement ou non et on détermine le nombre de places prise dans
        //la boite temporaire
        for (Node node : children){
            Pane emp = (Pane) node;
            if (emp.getChildren().size() > 0){
                size++;
            }
        }

        if (size < 8) {

            Button button = (Button) e.getGestureSource();
            Pane parent = (Pane) button.getParent();

            if (destination.getParent() != pane) {
                button.setPrefSize(sizeButtonCurrentBox,sizeButtonCurrentBox);
                button.setLayoutX(1);
                button.setLayoutY(1);
            }else {
                button.setPrefSize(52.5,52.5);
            }

            for (JSONObject object : DataBase.getLocationIdRequest(Integer.parseInt(button.getId()))){
                parent.getChildren().remove(button);
                destination.getChildren().add(button);
                DataBase.updateLocationDateRequest(Integer.parseInt(object.getString("ID_EMPLACEMENT")), 1, " ");
                DataBase.updateLocationDateRequest(Integer.parseInt(destination.getId()), 0, button.getId());

                //tracabilité
                String action = main.getCurrentUser().getUserName() +
                        " a déplacé l'échantillon ayant pour identifiant " + button.getId();
                main.tools.applyTraceability(Tools.spaceToUnderscore(action));
            }


        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.headerTextProperty().setValue(null);
            alert.setContentText("La boite de transition est pleine !");
            alert.show();
        }
    }



    public void handleSampleDragOver(DragEvent e){
        if (e.getDragboard().hasString()){
            e.acceptTransferModes(TransferMode.ANY);
        }
    }

}
