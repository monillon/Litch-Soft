package Modules.Menu.AjoutProjet;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Tools;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;

/**
 * Classe AddProjectModel est défini par un main
 * Elle contient les méthodes permettant d'ajouter un projet à la base de données
 */
public class AddProjectModel {

    private Main main;


    /**
     * Constructeur de la classe AddProjectModel
     * @param newMain objet Main
     */
    public AddProjectModel(Main newMain){
        main = newMain;
    }

    /**
     * Cette methode permet d'ajouter un projet à la base de données avec les informations saisi en paramètres
     * @param projectCode string du code projet
     * @param fullNameProject string du nom complet du projet
     * @param selectedFileName string du nom de l'image
     * @param imgLink string ajoutant le chemin avant le nom de l'image
     * @param addProjectButton objet Button
     * @param verif objet Text
     * @throws SQLException
     * @throws IOException
     */
    public void addNewProjectBDD(String projectCode, String fullNameProject , String selectedFileName, String imgLink, Button addProjectButton, Text verif) throws SQLException, IOException {

        String path = "src/IMG/";

        if (selectedFileName != null){
            DataBase.createProjectRequest(projectCode,main.tools.spaceToUnderscore(fullNameProject),selectedFileName);
            Files.copy(new File(imgLink).toPath(), new File(path + selectedFileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }else {
            DataBase.createProjectRequest(projectCode,main.tools.spaceToUnderscore(fullNameProject),null);
        }

        //get the id of the new project
        for (JSONObject object : DataBase.getAllProjectRequest()) {
            if (object.getString("NOM_PROJET").equals(fullNameProject) || object.getString("CODE_PROJET").equals(projectCode))
                groupsCreation(object.getInt("ID_PROJET"), projectCode);
        }

        //tracabilité
        Date maDate = new Date();
        String action = main.getCurrentUser().getUserName() +
                " a rajouté un projet qui a pour nom : " + fullNameProject
                + " le " + maDate;
        main.tools.applyTraceability(Tools.spaceToUnderscore(action));

        main.tools.goHome(addProjectButton);


    }

    /**
     * Cette méthode attribut le nom et le chemion d'une image que l'on veut ajouter
     * @param addImg objet Button
     * @param selectedFileName string nom de l'image
     * @throws IOException
     */
    public void uploadImg(Button addImg, String selectedFileName) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null){
            addImg.setText(selectedFile.getAbsolutePath());
            selectedFileName = selectedFile.getName();
        }
    }

    /**
     * Cette méthode ajoute tous les groupes necessaires pour le bon fonctionnement d'un projet
     * @param idProject id du projet
     * @param projectCode string code du projet
     * @throws IOException
     */
    public void groupsCreation(int idProject, String projectCode) throws IOException {

        //creation of the 4 humain groups

        DataBase.createHumanGroupRequest1(idProject,projectCode);
        DataBase.createHumanGroupRequest2(idProject,projectCode);
        DataBase.createHumanGroupRequest3(idProject,projectCode);
        DataBase.createHumanGroupRequest4(idProject,projectCode);

        //creation of the 4 rat groups

        DataBase.createRatGroupRequest1(idProject,projectCode);
        DataBase.createRatGroupRequest2(idProject,projectCode);
        DataBase.createRatGroupRequest3(idProject,projectCode);
        DataBase.createRatGroupRequest4(idProject,projectCode);

        //creation of the 4 cell groups

        DataBase.createCellGroupRequest1(idProject,projectCode);
        DataBase.createCellGroupRequest2(idProject,projectCode);
        DataBase.createCellGroupRequest3(idProject,projectCode);
        DataBase.createCellGroupRequest4(idProject,projectCode);
    }


}
