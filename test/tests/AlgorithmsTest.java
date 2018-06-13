//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package tests;

import context_free.Algorithms;
import context_free.Context_free;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AlgorithmsTest {

/**
 * Test of isEmpty method, of class Algorithms.
*/
    @Test
    public void testIsEmpty() {
        /**
        * S -> AB|A
        * A -> Aa
        * B -> bB|b
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "AB");
        g.setProductions('S', "A");
        g.setProductions('A', "Aa");
        g.setProductions('B', "bB");
        g.setProductions('B', "b");
        
        Algorithms result = new Algorithms();
        
        assertEquals(true, result.isEmpty(g));
    }
    
    @Test
    public void testIsEmpty2() {
        /**
        * S -> aFG|bFd|Sa
        * A -> aA|&
        * B -> cG|aCG
        * C -> cBa|ca|&
        * D -> dCc|&
        * F -> bFd|aC|Ab|GA
        * G -> Bc|BCa
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aFG");
        g.setProductions('S', "bFd");
        g.setProductions('S', "Sa");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('B', "cG");
        g.setProductions('B', "aCG");
        g.setProductions('C', "cBa");
        g.setProductions('C', "ca");
        g.setProductions('C', "&");
        g.setProductions('D', "dCc");
        g.setProductions('D', "&");
        g.setProductions('F', "bFd");
        g.setProductions('F', "aC");
        g.setProductions('F', "Ab");
        g.setProductions('F', "GA");
        g.setProductions('G', "Bc");
        g.setProductions('G', "BCa");
        
        Algorithms result = new Algorithms();
        
        assertEquals(false, result.isEmpty(g));
    }

/**
 * Test of isFinite method, of class Algorithms.
*/
    @Test
    public void testIsFinite() {
        /**
        * S -> aFG|bFd|Sa
        * A -> aA|&
        * B -> cG|aCG
        * C -> cBa|ca|&
        * D -> dCc|&
        * F -> bFd|aC|Ab|GA
        * G -> Bc|BCa
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aFG");
        g.setProductions('S', "bFd");
        g.setProductions('S', "Sa");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('B', "cG");
        g.setProductions('B', "aCG");
        g.setProductions('C', "cBa");
        g.setProductions('C', "ca");
        g.setProductions('C', "&");
        g.setProductions('D', "dCc");
        g.setProductions('D', "&");
        g.setProductions('F', "bFd");
        g.setProductions('F', "aC");
        g.setProductions('F', "Ab");
        g.setProductions('F', "GA");
        g.setProductions('G', "Bc");
        g.setProductions('G', "BCa");
        
        Algorithms result = new Algorithms();
        
        assertEquals(false, result.isFinite(g));
    }
    
    @Test
    public void testIsFinite2() {
        /**
        * ???????
        */
    }

/**
 * Test of isInfinite method, of class Algorithms.
*/
    @Test
    public void testIsInfinite() {
        /**
        * S -> aFG|bFd|Sa
        * A -> aA|&
        * B -> cG|aCG
        * C -> cBa|ca|&
        * D -> dCc|&
        * F -> bFd|aC|Ab|GA
        * G -> Bc|BCa
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aFG");
        g.setProductions('S', "bFd");
        g.setProductions('S', "Sa");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('B', "cG");
        g.setProductions('B', "aCG");
        g.setProductions('C', "cBa");
        g.setProductions('C', "ca");
        g.setProductions('C', "&");
        g.setProductions('D', "dCc");
        g.setProductions('D', "&");
        g.setProductions('F', "bFd");
        g.setProductions('F', "aC");
        g.setProductions('F', "Ab");
        g.setProductions('F', "GA");
        g.setProductions('G', "Bc");
        g.setProductions('G', "BCa");
        
        Algorithms result = new Algorithms();
        
        assertEquals(true, result.isInfinite(g));
    }
    
     @Test
    public void testIsInfinite2() {
        /**
        * ???????
        */
    }

/**
 * Test of transformProper method, of class Algorithms.
*/
    @Test
    public void testTransformProper() {
        /**
        * ???????
        */
    }

/**
 * Test of productive method, of class Algorithms.
*/
    @Test
    public void testProductive() {
        /**
        * S -> aS|BC|BD
        * A -> cC|AB
        * B -> bB|&
        * C -> aA|BC
        * D -> dDd|c 
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aS");
        g.setProductions('S', "BC");
        g.setProductions('S', "BD");
        g.setProductions('A', "cC");
        g.setProductions('A', "AB");
        g.setProductions('B', "bB");
        g.setProductions('B', "&");
        g.setProductions('C', "aA");
        g.setProductions('C', "BC");
        g.setProductions('D', "dDd");
        g.setProductions('D', "c");
        
        /**
        * S -> aS|BD
        * B -> bB|&
        * D -> dDd|c 
        */
        
        Context_free result = new Context_free();
        result.setInitialSymbol('S');
        result.setProductions('S', "aS");
        result.setProductions('S', "BD");
        result.setProductions('B', "bB");
        result.setProductions('B', "&");
        result.setProductions('D', "dDd");
        result.setProductions('D', "c");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(result.toString(), alg.productive(g));
    }

/**
 * Test of reachable method, of class Algorithms.
*/
    @Test
    public void testReachable() {
        /**
        * S -> aSa|dDd
        * A -> aB|Cc|a
        * B -> dD|bB|b
        * C -> Aa|dD|c
        * D -> bbB|d
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aSa");
        g.setProductions('S', "dDd");
        g.setProductions('A', "aB");
        g.setProductions('A', "Cc");
        g.setProductions('A', "a");
        g.setProductions('B', "dD");
        g.setProductions('B', "bB");
        g.setProductions('B', "b");
        g.setProductions('C', "Aa");
        g.setProductions('C', "dD");
        g.setProductions('C', "c");
        g.setProductions('D', "bbB");
        g.setProductions('D', "d");
        
        /**
        * S -> aSa|dDd
        * B -> dD|bB|b
        * D -> bbB|d
        */
        
        Context_free result = new Context_free();
        result.setInitialSymbol('S');
        g.setProductions('S', "aSa");
        g.setProductions('S', "dDd");
        g.setProductions('B', "dD");
        g.setProductions('B', "bB");
        g.setProductions('B', "b");
        g.setProductions('D', "bbB");
        g.setProductions('D', "d");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(result.toString(), alg.reachable(g));
    }

/**
 * Test of epsilonFree method, of class Algorithms.
*/
    @Test
    public void testEpsilonFree() {
        /**
        * P -> KL|bKLe
        * K -> cK|TV
        * T -> tT|&
        * V -> vV|&
        * L -> LC|C
        * C -> P|com|&
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('P');
        g.setProductions('P', "KL");
        g.setProductions('P', "bKLe");
        g.setProductions('K', "cK");
        g.setProductions('K', "TV");
        g.setProductions('T', "tT");
        g.setProductions('T', "&");
        g.setProductions('V', "vV");
        g.setProductions('V', "&");
        g.setProductions('L', "LC");
        g.setProductions('L', "C");
        g.setProductions('C', "P");
        g.setProductions('C', "com");
        g.setProductions('C', "&");
        
        /**
        *S -> P|& 
        * P -> KL|bKLe|K|L|bLe|bKe|be
        * K -> cK|TV|c|T|V
        * T -> tT|t
        * V -> vV|v
        * L -> LC|C|L
        * C -> P|com
        */
        
        Context_free result = new Context_free();
        result.setInitialSymbol('S');
        result.setProductions('S', "P");
        result.setProductions('S', "&");
        result.setProductions('P', "KL");
        result.setProductions('P', "bKLe");
        result.setProductions('P', "K");
        result.setProductions('P', "L");
        result.setProductions('P', "bLe");
        result.setProductions('P', "bKe");
        result.setProductions('P', "be");
        result.setProductions('K', "cK");
        result.setProductions('K', "TV");
        result.setProductions('K', "c");
        result.setProductions('K', "T");
        result.setProductions('K', "V");
        result.setProductions('T', "tT");
        result.setProductions('T', "t");
        result.setProductions('V', "vV");
        result.setProductions('V', "v");
        result.setProductions('L', "LC");
        result.setProductions('L', "C");
        result.setProductions('L', "L");
        result.setProductions('C', "P");
        result.setProductions('C', "com");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(result.toString(), alg.epsilonFree(g));
    }

/**
 * Test of removeCycles method, of class Algorithms.
*/
    @Test
    public void testRemoveCycles() {
        /**
        * P -> KL|bKLe|K|L|bLe|bKe|be
        * K -> cK|TV|c|T|V
        * T -> tT|t
        * V -> vV|v
        * L -> LC|C|L
        * C -> P|com
        */
        
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "P");
        g.setProductions('S', "&");
        g.setProductions('P', "KL");
        g.setProductions('P', "bKLe");
        g.setProductions('P', "K");
        g.setProductions('P', "L");
        g.setProductions('P', "bLe");
        g.setProductions('P', "bKe");
        g.setProductions('P', "be");
        g.setProductions('K', "cK");
        g.setProductions('K', "TV");
        g.setProductions('K', "c");
        g.setProductions('K', "T");
        g.setProductions('K', "V");
        g.setProductions('T', "tT");
        g.setProductions('T', "t");
        g.setProductions('V', "vV");
        g.setProductions('V', "v");
        g.setProductions('L', "LC");
        g.setProductions('L', "C");
        g.setProductions('L', "L");
        g.setProductions('C', "P");
        g.setProductions('C', "com");
        
        /**
        * S -> &|KL|bKLe|bLe|bKe|be|cK|TV|c|tT|t|vV|v|LC|com
        * P -> KL|bKLe|bLe|bKe|be|cK|TV|c|tT|t|LC|com
        * K -> cK|TV|c|tT|t|vV|v
        * T -> tT|t
        * V -> vV|v
        * L -> LC|KL|bKLe|bLe|bKe|be|cK|TV|c|tT|t|vV|v|com
        * C -> KL|bKLe|bLe|bKe|be|cK|TV|c|tT|t|vV|v|LC|com
        */
        
         Context_free result = new Context_free();
        result.setInitialSymbol('S');
        result.setProductions('S', "&");
        result.setProductions('S', "KL");
        result.setProductions('S', "bKLe");
        result.setProductions('S', "bLe");
        result.setProductions('S', "bKe");
        result.setProductions('S', "be");
        result.setProductions('S', "cK");
        result.setProductions('S', "TV");
        result.setProductions('S', "c");
        result.setProductions('S', "tT");
        result.setProductions('S', "t");
        result.setProductions('S', "vV");
        result.setProductions('S', "v");
        result.setProductions('S', "LC");
        result.setProductions('S', "com");
        result.setProductions('P', "KL");
        result.setProductions('P', "bKLe");
        result.setProductions('P', "bLe");
        result.setProductions('P', "bKe");
        result.setProductions('P', "be");
        result.setProductions('P', "cK");
        result.setProductions('P', "TV");
        result.setProductions('P', "c");
        result.setProductions('P', "tT");
        result.setProductions('P', "t");
        result.setProductions('P', "vV");
        result.setProductions('P', "v");
        result.setProductions('P', "LC");
        result.setProductions('P', "com");
        result.setProductions('K', "cK");
        result.setProductions('K', "TV");
        result.setProductions('K', "c");
        result.setProductions('K', "tT");
        result.setProductions('K', "t");
        result.setProductions('K', "vV");
        result.setProductions('K', "v");
        result.setProductions('T', "tT");
        result.setProductions('T', "t");
        result.setProductions('V', "vV");
        result.setProductions('V', "v");
        result.setProductions('L', "LC"); 
        result.setProductions('L', "KL");
        result.setProductions('L', "bKLe");
        result.setProductions('L', "bLe");
        result.setProductions('L', "bKe");
        result.setProductions('L', "be");
        result.setProductions('L', "cK");
        result.setProductions('L', "TV");
        result.setProductions('L', "c");
        result.setProductions('L', "tT");
        result.setProductions('L', "t");
        result.setProductions('L', "vV");
        result.setProductions('L', "v");
        result.setProductions('L', "com");
        result.setProductions('C', "KL");
        result.setProductions('C', "bKLe");
        result.setProductions('C', "bLe");
        result.setProductions('C', "bKe");
        result.setProductions('C', "be");
        result.setProductions('C', "cK");
        result.setProductions('C', "TV");
        result.setProductions('C', "c");
        result.setProductions('C', "tT");
        result.setProductions('C', "t");
        result.setProductions('C', "vV");
        result.setProductions('C', "v");
        result.setProductions('C', "LC");;
        result.setProductions('C', "com");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(result.toString(), alg.removeCycles(g));
    }

/**
 * Test of first method, of class Algorithms.
*/
    @Test
    public void testFirst() {
        /**
        * S -> ABC
        * A -> aA|&
        * B -> bB|ACd
        * C -> cC|&
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "ABC");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('B', "bB");
        g.setProductions('B', "ACd");
        g.setProductions('C', "cC");
        g.setProductions('C', "&");
        
        HashMap<Character, ArrayList<String>> list = new HashMap<>();
        
        ArrayList<String> fs = new ArrayList<>();
        fs.add("a");
        fs.add("b");
        fs.add("c");
        fs.add("d");
        fs.add("&");
        list.put('S', fs);
        
        ArrayList<String> fa = new ArrayList<>();
        fa.add("a");
        fa.add("&");
        list.put('A', fa);
        
        ArrayList<String> fb = new ArrayList<>();
        fs.add("a");
        fs.add("b");
        fs.add("c");
        fs.add("d");
        list.put('B', fb);
        
        ArrayList<String> fc = new ArrayList<>();
        fs.add("c");
        fs.add("&");
        list.put('C', fc);
        
        Algorithms alg = new Algorithms();
        
        assertEquals(list, alg.first(g));
    }
/**
 * Test of follow method, of class Algorithms.
*/
    @Test
    public void testFollow() {
        /**
        * ???????
        */
    }

/**
 * Test of firstNT method, of class Algorithms.
*/
    @Test
    public void testFirstNT() {
        /**
        * ???????
        */
    }

/**
 * Test of isFactored method, of class Algorithms.
*/
    @Test
    public void testIsFactored() {
        /**
        * ???????
        */
    }

/**
 * Test of factoredNsteps method, of class Algorithms.
*/
    @Test
    public void testFactoredNsteps() {
        /**
        * ???????
        */
    }

/**
 * Test of isRecursiveLeft method, of class Algorithms.
*/
    @Test
    public void testIsRecursiveLeft() {
        /**
        * ???????
        */
    }

/**
 * Test of removeRecursiveLeft method, of class Algorithms.
*/
    @Test
    public void testRemoveRecursiveLeft() {
        /**
        * ???????
        */
    }
    
}
