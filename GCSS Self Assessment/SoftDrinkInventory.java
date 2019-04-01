/* Written by Leslie Mares on 3-31-19 in response to UW Bothel programming
self-assessment prompt:
https://www.uwb.edu/getattachment/stem/graduate/gcsdd/self-assessment/CSS142-SelfAssessment.pdf
You work at a soft drink distributorship that sells at most
100 different kinds of soft drinks. The program you write for this assignment
will process weekly transactions and allow for a report to be displayed that
includes the softdrink name, ID, starting inventory, final inventory, and the
number of transactions received.

Write a SoftDrinkInventory class with the following functionality:
public constructor
public buildInventory
public processTransactions
public displayReport
private findID
private initializeInt
private initializeString
*/
import java.util.*;
public class SoftDrinkInventory {
    private String[] softDrink;
    private String[] drinkID;
    private int[] startingInventory;
    private int[] finalInventory;
    private int[] transactions;
    private int size;

    //Initializes arrays holding soft drink name and ID to hold all empty strings
    //Initializes arrays holding starting inventory, final inventory, and the counts
    //of the number of transaction to zero.
    public SoftDrinkInventory(){
        softDrink = initializeString();
        drinkID = initializeString();
        startingInventory = initializeInt();
        finalInventory = initializeInt();
        transactions = initializeInt();
    }

    //Sets the arrays for soft drink name, ID, and starting inventory from
    //information in the data file. The array holding final inventory is set
    //to the same values as the starting inventory.
    public void buildInventory(Scanner inventoryFile){
        int i = 0;
        while(inventoryFile.hasNextLine()){
            Scanner token = new Scanner(inventoryFile.nextLine());
            softDrink[i] = token.next();
            drinkID[i] = token.next();
            finalInventory[i] = startingInventory[i] = token.nextInt();
            i++;
            size++;
        }
    }

    //Processes the transactions by correctly adjusting the final inventory and
    //transaction counts arrays. Data for IDs which don't exist are not processed.
    public void processTransactions(Scanner transFile){
        while(transFile.hasNextLine()){
            Scanner token = new Scanner(transFile.nextLine());
            int index = findID(token.next());
            if(index != -1){
                finalInventory[index] += token.nextInt();
                transactions[index]++;
            }
        }
    }

    //Displays a report including soft drink name, ID, starting inventory, final
    //inventory, and number of transactions processed.
    public void displayReport(){
        System.out.printf("%-15s%-10s%-22s%-22s%-20s%n", "Soft drink", "ID", "Starting Inventory",
                "Final Inventory", "# transactions");
        for(int i = 0; i < size; i++){
            System.out.printf("%-15s%-5s%15d%20d%22d%n", softDrink[i], drinkID[i],
                    startingInventory[i], finalInventory[i], transactions[i]);
        }
    }


    //Takes an ID parameter and returns the position in the array (the subscript)
    //where the soft drink with that ID is found. Return -1 if the ID is not found.
    private int findID(String ID){
        for(int i = 0; i < size; i++){
            if(drinkID[i].equals(ID)){
                return i;
            }
        }
        return -1;
    }

    //Takes a String array parameter and initializes all values to the empty String (“”)
    private String[] initializeString(){
        String[] array = new String[100];
        Arrays.fill(array, "");
        return array;
    }

    //Takes an int array parameter and initializes all array values to zero.
    private int[] initializeInt(){
        int[] array = new int[100];
        Arrays.fill(array, 0);
        return array;
    }
}
