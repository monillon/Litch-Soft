package LITCH;

/**
 * Classe Unit pour renseigner les différentes unités des différents paramètres de l'application
 *
 * @author ben14
 */
public class Unit {

    /**
     * Identifiant dans la base de donnée. Ne peut être négatif ou nul
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idUnit;

    /**
     * Nom de l'Unit
     */
    private String nameUnit;

    /**
     * Description de l'Unit
     */
    private String descriptionUnit;

    /**
     * Constructeur de l'Unit
     *
     * @param newIdUnit          l'ID de l'unité
     * @param newNameUnit        le nom de l'unité
     * @param newDescriptionUnit la description de l'unité
     * @throws IllegalArgumentException si l'ID est null ou négatif
     */
    public Unit(int newIdUnit, String newNameUnit, String newDescriptionUnit) {
        if (newIdUnit <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        idUnit = newIdUnit;
        nameUnit = newNameUnit;
        descriptionUnit = newDescriptionUnit;
    }

    /**
     * Getter de l'ID de l'unité
     *
     * @return idUnit
     */
    public int getIdUnit() {
        return idUnit;
    }

    /**
     * Getter du nom de l'Unit
     *
     * @return nameUnit
     */
    public String getNameUnit() {
        return nameUnit;
    }

    /**
     * Setter du nom de l'Unit
     *
     * @param nameUnit le nouveau nom
     */
    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    /**
     * Getter de la description de l'unité
     *
     * @return descriptionUnit
     */
    public String getDescriptionUnit() {
        return descriptionUnit;
    }

    /**
     * Setter de la description de l'Unit
     *
     * @param descriptionUnit la nouvelle description
     */
    public void setDescriptionUnit(String descriptionUnit) {
        this.descriptionUnit = descriptionUnit;
    }
}
