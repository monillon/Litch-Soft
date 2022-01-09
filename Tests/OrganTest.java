package Tests;

import LITCH.Organ;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests unitaires de la classe Organ
 * Prend en charge tous les cas envisageables en fonction des spécifications
 */
public class OrganTest {
private Organ good;
    private Organ idneg;
    private Organ nomnull;
    private Organ nomvide;

    /**
     * Method setUp : initialisation d'un organe correct, 1 en id et "test" en nom
     */
    @Before
    public void setUp() throws Exception {
        good = new Organ(1,"test");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testOrgangood :
     * Vérifie que la creation d'un bon organe fonctionne et créé bien un objet
     */
    @Test
    public void testOrgangood(){
        assertNotNull(good);
    }

    /**
     * Methode testOrganidneg :
     * vérifie qu'en saisissant un id négatif l'exception se declenche
     */
    @Test
    public void testOrganidneg() {
        try{
            idneg = new Organ(-1,"Test");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testOrgannomvide :
     * Vérifie qu'en saisissant un nom string vide, l'exception se déclenche
     */
    @Test
    public void testOrgannomvide() {
        try{
            nomvide = new Organ(1,"");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testOrgannomnull :
     * Vérifie qu'avec un nom null, l'exception se déclenche
     */
    @Test
    public void testOrgannomnull() {
        try{
            nomnull = new Organ(1,null);
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }
}