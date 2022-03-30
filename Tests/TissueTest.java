package Tests;
import LITCH.Organ;
import LITCH.Tissue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *  * Tests unitaires de la classe Tissue
 * Prend en charge tous les cas envisageables en fonction des spécifications
 */
public class TissueTest {
    private Tissue good;
    private Tissue idneg;
    private Tissue nomnull;
    private Tissue nomvide;

    /**
     * Method setUp : initialisation d'un tissu correct, 1 en id et "test" en nom
     */
    @Before
    public void setUp() throws Exception {
        good = new Tissue(1,"test");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testTissugood :
     * Vérifie que la creation d'un bon tissue fonctionne et créé bien un objet
     */
    @Test
    public void testTissugood(){
        assertNotNull(good);
    }

    /**
     * Methode testTissuidneg :
     * vérifie qu'en saisissant un id négatif l'exception se declenche
     */
    @Test
    public void testTissuidneg() {
        try{
            idneg = new Tissue(-1,"Test");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testTissunomvide :
     * Vérifie qu'en saisissant un nom string vide, l'exception se déclenche
     */
    @Test
    public void testTissunomvide() {
        try{
            nomvide = new Tissue(1,"");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testTissunomnull :
     * Vérifie qu'avec un nom null, l'exception se déclenche
     */
    @Test
    public void testTissunomnull() {
        try{
            nomnull = new Tissue(1,null);
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Méthode testToString : vérifie que la méthode to string renvoi uniquement le nom du tissu
     * Condition nécessaire pour module listing
     */
    @Test
    public void testToSting() {
        assertEquals("test", good.toString());
        assertEquals(good.getNameTissue(), good.toString());
    }
}