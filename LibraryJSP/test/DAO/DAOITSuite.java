/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Sean
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BorrowedDAOIT.class, DAOIT.class, GenreDAOIT.class, TitleDAOIT.class, TitleGenreDAOIT.class, EmailDAOIT.class, UserDAOIT.class, ratingDAOIT.class})
public class DAOITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
