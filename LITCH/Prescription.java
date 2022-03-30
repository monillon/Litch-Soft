package LITCH;

/**
 * Définition de la prescription <définir ici ce qu'est une prescription, avec
 * au moins un exemple>
 *
 * @author ben14
 */
public class Prescription {

    /**
     * Identifiant dans la base de donnée. Ne peut être négatif
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idPresciption;

    /**
     * Nom de la prescription. Ne peut pas être nul ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String prescriptionName;

    /**
     * Prescription Constructor
     *
     * @param newIdPrescription   Permet de stocker l'id de la Base de donnée.
     * @param newPrescriptionName Le nom de la prescription
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Prescription(int newIdPrescription, String newPrescriptionName) {
        if (newIdPrescription < 0) throw new IllegalArgumentException("Un nombre de membres ne peut être negatif");
        if (newPrescriptionName == null || newPrescriptionName.length() < 1)
            throw new IllegalArgumentException("un string ne doit pas etre vide");
        idPresciption = newIdPrescription;
        prescriptionName = newPrescriptionName;
    }

    /**
     * @return le nom de la prescription
     */
    public String getPrescriptionName() {
        return prescriptionName;
    }

    /**
     * Method setPrescriptionName
     *
     * @param prescriptionName le nouveau nom de la prescription
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setPrescriptionName(String prescriptionName) {
        if (prescriptionName == null || prescriptionName.length() < 1)
            throw new IllegalArgumentException("un string ne doit pas etre vide");
        this.prescriptionName = prescriptionName;
    }

    /**
     * @return le nom de la prescription
     */
    public int getIdPresciption() {
        return idPresciption;
    }

    /**
     * @return le nom de la prescription
     */
    public String toString() {
        return this.prescriptionName;
    }

}

