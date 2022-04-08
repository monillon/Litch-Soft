package LITCH;



/**
 * Classe User : un user est représenté par un ID et un nom d'utilisateur
 * Created by ben14 on 05/02/2020.
 */
public class User {

    /**
     * Identifiant dans la base de donnée. Ne peut être négatif ou nul
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idUser;

    /**
     * Nom du User. Ne peut pas être nul ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String userName;

    /**
     * Role du User. Ne peut pas être nul ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String role;

    /**
     * Constructeur du User : utilisateur = ID + nom d'utilisateur
     *
     * @param newIdUser   l'ID de l'utilisateur
     * @param newUserName le nom de l'utilisateur
     * @throws IllegalArgumentException si l'ID est nul ou négatif, le user name nul ou vide
     */
    public User(int newIdUser, String newUserName, String newRole) {
        if (newIdUser <= 0) throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être nul ou négatif");
        if (newUserName == null) throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être null");
        if (newUserName.length() < 1) throw new IllegalArgumentException("Ne peut pas être vide");
        if (newRole == null) throw new IllegalArgumentException("Le rôle ne peut pas être null");
        if (newRole.length() < 1) throw new IllegalArgumentException("Le rôle ne peut pas être vide");

        idUser = newIdUser;
        userName = newUserName;
        role = newRole;
    }


    /**
     * Getter de l'ID de l'utilisateur
     *
     * @return idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Getter du nom d'utilisateur du User
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter du nom d'utilisateur du User
     *
     * @param userName le nouveau nom d'utilisateur
     * @throws IllegalArgumentException si le nouveau nom est null ou vide
     */
    public void setUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (userName.length() < 1) throw new IllegalArgumentException("Ne peut pas être vide");
        this.userName = userName;
    }

    /**
     * Getter sur role de l'utilisateur
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * La méthode toString est override pour retourner le nom de l'utilisateur
     * @return le nom de l'utilisateur
     */
    public String toString() {
        return userName;
    }
}

