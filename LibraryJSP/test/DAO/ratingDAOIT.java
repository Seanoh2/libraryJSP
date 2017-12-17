/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Rating;
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
public class ratingDAOIT {
    
    public ratingDAOIT() {
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
     * Test of getAverageRatingByID method, of class ratingDAO.
     */
    @Test
    public void testGetAverageRatingByID() {
        System.out.println("getAverageRatingByID");
        int id = 0;
        ratingDAO instance = null;
        double expResult = 0.0;
        double result = instance.getAverageRatingByID(id);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addReview method, of class ratingDAO.
     */
    @Test
    public void testAddReview() {
        System.out.println("addReview");
        Rating r1 = null;
        ratingDAO instance = null;
        boolean expResult = false;
        boolean result = instance.addReview(r1);
        assertEquals(expResult, result);
    }
    
}
