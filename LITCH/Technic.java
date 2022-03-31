package LITCH;

/**
 * Classe Technique : une technique est représentée par un id et un string
 */
public class Technic {

    /**
     * Identifiant de la technique. Ne peut être négatif ou nul. La vérification de l'unicité est laissée à la base de données
     */
    private int idTechnic;
    /**
     * Nom de la technique. Ne peut être nul ou vide.
     */
    private String nameTechnic;

    /**
     * Constructeur de la technique
     *
     * @param newIdTechnic   id de la technique
     * @param newNameTechnic nom de la technique
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le username nul ou vide
     */
    public Technic(int newIdTechnic, String newNameTechnic) {
        if (newIdTechnic <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameTechnic == null || newNameTechnic.length() < 1)
            throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        idTechnic = newIdTechnic;
        nameTechnic = newNameTechnic;
    }

    /**
     * Getter de l'id de la technique
     *
     * @return idTechnique
     */
    public int getIdTechnic() {
        return idTechnic;
    }

    /**
     * Getter du nom de la technique
     *
     * @return nameTechnic
     */
    public String getNameTechnic() {
        return nameTechnic;
    }


    /**
     * La méthode toString est override pour retourner le nom de la technique
     * @return le nom de la technique
     */
    public String toString() {
        return nameTechnic;
    }
}
