package LITCH;

import java.util.ArrayList;

/**
 * Un projet est défini par un id, un code, un nom ainsi qu'une image, ce projet possède une liste de groupes participant au projet
 *
 * @author ben14
 */
public class Project {


    private int idProject;
    private String codeProject;
    private String nameProject;
    private String imageUrl;
    private ArrayList<Group> ListOfGroups;


    /**
     * Constructeur d'objets de classe Projet
     *
     * @param newID       Id du projet stocké dansla base de données
     * @param newCode     code du projet
     * @param newName     nom du projet
     * @param newImageUrl url de l'image, peut être null
     *                    ListOfGroups : ArrayList des groupes participant au projet
     */
    public Project(int newID, String newCode, String newName, String newImageUrl) {
        if (newID < 0) throw new IllegalArgumentException("Un ID ne peut être negatif");
        if (newCode == null || newCode.length() < 1)
            throw new IllegalArgumentException("un string ne doit pas etre vide");
        if (newName == null || newName.length() < 1)
            throw new IllegalArgumentException("un string ne doit pas etre vide");
        if (newImageUrl == null || newImageUrl.length() < 1)
            throw new IllegalArgumentException("un string ne doit pas etre vide");
        idProject = newID;
        codeProject = newCode;
        nameProject = newName;
        imageUrl = newImageUrl;
        ListOfGroups = new ArrayList<Group>();
    }

    public Project() {

    }


    /**
     * @return : return the ID of the project
     */
    public int getidProject() {
        return idProject;
    }


    /**
     * @return : return the Code of the project
     */
    public String getcodeProject() {
        return codeProject;
    }


    /**
     * @return : return the Name of the project
     */
    public String getnameProject() {
        return nameProject;
    }


    /**
     * @return : return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }


    /**
     * @Param Group : prends toutes les informations necessaire à la création d'un groupe pour lui assigné directement sur le projet
     */
    public void addGroup(int newID, String newCode, String newName, boolean patho, boolean treated, int newNbMembers, Organism newOrganism) {
        ListOfGroups.add(new Group(newID, newCode, newName, patho, treated, newNbMembers, newOrganism));
    }


    /**
     * @return la liste des groupes appartenant au projet
     */
    public ArrayList<Group> getListOfGroup() {
        return ListOfGroups;
    }

    /**
     * @param groupId
     * @return l'objet groupe trouvé grâce à son id
     */
    public Group searchGroupById(int groupId) {
        Group theGroup = null;
        for (Group group : ListOfGroups) {
            if (group.getIdGroup() == groupId) {
                theGroup = group;
            }
        }
        return theGroup;
    }

}

