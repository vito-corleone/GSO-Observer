/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import fontys.util.InvalidSessionException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BankiersessieTest {

    private Bank bank;
    private Money money;
    private Bankiersessie bankiersessie;

    public BankiersessieTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bank = new Bank("Rabobank");
        money = new Money(1, "€");
        try {
            bankiersessie = new Bankiersessie(12345, bank);
        } catch (RemoteException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * @Author Kamil Wasylkiewicz 
     * Test of isGeldig method, of class
     * Bankiersessie.
     */
    @Test
    public void testIsGeldig() {
        System.out.println("isGeldig");
        // geldige sessie
        assertTrue(bankiersessie.isGeldig());

        // overschrijding van het tijdslimiet        
        bankiersessie.setGeldigheidsDuur(1000);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(bankiersessie.isGeldig());
    }

    /**
     * @Author Kamil Wasylkiewicz 
     * Test of maakOver method, of class
     * Bankiersessie with InvalidSessionException
     */
    @Test(expected = InvalidSessionException.class)
    public void testMaakOverSessionExceptie() throws Exception {
        System.out.println("maakOver");

        // test voor updateLaatsteAanroep() waarbij een exception optreedt;
        bankiersessie.setGeldigheidsDuur(1000);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        bankiersessie.maakOver(12345678, money);
    }

    /**
     * @Author Kamil Wasylkiewicz Test of maakOver method, of class
     * Bankiersessie with RuntimeException
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOverNrIsBestemmingException() throws Exception {
        System.out.println("maakOver");
        // RuntimeExceptie reknr == bestemming
        bankiersessie.maakOver(12345, money);
    }

    /**
     * @Author Kamil Wasylkiewicz Test of maakOver method, of class
     * Bankiersessie with RuntimeException
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOverBedragNotPositiveException() throws Exception {
        System.out.println("maakOver");
        money = new Money(-1, "€");
        // RuntimeExceptie bedrag not positive
        bankiersessie.maakOver(1234, money);
    }

    /**
     * @Author Kamil Wasylkiewicz Test of maakOver method, of class
     * Bankiersessie
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int source = bank.openRekening("Vito", "Roermond");
        int destination = bank.openRekening("Corleone", "Roermond");
        try {
            bankiersessie = new Bankiersessie(source, bank);
        } catch (RemoteException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(bankiersessie.maakOver(destination, money));
    }

    /**
     * @Author Kamil Wasylkiewicz Test of getRekening method, of class
     * Bankiersessie.
     */
    @Test(expected = InvalidSessionException.class)
    public void testGetRekeningGeldigheidsExceptie() throws Exception {
        System.out.println("getRekening");
        // test voor updateLaatsteAanroep() waarbij exception optreedt;
        bankiersessie.setGeldigheidsDuur(1000);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        bankiersessie.getRekening();
    }

    /**
     * @Author Kamil Wasylkiewicz Test of getRekening method, of class
     * Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception {
        System.out.println("getRekening");
        int source = bank.openRekening("Vito", "Roermond");
        try {
            bankiersessie = new Bankiersessie(source, bank);
        } catch (RemoteException ex) {
            Logger.getLogger(BankiersessieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(source, bankiersessie.getRekening().getNr());
    }
}
