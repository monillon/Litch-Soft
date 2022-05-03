package LITCH;

import java.util.Date;

/**
 * Un organisme est défini par un identifiant et un nom d'un échantillon
 *
 * @author ben14
 */
public class Sample {
    /**
     * Identifiant de l'échantillon correspondant à celui de la base de donnée, ne peut pas être négatif
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idSample;
    /**
     * Date à laquelle l'échantillon a été reçu, ne peut pas être null
     */
    private Date time;
    /**
     * Type d'organe auquel l'échantillon correspond
     */
    private String sampleOrgan;
    /**
     * Localistion de l'échantillon dans le laboratoire
     */
    private String sampleLocalization;
    /**
     * Commentaire concernant l'échantillon
     */
    private String sampleCommentary;


    /**
     * Sample Constructor
     *
     * @param newIdSample           Permet de stocker l'id de l'échantillon.
     * @param newSampleOrgan        Le nom de l'échantillon
     * @param newTime               date de l'échantillon
     * @param newSampleLocalization Le nom de l'organisme
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Sample(int newIdSample, String newSampleLocalization, String newSampleOrgan, Date newTime, String newSampleCommentary) {
        if (newIdSample <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");

        idSample = newIdSample;
        sampleLocalization = newSampleLocalization;
        sampleOrgan = newSampleOrgan;
        time = newTime;
        sampleCommentary = newSampleCommentary;
    }

    /**
     * Method getIdSample
     * retourne l'identifiant de l'échantillon
     */
    public int getIdSample() {
        return idSample;
    }

    /**
     * Method setIdSample
     *
     * @param idSample le nouvel identifiant de l'échantillon
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setIdSample(int idSample) {
        if (idSample <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.idSample = idSample;
    }

    /**
     * Method getIdSample
     * retourne la date de l'échantillon
     */
    public Date getTime() {
        return time;
    }

    /**
     * Method setTime
     *
     * @param time la nouvelle date de l'échantillon
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Method getSampleOrgan
     * retourne l'organe lié à l'échantillon
     */
    public String getSampleOrgan() {
        return sampleOrgan;
    }

    /**
     * Method setSampleOrgan
     *
     * @param sampleOrgan le nouvel organ de l'échantillon
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setSampleOrgan(String sampleOrgan) {
        this.sampleOrgan = sampleOrgan;
    }

    /**
     * Method getSampleOrgan
     * retourne la localisation de l'échantillon
     */
    public String getSampleLocalization() {
        return sampleLocalization;
    }

    /**
     * Method setTime
     *
     * @param sampleLocalization la nouvelle localisation de l'échantillon
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setSampleLocalization(String sampleLocalization) {
        if (sampleLocalization != null) {
            this.sampleLocalization = sampleLocalization;
        }
    }

    /**
     * Method getSampleOrgan
     * retourne le commentaire de l'échantillon s'il y en a un
     */
    public String getSampleCommentary() {
        return sampleCommentary;
    }

    /**
     * Method setTime
     *
     * @param sampleCommentary le nouveau commentaire de l'échantillon
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setSampleCommentary(String sampleCommentary) {
        this.sampleCommentary = sampleCommentary;
    }

}
