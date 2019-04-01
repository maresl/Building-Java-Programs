/* Written by Leslie Mares on 3-31-19 in response to UW Bothel programming
self-assessment prompt:
https://www.uwb.edu/getattachment/stem/graduate/gcsdd/self-assessment/CSS142-SelfAssessment.pdf
You work at a soft drink distributorship that sells at most
100 different kinds of soft drinks. The program you write for this assignment
will process weekly transactions and allow for a report to be displayed that
includes the softdrink name, ID, starting inventory, final inventory, and the
number of transactions received.

The file data6trans.txt should hold the transactions. Each transaction should
consist of the ID followed by the number of cases purchased (positive integer),
or the amount sold (negative integer). You can assume the format of the data is
correct, but not all IDs are valid. In the case of an invalid ID, do not process
the data (ignore it, no error message).

The TransactionsFileCreatorClass prompts user for transactions and creates a
formatted file of this data
*/
import java.util.*;
import java.io.*;
public class TransactionsFileCreator {
    public static void main(String[] args) throws FileNotFoundException{
        intro();
        Scanner console = new Scanner(System.in);
        System.out.print("Output file name? ");
        PrintStream output = new PrintStream(new File(console.next()));
        System.out.println();
        generateTransactionFile(console, output);
    }

    //prints a short intro for the user
    public static void intro(){
        System.out.println("I will prompt you for information about soft drink transactions");
        System.out.println("in the last week- any purchases or sales.");
        System.out.println("I will then generate a file with formatted transaction data.");
        System.out.println("When you are finished, simply type in 'Q'");
        System.out.println();
    }

    //prompts user for information and creates a file with formatted transactions
    public static void generateTransactionFile(Scanner console, PrintStream output){
        System.out.print("Drink ID(4 characters or less)? ");
        String drinkID = console.next();
        int dxCases = 0;

        while(!drinkID.substring(0, 1).equalsIgnoreCase("Q")){
            System.out.print("Cases purchased (positive) or sold (negative): ");
            dxCases = console.nextInt();
            output.printf("%-10s%-10d%n", drinkID, dxCases);
            System.out.println();
            System.out.print("Drink ID(less than 4)? ");
            drinkID = console.next();
        }

    }
}
