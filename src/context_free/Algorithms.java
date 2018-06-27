//  Copyright 2018 <Fabíola Maria Kretzer> <Maurício Machado Barbosa>

package context_free;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        boolean bool = true;
        Context_free gnew = grammar.getClone();
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(!Objects.equals(key, gnew.getInitialSymbol()) && "&".equals(s)){
                    bool = false;
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
        Set<Character> Ne = new HashSet<>();
        boolean control = true;
        //Passo 1 - Building Ne
        while(control){
            control = false;
            for(Character key: gnew.getProductions().keySet()){
                ArrayList<String> temp = gnew.getProductions().get(key);
                for(String s : temp){
                    if(s.equals("&") && !Ne.contains(key)){
                        Ne.add(key);
                        control = true;
                    }
                    boolean b = true;
                    for(int i = 0; i > s.length(); i++){
                        if(!Ne.contains(s.charAt(i))){
                            b = false;
                        }
                    }
                    if(b && !Ne.contains(key)){
                        Ne.add(key);
                        control = true;
                    }
                }
            }
        }
        // Passo 2a - copiar as produções
        gRet.setInitialSymbol(gnew.getInitialSymbol());
        for(Character key: gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            ArrayList<String> newProductions = new ArrayList<>();
            for(String s : temp){
                if(!s.equals("&")){
                    newProductions.add(s);
                }
            }
            gRet.setProductions(key, newProductions);
        }
        // Passo 2a - Done
        // Passo 2b - Combinação das produções 
        for(Character key: gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(!s.equals('&')){
                    ArrayList<Character> BeNe = new ArrayList<>();
                    for(Character c: s.toCharArray()){
                        if(Ne.contains(c))
                            BeNe.add(c);
                    }
                    //faz as combinações
                    String[] status = new String[BeNe.size()]; //aqui pode ser qualquer objeto que implemente Comparable
                    int cont=0;
                    for(Character c: BeNe){
                        status[cont++] = c.toString();
                    }
                    List<SortedSet<Comparable>> allCombList = new ArrayList<SortedSet<Comparable>>(); //aqui vai ficar a resposta
                    for (String nstatus : status) {
                            allCombList.add(new TreeSet<Comparable>(Arrays.asList(nstatus))); //insiro a combinação "1 a 1" de cada item
                    }
                    for (int nivel = 1; nivel < status.length; nivel++) { 
                            List<SortedSet<Comparable>> statusAntes = new ArrayList<SortedSet<Comparable>>(allCombList); //crio uma cópia para poder não iterar sobre o que já foi
                            for (Set<Comparable> antes : statusAntes) {
                                    SortedSet<Comparable> novo = new TreeSet<Comparable>(antes); //para manter ordenado os objetos dentro do set
                                    novo.add(status[nivel]);
                                    if (!allCombList.contains(novo)) { //testo para ver se não está repetido
                                            allCombList.add(novo);
                                    }
                            }
                    }
                    Collections.sort(allCombList, new Comparator<SortedSet<Comparable>>() { //aqui só para organizar a saída de modo "bonitinho"
                            @Override
                            public int compare(SortedSet<Comparable> o1, SortedSet<Comparable> o2) {
                                    int sizeComp = o1.size() - o2.size();
                                    if (sizeComp == 0) {
                                            Iterator<Comparable> o1iIterator = o1.iterator();
                                            Iterator<Comparable> o2iIterator = o2.iterator();
                                            while (sizeComp == 0 && o1iIterator.hasNext() ) {
                                                    sizeComp = o1iIterator.next().compareTo(o2iIterator.next());
                                            }
                                    }
                                    return sizeComp;
                            }
                    });
                    //allCombList contem todas as combinações
                    for(SortedSet se: allCombList){
                        String sclone = s+"";
                        for(Object obj: se){
                            sclone = sclone.replaceFirst(obj.toString(), "");
                        }
                        if(!sclone.equals("") && !gRet.getProductions().get(key).equals(sclone)){
                            gRet.setProductions(key, sclone);
                        }
                    }
                }
            }
        }
        //passo 2c
        char a = 'A';
        ArrayList<Character> alphabet = new ArrayList<>();
        while(a != 'Z'){
            alphabet.add(a);
            a++;
        }
        for(Character key : gnew.getProductions().keySet()){
            alphabet.remove(key);
        }
        if(alphabet.contains('S'))
            alphabet.set(0, 'S');
        gRet.setProductions(alphabet.get(0), gRet.getInitialSymbol().toString());
        gRet.setProductions(alphabet.get(0), "&");
        gRet.setInitialSymbol(alphabet.get(0));
        return gRet;
    }
    
/**
   * Transformer context free grammar in outer remove cycles
*/     
    public boolean hasCycles(Context_free grammar){
        Context_free gnew = grammar.getClone();
        ArrayList<Character> aux = new ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            aux.add(key);
        }
        Map<Character,ArrayList<String>> map = new HashMap<>();
        for(Character key : gnew.getProductions().keySet()){
            map.put(key, new ArrayList<>());
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(s.length() == 1 && aux.contains(s.charAt(0))){
                    map.get(key).add(s);
                }
            }
        }
        for(Character key : gnew.getProductions().keySet()){
            boolean control = true;
            while(control){
                control = false;
                ArrayList<String> alcan = new ArrayList<>();
                alcan.addAll(map.get(key));
                
                for(String s : alcan){
                    ArrayList<String> reac = map.get(s.charAt(0));
                    for(String s1: reac){
                        if(!map.get(key).contains(s1)){
                            map.get(key).add(s1);
                        }
                    }
                }
            }
        }
        for(Character key : gnew.getProductions().keySet()){
            if(map.get(key).contains(key.toString())){
                return true;
            }
        }
        return false;
    }
    
/**
   * Transformer context free grammar in outer remove cycles
*/     
    public Context_free removeCycles(Context_free grammar){
        Context_free gnew = grammar.getClone();
        if(!hasCycles(gnew)){
            return gnew;
        }
        ArrayList<Character> aux = new ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            aux.add(key);
        }
        Map<Character,ArrayList<String>> Nx = new HashMap<>();
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = new ArrayList<>();
            list.add(key.toString());
            Nx.put(key, list);
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(s.length() == 1 && aux.contains(s.charAt(0)) && !Nx.get(key).contains(s)){
                    Nx.get(key).add(s);
                }
            }
        }
        for(Character key : gnew.getProductions().keySet()){
            boolean control = true;
            while(control){
                control = false;
                ArrayList<String> alcan = new ArrayList<>();
                alcan.addAll(Nx.get(key));
                
                for(String s : alcan){
                    ArrayList<String> reac = Nx.get(s.charAt(0));
                    for(String s1: reac){
                        if(!Nx.get(key).contains(s1)){
                            Nx.get(key).add(s1);
                        }
                    }
                }
            }
        }
        Context_free gRet = new Context_free();
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> temp = gnew.getProductions().get(key);
            for(String s : temp){
                if(s.length() != 1 || !aux.contains(s.charAt(0))){
                    for(Character keyA: gnew.getProductions().keySet()) {
                        if(Nx.get(keyA).contains(key.toString())){
                            gRet.setProductions(keyA, s);
                        }
                    }
                }
            }
                
        }
        gRet.setInitialSymbol(gnew.getInitialSymbol());
        return gRet;
    }
 
/**
   * Get symbols fertiles in context free grammar
*/
    public Set<Character> getFertileSymbols(Context_free grammar){
        Context_free gnew = grammar.getClone();
        Set<Character> vi = new HashSet<>();
        boolean bool = true; 
        while(bool){
            bool = false;
            for(Character key : gnew.getProductions().keySet()){
                ArrayList<String> temp = gnew.getProductions().get(key);
                for(String s : temp) {
                    if(isFertile(s, vi)){
                        if(!vi.contains(key)) {
                            vi.add(key);
                            bool=true;
                        }    
                    }
                }
            }
        }
        return vi;
    }
    
/**
   * Symbol is fertile?
*/
    public boolean isFertile(String s, Set<Character> vi){
        Pattern p = Pattern.compile("[a-z&]");
        for(Character c: s.toCharArray()){
            Matcher m = p.matcher(c.toString());
            if(!vi.contains(c) && !m.matches()){
                return false;
            }
        }
        return true;
    }

/**
   * Transformer context free grammar in outer productive
*/     
    public Context_free fertile(Context_free grammar){
        Set<Character> set = getFertileSymbols(grammar);
        Context_free gnew = grammar.getClone();
        Context_free gRet = new Context_free();
        if(!set.contains(gnew.getInitialSymbol())){
            gRet.setInitialSymbol('S');
            gRet.setProductions('S',"S");
            return gRet;
        }
        for(Character key : gnew.getProductions().keySet()){
                ArrayList<String> temp = gnew.getProductions().get(key);
                for(String s : temp) {
                    if(isFertile(s, set)){
                        gRet.setProductions(key, s);
                    }
                }
        }
        gRet.setInitialSymbol(gnew.getInitialSymbol());
        System.out.println(gRet);
        return gRet;
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
        ArrayList<Character> nTerminal = new  ArrayList<>();
        Map<Character, ArrayList<String>> symbol = new HashMap<>();
        for(Character key : gnew.getProductions().keySet()){
            nTerminal.add(key);
        }
        for(Character key : gnew.getProductions().keySet()){
            ArrayList<String> list = gnew.getProductions().get(key);
            ArrayList<String> temp = new ArrayList<>();
            for(String s : list){
                temp.add(s.charAt(0)+"");
            }
            symbol.put(key, temp);
        }
        Boolean interator = true;
        while(interator){
            interator = false;
            for(Character key : gnew.getProductions().keySet()){
                ArrayList<String> toAdd = new ArrayList<>();
                ArrayList<String> toRemove = new ArrayList<>();
                for(String s : symbol.get(key)){
                    if(nTerminal.contains(s.charAt(0)) && symbol.get(s) != null){
                        for (String s2: symbol.get(s)) {
                            toAdd.add(s2);
			}
			toRemove.add(s);
                        interator = true;
                    }
                } 
                symbol.get(key).addAll(toAdd);
                symbol.get(key).removeAll(toRemove);
            }
        }
        for (Character key : gnew.getProductions().keySet()) {
            Set<String> temp = new HashSet<>();
            temp.addAll(symbol.get(key));
            if (symbol.get(key).size() != temp.size()) {
		return false;
            }
	}		
        return true;
    }

/**
   * Transformer context free grammar in outer factoted en n steps
*/      
    public Boolean factoredNsteps(Context_free grammar, int steps){
        Context_free gnew = grammar.getClone();
        if(isFactored(gnew)){
            return true;
        }
        Context_free fact = new Context_free();
        fact.setInitialSymbol(gnew.getInitialSymbol());
        ArrayList<Character> nTerminal = new  ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            nTerminal.add(key);
        }
        for(int i = 0; i < steps; i++){
            Map<Character, Set<String>> symbol = new HashMap<>();
            for(Character key : gnew.getProductions().keySet()){
               Set<String> temp = new HashSet<>();
               Set<String> toAdd = new HashSet<>(); 
               for(String s : gnew.getProductions().get(key)){
                   if(temp.contains(s.charAt(0))){
                       toAdd.add(s.charAt(0)+"");
                   } else{
                       temp.add(s.charAt(0)+"");
                   }
                }
                symbol.put(key, toAdd);				
                Set<String> toRemove = new HashSet<String>();
                
                for(String s : symbol.get(key)){
                    Character symbolNT = renameSymbol(gnew);
                    for(String prod : gnew.getProductions().get(key)){
                        if(prod.charAt(0) == s.charAt(0)){
                            ArrayList<String> temp2 = new ArrayList<>();
                            for(int j = 1; j < prod.length(); j++){
                                temp2.add(prod.charAt(j)+"");
                            }
                            if(temp2.size() == 0){
                                temp2.add("&");
                            }
                            fact.setProductions(symbolNT, temp2);
                        }
                    }
                }
            }
        }
        if (isFactored(fact)) {
            return true;
        }
        return false;
    }
 
/**
   * Rename symbol grammar
*/ 
    public Character renameSymbol(Context_free grammar){
        Context_free gnew = new Context_free();
        ArrayList<Character> nt = new ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            nt.add(key);
        }
        Character symbol = 'A';
        while(nt.contains(symbol)){
            symbol++;
        }
        return symbol;
    }

/**
   * Verify context free grammar is direct recursive left?
*/ 
    public boolean isRecursiveLeft(Context_free grammar){
        Context_free gnew = grammar.getClone();
        ArrayList<String> nt = new ArrayList();
        for(Character key : gnew.getProductions().keySet()){
            nt.add(key+"");
        }
        Context_free recur = removeRecursiveLeft(gnew);
        ArrayList<String> ntR = new ArrayList();
        for(Character key : recur.getProductions().keySet()){
            ntR.add(key+"");
        }
        if(nt.size() == ntR.size()){
            return false;
        }
        return true;
    }
    
/**
   * Verify context free grammar is direct recursive left?
*/ 
    public boolean isDirectRecursiveLeft(Context_free grammar){
        Context_free gnew = grammar.getClone();
        for(Character key : gnew.getProductions().keySet()){
            for(String s : gnew.getProductions().get(key)){
                 if(key == s.charAt(0)){
                     return true;
                 }  
            }
        }
        return false;
    }
    
/**
   * Verify  the symbols context free grammar is direct recursive left?
*/ 
    public ArrayList<String> symbolsDirectRecursiveLeft(Context_free grammar){
        Context_free gnew = grammar.getClone();
        ArrayList<String> symbol = new ArrayList<>();
        for(Character key : gnew.getProductions().keySet()){
            for(String s : gnew.getProductions().get(key)){
                 if(key == s.charAt(0)){
                     symbol.add(s.charAt(0)+"");
                 }  
            }
        }
        return symbol;
    }

/**
   * Transformer context free grammar in outer recursive left
*/  
    public Context_free removeRecursiveLeft(Context_free grammar){
        Context_free gnew = grammar.getClone();
        ArrayList<String> nt = new ArrayList<>();
        Context_free recur = new Context_free();
        recur.setInitialSymbol(gnew.getInitialSymbol());
        for(Character key : gnew.getProductions().keySet()){
            nt.add(key+"");
        }
        for(int i = 0; i < nt.size(); i++){
            for(int j = 0; j < i; j++){
                Set<String> prodsToAdd = new HashSet<>();
                Set<String> prodsToRemove = new HashSet<>();
                for(String s : gnew.getProductions().get(i)){
                    if(s.charAt(0) == nt.get(i).charAt(0)){
                        prodsToRemove.add(s);
                    }
                    for(String s2 : gnew.getProductions().get(nt.get(j))){
                        ArrayList<String> temp = new ArrayList<>();
                        for(int in = 0; in < s2.length(); in++){
                            temp.add(s2.charAt(in)+"");
                        }
                        for(int in = 0; in < s.length(); in++){
                            temp.add(s.charAt(in)+"");
                        }
                        temp.remove(nt.get(j));
                        recur.setProductions(nt.get(j).charAt(0), temp);
                    }
                }
            }
            ArrayList<String> ntRecur = symbolsDirectRecursiveLeft(gnew);
            if(ntRecur.contains(nt.get(i))){
                removeDirectLeftRecursion(nt.get(i), recur);
            }
        }
        return recur;
    }

    private void removeDirectLeftRecursion(String symbolNT, Context_free grammar) {
        Context_free gnew = grammar.getClone();
        Context_free left = new Context_free();
        left.setInitialSymbol(gnew.getInitialSymbol());
        Character newNT = renameSymbol(gnew);
        Set<String> prodsToAdd = new HashSet<>();
        Set<String> prodsToRemove = new HashSet<>();
        for(Character key : gnew.getProductions().keySet()){
            for(String s : gnew.getProductions().get(key)){
                if(s.charAt(0) == symbolNT.charAt(0)){
                    ArrayList<String> temp = new ArrayList<>();
                    for(int i = 0; i < s.length(); i++){
                        temp.add(s.charAt(i)+"");
                    }
                    temp.add(newNT+"");
                    left.setProductions(symbolNT.charAt(0), temp);
                    prodsToRemove.add(s);
                } else{
                    ArrayList<String> temp = new ArrayList<>();
                    for(int i = 0; i < s.length(); i++){
                        temp.add(s.charAt(i)+"");
                    }
                    temp.add(newNT+"");
                    left.setProductions(symbolNT.charAt(0), temp);
                    prodsToRemove.add(s);
                }
                left.setProductions(symbolNT.charAt(0), "&");
            }
        }     
    }
    
}
