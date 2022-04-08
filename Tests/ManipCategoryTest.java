package Tests;

import LITCH.ManipCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests unitaires de la classe ManipCategory
 *
 * @author Maxime ONILLON
 * @version 03/04/2022
 */
public class ManipCategoryTest {
    private ManipCategory good;
    private ManipCategory idneg;
    private ManipCategory nomnull;
    private ManipCategory nomvide;

    /**
     * Method setUp : initialisation d'une ManipCategory correcte, 1 en id et "nomDeLaCat" en nom
     */
    @Before
    public void setUp() throws Exception {
        good = new ManipCategory(1,"nomDeLaCat");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testManipCategoryGood :
     * Vérifie que la creation d'un bon ManipCategory fonctionne et créé bien un objet
     */
    @Test
    public void testManipCategoryGood(){
        assertNotNull(good);
    }

    /**
     * Methode testManipCategoryIdneg :
     * vérifie qu'en saisissant un id négatif l'exception se déclenche
     */
    @Test
    public void testManipCategoryIdneg() {
        try{
            idneg = new ManipCategory(-1,"nomDeLaCat");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testManipCategoryNomVide :
     * Vérifie qu'en saisissant un nom string vide, l'exception se déclenche
     */
    @Test
    public void testManipCategoryNomVide() {
        try{
            nomvide = new ManipCategory(1,"");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Methode testManipCategoryNomNull :
     * Vérifie qu'avec un nom null, l'exception se déclenche
     */
    @Test
    public void testManipCategoryNomNull() {
        try{
            nomnull = new ManipCategory(1,null);
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Méthode testToString : vérifie que la méthode to string renvoi uniquement le nom de la catégorie
     * Condition nécessaire pour module listing
     */
    @Test
    public void testToSting() {
        assertEquals("nomDeLaCat", good.toString());
        assertEquals(good.getNameManipCategory(), good.toString());
    }
}
