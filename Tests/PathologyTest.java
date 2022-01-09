package Tests;

import LITCH.Organism;
import LITCH.Pathology;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Pathology
 * Prend en charge tous les cas envisgeables en fonciton des spécifications
 */
public class PathologyTest {
private Pathology pathology;

    /**
     * Method setUp : initialisation d'un organe correct, une avec une classe et l'autre sans
     * 1 pour l'iD de la pathologie, "test" pour le nom de la pathologie
     */
    @Before
    public void setup() {
        pathology = new Pathology(1,"test");
    }

    @After
    public void teardown() {
    }

    /**
     * Method TestPatho
     * Vérification que la création d'une pathologie fonctionne et rend bien un objet
     */
    @Test
    public void TestPatho(){
        assertNotNull(pathology,"Pathology créée");

    }

    /**
     * Method TestPathoidnegatif : création d'une mutation avec un identifiant negatif
     * test avec newIdPathology = 0
     */
    @Test
    public void testPathoidnegatif(){
        try{
        Pathology badpathology = new Pathology(-2, "test");
        fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testPathonomvide : un nom vide dans le constructeur doit déclencher une exception
     * test avec un String vide ""
     */
    @Test
    public void testPathonomvide(){
        try{
        Pathology badpathology = new Pathology(1, "");
        fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testPathosetnomok : modifie le nom d'une pathologie
     * test avec un string ok "test2"
     */
    @Test
    public void testPathosetnomok(){
        pathology.setNamePathology("test2");
        assertEquals("test2",pathology.getNamePathology());
    }

    /**
     * Method testPathosetnomok : modifie le nom d'une pathologie
     * test avec un string vide""
     */
    @Test
    public void testPathosetnomvide(){
        try{
        pathology.setNamePathology("");
        fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("test",pathology.getNamePathology());
    }
}