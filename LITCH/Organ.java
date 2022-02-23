package LITCH;

/**
 * Classe Organ : un organe est représenté par un ID et un string
 */
public class Organ {

    /**
     * Identifiant de l'organe. Ne peut être négatif ou nul.La vérification de l'unicité est laissée à la base de données
     */
    private int idOrgan;
    /**
     * Nom de l'organe. Ne peut être nul ou vide.
     */
    private String nameOrgan;

    /**
     * Constructeur de l'organe
     *
     * @param newIdOrgan   l'ID de l'organe
     * @param newNameOrgan le nom de l'organe
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le user name nul ou vide
     */
    public Organ(int newIdOrgan, String newNameOrgan) {
        if (newIdOrgan <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameOrgan == null || newNameOrgan.length() < 1)
            throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        idOrgan = newIdOrgan;
        nameOrgan = newNameOrgan;
    }

    /**
     * Getter de l'id de l'organe
     *
     * @return idOrgan
     */
    public int getIdOrgan() {
        return idOrgan;
    }

    /**
     * Getter du nom de l'organe
     *
     * @return nameOrgan
     */
    public String getNameOrgan() {
        return nameOrgan;
    }


}
