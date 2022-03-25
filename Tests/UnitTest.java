package Tests;

import LITCH.Unit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests unitaires de la classe Unit
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
public class UnitTest {

    private Unit unit;
    private Unit unit2;
    private Unit unit3;
    private Unit idZero;
    private Unit idNeg;
    private Unit nameVide;
    private Unit descVide;

    /**
     * Method setUp : initialisation d'une Unit correcte avec les valeurs
     * 2, 3 ou 4 pour l'ID, "unit", "" ou "unit3" pour le nom de l'unit,
     * et "description", "description2" ou "" pour la description de l'unit
     */
    @BeforeEach
    void setUp() {
        unit = new Unit(2, "unit", "description");
        unit2 = new Unit(3, "", "description2");
        unit3 = new Unit(4, "unit3", "");
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Method testUnit
     * Vérifie que la création de l'Unit de base fonctionne et rend bien un objet
     */
    @Test
    public void testUnit(){
        assertNotNull(unit,"Unit OK");
        assertNotNull(unit2, "Unit OK");
        assertNotNull(unit3, "Unit OK");

    }

    /**
     * Method testGetIdUnitOK
     * Vérifie que l'ID retourné est le bon (2, 3 ou 4)
     */
    @Test
    public void testGetIdUnitOK() {
        assertEquals(2, unit.getIdUnit());
        assertEquals(3, unit2.getIdUnit());
        assertEquals(4, unit3.getIdUnit());

    }

    /**
     * Method testNameUnitOK
     * Vérifie que le nom retourné est le bon ("unit", "", "unit3")
     */
    @Test
    public void testGetNameUnitOK() {
        assertEquals("unit", unit.getNameUnit());
        assertEquals("", unit2.getNameUnit());
        assertEquals("unit3", unit3.getNameUnit());
    }

    /**
     * Method testGetDescriptionUnitOK
     * Vérifie que la description retournée est correcte ("description", "description2", "")
     */
    @Test
    public void testGetDescriptionUnitOK() {
        assertEquals("description", unit.getDescriptionUnit());
        assertEquals("description2", unit2.getDescriptionUnit());
        assertEquals("", unit3.getDescriptionUnit());
    }

    /**
     * Method testGetIdUnitZero : un ID égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'ID
     */
    @Test
    public void testGetIdUnitZero() {
        try{
            idZero = new Unit(0, "unit", "description");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testGetIdUnitNeg : un ID négatif doit déclencher une exception
     * test avec la valeur -1 pour l'ID
     */
    @Test
    public void testGetIdUnitNeg() {
        try{
            idNeg = new Unit(-1, "unit", "description");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testSetNameUnitEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetNameUnitEmpty() {
        unit.setNameUnit("");
        assertEquals("", unit.getNameUnit());
    }

    /**
     * Method testSetNameUnitOK
     * test avec la valeur "test2"
     */
    @Test
    public void testSetNameUnitOK() {
        unit.setNameUnit("test2");
        assertEquals("test2", unit.getNameUnit());
    }

    /**
     * Method testSetDescriptionUnitEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetDescriptionUnitEmpty() {
        unit.setDescriptionUnit("");
        assertEquals("", unit.getDescriptionUnit());
    }

    /**
     * Method testSetDescriptionUnitOK
     * test avec la valeur "test2"
     */
    @Test
    public void testSetDescriptionUnitOK() {
        unit.setDescriptionUnit("test2");
        assertEquals("test2", unit.getDescriptionUnit());
    }

    /**
     * Méthode testToString, permet de tester les valeurs de retours de la méthode ToString
     * Nécéssaire pour les lists views
     */
    @Test
    public void testToString() {
        assertEquals("unit - description", unit.toString());
        assertEquals(unit.getNameUnit() + " - " + unit.getDescriptionUnit(), unit.toString());
    }

}