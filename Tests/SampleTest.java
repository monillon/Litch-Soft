package Tests;



import LITCH.Sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Sample
 * Prend en charge tous les cas envisgeables en fonciton des spécifications
 */
public class SampleTest {

private Sample sample ;
private Date date;

    /**
     * Method setUp : initialisation d'un organe correct, une avec une classe et l'autre sans
     * 2 pour l'iD de la mutation, "orga" pour le nom de l'organisme
     */
    @Before
    public void setup() throws ParseException {
        SimpleDateFormat formater = null;

        String aujourdhui = "12-12-2020";

        formater = new SimpleDateFormat("dd-MM-yy");
        date = formater.parse(aujourdhui);
        sample = new Sample(1, "étagère 2","Lung", date, "Beau poumon");
    }

    @After
    public void teardown() {
    }

    /**
     * Method testPatho
     * Vérification que la création d'un organisme fonctionne et rend bien un objet
     */
    @Test
    public void TestPatho(){
        assertNotNull(sample,"Sample créé");
    }

    /**
     * Method getIdSubjectOK : retourne l'identifiant de l'échantillon
     */
    @Test
    public void getIdSubjectOK() {
        assertEquals(1, sample.getIdSample());
    }

    /**
     * Method getSampleLocalizationOK : retourne la localisation de l'échantillon
     */
    @Test
    public void getSampleLocalizationOK() {
        assertEquals("étagère 2", sample.getSampleLocalization());
    }

    /**
     * Method getSampleOrganOK : retourne l'organ dont est issu l'échantillon
     */
    @Test
    public void getSampleOrganOK() {
        assertEquals("Lung", sample.getSampleOrgan());
    }

    /**
     * Method getSampleDateOK : retourne la date de l'échantillon
     */
    @Test
    public void getSampleDateOK() {
        assertEquals(date, sample.getTime());
    }

    /**
     * Method getSampleCommentOK : retourne le commentaire de l'échantillon
     */
    @Test
    public void getSampleCommentOK() {
        assertEquals("Beau poumon", sample.getSampleCommentary());
    }

    /**
     * Method testSampleIdNeg : un identififiant négatif dans le constructeur doit déclencher une exception
     * test avec un Integer = -1
     */
    @Test
    public void testSampleIdNeg(){
        try{
            Sample badsample = new Sample(-1,"étagère 2","Lung", date, "Beau poumon");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testSampleId0 : un identififiant null dans le constructeur doit déclencher une exception
     * test avec un Integer = 0
     */
    @Test
    public void testSampleId0(){
        try{
            Sample badsample = new Sample(0,"étagère 2","Lung", date, "Beau poumon");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testSampleDateNull : une date nulle dans le constructeur doit déclencher une exception
     * test avec newTime = null
     */
    //@Test
   // public void testSampleDateNull(){
      //  try{
        //    Sample badsample = new Sample(2,"étagère 2","Lung", null, "Beau poumon");
        //    fail("exception");}
      //  catch(IllegalArgumentException ise){}

  //  }

    /**
     * Method testSampleOrganNull : un organ non renseigné dans le constructeur doit déclencher une exception
     * test avec newSampleOrgan = null
     */
   // @Test
   // public void testSampleOrganNull(){
      //  try{
       //     Sample badsample = new Sample(2,"étagère 2",null, date, "Beau poumon");
       //     fail("exception");}
      //  catch(IllegalArgumentException ise){}

  //  }

    /**
     * Method testSampleLocalizationNulle : une localisation non renseignée dans le constructeur doit déclencher une exception
     * test avec newSampleLocalisation = null
     */
  //  @Test
   // public void testSampleLocalizationNulle(){
    //    try{
    //        Sample badsample = new Sample(2,null,"Lung", date, "Beau poumon");
     //       fail("exception");}
    //    catch(IllegalArgumentException ise){}

  //  }

    /**
     * Method testSampleLocalizationNulle : une commentaire non renseigné dans le constructeur ne doit pas déclencher une exception
     * test avec newSampleCommentary = null
     */
    @Test
    public void testSampleComNull(){
        try{
            Sample badsample = new Sample(2,"étagère 2","Lung", date, null);
           assertEquals(null,badsample.getSampleCommentary());}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testSetLocalizationOk : modifie la localisation de l'échantillon
     * test avec le string "newLocalization"
     */
    @Test
    public void testSetLocalizationOk(){
        sample.setSampleLocalization("newLocalization");
        assertEquals("newLocalization",sample.getSampleLocalization());
    }

    /**
     * Method testSetLocalizationNull : modifie la localisation de l'échantillon
     * Une localisation ne doit pas être nulle, elle conservera donc l'ancienne localisation
     * test avec le string "newLocalization"
     */
    @Test
    public void testSetLocalizationNull(){
        sample.setSampleLocalization(null);
        assertEquals("étagère 2",sample.getSampleLocalization());
    }

    /**
     * Method testSetComOk : modifie le commentaire de l'échantillon
     * test avec le string "azerrty"
     */
    @Test
    public void testSetComOk(){
        sample.setSampleCommentary("azerrty");
        assertEquals("azerrty",sample.getSampleCommentary());
    }

    /**
     * Method testSetComNull : modifie le commentaire de l'échantillon
     * test avec newSampleCommentary = null
     */
    @Test
    public void testSetComNull(){
        sample.setSampleCommentary(null);
        assertEquals(null,sample.getSampleCommentary());
    }
}