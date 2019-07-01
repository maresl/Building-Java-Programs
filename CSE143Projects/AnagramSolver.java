/* Created by Leslie Mares on 06-27-19 in response to CSE 143 HW 6
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/19.html

AnagramSolver uses a dictionary to find all combinations of words that
have the same letters as a given phrase. 
 */

import java.util.*;
public class AnagramSolver {
    private Map<String, LetterInventory> dictionary;
    private List<String> orderedList;

    //pre: is passed a list of words is not empty and does not contain duplicates
    //post: constructs an anagram solver and saves a dictionary of words
    public AnagramSolver(List<String> list){
        dictionary = new HashMap<>();
        orderedList = new ArrayList<>();
        for(String word: list){
            dictionary.put(word, new LetterInventory(word));
            orderedList.add(word);
        }
    }

    //pre: is passed a word and the maximum number of words that the anagram can contain
    //      or unlimited words if 0 is passed
    //post: finds combinations of words that have the same letters as the passed word
    public void print(String s, int max){
        if (max < 0) {
            throw new IllegalArgumentException("max cannot be less than zero. Max = " + max);
        }
        LetterInventory targetWordInventory = new LetterInventory(s);
        Map<String, LetterInventory> prunedDictionary = new HashMap<>();
        List<String> prunedList = new ArrayList<>();
        for(String word: orderedList){
            if(targetWordInventory.subtract(dictionary.get(word)) != null){
                prunedDictionary.put(word, dictionary.get(word));
                prunedList.add(word);
            }
        }
        explore(targetWordInventory, prunedDictionary, prunedList, new ArrayList<String>(), max);
    }

    //pre: is passed a letter inventory of the target word, a shortened dictionary of words,
    //     a list of chosen anagram words, and a max number of words the anagram can contain
    //post: finds words with the same letters in the target word
    private void explore(LetterInventory targetWordInventory, Map<String,
            LetterInventory> prunedDictionary, List<String> prunedList, List<String> chosenWords, int max){
        //base case: no letters left in the target word inventory
        if (targetWordInventory.isEmpty()) {
            System.out.println(chosenWords);
        //max anagram words has not been reached
        } else if(chosenWords.size() < max || max == 0){
            for (String word : prunedList) {
                LetterInventory subtractWordInventory = targetWordInventory.subtract(prunedDictionary.get(word));
                if (subtractWordInventory != null) {
                    chosenWords.add(word);
                    explore(subtractWordInventory, prunedDictionary, prunedList, chosenWords, max);
                    chosenWords.remove(chosenWords.size() - 1);
                }
            }
        }
    }
}
