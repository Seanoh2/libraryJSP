/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Genre;
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
public class GenreDAOIT {
    
    public GenreDAOIT() {
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
     * Test of getGenreByID method, of class GenreDAO.
     */
    @Test
    public void testGetGenreByID() {
        System.out.println("getGenreByID");
        int genreID = 0;
        GenreDAO instance = new GenreDAO("librarydatabase");
        Genre expResult = null;
        Genre result = instance.getGenreByID(genreID);
        assertEquals(expResult, result);
    }
    
}
