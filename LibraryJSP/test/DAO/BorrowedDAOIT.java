/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Borrowed;
import java.util.ArrayList;
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
public class BorrowedDAOIT {
    
    public BorrowedDAOIT() {
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
     * Test of getBorrowedByUserID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByUserID() {
        System.out.println("getBorrowedByUserID");
        int userID = 19;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        ArrayList<Borrowed> result = instance.getBorrowedByUserID(userID);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getBorrowedByTitleID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByTitleID() {
        System.out.println("getBorrowedByTitleID");
        int titleID = 1;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        ArrayList<Borrowed> result = instance.getBorrowedByTitleID(titleID);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getBorrowedByStatus method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByStatus() {
        System.out.println("getBorrowedByStatus");
        int status = 0;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        ArrayList<Borrowed> result = instance.getBorrowedByStatus(status);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getStatusByUserID method, of class BorrowedDAO.
     */
    @Test
    public void testGetStatusByUserID() {
        System.out.println("getStatusByUserID");
        int userID = 19;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        ArrayList<Borrowed> result = instance.getBorrowedByStatus(userID);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of updateStatus method, of class BorrowedDAO.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        int borrowedID = 27;
        int newStatus = 1;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        boolean result = instance.updateStatus(borrowedID, newStatus);
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of getBorrowedByID method, of class BorrowedDAO.
     */
    @Test
    public void testGetBorrowedByID() {
        System.out.println("getBorrowedByID");
        int borrowedID = 0;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        Borrowed result = instance.getBorrowedByID(borrowedID);
        assertNotNull(result);
    }

    /**
     * Test of addBorrowed method, of class BorrowedDAO.
     */
    @Test
    public void testAddBorrowed() {
        System.out.println("addBorrowed");
        Borrowed borrowed = null;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.addBorrowed(borrowed);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfAlreadyBorrowed method, of class BorrowedDAO.
     */
    @Test
    public void testCheckIfAlreadyBorrowed() {
        System.out.println("checkIfAlreadyBorrowed");
        int userID = 19;
        int titleID = 1;
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        Borrowed result = instance.checkIfAlreadyBorrowed(userID, titleID);
        assertNotNull(result);
    }

    /**
     * Test of checkDays method, of class BorrowedDAO.
     */
    @Test
    public void testCheckDays() {
        System.out.println("checkDays");
        String checkOverdue = "22/11/17";
        BorrowedDAO instance = new BorrowedDAO("librarydatabase");
        double result = instance.checkDays(checkOverdue);
        assertTrue(result > 0.0);
    }
    
}
