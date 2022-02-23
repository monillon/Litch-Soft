package LITCH;

import java.io.IOException;
import java.sql.Date;


public class Traceability {

    /**
     * Identifiant de la trace. Ne peut être négatif ou nul. La vérification de l'unicité est laissée à la base de données
     */
    private int idTrace;
    /**
     * Utilisateur ayant réalisé l'action. Ne peut être null
     */
    private User user;
    /**
     * Date à laquelle l'action a été réalisée. Ne peut être null.
     */
    private Date date;
    /**
     * Description de l'action réalisée. Ne peut être null.
     */
    private String action;


    /**
     * Constructeur d'une trace lorsque l'on requete la base de données
     *
     * @param idTrace
     * @param user
     * @param date
     * @param action
     */
    public Traceability(int idTrace, User user, Date date, String action) {
        if (idTrace <= 0) throw new IllegalArgumentException("Ne peut pas être null ou négatif");
        if (user == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (date == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (action == null || action == "") throw new IllegalArgumentException("Ne peut pas être null");
        this.idTrace = idTrace;
        this.user = user;
        this.date = date;
        this.action = action;
    }


    /**
     * Contructeur d'une trace pour l'ajout d'une nouvelle trace à la base de données
     *
     * @param date
     * @param action
     */
    public Traceability(User user, Date date, String action) {
        if (user == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (date == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (action == null || action == "") throw new IllegalArgumentException("Ne peut pas être null");
        this.user = user;
        this.date = date;
        this.action = action;
    }

    public int getIdTrace() {
        return idTrace;
    }

    public void setIdTrace(int idTrace) {
        this.idTrace = idTrace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Methode permettant l'ajout de la trace à la base de données
     */
    public void send() throws IOException {
        DataBase.insertTrace(this.user.getIdUser(), this.date, this.action);
    }


}
