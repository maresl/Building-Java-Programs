/* Written by Leslie Mares on 3-11-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 6 Programming Project and CSE HW:
https://courses.cs.washington.edu/courses/cse142/19wi/handouts/12.html
Write a program that reads a file containing data about the changing popularity
of various baby names over time and displays the data about a particular name.
Each line of the file stores a name followed by integers representing
the name's popularity in each decade: 1880, 1890, 1900, 1910, 1920...
The rankings range from 1(most popular) to 1000(least popular), or 0
for a name less popular than the 1000th name.

for  names.txt set STARTING_YEAR = 1880
                    NUMBER_OF_DECADES = 14
                    WIDTH_PER_DECADE = 70
for names2.txt set STARTING_YEAR = 1920
                    NUMBER_OF_DECADES = 10
                    WIDTH_PER_DECADE = 90

*/


import java.io.*;
import java.util.*;
import java.awt.*;

public class Names {
    //earliest year ranks are available
    public static final int STARTING_YEAR = 1880;
    //number of decades ranking data is available for
    public static final int NUMBER_OF_DECADES = 14;
    //width of each decade graphed
    public static final int WIDTH_PER_DECADE = 70;

    public static void main(String[] args) throws FileNotFoundException{
        intro();
        //prompt user for name and sex
        Scanner console = new Scanner(System.in);
        System.out.print("name? ");
        String name = console.nextLine().trim();
        System.out.print("sex (M or F)? ");
        String sex = console.nextLine().trim();

        //search a name text file for specified name/sex
        Scanner input = new Scanner(new File("names.txt"));
        String line = find(input, name, sex);
        //if name/sex found then graph rankings on DrawingPanel, otherwise inform user
        if(!line.equals("")){
            Scanner ranks = new Scanner(line);
            DrawingPanel panel = new DrawingPanel(WIDTH_PER_DECADE * NUMBER_OF_DECADES,550);
            Graphics g = panel.getGraphics();
            makeGraph(g);
            plot(g, ranks);
        }else{
            System.out.println("name/sex combination not found");
        }
    }

    //prints an introduction to the user's console
    public static void intro(){
        System.out.println("This program allows you to search through the");
        System.out.println("data from the Social Security Administration");
        System.out.println("to see how popular a particular name has been");
        System.out.println("since " + STARTING_YEAR + ".");
        System.out.println();

    }

    //searches for user's specified name and gender in names file
    //returns String with that name's rank information
    //or an empty String if it not found
    public static String find(Scanner input, String name, String sex){
        String line = "";
        while (input.hasNextLine()) {
            line = input.nextLine();
            Scanner token = new Scanner(line);
            String lineName = token.next();
            String lineSex = token.next();
            if(lineName.equalsIgnoreCase(name) && lineSex.equalsIgnoreCase(sex)){
                return line;
            }
        }
        return "";
    }

    //creates a labeled graph based on class constants
    public static void makeGraph(Graphics g){
        int windowWidth = WIDTH_PER_DECADE * NUMBER_OF_DECADES;
        g.drawLine(0, 25, windowWidth, 25);
        g.drawLine(0, 525, windowWidth, 525);

        for(int i = 0; i < NUMBER_OF_DECADES; i ++) {
            int x = i * WIDTH_PER_DECADE;
            String year = String.valueOf(STARTING_YEAR + (10 * i));
            g.drawLine(x, 0, x, 550);
            g.drawString(year, x, 550);
        }

    }

    //plots the rankings for user's name/sex on the graph constructed using DrawingPanel
    public static void plot(Graphics g, Scanner ranks){
        g.setColor(Color.RED);
        String nameAndSex = ranks.next() + " " + ranks.next();
        int popularity = ranks.nextInt();
        int y1 = getY(popularity);
        g.drawString(nameAndSex + " " + popularity, 0, y1);

        for(int i = 1; i < NUMBER_OF_DECADES; i ++){
            int x1 = (i - 1) * WIDTH_PER_DECADE;
            int x2 = i * WIDTH_PER_DECADE;
            popularity = ranks.nextInt();
            int y2 = getY(popularity);

            g.drawString(nameAndSex + " " + popularity, x2, y2);
            g.drawLine(x1, y1, x2, y2);
            y1 = y2;
        }
    }

    //converts rank into a Y coordinate on DrawingPanel
    public static int getY(int rank){
        if (rank == 0) {
            return 525;
        }else{
            return (rank - 1) / 2 + 25;
        }
    }
}
