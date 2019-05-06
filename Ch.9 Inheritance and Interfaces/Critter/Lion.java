/* Created by Leslie Mares on 5-3-19 in response to CSE 142 Hw assignment 9:
https://courses.cs.washington.edu/courses/cse142/15au/handouts/17.html
"You will be given a program that runs a simulation of a world with many animals
wandering around in it.  Different kinds of animals will behave in different ways
and you are defining those differences.  In this world, animals propagate their
species by infecting other animals with their DNA, which transforms the other animal
into the infecting animal’s species..."

This class builds Lions within a simulated world that try to infect other animals into becoming
more Lions. Every three moves their color changes, randomly choosing between red, blue, and green.
They tend to bounce off the walls- when they encounter a wall they will return the direction they
came from.
 */
import java.awt.*;
import java.util.*;
public class Lion extends Critter {
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private int colorIndex;
    private Random random = new Random();
    private int moves;

    //is passed CritterInfo; returns an Action
    //always infects when an enemy is in front
    //turn left when there is a wall in front or to the right
    //turn right when there is there is another Lion in front
    //otherwise hop
    public Action getMove(CritterInfo info) {
        moves++;
        Critter.Neighbor inFront = info.getFront();
        if (inFront == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (inFront == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (inFront == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    //returns a Color
    //every three moves the color of the critter changes; it randomly
    //chooses between red, green, and blue
    public Color getColor() {
        if (moves % 3 == 0) {
            colorIndex = random.nextInt(3);
        }
        return colors[colorIndex];
    }

    //returns a String
    public String toString() {
        return "L";
    }
}