/*Written by Leslie Mares on 3-5-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 5 Programming Project 3
Write a program that plays a guessing game with the user.
The program should generate a random number between 1 and some
maximum (such as 100), then prompt the user repeatedly to guess the
user repeatedly to guess the number. When the user guesses incorrectly
the game should give the user a hint about whether the correct answer
is higher or lower than the guess.
*/

import java.util.*;
public class GuessingGame {
    //program will prompt user for a number between 1 and MAX
    public static final int MAX = 100;
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        Random rand = new Random();
        int gamesPlayed = 0;
        int totalGuesses = 0;
        int bestGame = MAX;
        intro();

        do {
            int guesses = play(console, rand);
            //update game stats
            gamesPlayed ++;
            totalGuesses += guesses;
            bestGame = Math.min(bestGame, guesses);
        } while (playAgain(console));
        reportResults(gamesPlayed, totalGuesses, bestGame);
    }

    //prints directions to user on how to play the game
    public static void intro(){
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and");
        System.out.println("100 and will allow you to guess until");
        System.out.println("you get it.  For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower");
        System.out.println("than your guess.");
    }

    //plays one game with the user
    public static int play(Scanner console, Random r){
        //program thinks of a random number
        int number = r.nextInt(MAX) + 1;
        System.out.println();
        System.out.println("I'm thinking of a number between 1 and "
                + MAX + "...");
        // debug System.out.println("Answer = " + number);
        System.out.print("Your guess? ");

        int userAnswer = console.nextInt();
        int guesses = 1;
        //case: user guesses right on first try
        if(userAnswer == number){
            System.out.println("You got it right in 1 guess");
        //case: user makes more than one guess
        } else {
            while(userAnswer != number){
                hint(number, userAnswer);
                guesses ++;
                System.out.print("Your guess? ");
                userAnswer = console.nextInt();
            }
            System.out.println("You got it right in " + guesses + " guesses");
        }
        return guesses;
    }

    //compares the user guess to the program's randomly chosen number
    public static void hint(int n, int guess){
        if (n > guess){
            System.out.println("It's higher.");
        } else {
            System.out.println("It's lower.");
        }
    }

    public static void reportResults(int games, int guesses, int best){
        System.out.println();
        System.out.println("Overall results: ");
        System.out.printf("\t%-14s = %d\n", "total games", games);
        System.out.printf("\t%-14s = %d\n", "total guesses", guesses);
        System.out.printf("\t%-14s = %.1f\n", "guesses/game", (double) guesses/games);
        System.out.printf("\t%-14s = %d\n", "best game", best);
    }

    //asks player whether he/she wants to play again
    //will interpret answer as true as long as user input
    //starts with "y" regardless of capitalization
    public static boolean playAgain(Scanner console){
        System.out.print("Do you want to play again? ");
        return console.next().substring(0,1).equalsIgnoreCase("y");
    }
}
