//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Algorithms {
    
    ArrayList<Context_free> list;

/**
   * Constructor
*/    
    public Algorithms(){
        this.list = new ArrayList<>();
    }

/**
   * Verify context free grammar is empty?
*/     
    public boolean isEmpty(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Context_free g_prod = fertile(gnew);
        Context_free g_reac = reachable(g_prod);
        ArrayList<Character> aux = new ArrayList<>();
        for(Character key : g_reac.getProductions().keySet()){
            aux.add(key);
        }
        for(Character key : g_reac.getProductions().keySet()){
            ArrayList<String> temp = g_reac.productions.get(key);
            for(String s : temp){
                int i = 0;
                HashSet<Boolean> bool = new HashSet<>();
                while(i < s.length()) {
                    char c = s.charAt(i);
                    if(aux.contains(c)){
                        bool.add(Boolean.TRUE);
                    } else {
                        bool.add(Boolean.FALSE);
                    }
                    i++;
                }
                if(bool.contains(Boolean.TRUE) && bool.contains(Boolean.FALSE)){
                    break;
                }
                if(bool.contains(Boolean.TRUE)){
                    break;
                }
                if(bool.contains(Boolean.FALSE)){
                    return false;
                }
            }
        }
        return true;
    }

/**
   * Verify context free grammar is finite?
*/     
    public boolean isFinite(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Context_free g_prod = fertile(gnew);
        Context_free g_reac = reachable(g_prod);
        ArrayList<Character> aux = new ArrayList<>();
        for(Character key : g_reac.getProductions().keySet()){
            aux.add(key);
        }
        for(Character key : g_reac.getProductions().keySet()){
            ArrayList<String> temp = g_reac.productions.get(key);
            for(String s : temp){
                int i = 0;
                while(i < s.length()) {
                    char c = s.charAt(i);
                    if(aux.contains(c)){
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }
 
/**
   * Verify context free grammar is infinite?
*/ 
    public boolean isInfinite(Context_free grammar){
        Context_free gnew = grammar.getClone();
        return !isFinite(gnew);
    }
 
/**
   * Transformer context free grammar in outer proper
   * Using productive(), reachable(), epsilonFree() and removeCycles()
*/ 
    public Context_free transformProper(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Context_free g_free = epsilonFree(gnew);
        Context_free g_cycles = removeCycles(g_free);
        Context_free g_prod = fertile(g_cycles);
        Context_free g_reac = reachable(g_prod);
        return g_reac;
    }
    
/**
   * Context free grammar is epsilon free?
*/ 
    public boolean isEpsilonFree(Context_free grammar){
        boolean bool = false;
        Context_free gnew = grammar.getClone();
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.productions.get(key);
            for(String s : temp){
                if(key != gnew.getInitialSymbol() && s == "&"){
                    bool = true;
                }
            }
        }
        return bool;
    }
    
/**
   * Transformer context free grammar in outer epsilon free
*/ 
    public Context_free epsilonFree(Context_free grammar){
        Context_free gnew = grammar.getClone();
        if(isEpsilonFree(gnew)){
            return gnew;
        }
        return new Context_free();
    }
    
/**
   * Transformer context free grammar in outer remove cycles
*/     
    public boolean isCycles(Context_free grammar){
        Context_free gnew = grammar.getClone();
        ArrayList<Character> aux = new ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            aux.add(key);
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.productions.get(key);
            for(String s : temp){
                if(s.length() == 1 && aux.contains(s.charAt(0))){
                    return true;
                }
            }
        }
        return false;
    }
    
/**
   * Transformer context free grammar in outer remove cycles
*/     
    public Context_free removeCycles(Context_free grammar){
        Context_free gnew = grammar.getClone();
        if(isCycles(gnew)){
            return gnew;
        }
        return new Context_free();
    }
 
/**
   * Get symbols fertiles in context free grammar
*/
    public Set<Character> getFertileSymbols(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Set<Character> vi = new HashSet<Character>();
        boolean bool = true; 
        while(bool){
            bool = false;
            for(Character key : gnew.getProductions().keySet()){
                if(isFertile(key, gnew)){
                    vi.add(key);
                    bool = true;
                }
            }
        }
        return vi;
    }
    
/**
   * Symbol is fertile?
*/
    public boolean isFertile(Character c, Context_free grammar){
        Context_free gnew = grammar.getClone();
        return true;
    }

/**
   * Transformer context free grammar in outer productive
*/     
    public Context_free fertile(Context_free grammar){
        
        return new Context_free();
    }

/**
   * Transformer context free grammar in outer reachable
*/     
    public Context_free reachable(Context_free grammar){
        
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
