package LITCH;

/**
 * Classe Protocole : un protocole est représenté par un id et un string
 */
public class Protocole {

    /**
     * Identifiant du protocole. Ne peut être négatif ou nul. La vérification de l'unicité est laissée à la base de données
     */
    private int idProtocole;
    /**
     * Nom du protocole. Ne peut être nul ou vide.
     */
    private String nameProtocole;


    /**
     * Constructeur du protocole
     *
     * @param newIdProtocole          id du protocole
     * @param newNameProtocole        nom du protocole
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le username nul ou vide
     */
    public Protocole(int newIdProtocole, String newNameProtocole) {
        if (newIdProtocole <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameProtocole == null || newNameProtocole.length() < 1)
            throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        idProtocole = newIdProtocole;
        nameProtocole = newNameProtocole;
    }

    /**
     * Getter de l'id du protocole
     *
     * @return idprotocole
     */
    public int getIdProtocole() {
        return idProtocole;
    }

    /**
     * Getter du nom du protocole
     *
     * @return nameProtocole
     */
    public String getNameProtocole() {
        return nameProtocole;
    }


    /**
     * La méthode toString est override pour retourner le nom du protocole
     *
     * @return le nom de la protocole
     */
    public String toString() {
        return nameProtocole;
    }


}


