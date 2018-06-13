//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.Map;

public class Algorithms {
    
    ArrayList<Context_free> list;
    
    public Algorithms(){
        list = new ArrayList<>();
    }
    
    public boolean isEmpty(Context_free grammar){
        return true;
    }
    
    public boolean isFinite(Context_free grammar){
        return true;
    }
    
    public boolean isInfinite(Context_free grammar){
        return true;
    }
    
    public Context_free transformProper(Context_free grammar){
        Context_free gnew = new Context_free();
        return gnew;
    }
    
    public Context_free productive(Context_free grammar){
        
        return new Context_free();
    }
    
    public Context_free reachable(Context_free grammar){
        
        return new Context_free();
    }
    
    public Context_free epsilonFree(Context_free grammar){
        
        return new Context_free();
    }
    
    public Context_free removeCycles(Context_free grammar){
        
        return new Context_free();
    }
    
    public Map<Character, ArrayList<String>> first(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<>();
    }
    
    public Map<Character, ArrayList<String>> follow(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<String>();
    }
    
    public Map<Character, ArrayList<String>> firstNT(Context_free grammar){
        return (Map<Character, ArrayList<String>>) new ArrayList<String>();
    }
    
    public boolean isFactored(Context_free grammar){
        return true;
    }
    
    public Context_free factoredNsteps(Context_free grammar, int steps){
        return new Context_free();
    }
    
    public boolean isRecursiveLeft(Context_free grammar){
        
        return true;
    }

    public Context_free removeRecursiveLeft(Context_free grammar){
        
        return new Context_free();
    }
    
}
