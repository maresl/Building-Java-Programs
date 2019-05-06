/* Created by Leslie Mares on 5-3-19 in response to CSE 142 Hw assignment 9:
https://courses.cs.washington.edu/courses/cse142/15au/handouts/17.html
"You will be given a program that runs a simulation of a world with many animals
wandering around in it.  Different kinds of animals will behave in different ways
and you are defining those differences.  In this world, animals propagate their
species by infecting other animals with their DNA, which transforms the other animal
into the infecting animal’s species..."

This class builds Giants within a simulated world that tries to infect other
animals and turn them into more Giants. They display a "fee" "foe" and
"fum" pattern that repeats. They will tend to follow the "walls" of the world
in a clockwise direction.
 */
import java.awt.*;
public class Giant extends Critter {
    private int moves;
    private String[] giantSayings = {"fee", "foe", "fum"};
    private int sayingsIndex = -1;

    //is passed CritterInfo and returns an Action
    //always infects when an enemy is in front, otherwise hop forward if possible,
    //otherwise turn right
    public Action getMove(CritterInfo info) {
        moves++;
        Critter.Neighbor inFront = info.getFront();
        if (inFront == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (inFront == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    //returns the Color gray
    public Color getColor() {
        return Color.GRAY;
    }

    //returns "fee" for 6 moves, then "foe" for 6 moves, and then
    //"fum" for another 6 moves, and repeat
    public String toString(){
        if(moves % 6 == 0){
            sayingsIndex++;
            if(sayingsIndex == 3){
                sayingsIndex = 0;
            }
        }
        return giantSayings[sayingsIndex];
    }


}
