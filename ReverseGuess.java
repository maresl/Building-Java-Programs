/* Written by Leslie Mares on 3-6-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 5 Programming Project 4
Write a program that plays a reverse guessing game with the user.
The user thinks of a number between 1 and 10, and the computer
repeatedly tries to guess it by guessing random numbers. It's
fine for the computer to guess the same random number more than
once. At the end of the game, the program reports how many guesses
it made.
For an additional challenge, consider having the user hint
to the computer whether the correct number is higher or
lower than than the computer's guess. The computer should
adjust its range of random guesses based on the hint.

 */

import java.util.*;
public class ReverseGuess {
    //samllest number computer can guess
    public static int SHIFT = 1;
    //the size of the range of possible guesses
    public static int RANGE_POSSIBLE_GUESSES = 10;

    public static void main(String[] args){
        Random rand = new Random();
        Scanner console = new Scanner(System.in);
        intro();
        //plays one game with user and prints results of game
        int computerGuess = rand.nextInt(RANGE_POSSIBLE_GUESSES) + SHIFT;
        System.out.print("Is it " + computerGuess + "? (y/n) ");
        String userAnswer = console.nextLine();
        //case: computer guesses correctly in first try
        if(correctGuess(userAnswer)){
            System.out.println("I got your number of " + computerGuess + " correct in my first guess!");
            System.out.println("Yay me!");
        //case: computer makes more than one guess
        } else {
            int guessesMade = 1;
            //game continues until computer guess correctly or there is only one possible answer
            do{
                hint(computerGuess, console);
                computerGuess = rand.nextInt(RANGE_POSSIBLE_GUESSES) + SHIFT;
                if(RANGE_POSSIBLE_GUESSES == 1) {
                    System.out.println("Your number can only be " + computerGuess);
                } else {
                    System.out.print("Is it " + computerGuess + "? (y/n) ");
                    userAnswer = console.nextLine();
                    guessesMade ++;
                }
            } while (RANGE_POSSIBLE_GUESSES != 1 && !correctGuess(userAnswer));
            //report results of game
            System.out.println("I got your number of " + computerGuess +
                    " correct in " + guessesMade + " tries. Hurray!");
        }
    }

    //prints directions/rules for the guesssing game
    public static void intro(){
        System.out.println("This program has you, the user, choose a number");
        System.out.println("between 1 and 10. Then I, the computer, will try ");
        System.out.println("my best to guess it.");
    }

    //checks whether computer guessed correctly
    public static boolean correctGuess(String a){
        return a.substring(0,1).equalsIgnoreCase("y");
    }

    //reads user's hint and adjusts MAX and MIN limits of range accordingly
    public static void hint(int guess, Scanner console){
        //program adjusts range of guesses without asking user if higher/lower can be inferred
        //case one: computer can infer the number is higher than its last guess
        if(guess == SHIFT){
            SHIFT++;
            RANGE_POSSIBLE_GUESSES--;
        //case two: computer can infer the number is lower than its last guess
        } else if (guess == SHIFT + RANGE_POSSIBLE_GUESSES - 1){
            RANGE_POSSIBLE_GUESSES = guess - SHIFT;
        //case three: higher/lower cannot be inferred, user is asked for a hint
        } else {
            System.out.print("Is your number higher or lower than my guess? (l/h) ");
            String h = console.nextLine().substring(0, 1);
            int difference = guess - SHIFT;
            //number user is thinking of is lower than the computer's guess
            if (h.equalsIgnoreCase("l")) {
                RANGE_POSSIBLE_GUESSES = difference;
                //number user is thinking of is higher than the computer's guess
            } else if (h.equalsIgnoreCase("h")) {
                SHIFT += (difference + 1);
                RANGE_POSSIBLE_GUESSES -= (difference + 1);
            }
        }
    }
}

