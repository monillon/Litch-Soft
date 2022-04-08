package Tests;

import LITCH.Organ;
import LITCH.Technic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests unitaires de la classe Technique
 */
public class TechnicTest {
    private Technic good;
    private Technic idneg;
    private Technic nomnull;
    private Technic nomvide;

    /**
     * Method setUp : initialisation d'une technique correcte, 1 en id et "test" en nom et "laDescription" en description
     */
    @Before
    public void setUp() throws Exception {
        good = new Technic(1,"test", "laDescription");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testTechnicGood :
     * Vérifie que la creation d'un bon organe fonctionne et créé bien un objet
     */
    @Test
    public void testTechnicGood(){
        assertNotNull(good);
        assertEquals(1, good.getIdTechnic());
        assertEquals("test", good.getNameTechnic());
        assertEquals("laDescription", good.getDescriptionTechnic());
    }

    /**
     * Methode testTechnicIdneg :
     * vérifie qu'en saisissant un id négatif l'exception se déclenche
     */
    @Test
    public void testTechnicIdneg() {
        try{
            idneg = new Technic(-1,"Test", "description");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testTechnicNomvide :
     * Vérifie qu'en saisissant un nom string vide, l'exception se déclenche
     */
    @Test
    public void testTechnicNomvide() {
        try{
            nomvide = new Technic(1,"", "description");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testTechnicNomnull :
     * Vérifie qu'avec un nom null, l'exception se déclenche
     */
    @Test
    public void testTechnicNomnull() {
        try{
            nomnull = new Technic(1,null, "description");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    @Test
    public void testToString() {
        assertEquals("test", good.toString());
        assertEquals(good.getNameTechnic(), good.toString());
    }
}