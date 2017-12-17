/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.User;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sean
 */
public class EmailDAOIT {
    
    public EmailDAOIT() {
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
     * Test of sendEmailRecovery method, of class EmailDAO.
     */
    @Test
    public void testSendEmailRecovery() {
        System.out.println("sendEmailRecovery");
        String email = "INVALIDEMAIL";
        EmailDAO instance = new EmailDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.sendEmailRecovery(email);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of CreateRecoveryLog method, of class EmailDAO.
     */
    @Test
    public void testCreateRecoveryLog() {
        System.out.println("CreateRecoveryLog");
        UUID id = null;
        String email = "INVALIDEMAIL";
        EmailDAO instance = null;
        boolean expResult = false;
        boolean result = instance.CreateRecoveryLog(id, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeRecovereyLog method, of class EmailDAO.
     */
    @Test
    public void testRemoveRecovereyLog() {
        System.out.println("removeRecovereyLog");
        int userID = 9999;
        EmailDAO instance = new EmailDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.removeRecovereyLog(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUUID method, of class EmailDAO.
     */
    @Test
    public void testCheckUUID() {
        System.out.println("checkUUID");
        String userUUID = "INVALIDEMAIL";
        EmailDAO instance = new EmailDAO("librarydatabase");
        User expResult = null;
        User result = instance.checkUUID(userUUID);
        assertEquals(expResult, result);
    }
    
}
