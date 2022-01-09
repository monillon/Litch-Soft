
package Tests;

        import LITCH.Group;
        import LITCH.Organism;
        import LITCH.Subject;
        import LITCH.Unit;
        import org.junit.Before;
        import org.junit.After;
        import org.junit.Test;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;

        import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Group
 * prend en charge les différents cas envisageables en fonction des spécifications
 */
public class GroupTest {
    private Organism organism;
    private Group group;
    private Subject subject;
    private Unit unit,unitkg;

    /**
     * setUp : Initialisation d'un groupe avec des valeurs correctes
     */
    @Before
    public void setUp() {
        unit = new Unit(1,"test","testing");
        unitkg = new Unit(2,"test","testing");
        subject = new Subject(1, "1", 1, unit,true, 50, unitkg, "lol");
        organism = new Organism(1,"test");
        group = new Group(1,"1","A",true,true,0, organism);
    }

    @After
    public void tearDown() {
    }

    /**
     * Methode testGroupOk
     * Vérifie que l'objet groupe avec des valeurs correctes est bien créé.
     */
    @Test
    public void testGroupOk (
    ) {

        assertNotNull(group,"group créé");
    }

    /**
     * Methode testGroupIdneg
     * Vérifie avec la valeur -1 pour idGroup que l'exception se déclenche
     */
    @Test
    public void testGroupIdneg (){
    try {
        Group badgroup = new Group(-1, "1", "A", true, true, 0, organism);
        fail("exception");}
        catch(IllegalArgumentException ise) {};
    }

    /**
     * Methode testGroupnbmembresneg
     * Vérifie que l'on déclenche une exception lorsque le nombre de membres est une valeur négative
     */
    @Test
    public void testGroupnbmembresneg (){
        try {
            Group badgroup = new Group(1, "1", "A", true, true, -1, organism);
            fail("exception");}
        catch(IllegalArgumentException ise) {};
    }

    /**
     * Methode testGroupnamevide
     * Vérifie qu'une exception se délenche lorsque l'on saisi un string vide pour le nom du groupe
     */
    @Test
    public void testGroupnamevide (){
        try {
            Group badgroup = new Group(1, "1", "", true, true, 0, organism);
            fail("exception");}
        catch(IllegalArgumentException ise) {};
    }

    /**
     * Methode testGroupcodevide
     * Vérifie qu'une exception se délenche lorsque l'on saisi un string vide pour le code du groupe
     */
    @Test
    public void testGroupcodevide (){
try {
        Group badgroup = new Group(1, "", "A", true, true, 0, organism);
        fail("exception");}
        catch(IllegalArgumentException ise) {};
}


    /**
     * Methode testSetGroupName
     * Vérifie qu'une exception se déclenche lorsque l'on saisi un string vide pour le nom du groupe et ne change pas le nom déjà saisi
     */
    @Test
    public void testSetGroupName(){
        try{
        group.setNameGroup("");
        fail("exception");}
        catch(IllegalArgumentException ise) {}
        assertEquals("A",group.getNameGroup());
}

    /**
     * Methode testSetGroupCode
     * Vérifie qu'une exception se délenche lorsque l'on saisi un string vide pour le code du groupe et ne change pas le code déjà saisi
     */
    @Test
    public void testSetGroupCode(){
        try{
        group.setCodeGroup("");
        fail("exception");}
        catch(IllegalArgumentException ise) {}
        assertEquals("1",group.getCodeGroup());
    }

    /**
     * Methode testSetNbMembres
     *  Vérifie qu'une exception se déclenche lorsque l'on modifie la valeur du nombre de membres avec une valeur négative.
     */
    @Test
    public void testSetNbMembres(){
        try{
        group.setNbMembers(-1);
        fail("exception");}
        catch(IllegalArgumentException ise) {}
        assertEquals("1",group.getCodeGroup());
    }

    /**
     * Methode testGroupaddmembers
     * Vérifie qu'en ajoutant un sujet le nombre de membre est bien incrémenté
     */
    @Test
    public void testGroupaddmembers(){
        group.addSubject(subject);
        assertEquals(1,group.getNbMembers());
}

    /**
     * Vérifie qu'en enlevant un sujet le nombre de membres est bien décrémenté
     */
    @Test
    public void testGroupremovemembers(){
        group.addSubject(subject);
        group.removeSubject(subject);
        assertEquals(0,group.getNbMembers());
    }

    /**
     * Methode testpathotruetreattrue
     * vérifie que les bons string sont affiché en fonction des booléens
     */
    @Test
    public void testpathotruetreattrue(){

        assertEquals("PATHOLOGIQUES | TRAITES",group.typeGroupToString());
    }

    /**
     * Methode testpathotruetreattrue
     * vérifie que les bons string sont affiché en fonction des booléens
     */
    @Test
    public void testpathofalsetreatfalse(){
        Group badgroup = new Group(1, "1", "A", false, false, 1, organism);
        assertEquals("NON PATHOLOGIQUES | NON TRAITES",badgroup.typeGroupToString());
    }

    /**
     * Methode testpathotruetreattrue
     * vérifie que les bons string sont affiché en fonction des booléens
     */
    @Test
    public void testpathotruetreatfalse(){
        Group badgroup = new Group(1, "1", "A", true, false, 1, organism);
        assertEquals("PATHOLOGIQUES | NON TRAITES",badgroup.typeGroupToString());
    }

    /**
     * Methode testpathotruetreattrue
     * vérifie que les bons string sont affiché en fonction des booléens
     */
    @Test
    public void testpathofalsetreattrue(){
        Group badgroup = new Group(1, "1", "A", false, true, 1, organism);
        assertEquals("NON PATHOLOGIQUES | TRAITES",badgroup.typeGroupToString());
    }

}