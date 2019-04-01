/* Written by Leslie Mares on 3-31-19 in response to UW Bothel programming
self-assessment prompt:
https://www.uwb.edu/getattachment/stem/graduate/gcsdd/self-assessment/CSS142-SelfAssessment.pdf
You work at a soft drink distributorship that sells at most
100 different kinds of soft drinks. The program you write for this assignment
will process weekly transactions and allow for a report to be displayed that
includes the softdrink name, ID, starting inventory, final inventory, and the
number of transactions received.

You should write two data files, data6.txt and data6trans.txt, which should
hold the initial soft drink information and transactions, respectively. The
file data6.txt should consist of at most 100 lines where each line contains
the soft drink name (one string), ID (string), and the starting inventory of
cases (int).

The DataFileCreator class will prompt the user for information and create data6.txt files.
 */

import java.io.*;
import java.util.*;
public class DataFileCreator {
    public static void main(String[] args) throws FileNotFoundException {
        intro();
        Scanner console = new Scanner(System.in);
        System.out.print("Output file name? ");
        PrintStream output = new PrintStream(new File(console.next()));
        System.out.println();
        generateFile(console, output);
    }

    //prints intro for the user
    public static void intro(){
        System.out.println("I will prompt you for information about the types of soft drinks sold,");
        System.out.println("ID, and the number in inventory. I will then generate a report with all");
        System.out.println("this data in a formatted grid. Maximum:100 kinds of drinks");
        System.out.println("After you finish adding drink information, simply type in 'Q'");
        System.out.println();
    }

    //prompts user for data and creates a file with formatted data
    public static void generateFile(Scanner console, PrintStream output){
        //fencepost
        System.out.print("Drink name(one word)? ");
        String drinkName = console.next();
        String ID = "";
        int inventory = 0;

        while(!drinkName.substring(0,1).equalsIgnoreCase("Q")){
            System.out.print("ID(4 characters or less)? ");
            ID = console.next();
            System.out.print("Starting inventory? ");
            inventory = console.nextInt();
            output.printf("%-20s%-10s%-10d%n", drinkName, ID, inventory);
            System.out.println();
            System.out.print("Drink name(one word)? ");
            drinkName = console.next();
        }
    }

}
