/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cga.scenegraph.animation;

import cga.framework.animation.Alpha;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Giacinto
 */
public class AlphaTest {

    public AlphaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of step method, of class Alpha.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        Alpha instance = new Alpha(1, 10);
        double expResult = 2.0;
        double result = instance.step();
        assertEquals(expResult, result, 0.0);
    }
    
}
