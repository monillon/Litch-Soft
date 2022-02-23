package LITCH;

/**
 * Une pathologie est défini par un identifiant et un nom de pathologie
 *
 * @author ben14
 */
public class Pathology {

    /**
     * Identifiant de la pathologie correspondant à celui de la base de donnée, ne peut pas être négatif
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idPathology;

    /**
     * Nom de la pathologie correspondant à celui de la base de donnée, ne peut pas être null
     * La vérification de l'unicité est laissée à la base de données
     */
    private String namePathology;


    /**
     * Pathology Constructor
     *
     * @param newIdPathology   Permet de stocker l'id de la pathologie.
     * @param newNamePathology Le nom de la pathologie
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Pathology(int newIdPathology, String newNamePathology) {
        if (newIdPathology < 0) throw new IllegalArgumentException("Un nombre de membres ne peut être negatif");
        if (newNamePathology.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        idPathology = newIdPathology;
        namePathology = newNamePathology;
    }

    /**
     * Method getIdPathology
     * retourne l'identifiant de la pathologie
     */
    public int getIdPathology() {
        return idPathology;
    }


    /**
     * Method getNamePathology
     * retourne le nom de la pathologie
     */
    public String getNamePathology() {
        return namePathology;
    }

    /**
     * Method setNamePathology
     *
     * @param namePathology le nouveau nom de la pathologie
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setNamePathology(String namePathology) {
        if (namePathology.length() < 1) throw new IllegalArgumentException("un string ne doit pas etre vide");
        this.namePathology = namePathology;
    }
}
