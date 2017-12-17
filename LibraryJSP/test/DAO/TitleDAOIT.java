/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Dtos.Title;
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
public class TitleDAOIT {
    
    public TitleDAOIT() {
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
     * Test of viewAllTitles method, of class TitleDAO.
     */
    @Test
    public void testViewAllTitles() {
        System.out.println("viewAllTitles");
        TitleDAO instance = new TitleDAO("librarydatabase");
        ArrayList<Title> result = instance.viewAllTitles();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of addTitle method, of class TitleDAO.
     */
    @Test
    public void testAddTitle() {
        System.out.println("addTitle");
        Title t = null;
        TitleDAO instance = new TitleDAO("librarydatabase");
        boolean expResult = false;
        boolean result = instance.addTitle(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateTitle method, of class TitleDAO.
     */
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
        int id = 3;
        Title title = null;
        TitleDAO instance = null;
        boolean expResult = false;
        boolean result = instance.updateTitle(id, title);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteTitleById method, of class TitleDAO.
     */
    @Test
    public void testDeleteTitleById() {
        System.out.println("deleteTitleById");
        int titleId = 6;
        TitleDAO instance = new TitleDAO("librarydatabase");
        boolean expResult = true;
        boolean result = instance.deleteTitleById(titleId);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchByAuthor method, of class TitleDAO.
     */
    @Test
    public void testSearchByAuthor() {
        System.out.println("searchByAuthor");
        String author = "Philip Roth";
        TitleDAO instance = new TitleDAO("librarydatabase");
        ArrayList<Title> result = instance.searchByAuthor(author);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of searchByNovelName method, of class TitleDAO.
     */
    @Test
    public void testSearchByNovelName() {
        System.out.println("searchByNovelName");
        String novelName = "American";
        TitleDAO instance = new TitleDAO("librarydatabase");
        ArrayList<Title> result = instance.searchByNovelName(novelName);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of searchByID method, of class TitleDAO.
     */
    @Test
    public void testSearchByID() {
        System.out.println("searchByID");
        int id = 2;
        TitleDAO instance = null;
        Title result = instance.searchByID(id);
        assertNotNull(result);
    }
    
}
