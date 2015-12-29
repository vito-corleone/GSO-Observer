/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IBank;
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
public class BalieTest {

    Bank bank;
    Balie balie;

    public BalieTest() {
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
        try {
            balie = new Balie(bank);
        } catch (RemoteException ex) {
            Logger.getLogger(BalieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * @Author Kamil Wasylkiewicz
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        // lege naam
        assertEquals(null, balie.openRekening("", "Roermond", "Wachtwoord"));
        // lege plaatsnaam
        assertEquals(null, balie.openRekening("Vito", "", "Wachtwoord"));
        // wachtwoord lager dan 4 tekens
        assertEquals(null, balie.openRekening("Vito", "Roermond", "123"));
        // wachtwoord langert dan 8 tekens
        assertEquals(null, balie.openRekening("Vito", "Roermond", "123456789"));
        // accountnaam verkrijgen
        assertNotNull(balie.openRekening("Vito", "Roermond", "1234567"));
    }

    /**
     * @Author Kamil Wasylkiewicz
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception {
        System.out.println("logIn");
        String accountName = balie.openRekening("Vito", "Roermond", "1234567");
        System.out.println("Accountname: " + accountName);
        // Accountname null
        assertEquals(null, balie.logIn("NotExisting", "NoP@ss"));
        // Password null
        assertEquals(null, balie.logIn(accountName, "NoP@ss"));
        // succesfull login
        assertNotNull(null, balie.logIn(accountName, "1234567"));
    }
}
