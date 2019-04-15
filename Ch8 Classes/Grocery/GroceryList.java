/* Written by Leslie Mares on 3-28-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 8 Programming Project
Write a class named GroceryList that represents a list of items to buy from the market
and another class GroceryItemOrder that represents a request to purchase a particular
item in a given quantity (example: four boxes of cookies). The GroceryList class should
use an array field to store the grocery items and to keep track of its size
(number of items in the list so far). Assume the grocery list will have no more than
10 items.

A GroceryList object should have the following methods:

public GroceryList()
public void add(GroceryItemOrderList)
public double getTotalCost()
*/
import java.util.Arrays;
public class GroceryList {
    private GroceryItemOrder[] list; //can only hold up to 10 items
    private int size;                //tracks size of list

    //Constructs a new empty grocery list
    public GroceryList(){
        list = new GroceryItemOrder[10];
        size = 0;
    }

    //Adds the given item order to this list if the list has fewer than 10 items
    public void add(GroceryItemOrder item){
        if(size == 10){
            throw new IllegalArgumentException("This list is already full!");
        }
        list[size] = item;
        size++;
    }

    //Returns the total sum of all grocery item orders on the list
    public double getTotalCost(){
        double sum = 0.0;
        for(int i = 0; i < size; i++){
            sum += list[i].getCost();
        }
        return sum;
    }

    //prints array of items in the grocery list
    public String toString(){
        return Arrays.toString(list);
    }

}
