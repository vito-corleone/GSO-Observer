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

    // test voor maak over zonder excepties
    @Test
    public void testMaakOver() throws NumberDoesntExistException {
        System.out.println("maakOver");
        Bank instance = new Bank("Rabobank");
        int source = instance.openRekening("Vito", "Roermond");
        int destination = instance.openRekening("Corleone", "Roermond");
        destination = instance.openRekening("Corleone", "Roermond");
        Money money = new Money(1, "€");

        // money negative succes TRUE           
        boolean result = instance.maakOver(source, destination, money);
        assertEquals(true, result);

        // Source succes FALSE
        money = new Money(100000000, "€");
        result = instance.maakOver(source, destination, money);
        assertEquals(true, result);
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOverRunTimeException() throws NumberDoesntExistException {
        System.out.println("testMaakOverRunTimeException");
        int source = 1;
        int destination = 1;
        Money money = new Money(100, "€");
        Bank instance = new Bank("Rabobank");

        //Test source account is null
        instance.maakOver(source, destination, money);
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOverMoneyRunTimeException() throws NumberDoesntExistException {
        System.out.println("testMaakOverMoneyRunTimeException");
        int source = 1;
        int destination = 2;
        Money money = new Money(-1, "€");
        Bank instance = new Bank("Rabobank");
        boolean expResult = false;
        instance.maakOver(source, destination, money);
    }

    // test for NumberDoesntExistException
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOverNumberDoesntExistException() throws NumberDoesntExistException {
        System.out.println("testMaakOverNumberDoesntExistException");
        int source = 1;
        int destination = 3;
        Money money = new Money(100, "euro");
        Bank instance = new Bank("Rabobank");
        boolean expResult = false;

        //Test source account is null
        instance.maakOver(source, destination, money);
    }

    // test for NumberDoesntExistException
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOverDestinationNumberDoesntExistException() throws NumberDoesntExistException {
        System.out.println("testMaakOverDestinationNumberDoesntExistException");
        Bank instance = new Bank("Rabobank");
        int source = instance.openRekening("Vito", "Roermond");
        int destination = instance.openRekening("Corleone", "Roermond");
        Money money = new Money(1, "€");

        //Test destination account is null
        instance.maakOver(source, 3, money);
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
