/* Created by Leslie Mares on 06-12-19 in response to CSE 143 HW 3
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/05.html

AssassinManager is a class that allows a client to manage a game of Assassin.
It can keep track of a kill ring and graveyard of players, allows the client to
"kill" players, and can give information about the state of the game and players
(like whether a player is in the kill ring or graveyard, who the winner is,
and whether the game is over).
 */

import java.util.*;
public class AssassinManager {

    //reference to the first player in the kill ring
    //each player is "stalking" the next person in the list;
    //last person is "stalking" the first person in the list
    private AssassinNode frontKillRing;
    //reference to players who lost; listed in order of most recent kill
    private AssassinNode frontGraveyard;

    //pre: a list of names is passed as a parameter
    //      throws an IllegalArgumentException if the list is empty
    //post: initializes a game by storing a list of players in the same order
    //      as the passed list
    public AssassinManager(List<String> names){
        if(names.isEmpty()){
            throw new IllegalArgumentException("list of names is empty");
        }
        frontKillRing = new AssassinNode(names.get(0));
        AssassinNode current = frontKillRing;
        for(int i = 1; i < names.size(); i++){
            current.next = new AssassinNode(names.get(i));
            current = current.next;
        }
    }

    //post: prints the current kill ring in the format: “<name> is stalking <name>”
    public void printKillRing(){
        AssassinNode current = frontKillRing;
        while(current.next != null){
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
        System.out.println("    " + current.name + " is stalking " + frontKillRing.name);
    }

    //post: prints the current graveyard of players who lost in the format:
    //      “<name> was killed by <name>”
    public void printGraveyard(){
        AssassinNode current = frontGraveyard;
        while(current != null){
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    //pre: a player's name is passed as a parameter
    //post: returns true if the player is currently a part of the kill ring
    //      otherwise returns false
    public boolean killRingContains(String name){
        return listContains(frontKillRing, name);
    }

    //pre: a player's name is passed as a parameter
    //post: returns true if the player is currently a part of the kill ring
    //      otherwise returns false
    public boolean graveyardContains(String name){
        return listContains(frontGraveyard, name);
    }

    //pre: is passed a reference to the front of a list of players
    //     and a player's name as parameters
    //post: returns true if the player is a part of the passed list
    //      otherwise returns false
    private boolean listContains(AssassinNode current, String name){
        while(current != null){
            if(current.name.equalsIgnoreCase(name)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //post: returns true if there is one player left in the kill ring
    //      otherwise returns false
    public boolean gameOver(){
        return frontKillRing.next == null;
    }

    //post: returns the name of the player who won the game
    //      or null if the game is not over
    public String winner(){
        if(gameOver()){
            return frontKillRing.name;
        } else {
            return null;
        }
    }

    //pre: a player's name is passed as a parameter
    //post: player is removed from the kill ring and added to the begining of the graveyard
    //      throws an IllegalArgumentException if the player does not exist
    //      throws an IllegalStateException if the game is already over
    public void kill(String name){
        if(!killRingContains(name)){
            throw new IllegalArgumentException(name + " is not a part of the kill ring");
        } else if(gameOver()){
            throw new IllegalStateException("game is over");
        }
        //case 1: player "killed" is the first one in the kill ring
        if(frontKillRing.name.equalsIgnoreCase(name)){
            AssassinNode lastNode = frontKillRing;
            while(lastNode.next != null){
                lastNode = lastNode.next;
            }
            moveToGraveyard(lastNode, frontKillRing);
        //case 2: player "killed" is not the first in the list
        } else {
            AssassinNode killer = frontKillRing;
            while(!killer.next.name.equalsIgnoreCase(name)){
                killer = killer.next;
            }
            moveToGraveyard(killer, killer.next);
        }
    }

    //pre: is passed two players' nodes; first one is the "killer" and second is the "deceased"
    //post: the "deceased" records who his/her "killer" was
    //      "deceased" is removed from the kill ring and added to the graveyard
    private void moveToGraveyard(AssassinNode killer, AssassinNode deceased){
        deceased.killer = killer.name;
        if(deceased == frontKillRing){
            frontKillRing = frontKillRing.next;
        } else {
            killer.next = deceased.next;
        }
        deceased.next = frontGraveyard;
        frontGraveyard = deceased;

    }
}
