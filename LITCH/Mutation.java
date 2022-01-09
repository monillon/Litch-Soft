/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LITCH;

/**
 *  Une mutation est définie par un identifiant et un nom de mutation, elle est intimement liée à une pathologie
 *  et est aussi définie par une classe de mutation
 * @author ben14
 */
public class Mutation {
    /*+
     * Identifiant dans la base de donnée. Ne peut être négatif
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idMutation;

    /**
     * Nom de la mutation. Ne peut pas être null ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String mutationName;

    /**
     * Nom de la classe de la mutation. Ne peut pas être null ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String mutationClass;

    /**
     * Mutation Constructor
     *
     * @param newIdMutation Permet de stocker l'id de la mutation.
     * @param newMutationName Le nom de la mutation
     * @param newMutationClass Le nom de la classe de la mutation
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Mutation(int newIdMutation, String newMutationName, String newMutationClass){
        if (newIdMutation <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newMutationName == null)  throw new IllegalArgumentException("Ne peut pas être null");
        if (newMutationName.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");

        idMutation = newIdMutation;
        mutationName = newMutationName;
        mutationClass = newMutationClass;
    }

    /**
     * Method getIdMutation
     * retourne l'identifiant de la mutation
     */
    public int getIdMutation() {
        return idMutation;
    }

    /**
     * Method getMutationName
     * retourne le nom de la mutation
     */
    public String getMutationName() {
        return mutationName;
    }

    /**
     * Method setMutationName
     *
     * @param mutationName le nouveau nom de la mutation
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setMutationName(String mutationName) {
        if (mutationName == null)  throw new IllegalArgumentException("Ne peut pas être null");
        if (mutationName.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");
        this.mutationName = mutationName;
    }

    /**
     * Method getMutationClass
     * retourne laclasse de la mutation
     */
    public String getMutationClass() {
        return mutationClass;
    }

    /**
     * Method setMutationClasse
     * @param mutationClass la nouvelle classe de la mutation
     */
    public void setMutationClass(String mutationClass) {
        this.mutationClass = mutationClass;
    }
    
}
