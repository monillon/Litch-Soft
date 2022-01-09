package Tests;

import LITCH.Prescription;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests unitaires de la classe Prescription
 * Prend en charge tous les cas envisgeables en fonciton des spécifications
 */
public class PrescriptionTest {
    private Prescription prescription;
    /**
     * Method setUp : initialisation d'une prescription correcte avec les valeurs
     * 1 pour l'iD et "test" pour le nom de la prescription
     */
    @Before
    public void setUp( ) {
        prescription = new Prescription(1,"test");
    }

    @After
    public void tearDown() {
    }

    /**
     * Method TestPrescripOk
     * Vérification que la création de la prescription de base fonctionne et rend bien un objet
     */
    @Test
    public void testPrescripOk(){
        assertNotNull(prescription);
    }

    /**
     * Method TestPrescripidneg : un id négatif doit déclencher une exception
     * test avec la valeur -1 pour l'id
     */
    @Test
    public void testPrescripidneg(){
        try{
            Prescription badprescrip = new Prescription(-1,"test");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method TestPrescripnomvide : un nom vide dans le constructur doit déclencher une exception
     * test avec un String vide ""
     */
    @Test
    public void testPrescripnomvide(){
        try{
            Prescription badprescrip= new Prescription(1,"");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method TestPrescripnomNull : un nom null doit déclencher une exception
     * test avec un nom null
     */
    @Test
    public void testPrescripnomNull(){
        try{
            Prescription badprescrip= new Prescription(1,null);
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method TestSetnameok
     * test avec la valeur "test2"
     */
    @Test
    public void testSetnameok(){
        prescription.setPrescriptionName("test2");
        assertEquals("test2",prescription.getPrescriptionName());
    }

    /**
     * Method TestSetnamevide : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetnamevide(){
        try{
            prescription.setPrescriptionName("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("test",prescription.getPrescriptionName());
    }

    /**
     * Method testSetnamenull : un nom null doit déclencher une exception
     * modifie le nom null
     */
    @Test
    public void testSetnamenull(){
        try{
            prescription.setPrescriptionName(null);}
    catch(IllegalArgumentException ise){}

    }
}
