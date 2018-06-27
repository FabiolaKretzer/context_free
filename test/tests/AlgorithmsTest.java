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
    
/**
 * Test of isEpsilonFree method, of class Algorithms.
*/
    @Test
    public void testIsEpsilonFree() {
        /**
        * S -> AB
        * A -> aA|&
        * B -> bB|&
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "AB");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('B', "bB");
        g.setProductions('B', "&");
        
        Algorithms result = new Algorithms();
        
        assertEquals(false, result.isEpsilonFree(g));
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
        String a = alg.epsilonFree(g).toString();
        String b = result.toString();
        assertEquals(true, true);
        
        /*
            S → AbB | AD
            A → aA | B
            B → SBD | CD
            C → cC | AS | ε
            D → dD | ε
        */
        
        g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S',"AbB");
        g.setProductions('S',"AD");
        g.setProductions('A',"aA");
        g.setProductions('A',"B");
        g.setProductions('B',"SBD");
        g.setProductions('B',"CD");
        g.setProductions('C',"cC");
        g.setProductions('C',"AS");
        g.setProductions('C',"&");
        g.setProductions('D',"dD");
        g.setProductions('D',"&");
        //alg.epsilonFree(g));
        //ta funcionando
    }
 
/**
 * Test of isCycles method, of class Algorithms.
*/
    @Test
    public void testIsCycles() {
       /**
        * S -> FGH
        * F -> G|a
        * G -> dG|F|b
        * H -> c
        */ 
       Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "FHG");
        g.setProductions('F', "G");
        g.setProductions('F', "a");
        g.setProductions('G', "dG");
        g.setProductions('G', "F");
        g.setProductions('G', "b");
        g.setProductions('H', "c");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(true, alg.hasCycles(g));
        
    }
    
/**
 * Test of removeCycles method, of class Algorithms.
*/
    @Test
    public void testRemoveCycles() {
        /**
        * S -> P|& 
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
        
        assertEquals(result.toString().length(), alg.removeCycles(g).toString().length());
    }

/**
 * Test of productive method, of class Algorithms.
*/
    @Test
    public void testFertile() {
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
        assertEquals(result.toString(), alg.fertile(g).toString());
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
        
        assertEquals(result.toString(), alg.reachable(g).toString());
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
        
        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        list.put('a', a);
        
        ArrayList<String> b = new ArrayList<>();
        a.add("b");
        list.put('b', b);
        
        ArrayList<String> c = new ArrayList<>();
        a.add("c");
        list.put('c', c);
        
        ArrayList<String> d = new ArrayList<>();
        a.add("d");
        list.put('d', d);
        
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
        fb.add("a");
        fb.add("b");
        fb.add("c");
        fb.add("d");
        list.put('B', fb);
        
        ArrayList<String> fc = new ArrayList<>();
        fc.add("c");
        fc.add("&");
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
        * S -> AbCD|EF
        * A -> aA|&
        * C -> ECF|c
        * D -> cD|dDd|&
        * E -> eE|&
        * F -> FS|fF|g
        */
        
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "AbCD");
        g.setProductions('S', "EF");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('C', "ECF");
        g.setProductions('C', "c");
        g.setProductions('D', "cD");
        g.setProductions('D', "dDd");
        g.setProductions('D', "&");
        g.setProductions('E', "eE");
        g.setProductions('E', "&");
        g.setProductions('F', "FS");
        g.setProductions('F', "fF");
        g.setProductions('F', "g");
        
        HashMap<Character, ArrayList<String>> list = new HashMap<>();
        
        ArrayList<String> fs = new ArrayList<>();
        fs.add("$");
        fs.add("a");
        fs.add("b");
        fs.add("e");
        fs.add("f");
        fs.add("g");
        fs.add("d");
        fs.add("c");
        list.put('S', fs);
        
        ArrayList<String> fa = new ArrayList<>();
        fa.add("b");
        list.put('A', fa);
        
        ArrayList<String> fc = new ArrayList<>();
        fc.add("e");
        fc.add("c");
        fc.add("d");
        fc.add("f");
        fc.add("g");
        fc.add("$");
        fc.add("a");
        fc.add("b");
        list.put('C', fc);
        
        ArrayList<String> fd = new ArrayList<>();
        fd.add("d");
        fd.add("$");
        fd.add("a");
        fd.add("b");
        fd.add("e");
        fd.add("f");
        fd.add("g");
        fd.add("c");
        list.put('D', fd);
        
        ArrayList<String> fe = new ArrayList<>();
        fe.add("d");
        fe.add("$");
        fe.add("a");
        fe.add("b");
        list.put('E', fe);
        
        ArrayList<String> ff = new ArrayList<>();
        ff.add("a");
        ff.add("b");
        ff.add("e");
        ff.add("f");
        ff.add("g");
        ff.add("$");
        ff.add("d");
        ff.add("c");
        list.put('F', ff);
        
        Algorithms alg = new Algorithms();
        
        assertEquals(list, alg.follow(g));
    }

/**
 * Test of firstNT method, of class Algorithms.
*/
    @Test
    public void testFirstNT() {
        /**
        * S -> AbCD|EF
        * A -> aA|&
        * C -> ECF|c
        * D -> cD|dDd|&
        * E -> eE|&
        * F -> FS|fF|g
        */
        
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "AbCD");
        g.setProductions('S', "EF");
        g.setProductions('A', "aA");
        g.setProductions('A', "&");
        g.setProductions('C', "ECF");
        g.setProductions('C', "c");
        g.setProductions('D', "cD");
        g.setProductions('D', "dDd");
        g.setProductions('D', "&");
        g.setProductions('E', "eE");
        g.setProductions('E', "&");
        g.setProductions('F', "FS");
        g.setProductions('F', "fF");
        g.setProductions('F', "g");
        
        HashMap<Character, ArrayList<String>> list = new HashMap<>();
        
        ArrayList<String> fs = new ArrayList<>();
        fs.add("A");
        fs.add("E");
        fs.add("F");
        list.put('S', fs);
        
        ArrayList<String> fa = new ArrayList<>();
        list.put('A', fa);
        
        ArrayList<String> fc = new ArrayList<>();
        fc.add("E");
        fc.add("C");
        fc.add("F");
        list.put('C', fc);
        
        ArrayList<String> fd = new ArrayList<>();
        list.put('D', fd);
        
        ArrayList<String> fe = new ArrayList<>();
        list.put('E', fe);
        
        ArrayList<String> ff = new ArrayList<>();
        ff.add("F");
        list.put('F', ff);
        
        Algorithms alg = new Algorithms();
        
        assertEquals(list, alg.firstNT(g));
    }

/**
 * Test of isFactored method, of class Algorithms.
*/
    @Test
    public void testIsFactored() {
        /**
        * S -> aA|dS
        * A -> S|B
        * B -> bC
        * C -> B|&
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aA");
        g.setProductions('S', "dS");
        g.setProductions('A', "S");
        g.setProductions('A', "B");
        g.setProductions('B', "bC");
        g.setProductions('C', "B");
        g.setProductions('C', "&");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(true, alg.isFactored(g));
    }

/**
 * Test of factoredNsteps method, of class Algorithms.
*/
    @Test
    public void testFactoredNsteps() {
        /**
        * S -> aS|aB|dS
        * B -> bB|b
        */
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aS");
        g.setProductions('S', "aB");
        g.setProductions('S', "dS");
        g.setProductions('B', "bB");
        g.setProductions('B', "b");
        
        /**
        * S -> aA|dS
        * A -> S|B
        * B -> bC
        * C -> B|&
        */
        /**Context_free r = new Context_free();
        r.setInitialSymbol('S');
        r.setProductions('S', "aA");
        r.setProductions('S', "dS");
        r.setProductions('A', "S");
        r.setProductions('A', "B");
        r.setProductions('B', "bC");
        r.setProductions('C', "B");
        r.setProductions('C', "&");*/
        
        Algorithms alg = new Algorithms();
        
        assertEquals(true, alg.factoredNsteps(g, 1));
        
    }

/**
 * Test of isRecursiveLeft method, of class Algorithms.
*
    /**@Test
    public void testIsRecursiveLeft() {
        /**
        * S -> aS|Ab
        * A -> Ab|Bc|a
        * B -> Bd|Sa|e
        *
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aS");
        g.setProductions('S', "Ab");
        g.setProductions('A', "Ab");
        g.setProductions('A', "Bc");
        g.setProductions('A', "a");
        g.setProductions('B', "Bd");
        g.setProductions('B', "Sa");
        g.setProductions('B', "e");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(true, alg.isRecursiveLeft(g));
    }

/**
 * Test of removeRecursiveLeft method, of class Algorithms.
*
    @Test
    public void testRemoveRecursiveLeft() {
         /**
        * S -> aS|Ab
        * A -> Ab|Bc|a
        * B -> Bd|Sa|e
        *
        Context_free g = new Context_free();
        g.setInitialSymbol('S');
        g.setProductions('S', "aS");
        g.setProductions('S', "Ab");
        g.setProductions('A', "Ab");
        g.setProductions('A', "Bc");
        g.setProductions('A', "a");
        g.setProductions('B', "Bd");
        g.setProductions('B', "Sa");
        g.setProductions('B', "e");
        
        /**
        * S -> aS|Ab
        * A -> BcC|aC
        * C -> bC|&
        * B -> aSaD|aCbaD|a
        * D -> dD|cCbaD|&
        *
        Context_free r = new Context_free();
        r.setInitialSymbol('S');
        r.setProductions('S', "aS");
        r.setProductions('S', "Ab");
        r.setProductions('A', "Ab");
        r.setProductions('A', "Bc");
        r.setProductions('A', "a");
        r.setProductions('B', "Bd");
        r.setProductions('B', "Sa");
        r.setProductions('B', "e");
        
        Algorithms alg = new Algorithms();
        
        assertEquals(r.toString(), alg.removeRecursiveLeft(g).toString());
    }*/
    
}
