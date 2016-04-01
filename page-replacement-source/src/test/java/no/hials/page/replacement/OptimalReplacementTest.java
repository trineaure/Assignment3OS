package no.hials.page.replacement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Optimal page replacement algorithm
 * @author Girts Strazdins, 2016-03-11 (only a template)
 */
public class OptimalReplacementTest {
    /**
     * Test of process method, of class OptimalReplacement.
     */
    @Test
    public void testProcess() {
        System.out.println("Optimal Replacement process test");
        OptimalReplacement algo = new OptimalReplacement();

        // Use the book example with 3 frames and the given reference string
        algo.setup(3);
        String ref = "7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1";
        int replacements = algo.process(ref);
        String frameStatus = algo.getFrameStatus();
        assertEquals(6, replacements);
        assertEquals("7, 0, 1", frameStatus);

        // TODO - add additional Unit tests here
        
        //Test sequence where some pages are alredy loaded
        replacements = algo.process("2, 0, 4, 6, 5, 2, 1");
        frameStatus = algo.getFrameStatus();
        assertEquals("2, 5, 1", frameStatus);
        assertEquals(4, replacements);
        
        //Test sequence which noe pages are loaded
        replacements = algo.process("3, 4, 6");
        frameStatus = algo.getFrameStatus();
        assertEquals("6, 5, 1", frameStatus);
        assertEquals(3, replacements);
        
        //Test sequense where all pages are loaded
        replacements = algo.process("6, 1, 6, 1, 1, 5");
        frameStatus = algo.getFrameStatus();
        assertEquals("6, 5, 1", frameStatus);
        assertEquals(0, replacements);
        
        //Test sequense shorter then frame count
        replacements = algo.process("2, 4");
        frameStatus = algo.getFrameStatus();
        assertEquals("4, 5, 1", frameStatus);
        assertEquals(2, replacements);
        
        //Test sequense with a letters, which should not end in a replacement
        replacements = algo.process("0, E, 0, 1, 1, 2, 0");
        frameStatus = algo.getFrameStatus();
        assertEquals("0, 2, 1", frameStatus);
        assertEquals(2, replacements);
        
    }
   
}
