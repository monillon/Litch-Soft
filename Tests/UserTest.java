package Tests;

import LITCH.Subject;
import LITCH.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests unitaires de la classe User
 *  Prend en charge tous les cas envisageables en fonction des spécifications
 *
 */
class UserTest {

    private User user;
    private User idZero;
    private User idNeg;
    private User nameVide;
    private User nameNull;

    /**
     * Method setUp : initialisation d'une Unit correcte avec les valeurs
     * 2 pour l'ID et "user" pour le nom de l'utilisateur
     */
    @BeforeEach
    void setUp() {
        user = new User(2, "user", "Admin");
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Method testUser
     * Vérifie que la création du User de base fonctionne et rend bien un objet
     */
    @Test
    public void testUser(){
        assertNotNull(user,"User OK");

    }

    /**
     * Method testGetIdUserOK
     * Vérifie que l'ID retourné est le bon (2)
     */
    @Test
    public void testGetIdUserOK() {
        assertEquals(2, user.getIdUser());
    }

    /**
     * Method testGetNameUserOK
     * Vérifie que le name retourné est le bon ("user")
     */
    @Test
    public void testGetNameUserOK() {
        assertEquals("user", user.getUserName());
    }

    /**
     * Method testGetNameUserEmpty : un nameUSer vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testGetNameUserEmpty() {
        try{
            nameVide = new User(2, "", "Admin");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testGetIdUserZero : un ID égal à 0 doit déclencher une exception
     * test avec la valeur 0 pour l'ID
     */
    @Test
    public void testGetIdUserZero() {
        try{
            idZero = new User(0, "user", "Admin");
            fail("exception");}
        catch(IllegalArgumentException ise){}

    }

    /**
     * Method testGetIdUserNeg : un ID négatif doit déclencher une exception
     * test avec la valeur -1 pour l'ID
     */
    @Test
    public void testGetIdUserNeg() {
        try{
            idNeg = new User(-1, "user","Admin");
            fail("exception");}
        catch(IllegalArgumentException ise){}
    }

    /**
     * Method testSetNameUserEmpty : un paramètre vide doit déclencher une exception
     * test avec un String vide
     */
    @Test
    public void testSetNameUserEmpty() {
        try{
            user.setUserName("");
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("user",user.getUserName());
    }

    /**
     * Method testSetNameUserOK
     * test avec la valeur "test2"
     */
    @Test
    public void testSetNameUserOK() {
        user.setUserName("test2");
        assertEquals("test2", user.getUserName());
    }

    /**
     * Method testGetUserNameNull : un user name null dans le constructeur doit déclencher une exception
     * test avec un username null
     */
    @Test
    public void testGetUserNameNull() {
        try {
            nameNull = new User(0, null, "Admin");
            fail("exception");
        } catch (IllegalArgumentException ise) {
        }
    }

    /**
     * Method testSetUserNameNull : un paramètre null doit déclencher une exception
     * test avec un userName null
     */
    @Test
    public void testSetUserNameNull(){
        try{
            user.setUserName(null);
            fail("exception");}
        catch(IllegalArgumentException ise){}
        assertEquals("user",user.getUserName());
    }

    @Test
    public void testToString() {
        assertEquals("user", user.getUserName());
        assertEquals(user.getUserName(), user.toString());
    }

}