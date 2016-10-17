/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allangraytest;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * addFollow is the only method from the user class worth testing, all others are trivial
     * The user file is said to be well formatted and thus, not much validation will go into this.
     * If was not well formatted, I would enforce the expected format - but I make the assumption that the line is well formatted as intended
     */
    @Test
    public void testAddFollow() { //Testing multiple user
        Queue q = new LinkedList();
        String line = "Add, New, Follow"; //three names
        User instance = new User("Benny",q);
        instance.addFollow(line); //tests if the input runs successfully in the method

    }
    
    /**
     * addFollow is the only method from the user class worth testing, all others are trivial
     * The user file is said to be well formatted and thus, not much validation will go into this.
     * If was not well formatted, I would enforce the expected format - but I make the assumption that the line is well formatted as intended
     */
    
    @Test
    public void testAddFollow1() { //Testing single user
        Queue q = new LinkedList();
        String line = "Add"; //three names
        User instance = new User("Benny",q);
        instance.addFollow(line); //tests if the input runs successfully in the method

    }
    
    /**NB: IN ORDER TO RUN THE TESTS BELOW, PLEASE CHANGE validateUser()'s ACCESS TO PUBLIC (could make a copy here, but that requires copying other methods and becomes messy)
     * 
     * The validateUser method formated any names containing numbers or special characters as well as enforces a first capital letter, rest lowercase letters format for name
     * Many different inputs can be tested; below covers all kinds of formatting that can occur here
     */
    
    @Test
    public void validateUser() {
        Queue q = new LinkedList();
        User instance = new User("Kevin", q);
        String name = instance.getUser();
        String expResult = "Kevin";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void validateUser1() { //special character in name
        Queue q = new LinkedList();
        User instance = new User("J%ane", q);
        String name = instance.getUser();
        String expResult = "Jane";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void validateUser2() { //numeric character in name
        Queue q = new LinkedList();
        User instance = new User("St3ephen", q);
        String name = instance.getUser();
        String expResult = "Stephen";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void validateUser3() { //lowercase first letter
        Queue q = new LinkedList();
        User instance = new User("thandi", q);
        String name = instance.getUser();
        String expResult = "Thandi";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void validateUser4() { //uppercase in name where not in first position
        Queue q = new LinkedList();
        User instance = new User("EriC", q);
        String name = instance.getUser();
        String expResult = "Eric";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);

    }
    
    @Test
    public void validateUser5() { //All possible wrong inputs
        Queue q = new LinkedList();
        User instance = new User("ch@ris4topheR", q);
        String name = instance.getUser();
        String expResult = "Christopher";
        String result = instance.validateUser(name);
        assertEquals(expResult, result);

    }
}
