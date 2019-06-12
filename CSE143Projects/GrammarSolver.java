/* Created by Leslie Mares on 06-05-19
CSE 143 HW Assignment 5 https://courses.cs.washington.edu/courses/cse143/19sp/handouts/10.html

GrammarSolver stores rules and symbols of grammar in Backus-Naur Form (BNF) that can be used
to generate random elements of grammar, return the stored symbols,
and whether or not a given symbol is a nonterminal
*/

import java.util.*;
public class GrammarSolver {
    //maps nonterminals to rules in Backus-Naur Form (BNF)
    private SortedMap<String, String[]> grammarBNF;
    //used to randomly choose between rules
    private Random r = new Random();

    //pre: is passed a list of grammar in Backus-Naur Form (BNF) as a parameter
    //     throws an IllegalArgumentException if the list is empty
    //post: constructs a new GrammarSolver object that stores
    //      a map of grammar nonterminals to rules
    public GrammarSolver(List<String> grammar){
        if(grammar.isEmpty()){
            throw new IllegalArgumentException("list of strings is empty");
        }
        grammarBNF = new TreeMap<>();
        for(String s: grammar){
            String[] splitSymbolRules = s.split("::=");
            if(grammarBNF.containsKey(splitSymbolRules[0])){
                throw new IllegalArgumentException("duplicate nonterminal symbols");
            }
            String[] splitRulesRules = splitSymbolRules[1].split("[|]");
            grammarBNF.put(splitSymbolRules[0], splitRulesRules);

            //debugger it
            System.out.print(splitSymbolRules[0] + " = [" + splitRulesRules[0]);
            for(int i = 1; i < splitRulesRules.length; i++){
                System.out.print(", " + splitRulesRules[i]);
            }
            System.out.println("]");
        }
    }

    //pre: a symbol is passed as a parameter
    //post: returns true of the symbol is a nonterminal or false if it is not
    public boolean grammarContains(String symbol){
        return grammarBNF.containsKey(symbol);
    }

    //pre: a symbol and a number are passed as parameters
    //     throws an IllegalArgumentException if the symbol
    //     passed is not a nonterminal or if the number is less than 0
    //post: uses grammar to randomly generate elements of grammar the
    //      given number of times; returned as an array of strings
    public String[] generate(String symbol, int times){
        if (times < 0 || !grammarContains(symbol)){
            throw new IllegalArgumentException("symbol must be a nonterminal " +
                    "and number of times must be greater than zero");
        }

        String[] generatedSentences = new String[times];
        for(int i = 0; i < times; i++){
            generatedSentences[i] = generateOneSentence(symbol);
        }
        return generatedSentences;
    }

    //pre: a symbol is passed as a parameter
    //post: returns one randomly generated element of grammar as a string
    private String generateOneSentence(String symbol){
        if(grammarContains(symbol)) {
            String[] rules = grammarBNF.get(symbol);
            String randomlyChosenRule = rules[r.nextInt(rules.length)].trim();
            String[] chosenRuleSplit = randomlyChosenRule.split("[ \t]+");
            String sentence = "";

            for (String s: chosenRuleSplit) {
                sentence += generateOneSentence(s);
            }
            return sentence;
        } else {
            return symbol + " ";
        }
    }

    //post: returns a string of all the nonterminal symbols in the grammar
    //      symbols are listed between brackets, in alphabetical order,
    //      and separated by commas
    public String getSymbols(){
        return grammarBNF.keySet().toString();
    }
}
