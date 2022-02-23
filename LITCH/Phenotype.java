package LITCH;

/**
 * Le phénotype regroupe toutes les informations pré opératoire d'un sujet : valeurs préopératoire, pathologies, mutations, prescriptions.
 *
 * @author ben14
 */
public class Phenotype {

    private float preopValue;
    private Unit unitPreopValue;
    private Prescription prescription;
    private Mutation mutation;
    private Pathology pathology;
    private PreopData preopData;

    /**
     * constructeur de la classe Phenotype
     *
     * @param newPreopValue     valeur pré opératoire
     * @param newUnitPreopValue unité de la valeur pré opératoire
     * @param newPrescription   objet prescription
     * @param newMutation       objet mutation
     * @param newPathology      objet pathologie
     * @param newPreopData      objet donnée pré opératoire
     */
    public Phenotype(float newPreopValue, Unit newUnitPreopValue, Prescription newPrescription, Mutation newMutation, Pathology newPathology, PreopData newPreopData) {
        if (newPreopValue < (float) 0.0)
            throw new IllegalArgumentException("Une données opératoire ne peut être negative");

        unitPreopValue = newUnitPreopValue;
        preopValue = newPreopValue;
        prescription = newPrescription;
        mutation = newMutation;
        pathology = newPathology;
        preopData = newPreopData;
    }

    public Phenotype() {
    }

    /**
     * Retourne la valeur preopValue
     *
     * @return la valeur preopValue, récupéré dans la base de données
     */
    public float getPreopValue() {
        return preopValue;
    }

    /**
     * Modifie la valeur preopValue
     *
     * @param preopValue la nouvelle valeur
     * @throws IllegalArgumentException si la valeur est négative
     */
    public void setPreopValue(float preopValue) {
        if (preopValue < (float) 0.0)
            throw new IllegalArgumentException("Une données opératoire ne peut être negative");

        this.preopValue = preopValue;
    }

    /**
     * @return un objet  Unit
     */
    public Unit getUnitPreopValue() {
        return unitPreopValue;
    }

    /**
     * Modifie l'objet Unit
     *
     * @param unitPreopValue le nouvel objet, la nouvelle unité
     */
    public void setUnitPreopValue(Unit unitPreopValue) {
        this.unitPreopValue = unitPreopValue;
    }

    /**
     * @return un objet Prescription
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Modifie l'objet prescription
     *
     * @param prescription le nouvel objet, la nouvelle prescription
     */
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    /**
     * @return un objet Mutation
     */
    public Mutation getMutation() {
        return mutation;
    }

    /**
     * Modifie l'objet mutation
     *
     * @param mutation le nouvel objet, la nouvelle mutation
     */
    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    /**
     * @return un objet Pathology
     */
    public Pathology getPathology() {
        return pathology;
    }

    /**
     * Modifie l'objet pathology
     *
     * @param pathology le nouvel objet, la nouvelle pathology
     */
    public void setPathology(Pathology pathology) {
        this.pathology = pathology;
    }

    /**
     * @return un objet PreopData
     */
    public PreopData getPreopData() {
        return preopData;
    }

    /**
     * Modifie l'objet preopdata
     *
     * @param preopData le nouvel objet, la nouvelle donnée préopératoire
     */
    public void setPreopData(PreopData preopData) {
        this.preopData = preopData;
    }

}
