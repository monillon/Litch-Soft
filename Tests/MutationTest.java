package Tests;

import LITCH.Mutation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests unitaires de la classe Mutation
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
public class MutationTest {

    private Mutation mut;
    private Mutation idZero;
    private Mutation idNeg;
    private Mutation badNameMut;
    private Mutation noClasse;
    private Mutation noClasse2;

    /**
     * Method setUp : initialisation d'une mutation correcte avec les valeurs
     * 2 pour l'iD, "muttest" pour le nom de la mutation
     * et "classtest" ou ""pour la classe de la mutation
     */
    @Before
    public void setUp() {
        mut = new Mutation(2, "muttest", "classtest");
        noClasse = new Mutation(2, "muttest", "");
        noClasse2 = new Mutation(2, "muttest", null);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Method testMutation
     * Vérifie que la création de la mutation de base fonctionne et rend bien un objet
     */
    @Test
    public void testMutation(){
        assertNotNull(mut,"Mutation OK");

    }

    /**
     * Method testGetIdMutationOK
     * Vérifie que l'ID retourné est le bon (2)
     */
    @Test
    public void testGetIdMutationOK() {
        assertEquals(2, mut.getIdMutation());
    }

    /**
     * Method testGetMutationNameOK
     * Vérifie que le nom retourné est le bon (muttest)
     */
    @Test
    public void testGetMutationNameOK() {
        assertEquals("muttest", mut.getMutationName());
    }

    /**
     * Method testGetMutationClassOK
     * Vérifie que la classe retournée est correcte (classtest)
     */
    @Test
    public void testGetMutationClassOK() {
        assertEquals("classtest", mut.getMutationClass());
    }

    /**
     * Method testGetMutationClassVideOK
     * Vérifie qu'une classe peut être vide (string vide)
     */
    @Test
    public void testGetMutationClassVideOK() {
        assertEquals("", noClasse.getMutationClass());
    }

    /**
     * Method testGetMutationClassNullOK
     * Vérifie qu'une classe peut être null
     */
    @Test
    public void testGetMutationClassNullOK() {
        assertEquals(null, noClasse2.getMutationClass());
    }

    /**
     * Method testSetMutationNameOK
     * test avec la valeur "test2"
     */
    @Test
    public void testSetMutationNameOK() {
        mut.setMutationName("test2");
        assertEquals("test2", mut.getMutationName());
    }

    /**
     * Method testSetMutationClassOK
     * test avec la valeur "testC"
     */
    @Test
    public void testSetMutationClassOK() {
        mut.setMutationClass("testC");
        assertEquals("testC", mut.getMutationClass());
    }

    /**
     * Method testSetMutationClassEmptyOK
     * test avec la valeur ""
     */
    @Test
    public void testSetMutationClassEmptyOK() {
        mut.setMutationClass("");
        assertEquals("", mut.getMutationClass());
    }

    /**
     * Method testSetMutationClassNullOK
     * test avec la valeur null
     */
    @Test
    public void testSetMutationClassNullOK() {
        mut.setMutationClass(null);
        assertEquals(null, mut.getMutationClass());
    }


    /**
     * Method testGetMutationClassIdZero : un ID égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'ID
     */
    @Test
    public void testGetMutationClassIdZero(){
        try{
            idZero = new Mutation(0, "muttest", "classtest");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }
    /**
     * Method testGetMutationClassIdZero : un ID négatif doit déclencher une exception
     * test avec la valeur -1 pour l'ID
     */
    @Test
    public void testGetMutationClassIdNeg(){
        try{
            idNeg = new Mutation(-1, "muttest", "classtest");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testGetMutationNameVide : un nom vide dans le constructeur doit déclencher une exception
     * test avec un String vide ""
     */
    @Test
    public void testGetMutationNameVide() {
        try {
            badNameMut = new Mutation(2, "", "classtest");
            fail("exception");
        } catch (IllegalArgumentException ise) {
        }

    }

    /**
     * Method testGetMutationNameNull : un nom null dans le constructeur doit déclencher une exception
     * test avec un newMutationName null
     */
    @Test
    public void testGetMutationNameNull() {
        try {
            badNameMut = new Mutation(2, null, "classtest");
            fail("exception");
        } catch (IllegalArgumentException ise) {
        }
    }

    /**
     * Method testSetMutationNameEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetMutationNameEmpty(){
        try{
            mut.setMutationName("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("muttest",mut.getMutationName());
    }

    /**
     * Method testSetMutationNameNull : un paramètre null doit déclencher une exception
     * test avec un mutationName null
     */
    @Test
    public void testSetMutationNameNull(){
        try{
            mut.setMutationName(null);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("muttest",mut.getMutationName());
    }


    @Test
    public void testToString() {
        assertEquals("muttest", mut.toString());
        assertEquals(mut.getMutationName(), mut.toString());
    }

}