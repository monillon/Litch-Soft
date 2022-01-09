package Tests;
import LITCH.Organism;
import LITCH.Project;
import LITCH.Group;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Tests unitaires de la classe Project
 * Prend en charge tous les cas envisgeables en fonciton des spécifications
 */
public class ProjectTest {

    private Project goodproject;
    private Organism organism;
    private ArrayList<Group> ListOfGroups;

    /**
     * Method setUp : initialisation d'un projet correct avec les valeurs
     * on instancie un organisme pour tester des méthodes lié à la liste de groupes
     */
    @Before
    public void setUp() throws Exception {
        organism = new Organism(1,"A");
        goodproject = new Project(1, "1", "A", ".png");

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Methode testBonProject
     * Vérifie que l'objet goodproject avec des valeurs correctes est bien instancié
     */
    @Test
    public void testBonProject() {

        assertNotNull(goodproject);
    }

    /**
     * Methode testIdNegatif
     * Vérifie que l'exception se déclenche lorsque l'on saisi une valeur d'id negative
     */
    @Test
    public void testIdNegatif() {
        try{
        Project badproject = new Project(-1, "1", "A", ".png");
fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Methode testCodeVide
     * Vérifie qu'un projet avec un code string vide déclenche une exception
     */
    @Test
    public void testCodeVide() {
        try{
        Project badproject = new Project(1, "", "A", ".png");
fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Methode testNameVide
     * Vérifie qu'un projet avec un nom string vide déclenche une exception
     */
    @Test
    public void testNameVide() {
        try{
        Project badproject = new Project(1, "1", "", ".png");
fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Methode testImageUrlVide
     * Vérifie qu'un projet avec un url d'image string vide déclenche une exception
     */
    @Test
    public void testImageUrlVide() {
        try{
        Project badproject = new Project(1, "1", "A", "");
        fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Methode testAjoutGroup
     * Vérifie qu'en ajoutant un groupe au projet, ce groupe figure bien dans la ListOfGroups
     */
    @Test
    public void testAjoutGroup() {
       goodproject.addGroup(1,"1","A",true,true,0,organism);
        assertNotNull(goodproject.searchGroupById(1));
    }

    /**
     * Methode testCodenull
     * Vérifie qu'un projet avec un code null déclenche une exception
     */
    @Test
    public void testCodenull() {
        try{
            Project badproject = new Project(1, null, "A", ".png");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Methode testNamenull
     * Vérifie qu'un projet avec un nom null déclenche une exception
     */
    @Test
    public void testNamenull() {
        try{
            Project badproject = new Project(1, "1", null, ".png");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

}