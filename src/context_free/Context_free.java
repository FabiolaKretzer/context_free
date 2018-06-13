//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Context_free {

    protected Character initialSymbol;
    Map<Character, ArrayList<String>> productions;
    public String name ="";
    Context_free proper;
    Context_free productive;
    Context_free reachable;
    Context_free epsilonFree;
    Context_free removeCycles;
    Context_free factoredNsteps;
    Context_free recursiveLeft;
    Map<Character, ArrayList<String>> first;
    Map<Character, ArrayList<String>> follow;
    Map<Character, ArrayList<String>> firstNT;
         
/**
   * Constructor sem parâmetros, apenas inicialização das produções de uma gramática
*/
    public Context_free(){
        this.productions = new HashMap<>();
    }
        
/**
   * Constructor com único parâmetro e inicialização das produções de uma gramática
*/      
    public Context_free(Character initialSymbol) {
        this.initialSymbol = initialSymbol;
        this.productions = new HashMap<>();
    }
        
/**
   * Constructor com parâmetros e inicialização das produções de uma gramática
*/
    public Context_free(Character initialSymbol,String name) {
        this.initialSymbol = initialSymbol;
        this.name = name;
        this.productions = new HashMap<>();
        this.proper = new Context_free();
        this.reachable = new Context_free();
        this.epsilonFree = new Context_free();
        this.removeCycles = new Context_free();
        this.factoredNsteps = new Context_free();
        this.recursiveLeft = new Context_free();
        this.first = new HashMap<>();
        this.follow = new HashMap<>();
        this.firstNT = new HashMap<>();
    }
   
/**
   * Pegar nome da gramática
*/        
    public String getName(){
        return this.name;
    }
  
/**
   * Modificar nome da gramática
*/
    public void setName(String name){
        this.name = name;
    }
        
/**
   * Pegar símbolo inicial da gramática
*/
    public Character getInitialSymbol(){
        return initialSymbol;
    }
        
/**
   * Modificar símbolo inicial da gramática
*/
    public void setInitialSymbol(Character initialSymbol){
        this.initialSymbol = initialSymbol;
    }
        
/**
   * Recebe um mapa de produções e adiciona essas produção à gramática
*/       
    public void setProductions(Map<Character, ArrayList<String>> productions){
        for(Character key :productions.keySet()){
            setProductions(key, productions.get(key));
        }
    }
 
/**
   * Recebe um símbolo não-terminal e suas produções, e adiciona à gramática
*/ 
    protected void setProductions (Character c, ArrayList<String> list) {
        for(String s: list){
            setProductions(c, s);
        }
    }

/**
   * Pega as produções da gramática
*/         
    public Map<Character, ArrayList<String>> getProductions() {
        return productions;
    }
 
/**
   * Recebe um símbolo não-terminal e uma produção, e adiciona à gramática
*/ 
    public void setProductions (Character c, String prod) {
        if(productions.containsKey(c)) {
            ArrayList<String> list = productions.get(c);
            if(!list.contains(prod)){
                list.add(prod);
                productions.put(c, list);
            }
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(prod);
            productions.put(c, list);
        }
    }
    
 /**
   * Pega as gramaticas livre de contexto
*/    
    public Context_free getProper(){
        return proper;
    }
    
    public Context_free getProductive(){
        return productive;
    }
    
    public Context_free getReachable(){
        return reachable;
    }
    
    public Context_free getEpsilonFree(){
        return epsilonFree;
    }
    
    public Context_free getRemoveCycles(){
        return removeCycles;
    }
    
    public Context_free getFactoredNsteps(){
        return factoredNsteps;
    }
    
    public Context_free getRecursiveLeft(Context_free recursiveLeft){
        return recursiveLeft;
    }
 
/**
   * Seta uma gramatica livre de contexto
*/ 
     public void setProper(Context_free proper){
        this.proper = proper;
    }
    
    public void setProductive(Context_free productive){
        this.productive = productive;
    }
    
    public void setReachable(Context_free reachable){
        this.reachable = reachable;
    }
    
    public void setEpsilonFree(Context_free epsilonFree){
        this.epsilonFree = epsilonFree;
    }
    
    public void setRemoveCycles(Context_free removeCycles){
        this.removeCycles = removeCycles;
    }
    
    public void setFactoredNsteps(Context_free factoredNsteps){
        this.factoredNsteps = factoredNsteps;
    }
    
    public void setRecursiveLeft(Context_free recursiveLeft){
        this.recursiveLeft = recursiveLeft;
    }       
    
/**
   * Visualizar a gramática regular
*/ 
    @Override
    public String toString(){
        String resp = initialSymbol+"->";
        ArrayList<String> prodI = productions.get(initialSymbol);
        for(String key: prodI){
            resp += key+"|";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="\n";
        for(Character key : productions.keySet()){
            if(!key.equals(initialSymbol)){
                resp += key+"->";
                ArrayList<String> prodT = productions.get(key); 
                for(String key2: prodT){
                    resp += key2+"|";
                }
                resp = resp.substring(0, resp.length()-1);
                resp+="\n";
            }
        }
        return resp;
    }

/**
   * Fazer uma cópia da gramática regular
*/
    public Context_free getClone(){
        Context_free gClone = new Context_free(this.getInitialSymbol());
        for(Character key : this.productions.keySet()) {
            ArrayList<String> list = this.productions.get(key);
            for(String s : list){
                gClone.setProductions(key, s+"");
            }
        }
        gClone.setName(getName());
        return gClone;
    }
    
}
