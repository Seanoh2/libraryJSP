/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.User;
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
public class UserDAOIT {
    
    public UserDAOIT() {
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
     * Test of selectUserContainingName method, of class UserDAO.
     */
    @Test
    public void testSelectUserContainingName() {
        System.out.println("selectUserContainingName");
        String name = "sean";
        UserDAO instance = new UserDAO("librarydatabase");
        ArrayList<User> result = instance.selectUserContainingName(name);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of findUserByID method, of class UserDAO.
     */
    @Test
    public void testFindUserByID() {
        System.out.println("findUserByID");
        int userID = 19;
        UserDAO instance = new UserDAO("librarydatabase");
        User result = instance.findUserByID(userID);
        assertNotNull(result);
    }

    /**
     * Test of addUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User u = null;
        UserDAO instance = new UserDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.addUser(u);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "seanoh2@live.com";
        String password = "test";
        UserDAO instance = new UserDAO("librarydatabase");
        User result = instance.login(email, password);
        assertNotNull(result);
    }

    /**
     * Test of getUserByEmail method, of class UserDAO.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "seanoh2@live.com";
        UserDAO instance = new UserDAO("librarydatabase");
        User result = instance.getUserByEmail(email);
        assertNotNull(result);
    }

    /**
     * Test of removeUser method, of class UserDAO.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        int id = 17;
        UserDAO instance = new UserDAO("librarydatabase");
        int expResult = 1;
        int result = instance.removeUser(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUser method, of class UserDAO.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User attemptUpdate = null;
        UserDAO instance = new UserDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.updateUser(attemptUpdate);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePassword method, of class UserDAO.
     */
    @Test
    public void testUpdatePassword() {
        System.out.println("updatePassword");
        String password = "NULL";
        User user = null;
        UserDAO instance = new UserDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.updatePassword(password, user);
        assertEquals(expResult, result);
    }
    
}
