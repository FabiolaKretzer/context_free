//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Context_free {

    protected Character initialSymbol;
    Map<Character, ArrayList<String>> productions;
    public String name ="";
         
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
