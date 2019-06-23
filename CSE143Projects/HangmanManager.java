/* Created by Leslie Mares on 06-18-19 in response to CSE 143 Hw 4
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/07.html
*/

import java.util.*;
public class HangmanManager {

    //current dictionary of words the manager is considering
    private Set<String> wordChoices;
    //player's past guesses
    private Set<Character> guesses;
    //number of attempts left
    private int guessesLeft;
    //displays the letters the player has guessed correctly and
    //dashes for letters that have not been guessed yet
    private String pattern;

    //pre: passed a dictionary of words, a target word length, and the max number of
    //     wrong guesses a player can make
    //post: sets the initial conditions of a new Hangman game
    //      throws an IllegalArgumentException if length is less than one or
    //      max is less than zero
    public HangmanManager (Collection<String> dictionary, int length, int max){
        if(length < 1 || max < 0){
            throw new IllegalArgumentException("length cannot be less than 1 and " +
                    "max cannot be less than 0\nlength = " + length + "; max = " + max);
        }
        guessesLeft = max;
        guesses = new TreeSet<>();
        wordChoices = initializeDictionary(dictionary, length);
        pattern = initializePattern(length);
    }

    //pre: is passed the target word length
    //post: builds and returns the first pattern for the word
    //      no letters have been guessed so pattern is made up of only dashes
    private String initializePattern(int length){
        String initialPattern = "";
        for(int i = 0; i < length; i++){
            initialPattern += "-";
        }
        return initialPattern;
    }

    //pre: is passed the target word length and a dictionary of words
    //post: returns a new dictionary of words that are the target length
    private Set<String> initializeDictionary(Collection<String> dictionary, int length){
        Set<String> choices = new TreeSet<>();
        for(String word: dictionary){
            if(word.length() == length){
                choices.add(word);
            }
        }
        return choices;
    }

    //post: returns the current dictionary of words being considered
    public Set<String> words(){
        return wordChoices;
    }

    //post: returns the number of attempts left
    public int guessesLeft(){
        return guessesLeft;
    }

    //post: returns the player's previous guesses
    public Set<Character> guesses(){
        return guesses;
    }

    //post: returns the current word pattern
    //      letters that have not been guessed yet are displayed as dashes
    //      throws an IllegalStateException if the current dictionary is empty
    public String pattern(){
        if(wordChoices.isEmpty()){
            throw new IllegalStateException("dictionary is empty");
        }
        String wordPattern = "" + pattern.charAt(0);
        for(int i = 1; i < pattern.length(); i++){
            wordPattern += " " +  pattern.charAt(i);
        }
        return wordPattern;
    }

    //pre: is passed the player's new guess
    //post: records the guess, chooses a new set of words that keeps the most options open
    //      throws an IllegalStateException if the number of guesses is less than one,
    //      or if the set of words is empty
    //      throws an IllegalArgumentException if the character was already guessed
    public int record(char guess){
        if(guessesLeft < 1 || wordChoices.isEmpty()){
            throw new IllegalStateException("Guesses left = " + guessesLeft +
                    "; words left in dictionary = " + wordChoices.size());
        } else if(guesses.contains(guess)){
            throw new IllegalArgumentException(guess + " has already been guessed");
        }
        //build map of patterns to sets of strings
        Map<String, Set<String>> patternChoices = buildWordFamilies(guess);
        //find largest set
        String largestSetPattern = findLargestWordSet(patternChoices);
        //find number of times guess occurs in new pattern
        int correctlyGuessed = lettersCorrectlyGuessed(largestSetPattern, guess);
        //update class variables
        pattern = largestSetPattern;
        wordChoices = patternChoices.get(largestSetPattern);
        guesses.add(guess);
        if(correctlyGuessed < 1){
            guessesLeft--;
        }
        return correctlyGuessed;
    }

    //pre: is passed a new guess
    //post: all possible word patters are mapped to families of words that fit those patters
    //      map is then returned
    private Map<String, Set<String>> buildWordFamilies(char guess) {
        Map<String, Set<String>> patternChoices = new TreeMap<>();
        for (String word : wordChoices) {
            String newPattern = createNewPattern(word, guess);
            //update Map
            if(!patternChoices.containsKey(newPattern)){
                patternChoices.put(newPattern, new TreeSet<>());
            }
            patternChoices.get(newPattern).add(word);
        }
        return patternChoices;
    }


    //pre: passed a map of word patterns to sets of words that match each pattern
    //post: returns the word pattern with the largest set of words that fit the pattern
    private String findLargestWordSet(Map<String, Set<String>> patternChoices){
        String largestSetPattern = "";
        int largestSetSize = 0;
        for(String newPattern: patternChoices.keySet()){
            //update size of largest set
            if(patternChoices.get(newPattern).size() > largestSetSize){
                largestSetSize = patternChoices.get(newPattern).size();
                largestSetPattern = newPattern;
            }
        }
        return largestSetPattern;
    }

    //pre: passed the word pattern and the latest guessed character
    //post: returns the number of times the guessed character appears in the word pattern
    private int lettersCorrectlyGuessed(String largestSetPattern, char guess){
        int correctlyGuessed = 0;
        for(int i = 0; i < largestSetPattern.length(); i++){
            if(largestSetPattern.charAt(i) == guess){
                correctlyGuessed++;
            }
        }
        return correctlyGuessed;
    }

    //pre: is passed a word and the player's current guess
    //post: creates and returns a new word pattern that takes into account the player's guess
    private String createNewPattern(String word, char guess){
        String newPattern = pattern;
        for (int i = 0; i < newPattern.length(); i++) {
            if (word.charAt(i) == guess) {
                newPattern = newPattern.substring(0, i) + guess + newPattern.substring(i + 1);
            }
        }
        return newPattern;
    }
}
