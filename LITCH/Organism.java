/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LITCH;

/**
 *  Un organisme est défini par un identifiant et un nom d'organimse
 * @author ben14
 */
public class Organism {

    /**
     * Identifiant de l'organisme correspondant à celui de la base de donnée, ne peut pas être négatif
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idOrganism;

    /**
     * Nom de l'organisme correspondant à celui de la base de donnée, ne peut pas être null
     * La vérification de l'unicité est laissée à la base de données
     */
    private String nameOrganism;

    /**
     * Organism Constructor
     *
     * @param newIdOrganism Permet de stocker l'id de l'Organisme.
     * @param newNameOrganism Le nom de l'organisme
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Organism(int newIdOrganism, String newNameOrganism){
        if (newIdOrganism <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNameOrganism.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");
        idOrganism = newIdOrganism;
        nameOrganism = newNameOrganism;
    }

    /**
     * Method getIdOrganism
     * retourne l'identifiant de l'organisme
     */
    public int getIdOrganism() {
        return idOrganism;
    }

    /**
     * Method getNameOrganism
     * retourne le nom de l'organisme
     */
    public String getNameOrganism() {
        return nameOrganism;
    }

    /**
     * Method setNameOrganism
     *
     * @param nameOrganism le nouveau nom de l'organisme
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setNameOrganism(String nameOrganism) {
        if (nameOrganism.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");
        this.nameOrganism = nameOrganism;
    }
    
}
