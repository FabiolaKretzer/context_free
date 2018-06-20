//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
            ArrayList<String> temp = g_reac.getProductions().get(key);
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
            ArrayList<String> temp = g_reac.getProductions().get(key);
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
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(!Objects.equals(key, gnew.getInitialSymbol()) && "&".equals(s)){
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
        Context_free gRet = new Context_free();
        if(isEpsilonFree(gnew)){
            return gnew;
        }
        return gRet;
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
            ArrayList<String> temp = gnew.getProductions().get(key);
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
        Context_free gnew = grammar.getClone();
        Map<Character, ArrayList<String>> vt = new HashMap<>();
        ArrayList<Character> vn = new ArrayList<>();
        for (Character key : gnew.getProductions().keySet()){
            vn.add(key);
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> list_aux = gnew.getProductions().get(key);
            for(String s : list_aux){
                int i = 0;
                while(i < s.length()){
                    if(!vn.contains(s.charAt(i))){
                        ArrayList<String> terminal = new ArrayList<>();
                        terminal.add(s.charAt(i)+"");
                        vt.put(s.charAt(i), terminal);
                    }
                    i++;
                }
            }
        }
        for (Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            for(String s : list){
                if(!vn.contains(s.charAt(0))){
                    if(vt.get(key) == null){
                        ArrayList<String> aux = new ArrayList<>();
                        aux.add(s.charAt(0)+"");
                        vt.put(key, aux);
                    } else {
                        ArrayList<String> aux = vt.get(key);
                        aux.add(s.charAt(0)+"");
                        vt.put(key, aux);
                    }
                }
            }
        }
        Algorithms alg = new Algorithms();
        Map<Character, ArrayList<String>> fNT = alg.firstNT(gnew);
        for (Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            for(String s : list){
                int i = 0;
                while(i < s.length() && vn.contains(s.charAt(i))){
                    ArrayList<String> list2 = fNT.get(key);
                    if(list2.contains(s.charAt(i)+"")){
                        if(vt.get(key) == null){
                            ArrayList<String> aux = vt.get(s.charAt(i));
                            vt.put(key, aux);
                        } else {
                            ArrayList<String> aux = vt.get(key);
                            ArrayList<String> aux2 = vt.get(s.charAt(i));
                            for(String st : aux2){
                                aux.add(st);
                            }
                            vt.put(key, aux);
                        }
                    }
                    i++;
                }
            }
        }        
        return vt;
    }

/**
   * Calculate follow the context free grammar
*/      
    public Map<Character, ArrayList<String>> follow(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Algorithms alg = new Algorithms();
        Map<Character, ArrayList<String>> vt_first = alg.first(gnew);
        Map<Character, ArrayList<String>> vt = new HashMap<>();
        ArrayList<Character> vn = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>();
        aux.add("$");
        vt.put(gnew.getInitialSymbol(), aux);
        for (Character key : gnew.getProductions().keySet()){
            vn.add(key);
        }
        for (Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            for(String s : list){
                for(int i = 0; i < s.length() - 1; i++){
                    char c = s.charAt(i);
                    if(vn.contains(c) && i != s.length()){
                        if(vt_first.get(s.charAt(i+1)) != null){
                            ArrayList<String> aux2 = vt_first.get(s.charAt(i+1));
                            if(aux2.contains("&")){
                                aux2.remove(c);
                            }
                            vt.put(c, aux2);
                        }
                    } else{
                        break;
                    }
                }
            }
        }
        return vt;
    }
    
/**
   * Calculate firstNT the context free grammar
*/  
    public Map<Character, ArrayList<String>> firstNT(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Map<Character, ArrayList<String>> vt = new HashMap<>();
        ArrayList<Character> vn = new ArrayList<>();
        for (Character key : gnew.getProductions().keySet()){
            vn.add(key);
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            for(String s : list){
                if(vn.contains(s.charAt(0))){
                    if(vt.get(key) == null){
                        ArrayList<String> aux = new ArrayList<>();
                        aux.add(s.charAt(0)+"");
                        vt.put(key, aux);
                    } else {
                        ArrayList<String> aux = vt.get(key);
                        aux.add(s.charAt(0)+"");
                        vt.put(key, aux);
                    }
                    int i = 0;
                    while(i < s.length() && vn.contains(s.charAt(i))){
                        char c = s.charAt(0);
                        ArrayList<String> list2 = gnew.getProductions().get(c);
                        if(list2.contains("&")){
                            if(vt.get(key) == null){
                                ArrayList<String> aux2 = new ArrayList<>();
                                aux2.add(s.charAt(i)+"");
                                vt.put(key, aux2);
                            } else {
                                ArrayList<String> aux2 = vt.get(key);
                                aux2.add(s.charAt(i)+"");
                                vt.put(key, aux2);
                            }
                        }
                        i++;
                    }
                }
            }       
        }
        return vt;
    }

/**
   * Calculate first the context free grammar
*/      
    public boolean isFactored(Context_free grammar){
        Context_free gnew = grammar.getClone();
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            for(String s : list){
                
            }
        }
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
