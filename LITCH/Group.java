package LITCH;

import java.util.ArrayList;

/**
 * Un groupe définit une liste de sujets similaires et possède divers attributs afin de pouvoir différencier
 * ces groupes. Par exemple un groupe pathologique non traité qui ne contient que des sujets humain.
 *
 * @author ben14
 */
public class Group {
    /**
     * identifiant du groupe géré par la base de donnée
     */
    private int idGroup;
    /**
     * Code du groupe afin d'être identifié
     */
    private String codeGroup;
    /**
     * Nom du groupe Le nom du groupe
     */
    private String nameGroup;
    /**
     * if true : groupe pathologique | if false : groupe non pathologique
     */
    private boolean groupPatho;
    /**
     * if true : groupe traité | if false : groupe non traité
     */
    private boolean groupTreated;
    /**
     * nombre de membres dans le groupe
     */
    private int nbMembers;
    /**
     * type d'organisme traité : humain, rat ou organe
     */
    private Organism organism;
    /**
     * Liste des sujets dans le groupe
     */
    private ArrayList<Subject> subjectList;

    /**
     * Constructeur d'objets de classe Group
     *
     * @param newID        Stock l'id de la base de données
     * @param newCode      Nom de code du groupe
     * @param newName      Nom du groupe
     * @param patho        Définit dans quel type de groupe en fonction de l'état pathologique : pathologique ou non
     * @param treated      Définit le type de groupe en fonction du traitement : traité ou pas
     * @param newNbMembers Nombre de sujets dans le groupe
     * @param newOrganism  Type de sujet à traiter : humain, rat, organe
     * @throws IllegalArgumentException si les contraintes sont violées
     */
    public Group(int newID, String newCode, String newName, boolean patho, boolean treated, int newNbMembers, Organism newOrganism) {
        if (newID < 1) throw new IllegalArgumentException("Un id ne peut être negatif");
        if (newNbMembers < 0) throw new IllegalArgumentException("Un nombre de membres ne peut être negatif");
        if (newCode.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        if (newName.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        idGroup = newID;
        codeGroup = newCode;
        nameGroup = newName;
        groupPatho = patho;
        groupTreated = treated;
        nbMembers = newNbMembers;
        organism = newOrganism;
        subjectList = new ArrayList<Subject>();


    }

    /**
     * Retourne l'id du groupe
     *
     * @return l'id du groupe, récupéré dans la base de données
     */
    public int getIdGroup() {
        return idGroup;
    }

    /**
     * Retourne le code du groupe
     *
     * @return le code du groupe, récupéré dans la base de données
     */
    public String getCodeGroup() {
        return codeGroup;
    }

    /**
     * Retourne le nom du groupe
     *
     * @return le nom du groupe, récupéré dans la base de données
     */
    public String getNameGroup() {
        return nameGroup;
    }

    /**
     * Retourne le fait que le groupe soit pathologique ou non
     *
     * @return le booleen groupPatho, récupéré dans la base de données
     */
    public boolean getGroupPatho() {
        return groupPatho;
    }

    /**
     * Retourne le fait que le groupe soit traité ou non
     *
     * @return le booleen groupTreated, récupéré dans la base de données
     */
    public boolean getGroupTreated() {
        return groupTreated;
    }

    /**
     * Retourne le nombre de membres dans le groupe
     *
     * @return le nombre de membres, récupéré dans la base de données
     */
    public int getNbMembers() {
        return nbMembers;
    }


    /**
     * Modifie le code du groupe
     *
     * @param newCode le nouveau code du groupe
     * @throws IllegalArgumentException si le code est vide
     */
    public void setCodeGroup(String newCode) {
        if (newCode.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        codeGroup = newCode;
    }

    /**
     * Modifie le nom de groupe
     *
     * @param newName le nouveau nom du groupe
     * @throws IllegalArgumentException si le nom est vide
     */
    public void setNameGroup(String newName) {
        if (newName.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        nameGroup = newName;
    }

    /**
     * Modifie le nombre de membres dans le groupe
     *
     * @param newNbMembers nouveau nombre de membres
     * @throws IllegalArgumentException si le nombre est négatif
     */
    public void setNbMembers(int newNbMembers) {
        if (newNbMembers < 0) throw new IllegalArgumentException("Un nombre de membres ne peut être negatif");
        nbMembers = newNbMembers;
    }

    /**
     * Retourne l'objet organisme
     *
     * @return le type d'organisme des sujets du groupe
     */
    public Organism getOrganism() {
        return organism;
    }


    /**
     * Retourne la liste des sujets composant le groupe
     *
     * @return un ArrayList, une liste de tous les sujets du groupe
     */
    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }


    /**
     * Ajoute un nouveau sujet dans la liste des sujets du groupe et incrémente le nombre de membres de 1
     *
     * @param newSubject objet subject à ajouter
     */
    public void addSubject(Subject newSubject) {
        subjectList.add(newSubject);
        nbMembers++;
    }

    /**
     * Supprime un sujet de la liste de sujet du groupe et décrémente le nombre de membres de 1
     *
     * @param newSubject objet subject que l'on veut enlever
     */
    public void removeSubject(Subject newSubject) {
        subjectList.remove(newSubject);
        nbMembers--;
    }

    /**
     * Methode de recherche qui retourne un sujet
     *
     * @param subjectId id du sujet recherché
     * @return le sujet qui possède l'id saisi plus tôt
     */
    public Subject getSubjectById(int subjectId) {
        Subject subjectSearch = null;

        for (Subject subject1 : subjectList) {
            if (subjectId == subject1.getIdSubject()) {
                subjectSearch = subject1;
            }
        }

        return subjectSearch;
    }

    /**
     * Methode qui traduit les booleens groupPatho et groupTreated en string
     *
     * @return le résultat de ces vérification sous la forme d'un string
     */
    public String typeGroupToString() {
        String result = "";

        if (groupPatho && groupTreated) {
            result = "PATHOLOGIQUES | TRAITES";
        } else if (!groupPatho && groupTreated) {
            result = "NON PATHOLOGIQUES | TRAITES";
        } else if (groupPatho && !groupTreated) {
            result = "PATHOLOGIQUES | NON TRAITES";
        } else if (!groupPatho && !groupTreated) {
            result = "NON PATHOLOGIQUES | NON TRAITES";
        }

        return result;
    }


}
