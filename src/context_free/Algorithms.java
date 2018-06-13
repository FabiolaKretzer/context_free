//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.Map;

public class Algorithms {
    
    ArrayList<Context_free> list;

/**
   * Constructor
*/    
    public Algorithms(){
        list = new ArrayList<>();
    }

/**
   * Verify context free grammar is empty?
*/     
    public boolean isEmpty(Context_free grammar){
        return true;
    }

/**
   * Verify context free grammar is finite?
*/     
    public boolean isFinite(Context_free grammar){
        return true;
    }
 
/**
   * Verify context free grammar is infinite?
*/ 
    public boolean isInfinite(Context_free grammar){
        return true;
    }
 
/**
   * Transformer context free grammar in outer proper
   * Using productive(), reachable(), epsilonFree() and removeCycles()
*/ 
    public Context_free transformProper(Context_free grammar){
        Context_free gnew = new Context_free();
        return gnew;
    }

/**
   * Transformer context free grammar in outer productive
*/     
    public Context_free productive(Context_free grammar){
        
        return new Context_free();
    }

/**
   * Transformer context free grammar in outer reachable
*/     
    public Context_free reachable(Context_free grammar){
        
        return new Context_free();
    }

/**
   * Transformer context free grammar in outer epsilon free
*/ 
    public Context_free epsilonFree(Context_free grammar){
        
        return new Context_free();
    }

/**
   * Transformer context free grammar in outer remove cycles
*/     
    public Context_free removeCycles(Context_free grammar){
        
        return new Context_free();
    }

/**
   * Calculate first the context free grammar
*/     
    public Map<Character, ArrayList<String>> first(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<>();
    }

/**
   * Calculate follow the context free grammar
*/      
    public Map<Character, ArrayList<String>> follow(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<String>();
    }
    
/**
   * Calculate firstNT the context free grammar
*/  
    public Map<Character, ArrayList<String>> firstNT(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<String>();
    }

/**
   * Calculate first the context free grammar
*/      
    public boolean isFactored(Context_free grammar){
        return true;
    }

/**
   * Transformer context free grammar in outer factoted en n steps
*/      
    public Context_free factoredNsteps(Context_free grammar, int steps){
        return new Context_free();
    }

/**
   * Verify context free grammar is recursive left?
*/     
    public boolean isRecursiveLeft(Context_free grammar){
        
        return true;
    }

/**
   * Transformer context free grammar in outer recursive left
*/  
    public Context_free removeRecursiveLeft(Context_free grammar){
        
        return new Context_free();
    }
    
}
