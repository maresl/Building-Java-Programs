/* Written by Leslie Mares on 3-12-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 6 Programming Project and CSE 142 HW:
https://courses.cs.washington.edu/courses/cse142/18sp/homework/6/assign6.pdf
Write a program that plays a game where the player is asked to fill in various words
of a mostly complete story without being able to see the rest. Then the user us shown
his/her story, which is often funny. The input for your program is a set of story files,
each of which contains "placeholder" tokens surrounded by < and >.
*/

import java.io.*;
import java.util.*;
public class MadLibs {
    public static void main(String[] args) throws FileNotFoundException{
        intro();
        Scanner console = new Scanner(System.in);
        String userChoice = "";
        //prints menu of options for the user
        do{
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            userChoice = console.nextLine();
            //user chooses to create a new mad lid
            if(userChoice.substring(0, 1).equalsIgnoreCase("C")){
                createLid(promptForFile(console), console, createOutputFile(console));
            //user chooses to read a mad lid
            } else if(userChoice.substring(0, 1).equalsIgnoreCase("V")) {
                viewLid(promptForFile(console));
            }
        //user quits program
        } while (!userChoice.substring(0, 1).equalsIgnoreCase("Q"));
    }

    //prints a small introduction for user about  program
    public static void intro(){
        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();
    }


    public static void createLid(Scanner input, Scanner console, PrintStream output) {
        System.out.println();
        while(input.hasNextLine()){
            Scanner tokens = new Scanner(input.nextLine());
            //each word in the line is processed
            while (tokens.hasNext()){
                String word = tokens.next();
                //case: word is a placeholder; prompts user for a replacement
                if(word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>'){
                    word = word.toLowerCase().replace('-', ' ');
                    output.print(promptForWord(console, word));
                //word is not a placeholder
                }else {
                    output.print(word + " ");
                }
            }
            output.println();
        }
        System.out.println("Your mad-lib has been created!");
        System.out.println();
    }

    //prints contents of file onto the console
    public static void viewLid(Scanner input){
        System.out.println();
        while (input.hasNextLine()){
            System.out.println(input.nextLine());
        }
        System.out.println();
    }

    //prompts user for a file and checks whether it exists or not; returns a Scanner with file
    public static Scanner promptForFile(Scanner console) throws FileNotFoundException{
        System.out.print("Input file name: ");
        File f = new File(console.nextLine());
        while(!f.canRead()){
            System.out.print("File not found. Try again: ");
            f = new File(console.nextLine());
        }
        return new Scanner(f);
    }

    //prompts user for an ouput file name and returns a PrintStream
    public static PrintStream createOutputFile(Scanner console) throws FileNotFoundException{
        System.out.print("Output file name: ");
        return new PrintStream(new File(console.nextLine()));
    }

    //prompts use for a word/phrase to replace placeholder; returns user's word
    public static String promptForWord(Scanner console, String word){
        System.out.print("Please type ");
        //print "an" if first word of placeholder starts with a vowel
        if(isVowel(word.charAt(1))){
            System.out.print("an ");
        } else {
            System.out.print("a ");
        }
        System.out.print(word.substring(1, word.length() - 1) +  ": ");
        return console.nextLine() + " ";
    }

    //checks if letter is a vowel
    public static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
