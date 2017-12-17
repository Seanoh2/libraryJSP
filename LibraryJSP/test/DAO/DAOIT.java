/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
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
public class DAOIT {
    
    public DAOIT() {
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
     * Test of getConnection method, of class DAO.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DAO instance = new DAO("librarydatabase");
        Connection result = instance.getConnection();
        assertNotNull(result);
    }

    /**
     * Test of freeConnection method, of class DAO.
     */
    @Test
    public void testFreeConnection() {
        System.out.println("freeConnection");
        Connection con = null;
        DAO instance = new DAO("librarydatabase");
        instance.freeConnection(con);
        assertNull(con);
    }
    
}
