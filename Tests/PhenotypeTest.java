package Tests;

import LITCH.*;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Phenotype
 * prend en charge les différents cas envisageables en fonction des spécifications
 */
class PhenotypeTest {
private Phenotype phenotype;
private Unit unit;
private Mutation mutation;
private Prescription prescrip;
private Pathology pathology;
private PreopData preopdata;


    @BeforeEach
    /**
     * Methode setUp : initialisation d'un phenotype avec des valeurs correctes
     */
    public void setUp() {
        unit = new Unit(1,"test","testing");
        mutation = new Mutation(1,"testm","testc");
        prescrip = new Prescription(1,"testprescription");
        pathology = new Pathology(1,"testpatho");
        preopdata = new PreopData(1,"testpreop");
        phenotype = new Phenotype((float) 0.5,unit,prescrip,mutation,pathology,preopdata);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Methode testPheno
     * Vérifie que l'objet phenotype avec des valeurs correctes est bien instancié
     */
    @Test
    public void testPheno() {
        assertNotNull(phenotype);
    }

    /**
     * Methode testPhenoValueneg
     * Vérifie que l'exception se déclenche lorsque l'on saisi une valeur preop négative
     */
    @Test
    void testPhenovalueneg() {
        try{
        Phenotype badphenotype = new Phenotype((float) -0.5,unit,prescrip,mutation,pathology,preopdata);
        fail("Exception");}
        catch(IllegalArgumentException ise){};

    }

    /**
     * Methode testSetok
     * Vérifie que le setter de la valeur preop fonctionne bien avec une valeur correcte
     */
    @Test
    void testSetok() {
        phenotype.setPreopValue((float) 1.2);
        assertEquals((float) 1.2, phenotype.getPreopValue());
    }

    /**
     * Methode testSetnega
     * Vérifie que l'exception se déclenche lorsque l'on modifie la valeur preop avec une valeur negative
     */
    @Test
    void testSetnega() {
        try{
        phenotype.setPreopValue((float) -2.5);
        fail("Exception");}
        catch(IllegalArgumentException ise){}
        assertEquals((float) 0.5, phenotype.getPreopValue());
    }


}