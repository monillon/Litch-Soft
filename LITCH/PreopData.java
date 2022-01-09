/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LITCH;

/**
 * Classe PreopData regroupant les données pré-opératoires
 * @author ben14
 */
public class PreopData {

    /**
     * Identifiant dans la base de donnée. Ne peut être négatif ou nul
     * La vérification de l'unicité est laissée à la base de données
     */
    private int idPreop;
    /**
     * Nom de PreopData. Ne peut pas être nul ni vide
     * La vérification de l'unicité est laissée à la base de données
     */
    private String namePreop;

    /**
     * Constructeur de la classe PreopData
     * @param newIdPreop l'ID de la donnée pré-opératoire
     * @param newNamePreop le nom de la donnée pré-opératoire
     * @throws IllegalArgumentException si l'ID est nul ou négatif ou si le nom est vide ou null
     */
    public PreopData(int newIdPreop, String newNamePreop){
        if (newIdPreop <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newNamePreop == null)  throw new IllegalArgumentException("Ne peut pas être null");
        if (newNamePreop.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");
        idPreop = newIdPreop;
        namePreop = newNamePreop;
    }

    /**
     * Getter de l'ID de la donnée pré-opératoire
     * @return idPreop
     */
    public int getIdPreop() {
        return idPreop;
    }

    /**
     * Getter du nom de la donnée pré-opératoire
     * @return namePreop
     */
    public String getNamePreop() {
        return namePreop;
    }

    /**
     * Setter du nom de la donnée pré-opératoire
     * @param namePreop le nouveau nom
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setNamePreop(String namePreop) {
        if (namePreop == null)  throw new IllegalArgumentException("Ne peut pas être null");
        if (namePreop.length() <1) throw new IllegalArgumentException("Ne peut pas être vide");
        this.namePreop = namePreop;
    }
    
}
