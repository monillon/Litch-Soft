package LITCH;

/**
 * Classe Tissue : un tissu est réprésenté par un id et un string
 */
public class Tissue {

    /**
     * Identifiant du tissu. Ne peut être négatif ou nul.La vérification de l'unicité est laissée à la base de données
     */
    private int idTissue;
    /**
     * Nom du tissu. Ne peut être nul ou vide.
     */
    private String nameTissue;

    /**
     * Constructeur du tissu
     *
     * @param newIdTissue   id du tissu
     * @param newNameTissue nom du tissu
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le user name nul ou vide
     */
    public Tissue(int newIdTissue, String newNameTissue) {
        if (newIdTissue <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameTissue == null || newNameTissue.length() < 1)
            throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        idTissue = newIdTissue;
        nameTissue = newNameTissue;
    }

    /**
     * Getter de l'id du tissu
     *
     * @return idTissue
     */
    public int getIdTissue() {
        return idTissue;
    }

    /**
     * Getter du nom du tissu
     *
     * @return nameTissue
     */
    public String getNameTissue() {
        return nameTissue;
    }


    /**
     * La méthode toString est override pour retourner le nom du tissu
     * @return le nom du tissu
     */
    public String toString() {
        return nameTissue;
    }
}
