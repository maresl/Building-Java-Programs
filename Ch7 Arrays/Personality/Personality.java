/* Written by Leslie Mares on 3-19-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 7 Programming Project and CSE HW:
https://courses.cs.washington.edu/courses/cse142/19wi/handouts/15.html
Write a program to score users' responses to the classic Myers-Briggs
personality test. Assume that the test has 70 questions that determine
a person's personality in four dimensions. Each question has two answers
A or B, Questions are organized into 10 groups of seven questions, with
the following repeating pattern in each group:

- The first question in each group (1, 8, 15...) tells whether the person
is introverted or extroverted (I/E)
-The next two questions (2 and 3, 9 and 10, 16 and 17...) test whether
the person is guided by his/her senses or intuition (S/N)
-The next two questions (4 and 5, 11 and 12, 18 and 19...) test whether
the person focuses on thinking or feeling (T/F)
-The final two questions (6 and 7, 13 and 14, 20 and 21...) test whether
the person prefers to judge or be guided by perception (J/P)
*/
import java.util.*;
import java.io.*;
public class Personality {
    public static final int DIMENSIONS_OF_PERSONALITY = 4;
    public static final int PATTERN_LENGTH = 7;
    public static final int CYCLES = 10;
    public static void main(String[] args) throws FileNotFoundException {
        intro();
        Scanner console = new Scanner(System.in);

        System.out.print("input file name? ");
        Scanner input = new Scanner(new File(console.nextLine()));

        System.out.print("output file name? ");
        PrintStream output = new PrintStream(new File(console.nextLine()));

        while(input.hasNextLine()){
            String name = input.nextLine();
            String answers = input.nextLine().toUpperCase();

            int[] aCounts = new int[DIMENSIONS_OF_PERSONALITY];
            int[] bCounts = new int[DIMENSIONS_OF_PERSONALITY];
            makeCounts(aCounts, bCounts, answers, 0, 1, 0);
            makeCounts(aCounts, bCounts, answers, 1, 2, 1);
            makeCounts(aCounts, bCounts, answers, 3, 2, 2);
            makeCounts(aCounts, bCounts, answers, 5, 2, 3);

            int[] bPercentages = makePercentages(aCounts, bCounts);
            String personalityType = determinePersonality(bPercentages);
            printAll(name, bPercentages, personalityType, output);
        }

    }

    //prints a small introduction for the user
    public static void intro(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter.  It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    //updates counts of A/B by searching the String of answers according to the pattern
    //and cycles of questions for each category
    public static void makeCounts(int[] a, int[] b, String answers, int start, int consecutive,
                                                                                int index){
        for(int i = start; i < PATTERN_LENGTH * CYCLES; i += PATTERN_LENGTH){
            for(int j = i; j < i + consecutive; j++){
                char c = answers.charAt(j);
                if(c == 'A'){
                    a[index]++;
                } else if (c == 'B') {
                    b[index]++;
                }
            }
        }
    }

    //creates and retuurns a new array that contains the percentage of 'B' answers for each
    // personality category
    public static int[] makePercentages(int[] a, int[] b){
        int[] bPercentages = new int[DIMENSIONS_OF_PERSONALITY];
        for(int i = 0; i < DIMENSIONS_OF_PERSONALITY; i++){
            int total = a[i] + b[i];
            double percent = (double) b[i] / total * 100;
            bPercentages[i] = (int) Math.round(percent);
        }
        return bPercentages;
    }

    //creates and returns a String of the personality type determined by the percentage
    // of A and B answers
    public static String determinePersonality(int[] percentages){
        String[] higherA = {"E", "S", "T", "J"};
        String[] higherB = {"I", "N", "F", "P"};
        String personality = "";
        for(int i = 0; i < DIMENSIONS_OF_PERSONALITY; i++){
            if(percentages[i] > 50){
                personality += higherB[i];
            } else if (percentages[i] < 50){
                personality += higherA[i];
            } else {
                personality += "X";
            }
        }
        return personality;
    }

    //prints the person's name, percentage of B answers for each category,
    //and personality type to the output file
    public static void printAll(String name, int[] percentages, String personality,
                                                                PrintStream output){
        output.print(name + ": ");
        output.print(Arrays.toString(percentages) + " = ");
        output.println(personality);
    }
}
