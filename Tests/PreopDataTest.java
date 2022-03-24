package Tests;

import LITCH.Mutation;
import LITCH.PreopData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests unitaires de la classe PreopData
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
class PreopDataTest {

    private PreopData preop;
    private PreopData badPreop;
    private PreopData idZero;
    private PreopData idNeg;
    private PreopData nameVide;

    /**
     * Method setUp : initialisation d'une preopData correcte avec les valeurs
     * 2 pour l'iD et "preop" pour le nom de la preopData
     */
    @BeforeEach
    void setUp() {
        preop = new PreopData(2, "preop");
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Method testPreopData
     * Vérifie que la création de la preopData de base fonctionne et rend bien un objet
     */
    @Test
    public void testPreopData(){
        assertNotNull(preop,"PreopData OK");

    }

    /**
     * Method testGetIdPreopDataOK
     * Vérifie que l'ID retourné est le bon (2)
     */
    @Test
    public void testGetIdPreopDataOK() {
        assertEquals(2, preop.getIdPreop());
    }

    /**
     * Method testGetNamePreopDataOK
     * Vérifie que le nom retourné est le bon (preop)
     */
    @Test
    public void testGetNamePreopDataOK() {
        assertEquals("preop", preop.getNamePreop());
    }

    /**
     * Method testGetNamePreopDataEmpty : un nom vide dans le constructeur doit déclencher une exception
     * test avec un String vide ""
     */
    @Test
    public void testGetNamePreopDataEmpty() {
        try{
            nameVide = new PreopData(2, "");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetIdPreopDataZero : un ID égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'ID
     */
    @Test
    public void testGetIdPreopDataZero() {
        try{
            idZero = new PreopData(0, "preop");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testGetIdPreopDataNeg : un ID négatif doit déclencher une exception
     * test avec la valeur -1 pour l'ID
     */
    @Test
    public void testGetIdPreopDataNeg() {
        try{
            idNeg = new PreopData(-1, "preop");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testSetNamePreopDataEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetNamePreopDataEmpty() {
        try{
            preop.setNamePreop("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("preop",preop.getNamePreop());
    }

    /**
     * Method testSetNamePreopDataOK
     * test avec la valeur "test2"
     */
    @Test
    public void testSetNamePreopDataOK() {
        preop.setNamePreop("test2");
        assertEquals("test2", preop.getNamePreop());
    }

    /**
     * Method testGetPreopDataNameNull : un nom null dans le constructeur doit déclencher une exception
     * test avec un newNamePreop null
     */
    @Test
    public void testGetPreopDataNameNull() {
        try {
            badPreop = new PreopData(2, null);
            fail("exception");
        } catch (IllegalArgumentException ise) {
        }
    }

    /**
     * Method testSetPreopDataNameNull : un paramètre null doit déclencher une exception
     * test avec un namePreop null
     */
    @Test
    public void testSetMutationNameNull(){
        try{
            preop.setNamePreop(null);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("preop",preop.getNamePreop());
    }

    /**
     * Méthode testToString : vérifie que la méthode to string renvoi uniquement le nom du tissu
     * Condition nécessaire pour module suppression
     */
    @org.junit.Test
    public void testToSting() {
        assertEquals("preop", preop.toString());
        assertEquals(preop.getNamePreop(), preop.toString());
    }

}