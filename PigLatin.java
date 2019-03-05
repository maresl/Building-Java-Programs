/*
Written by Leslie Mares on 3-4-18 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 5 Programming Project 1
Write an interactive program that reads lines of input from the user
and converts each line into "Pig Latin." Pig Latin is English with the
initial consonant sound moved to the end of each word, followed by "ay."
For example the phrase
"The deepest shade of mushroom blue"
would have the following appearance in Pig Latin:
"e-Thay eepest-day ade-shay of-ay ushroom-may ue-blay"
Terminates the program when the user types a blank line.

 */

import java.util.*;
public class PigLatin {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);

        //first prompt and user input
        prompt();
        String line = console.nextLine();
        //program ends when user inputs an empty line
        while (!line.equals("")){
            translate(line);
            prompt();
            line = console.nextLine();
        }
    }

    //prompts user for a line of input
    public static void prompt(){
        System.out.println("Type in a word or phrase and this program");
        System.out.println("will translate it into Pig Latin");
        System.out.println();
    }

    public static void translate(String line){
        String initialConsonant = "";
        boolean firstVowelFound = false;

        for(int i = 0; i < line.length(); i++){
            char index = line.charAt(i);
            //first case: index at i is a space that separates words
            if(index == ' ' && i != 0 && line.charAt(i-1) != ' ') {
                System.out.print("-" + initialConsonant + "ay ");
                initialConsonant = "";
                firstVowelFound = false;
            //second case: index is a consonant/vowel after first vowel
            } else if(firstVowelFound){
                System.out.print(index);
            } else {
                //third case: first vowel sound found
                firstVowelFound = isVowel(index);
                if(firstVowelFound){
                    System.out.print(index);
                //fourth case:index at i is a consonant before first vowel
                } else if(index != ' '){
                    initialConsonant += index;
                }
            }

            //fifth case: last index in the string is a letter, not a space
            if(i == line.length() - 1 && index != ' '){
                System.out.print("-" + initialConsonant + "ay ");
            }
        }

        System.out.println();
        System.out.println();
    }

    //tests whether character passed is a vowel (y is considered a vowel)
    public static boolean isVowel(char c1){
        char c2 = Character.toLowerCase(c1);
        return c2 == 'a' || c2 == 'e' || c2 == 'i' ||
                c2 =='o' || c2 == 'u'|| c2 == 'y';
    }
}
