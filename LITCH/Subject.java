package LITCH;

import java.util.ArrayList;

/**
 * Classe Subject pour renseigner les différents sujets des groupes de projets
 *
 * @author ben14
 */
public class Subject {
    /**
     * Identifiant dans la base de donnée. Ne peut être négatif ou nul
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idSubject;
    /**
     * Code du Subject. Ne peut pas être nul ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String codeSubject;
    /**
     * Age dtu Subject
     */
    private int ageSubject;
    /**
     * Unité de l'âge du Subject
     */
    private Unit ageUnit;
    /**
     * Boolean pour déterminer le sexe du Subject
     */
    private boolean sexeSubject;
    /**
     * Le poids du Subject
     */
    private int weightSubject;
    /**
     * L'unité du poids du Subject
     */
    private Unit weightUnit;
    /**
     * Commentaire facultatif à propos du Subject
     */
    private String commentSubject;
    /**
     * Phénotype relatif au sujet
     */
    private ArrayList<Phenotype> phenotype;
    /**
     * Liste d'echantillons relatifs au Subject
     */
    private ArrayList<Sample> sampleList;

    /**
     * Constructeur d'un Subject
     *
     * @param newIdSubject      l'ID du sujet
     * @param newCodeSubject    le code du sujet
     * @param newAgeSubject     l'âge du sujet
     * @param newAgeUnit        l'unité de l'âge
     * @param newSexeSubject    le sexe du sujet
     * @param newWeightSubject  le poids du sujet
     * @param newWeightUnit     l'unité du poids du sujet
     * @param newCommentSubject un commentaire nécessaire facultatif
     * @throws IllegalArgumentException si l'age, l'ID ou le poids du Subject sont nul ou négatif, si le code ou les unités
     *                                  de l'âge et du poids du Subject sont null ou si le code du Subject est vide
     */
    public Subject(int newIdSubject, String newCodeSubject, int newAgeSubject, Unit newAgeUnit, boolean newSexeSubject, int newWeightSubject, Unit newWeightUnit, String newCommentSubject) {
        if (newAgeSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newIdSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newWeightSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newCodeSubject == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (newAgeUnit == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (newWeightUnit == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (newCodeSubject.length() < 1) throw new IllegalArgumentException("Ne peut pas être vide");


        idSubject = newIdSubject;
        codeSubject = newCodeSubject;
        ageSubject = newAgeSubject;
        ageUnit = newAgeUnit;
        sexeSubject = newSexeSubject;
        weightSubject = newWeightSubject;
        weightUnit = newWeightUnit;
        commentSubject = newCommentSubject;
        phenotype = new ArrayList<Phenotype>();
        sampleList = new ArrayList<Sample>();
    }

    /**
     * Constructeur sans l'ID dans le cas où on prépare le sujet à l'ajout à la base de données
     *
     * @param newCodeSubject    le code du sujet
     * @param newAgeSubject     l'âge du sujet
     * @param newAgeUnit        l'unité de l'âge du sujet
     * @param newSexeSubject    le sexe du sujet
     * @param newWeightSubject  le poids du sujet
     * @param newWeightUnit     l'unité du poids du sujet
     * @param newCommentSubject le commentaire (facultatif)
     * @throws IllegalArgumentException si l'age du Subject est nul ou négatif, si le code ou les unités
     *                                  de l'âge et du poids du Subject sont null ou si le code du Subject est vide
     */
    public Subject(String newCodeSubject, int newAgeSubject, Unit newAgeUnit, boolean newSexeSubject, int newWeightSubject, Unit newWeightUnit, String newCommentSubject) {
        if (newCodeSubject.length() < 1) throw new IllegalArgumentException("Ne peut pas être vide");
        if (newCodeSubject == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (newAgeUnit == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (newAgeSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");


        codeSubject = newCodeSubject;
        ageSubject = newAgeSubject;
        ageUnit = newAgeUnit;
        sexeSubject = newSexeSubject;
        weightSubject = newWeightSubject;
        weightUnit = newWeightUnit;
        commentSubject = newCommentSubject;
        phenotype = new ArrayList<Phenotype>();
        sampleList = new ArrayList<Sample>();
    }

    /**
     * Getter de l'ID du sujet
     *
     * @return idSubject
     */
    public int getIdSubject() {
        return idSubject;
    }

    /**
     * Getter du code du sujet
     *
     * @return the codeSubject
     */
    public String getCodeSubject() {
        return codeSubject;
    }

    /**
     * Setter du code du sujet
     *
     * @param codeSubject the codeSubject to set
     * @throws IllegalArgumentException si le code du Subject est null ou vide
     */
    public void setCodeSubject(String codeSubject) {
        if (codeSubject == null) throw new IllegalArgumentException("Ne peut pas être null");
        if (codeSubject.length() < 1) throw new IllegalArgumentException("Ne peut pas être vide");
        this.codeSubject = codeSubject;
    }

    /**
     * Getter de l'âge du sujet
     *
     * @return the ageSubject
     */
    public int getAgeSubject() {
        return ageSubject;
    }

    /**
     * Setter de l'âge du sujet
     *
     * @param ageSubject the ageSubject to set
     * @throws IllegalArgumentException si l'âge du Subject est nul ou négatif
     */
    public void setAgeSubject(int ageSubject) {
        if (ageSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.ageSubject = ageSubject;
    }

    /**
     * Getter de l'unité de l'age du sujet
     *
     * @return ageUnit
     */
    public Unit getAgeUnit() {
        return ageUnit;
    }

    /**
     * Setter de l'unité de l'age du sujet
     *
     * @param ageUnit la nouvelle unité
     */
    public void setAgeUnit(Unit ageUnit) {
        this.ageUnit = ageUnit;
    }

    /**
     * Getter du sexe du sujet
     *
     * @return the sexeSubject
     */
    public boolean getSexeSubject() {
        return sexeSubject;
    }

    /**
     * Setter du sexe du sujet
     *
     * @param sexeSubject the sexeSubject to set
     */
    public void setSexeSubject(boolean sexeSubject) {
        this.sexeSubject = sexeSubject;
    }

    /**
     * Getter du poids du sujet
     *
     * @return the weightSubject
     */
    public int getWeightSubject() {
        return weightSubject;
    }

    /**
     * Setter du poids du sujet
     *
     * @param weightSubject the weightSubject to set
     * @throws IllegalArgumentException si le poids du Subject est nul ou négatif
     */
    public void setWeightSubject(int weightSubject) {
        if (weightSubject <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.weightSubject = weightSubject;
    }

    /**
     * Getter du commentaire associé au sujet
     *
     * @return the commentSubject
     */
    public String getCommentSubject() {
        return commentSubject;
    }

    /**
     * Setter du commentaire associé au sujet
     *
     * @param commentSubject the commentSubject to set
     */
    public void setCommentSubject(String commentSubject) {
        this.commentSubject = commentSubject;
    }

    /**
     * Getter du phénotype du sujet
     *
     * @return phenotype
     */
    public ArrayList<Phenotype> getPhenotype() {
        return phenotype;
    }

    /**
     * Setter du phenotype du sujet
     *
     * @param phenotype le nouveau phénotype
     */
    public void setPhenotype(Phenotype phenotype) {
        this.phenotype.add(phenotype);
    }

    /**
     * Getter de la liste d'échantillons associés au sujet
     *
     * @return sampleList
     */
    public ArrayList<Sample> getSampleList() {
        return sampleList;
    }

    /**
     * Setter de la liste d'échantillons associés au sujet
     *
     * @param sampleList la nouvelle sampleList
     */
    public void setSampleList(ArrayList<Sample> sampleList) {
        this.sampleList = sampleList;
    }

    /**
     * Add a new sample in the list
     *
     * @param newSample l'échantillon à ajouter
     */
    public void addSample(Sample newSample) {
        sampleList.add(newSample);
    }

    /**
     * Remove a new sample from the list
     *
     * @param newSample l'échantillon à retirer
     */
    public void removeSample(Sample newSample) {
        sampleList.remove(newSample);
    }

    /**
     * Getter de l'unité du poids
     *
     * @return weightUnit
     */
    public Unit getWeightUnit() {
        return weightUnit;
    }

    /**
     * Setter de l'unité du poids
     *
     * @param weightUnit
     */
    public void setWeightUnit(Unit weightUnit) {
        this.weightUnit = weightUnit;
    }

    /**
     * Setter de l'ID du sujet
     *
     * @param id_sujet le nouvel ID
     * @throws IllegalArgumentException si l'ID est ou négatif
     */
    public void setIdSubject(int id_sujet) {
        if (id_sujet <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.idSubject = id_sujet;

    }
}
