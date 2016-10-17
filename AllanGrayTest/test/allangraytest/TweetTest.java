/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allangraytest;

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
public class TweetTest {
    
    public TweetTest() {
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
     * Test of getTweeter method, of class Tweet.
     */
    @Test
    public void testGetTweeter() {
        Tweet instance = new Tweet("Alan","My name is Alan and this is my tweet");
        String expResult = "Alan";
        String result = instance.getTweeter();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetTweeter1() {
        Tweet instance = new Tweet("Paula","My name is Paula and this is my tweet");
        String expResult = "Paula";
        String result = instance.getTweeter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTweet method, of class Tweet.
     */
    @Test
    public void testGetTweet() {
        Tweet instance = new Tweet("Sarah","My name is Sarah and this is my tweet");
        String expResult = "My name is Sarah and this is my tweet";
        String result = instance.getTweet();
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testGetTweet1() { //the letter "l" is the 141st character and because tweets can only be 140 characters, the "l" is truncated
        Tweet instance = new Tweet("Lucy","This tweet is going to be 141 characters in order to test the validation that has been put in place by the author of this code who is Michael");
        String expResult = "This tweet is going to be 141 characters in order to test the validation that has been put in place by the author of this code who is Michae";
        String result = instance.getTweet();
        assertEquals(expResult, result);
        
    }
    
}
