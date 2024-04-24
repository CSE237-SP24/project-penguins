package tests.Utility_general_tests;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Utility.Pair;


public class testsPair {
	Pair<String, String> sampleUserPass;

    @BeforeEach
    void setup() {
    	
        sampleUserPass = new Pair<>("BestCoder", "BestestCoder@");

    }

    @Test // testing toString
    void toStringTest() {

        assertEquals(sampleUserPass.toString(), "first: BestCoder second: BestestCoder@");
    }
    
    @Test // testing equality holds 
    void testEqualsTrue() {
    	Pair<String, String> sampleUserPass2 = new Pair<>("BestCoder", "BestestCoder@");
    	
        assertEquals(sampleUserPass.equals(sampleUserPass2), true);
        assertEquals(sampleUserPass2.equals(sampleUserPass), true);
        assertEquals(sampleUserPass.equals(sampleUserPass), true);
    }
    
    @Test // testing equals for false case
    void testEqualsFalse() {
    	Pair<String, String> sampleUserPass2 = new Pair<>("BadCoder", "BaddestCoder@");
    	
    	assertEquals(sampleUserPass.equals(sampleUserPass2), false);
        assertEquals(sampleUserPass2.equals(sampleUserPass), false);
       
    }
    
    @Test // testing hashcode, 
    //(potentially for future implementation, also for .contains() in containers)
    void testHashTrue() {
    	Pair<String, String> sampleUserPass2 = new Pair<>("BestCoder", "BestestCoder@");
    	
        assertEquals(sampleUserPass.hashCode(), sampleUserPass2.hashCode());
    }
    

    
}
