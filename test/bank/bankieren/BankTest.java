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

        Bank instance = new Bank("Rabobank");
        int expResult = -1;
        String name = "";
        String city = "";
        int result = instance.openRekening(name, city);
        assertEquals(expResult, result);

        expResult = 100000000;
        name = "Loek";
        city = "Roermond";
        result = instance.openRekening(name, city);
        assertEquals(expResult, result);

    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        Bank instance = new Bank("Rabobank");
        instance.openRekening("Loek", "Roermond");

        int expResult = 100000000;
        IRekening result = instance.getRekening(expResult);

        
        assertEquals(expResult, result.getNr());
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver");
        int source = 0;
        int destination = 0;
        Money money = new Money(100, "euro");
        Bank instance = new Bank("Rabobank");
        boolean expResult = false;
        boolean result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
        money = new Money(-100, "euro");
        source = 1;
        result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
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
