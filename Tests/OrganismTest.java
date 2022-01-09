package Tests;

import LITCH.Organism;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Organism
 * Prend en charge tous les cas envisgeables en fonciton des spécifications
 */
public class OrganismTest {

    private Organism orga;
    private Organism idZero;
    private Organism idNeg;
    private Organism nameVide;

    /**
     * Method setUp : initialisation d'un organe correct, une avec une classe et l'autre sans
     * 2 pour l'iD de la mutation, "orga" pour le nom de l'organisme
     */
    @BeforeEach
    public void setUp() {
        orga = new Organism(2, "orga");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Method testOrganism
     * Vérification que la création d'un organisme fonctionne et rend bien un objet
     */
    @Test
    public void testOrganism(){
        assertNotNull(orga,"Organism OK");

    }

    /**
     * Method testGetIdOrganismOK : retourne l'identifiant de l'organisme
     */
    @Test
    public void testGetIdOrganismOK() {
        assertEquals(2, orga.getIdOrganism());
    }

    /**
     * Method testGetNameOrganismOK : retourne le nom de la l'organisme
     */
    @Test
    public void testGetNameOrganismOK() {
        assertEquals("orga", orga.getNameOrganism());
    }

    /**
     * Method testGetNameOrganismEmpty : un nom vide dans le constructeur doit déclencher une exception
     * test avec un String vide ""
     */
    @Test
    public void testGetNameOrganismEmpty() {
        try{
            nameVide = new Organism(2, "");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetIdOrganismZero : un identifiant vide dans le constructeur doit déclencher une exception
     * test avec un Integer égal à 0
     */
    @Test
    public void testGetIdOrganismZero() {
        try{
            idZero = new Organism(0, "orga");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testGetIdOrganismNeg : un identifiant négatif dans le constructeur doit déclencher une exception
     * test avec un Integer égal à -1
     */
    @Test
    public void testGetIdOrganismNeg() {
        try{
            idNeg = new Organism(-1, "orga");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }


    /**
     * Method testSetNameOrganismEmpty : modifie le nom d'un organisme
     * test avec un string vide ""
     * Ceci ne doit pas effacer l'ancien nom
     */
    @Test
    public void testSetNameOrganismEmpty() {
        try{
            orga.setNameOrganism("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("orga",orga.getNameOrganism());
    }

    /**
     * Method testSetNameOrganismOK : modifie le nom d'un organisme
     * test avec le string "test2"
     */
    @Test
    public void testSetNameOrganismOK() {
        orga.setNameOrganism("test2");
        assertEquals("test2", orga.getNameOrganism());
    }

}