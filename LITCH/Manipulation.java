/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LITCH;

import java.util.Date;

/**
 * Un prélèvement est défini par un idManipulation, un idUtilisateur, un idModeleManip, un idResultat, une dateHeureManipulation et un commentaireManipulation
 * @author vmichalski
 */
public class Manipulation {
    /**
     * id de la manipulation
     */
    private int idManipulation;
    /**
     * Utilisateur réalisant la manipulation
     */
    private int idUtilisateur;
    /**
     * modèle de manipulation utilisé pour la manipulation
     */
    private int idModeleManip;
    /**
     * id du résultat de la manipulation
     */
    private int idResultat;
    /**
     * la date à laquelle la manipulation a été réalisé
     */
    private Date dateHeureManipulation;
    /**
     * commentaire de la manipulation
     */
    private String commentaireManipulation;


    /**
     * Sample Constructor
     *
     * @param newIdManipulation Permet de stocker l'id de la manipulation.
     * @param newIdUtilisateur Permet de stocker l'id de l'utilisateur réalisant la manipulation
     * @param newIdModeleManip Permet de stocker le modèle de manipulation utilisé pour la manipulation
     * @param newIdResultat Permet de stocker l'id du résultat de la manipulation
     * @param newDateHeureManipulation Permet de stocker la date à laquelle la manipulation a été réalisé
     * @param newCommentaireManipulation Permet de stocker le commentaire de la manipulation
     * @throws IllegalArgumentException si les contraintes des attributs sont violées.
     */
    public Manipulation(int newIdManipulation, int newIdUtilisateur, int newIdModeleManip, int newIdResultat, Date newDateHeureManipulation, String newCommentaireManipulation){
        if (newIdManipulation <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newIdUtilisateur <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newIdModeleManip <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        if (newIdResultat <= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        idManipulation = newIdManipulation;
        idUtilisateur = newIdUtilisateur;
        idModeleManip = newIdModeleManip;
        idResultat = newIdResultat;
        dateHeureManipulation = newDateHeureManipulation;
        commentaireManipulation = newCommentaireManipulation;
    }

    /**
     * Method getIdManipulation
     * retourne l'id de la manipulation
     */
    public int getIdManipulation() {
        return idManipulation;
    }

    /**
     * Method setIdSample
     * @param idManipulation le nouvel id de la manipulation
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setIdManipulation(int idManipulation) {
        if (idManipulation<= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.idManipulation = idManipulation;
    }

    /**
     * Method getIdUtilisateur
     * retourne l'id de l'utilisateur de la manipulation
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Method setIdUtilisateur
     * @param idUtilisateur le nouvel identifiant de l'utilisateur
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setIdUtilisateur(int idUtilisateur) {
        if (idUtilisateur<= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Method getIdModeleManip
     * retourne l'id du modèle de manipulation
     */
    public int getIdModeleManip() {
        return idModeleManip;
    }

    /**
     * Method idModeleManip
     * @param idModeleManip le nouvel identifiant du modèle de manipulation
     * @throws IllegalArgumentException si le nom est vide ou null
     */
    public void setIdModeleManip(int idModeleManip) {
        if (idModeleManip<= 0) throw new IllegalArgumentException("Ne peut pas être nul ou négatif");
        this.idModeleManip = idModeleManip;
    }


}
