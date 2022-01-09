package Tests;

import LITCH.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests unitaires de la classe Subject
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
public class SubjectTest {

    private Unit unit1;
    private Unit unit2;
    private Subject subject;
    private Subject sansCode;
    private Subject ageZero;
    private Subject ageNeg;
    private Subject weightZero;
    private Subject weightNeg;
    private Subject sansComm;
    private Subject codeNull;
    private Sample sample;
    private ArrayList<Sample> sampleList;
    private ArrayList<Phenotype> phenotype;

    /**
     * Method setUp : initialisation d'un Subject correct avec les valeurs
     * 1 pour l'iD, "code" pour le code du sujet, 22 pour l'age, unit1 pour ageUnit,
     * true pour SexeSubject, 32 pour weight, unit2 pour weightUnit, "comment" pour commentSubject
     */
    @Before
    public void setUp() {
        unit1 = new Unit(1, "name1", "desc1");
        unit2 = new Unit(2, "name2", "desc2");
        sample = new Sample(1,"etagere2","lung", null ,"beau poumon");
        phenotype = new ArrayList<Phenotype>();
        sampleList = new ArrayList<Sample>();
        subject = new Subject(1,"code", 22, unit1, true, 32, unit2, "comment");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Method testSubject
     * Vérifie que la création du Subject de base fonctionne et rend bien un objet
     * (idem pour Sample, pour être sur qu'aucun problème ne vienne de cet élément)
     */
    @Test
    public void testSubject(){
        assertNotNull(sample, "Sample OK");
        assertNotNull(subject,"Subject OK");

    }

    /**
     * Method testGetIdSubjectOK
     * Vérifie que l'ID retourné est le bon (1)
     */
    @Test
    public void testGetIdSubjectOK() {
        assertEquals(1, subject.getIdSubject());
    }

    /**
     * Method testGetCodeSubjectOK
     * Vérifie que le code retourné est le bon ("code")
     */
    @Test
    public void testGetCodeSubjectOK() {
        assertEquals("code", subject.getCodeSubject());
    }

    /**
     * Method testGetAgeSubjectOK
     * Vérifie que l'âge retourné est le bon (22)
     */
    @Test
    public void testGetAgeSubjectOK() {
        assertEquals(22, subject.getAgeSubject());
    }

    /**
     * Method testGetUnitAgeSubjectOK
     * Vérifie que l'unité retournée est correcte ("name1")
     */
    @Test
    public void testGetUnitAgeSubjectOK() {
        assertEquals("name1", subject.getAgeUnit().getNameUnit());
    }

    /**
     * Method testGetSexeSubjectOK
     * Vérifie que le boolean retourné est le bon (true)
     */
    @Test
    public void testGetSexeSubjectOK() {
        assertEquals(true, subject.getSexeSubject());
    }

    /**
     * Method testGetWeightSubjectOK
     * Vérifie que le poids retourné est le bon (32)
     */
    @Test
    public void testGetWeightSubjectOK() {
        assertEquals(32, subject.getWeightSubject());
    }

    /**
     * Method testGetUnitWieghtSubjectOK
     * Vérifie que l'unité retournée est correcte ("name2")
     */
    @Test
    public void testGetUnitWeightSubjectOK() {
        assertEquals("name2", subject.getWeightUnit().getNameUnit());
    }

    /**
     * Method testGetCommentSubjectOK
     * Vérifie que le comment retourné est le bon ("comment")
     */
    @Test
    public void testGetCommentSubjectOK() {
        assertEquals("comment", subject.getCommentSubject());
    }

    /**
     * Method testGetCommentSubjectEmpytOK
     * Vérifie que le comment retourné est le bon ("")
     */
    @Test
    public void testGetCommentSubjectEmptyOK() {
        sansComm = new Subject(1, "code", 22, unit1, true, 32, unit2, "");
        assertEquals("", sansComm.getCommentSubject());
    }

    /**
     * Method testGetSubjectIdZero : un ID égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'ID
     */
    @Test
    public void testGetSubjectIdZero() {
        try{
            ageZero = new Subject(0,"code", 0, unit1, true, 32, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }


    /**
     * Method testGetSubjectIdNeg : un ID négatif doit déclencher une exception
     * test avec la valeur -1 pour l'ID
     */
    @Test
    public void testGetSubjectIdNeg() {
        try{
            ageNeg = new Subject(-1,"code", -1, unit1, true, 32, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }


    /**
     * Method testGetSubjectSansCode : un code vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testGetSubjectSansCode() {
        try{
            sansCode = new Subject(1,"", 22, unit1, true, 32, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetSubjectAgeZero : un age égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'age
     */
    @Test
    public void testGetSubjectAgeZero() {
        try{
            ageZero = new Subject(1,"code", 0, unit1, true, 32, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetSubjectAgeNeg : un age négatif doit déclencher une exception
     * test avec la valeur -1 pour l'age
     */
    @Test
    public void testGetSubjectAgeNeg() {
        try{
            ageNeg = new Subject(1,"code", -1, unit1, true, 32, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetSubjectWeightZero : un poids égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour le poids
     */
    @Test
    public void testGetSubjectWeightZero() {
        try{
            weightZero = new Subject(1,"code", 22, unit1, true, 0, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetSubjectWeightNeg : un poids négatif doit déclencher une exception
     * test avec la valeur -1 pour le poids
     */
    @Test
    public void testGetSubjectWeightNeg() {
        try{
            weightNeg = new Subject(1,"code", 22, unit1, true, -1, unit2, "comment");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testSetSubjectCodeOK
     * test avec la valeur "testC"
     */
    @Test
    public void testSetSubjectCodeOK() {
        subject.setCodeSubject("testC");
        assertEquals("testC", subject.getCodeSubject());
    }

    /**
     * Method testGetSubjectCodeNull : un code null dans le constructeur doit déclencher une exception
     * test avec un codeSubject null
     */
    @Test
    public void testGetSubjectCodeNull() {
        try {
            codeNull = new Subject(1,null, 22, unit1, true, 32, unit2, "comment");
            fail("exception");
        } catch (IllegalArgumentException ise) {
        }
    }

    /**
     * Method testSetSubjectNull : un paramètre null doit déclencher une exception
     * test avec un subjectCode null
     */
    @Test
    public void testSetSubjectCodeNull(){
        try{
            subject.setCodeSubject(null);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("code",subject.getCodeSubject());
    }

    /**
     * Method testSetSubjectCodeEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetSubjectCodeEmpty() {
        try{
            subject.setCodeSubject("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("code",subject.getCodeSubject());
    }

    /**
     * Method testSetSubjectAgeOK
     * test avec la valeur 23
     */
    @Test
    public void testSetSubjectAgeOK() {
        subject.setAgeSubject(23);
        assertEquals(23, subject.getAgeSubject());
    }

    /**
     * Method testSetSubjectAgeZero : un paramètre égal à 0 doit déclencher une exception
     * test avec un age 0
     */
    @Test
    public void testSetSubjectAgeZero() {
        try{
            subject.setAgeSubject(0);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals(22,subject.getAgeSubject());
    }

    /**
     * Method testSetSubjectAgeNeg : un paramètre négatif doit déclencher une exception
     * test avec un age négatif -1
     */
    @Test
    public void testSetSubjectAgeNeg() {
        try{
            subject.setAgeSubject(-1);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals(22,subject.getAgeSubject());
    }

    /**
     * Method testSetSubjectSexeOK
     * test avec la valeur false
     */
    @Test
    public void testSetSubjectSexeOK() {
        subject.setSexeSubject(false);
        assertEquals(false, subject.getSexeSubject());
    }

    /**
     * Method testSetSubjectWeightOK
     * test avec la valeur 50
     */
    @Test
    public void testSetSubjectWeightOK() {
        subject.setWeightSubject(50);
        assertEquals(50, subject.getWeightSubject());
    }

    /**
     * Method testSetSubjectWeightZero : un paramètre égal à 0 doit déclencher une exception
     * test avec un poids 0
     */
    @Test
    public void testSetSubjectWeightZero() {
        try{
            subject.setAgeSubject(0);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals(32,subject.getWeightSubject());
    }

    /**
     * Method testSetSubjectWeightNeg : un paramètre négatif doit déclencher une exception
     * test avec un age négatif -1
     */
    @Test
    public void testSetSubjectWeightNeg() {
        try{
            subject.setAgeSubject(-1);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals(32,subject.getWeightSubject());
    }

    /**
     * Method testSetSubjectCommentOK
     * test avec le string "newComment"
     */
    @Test
    public void testSetSubjectCommentOK() {
        subject.setCommentSubject("newComment");
        assertEquals("newComment", subject.getCommentSubject());
    }

    /**
     * Method testSetSubjectCommentEmptyOK
     * test avec un String vide
     */
    @Test
    public void testSetSubjectCommentEmptyOK() {
        subject.setCommentSubject("");
        assertEquals("", subject.getCommentSubject());
    }

    /**
     * Method testAddSample
     * test que l'ajout d'un échantillon se fait correctement en vérifiant la longue de la liste
     */
    @Test
    public void testAddSample() {
        sampleList.add(sample);
        assertEquals(1, sampleList.size());
    }

    /**
     * Method testRemoveSample
     * test que le retrait d'un échantillon se fait correctement en vérifiant la longue de la liste
     */
    @Test
    public void testRemoveSample() {
        sampleList.add(sample);
        sampleList.remove(sample);
        assertEquals(0, sampleList.size());
    }

}