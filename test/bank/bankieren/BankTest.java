/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import fontys.util.NumberDoesntExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vito Corleone
 */
public class BankTest {
    
    public BankTest() {
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
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        String name = "";
        String city = "";
        Bank instance = null;
        int expResult = 0;
        int result = instance.openRekening(name, city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        System.out.println("getRekening");
        int nr = 0;
        Bank instance = null;
        IRekening expResult = null;
        IRekening result = instance.getRekening(nr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test (expected=RuntimeException.class)
    public void testMaakOverRunTimeException() throws NumberDoesntExistException {
        System.out.println("maakOver");
        // test for RunTimeException
        int source = 1;
        int destination = 1;
        Money money = new Money(100, "euro");
        Bank instance = new Bank("Rabobank");
        boolean expResult = false;
        instance.maakOver(source, destination, money);       
        
        //test for negative moneny, RunTimeException
        money = new Money(-100, "euro");
        source = 1;
        instance.maakOver(source, destination, money);        
        
        //Test source account is null
        instance.maakOver(1, destination, money);       
        
        // money negative    
        money = new Money (100, "euro");
        boolean result = instance.maakOver(1, destination, money); 
        assertEquals(false, result);      
              
    }
    
     @Test 
    public void testMaakOver() throws NumberDoesntExistException {
        System.out.println("maakOver");
        int source = 0;
        int destination = 1;
        Money money = new Money(100, "euro");
        Bank instance = new Bank("Rabobank");        
        
        // money negative            
        boolean result = instance.maakOver(source, destination, money); 
        assertEquals(true, result);
        
        
        
//        // destination account null    
//        money = new Money (100, "euro");
//        result = instance.maakOver(1, -1, money); 
//        assertEquals(false, result);        
    }
    
    // test for NumberDoesntExistException
    @Test (expected=NumberDoesntExistException.class)
    public void testMaakOverNumberDoesntExistException() throws NumberDoesntExistException {
        System.out.println("maakOver");
        int source = 1;
        int destination = 3;
        Money money = new Money(100, "euro");
        Bank instance = new Bank("Rabobank");
        boolean expResult = false;             
        
        //Test source account is null
        instance.maakOver(1, destination, money);          
    }
    
    

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Bank instance = new Bank("Rabobank");
        String expResult = "Rabobank";
        String result = instance.getName();
        assertEquals(expResult, result);      
        
    }
    
}
