/* Created by Leslie Mares on 5-3-19 in response to CSE 142 Hw assignment 9:
https://courses.cs.washington.edu/courses/cse142/15au/handouts/17.html
"You will be given a program that runs a simulation of a world with many animals
wandering around in it.  Different kinds of animals will behave in different ways
and you are defining those differences.  In this world, animals propagate their
species by infecting other animals with their DNA, which transforms the other animal
into the infecting animal’s species..."

This class builds Bears within a simulated world that try to infect other animals to
transform them into more Bears. Each Bears is passed a boolean value that
determine whether that Bear is displayed as either black or white. Display will
alternate between a slash and a backslash. They tend to follow the "walls" of
the world in a counterclockwise direction.
*/
import java.awt.*;
public class Bear extends Critter{
    private boolean polar;
    private int moves;

    //constructs a new Bear Critter; is passed a boolean parameter which
    //determines whether the Bear is black or white
    //initalizes moves to zero
    public Bear(boolean polar){
        this.polar = polar;
    }

    //is passed a CritterInfo parameter and returns an Action
    //always infects when an enemy is in front, otherwise hop forward if possible,
    //otherwise turn left
    public Action getMove(CritterInfo info){
        moves++;
        Critter.Neighbor inFront = info.getFront();
        if(inFront == Neighbor.OTHER){
            return Action.INFECT;
        } else if (inFront == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }

    //returns a Color
    //if polar boolean variable is true then the bear returns white,
    //otherwise returns black
    public Color getColor(){
        if(polar){
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    //returns a string
    //alternates between / and \
    public String toString(){
        if(moves % 2 == 0){
            return "/";
        } else {
            return "\\";
        }
    }
}
