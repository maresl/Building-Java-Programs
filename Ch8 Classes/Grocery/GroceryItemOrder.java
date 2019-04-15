/* Written by Leslie Mares on 3-28-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 8 Programming Project
Write a class named GroceryList that represents a list of items to buy from the market
and another class GroceryItemOrder that represents a request to purchase a particular
item in a given quantity (example: four boxes of cookies). The GroceryList class should
use an array field to store the grocery items and to keep track of its size
(number of items in the list so far). Assume the grocery list will have no more than
10 items.

A GroceryItemOrder object should have the following methods:

public GroceryItemOrder(String name, int quantity, double cost)
public double getCost()
public void setQuantity(int quantity)
*/
public class GroceryItemOrder {
    private String name;
    private int quantity;
    private double pricePerUnit;

    //constructs and item to purchase with the given name, quantity, and price per unit
    public GroceryItemOrder(String name, int quantity, double pricePerUnit){
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    //Returns the total cost of the this item in its given quantity
    public double getCost(){
        return pricePerUnit * quantity;
    }

    //sets this grocery item's quantity to the given value
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //prints name of item, its quantity, and cost per unit
    public String toString(){
        return name + "(quantity: " + quantity + ", price per unit: " + pricePerUnit + ")";
    }
}
