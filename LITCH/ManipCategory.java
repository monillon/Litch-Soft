package LITCH;

public class ManipCategory {

    /**
     * Identifiant de la catégorie de manipulation. Ne peut être négatif ou nul. La vérification de l'unicité est laissée à la base de données
     */
    private int idManipCategory;
    /**
     * Nom de la catégorie. Ne peut être nul ou vide.
     */
    private String nameManipCategory;

    /**
     * Constructeur de la catégorie de manipulation
     *
     * @param newIdManipCategory   id de la catégorie de la manipulation
     * @param newNameManipCategory nom de l'organe
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le user name nul ou vide
     */
    public ManipCategory(int newIdManipCategory, String newNameManipCategory) {
        if (newIdManipCategory <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameManipCategory == null || newNameManipCategory.length() < 1)
            throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        idManipCategory = newIdManipCategory;
        nameManipCategory = newNameManipCategory;
    }

    /**
     * Getter de l'id de la catégorie de manipulation
     *
     * @return idManipCategory
     */
    public int getIdManipCategory() {
        return idManipCategory;
    }

    /**
     * Getter du nom de la catégorie de manipulation
     *
     * @return nameManipCategory
     */
    public String getNameManipCategory() {
        return nameManipCategory;
    }


    /**
     * La méthode toString est override pour retourner le nom de la catégorie de manipulation
     * @return le nom de la catégorie
     */
    public String toString() {
        return nameManipCategory;
    }


}
