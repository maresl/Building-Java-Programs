/* Written by Leslie Mares on 5-6-19 in response to CSE 143 hw
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/02.html

Is initialized with a string as a parameter and builds an inventory of the letter
counts in that string. User can get the count of a particular letter, set the count
of any letter, find out the overall size of the inventory, or know if the inventory
is empty. Can also add or subtract two inventories.
*/
public class LetterInventory {
    private static final int ALPHABET_SIZE = 26;
    private int[] alphabetCounts;
    private int size;

    //Constructor takes a String as a parameter; builds an array with the
    //number of times each letter is present in the string
    public LetterInventory(String data){
        alphabetCounts = new int[ALPHABET_SIZE];
        for(int i = 0; i < data.length(); i++){
            char letter = data.charAt(i);
            if(Character.isLetter(letter)){
                //index is the integer displacement between the letter and 'a'
                alphabetCounts[convertToIndex(letter)]++;
                size++;
            }
        }
    }

    //returns the total sum of the counts in the inventory
    public int size(){
        return size;
    }

    //returns boolean value; whether the inventory has a size of 0
    public boolean isEmpty(){
        return size == 0;
    }

    //accepts a character as a parameter; returns count of that character
    //throws an IllegalArgumentException if a non-letter character is passed
    public int get(char letter){
        if(!Character.isLetter(letter)){
            throw new IllegalArgumentException( letter + " is not a letter!");
        }
        int index = convertToIndex(letter);
        return alphabetCounts[index];
    }

    //returns a string representation of the inventory
    public String toString(){
        String inventory = "[";
        for(int i = 0; i < ALPHABET_SIZE; i++){
            int count = alphabetCounts[i];
            char letter = convertToLetter(i);
            for(int j = 0; j < count; j++){
                inventory += letter;
            }
        }
        return inventory += "]";
    }

    //accepts a character and an integer as parameters
    //sets the count for the given letter to the given value
    //throws an IllegalArgumentException if a non-letter is passed
    public void set(char letter, int value){
        int difference = value - get(letter);
        int index = convertToIndex(letter);
        size += difference;
        alphabetCounts[index] = value;
    }

    //accepts a LetterInventory as a parameter; returns a new LetterInventory
    //returns a new inventory that represents the sum of every count in
    // this inventory and the other inventory
    public LetterInventory add(LetterInventory other){
        LetterInventory sum = new LetterInventory("");
        for(int i = 0; i < ALPHABET_SIZE; i++){
            char letter = convertToLetter(i);
            int addition = this.alphabetCounts[i] + other.alphabetCounts[i];
            sum.set(letter, addition);
        }
        return sum;
    }

    //accepts a LetterInventory as a parameter; returns a new LetterInventory
    //returns null if any reslting difference is negative
    //returns a new inventory that represents the subtraction of every count in
    //this inventory and the other inventory
    public LetterInventory subtract(LetterInventory other){
        LetterInventory subtract = new LetterInventory("");
        for(int i = 0; i < ALPHABET_SIZE; i++){
            char letter = convertToLetter(i);
            int difference = this.alphabetCounts[i] - other.alphabetCounts[i];
            if(difference < 0){
                return null;
            }
            subtract.set(letter, difference);
        }
        return subtract;
    }

    //accepts an integer and returns a character
    //takes an index and returns its corresponding letter
    private char convertToLetter(int index){
        return (char) (index + 'a');
    }

    //accepts a character and returns an integer
    //takes an letter and returns its corresponding index in the array
    private int convertToIndex(char letter){
        letter = Character.toLowerCase(letter);
        return letter - 'a';
    }

}
