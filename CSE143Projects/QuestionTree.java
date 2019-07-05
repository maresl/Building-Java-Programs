/* Created by Leslie Mares on 07-03-19 in response to CSE 143 HW 7
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/20.html

QuesitonTree implements a yes/no guessing game that learns from its mistakes.
User thinks of an object and the computer will try to guess the object by asking
a series of yes/no questions and then making a guess. If the computer loses the game,
it will prompt the user for a new question it can use in the next round.
A question tree can be read from a file or built round by round.
Can write the current question tree into a file.
*/
import java.io.*;
import java.util.*;
public class QuestionTree {
    private QuestionNode overallRoot;
    private Scanner console;

    //post: creates a question tree with a single answer: "computer"
    public QuestionTree(){
        overallRoot = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    //pre: is passed a scanner that reads from a file of questions and answers in preorder
    //post: builds the question tree using the questions and answers in the file
    public void read(Scanner input){
        overallRoot = readTreeFile(input);
    }

    //pre: is passed a scanner that reads a file of answers/questions in preorder
    //post: builds question tree using file and returns the QuestionNode
    //      at the top of the tree
    private QuestionNode readTreeFile(Scanner input){
        String type = input.nextLine().trim();
        String data = input.nextLine();
        QuestionNode add = new QuestionNode(data);
        if(type.startsWith("Q")){
            add.left = readTreeFile(input);
            add.right = readTreeFile(input);
        }
        return add;
    }

    //pre: is passed an output stream for writing
    //post: stores the current tree to an output file
    public void write(PrintStream output){
        write(output, overallRoot);
    }

    //pre: is passed an output to print to a file and a QuestionNode
    //post: writes the current question tree into a file in preorder
    private void write(PrintStream output, QuestionNode node){
        //base case: is an answer node
        if(node.right == null && node.left == null){
            output.println("A:\n" + node.data);
        //is a question node
        } else {
            output.println("Q:\n" + node.data);
            write(output, node.left);
            write(output, node.right);
        }
    }

    //post: asks the user a series of yes/no questions until an answer is reached;
    //      if the answer is incorrect then the tree expands to include their
    //      object and a new question
    public void askQuestions(){
        overallRoot = askQuestions(overallRoot);
    }

    private QuestionNode askQuestions(QuestionNode node){
        if(node.right == null && node.left == null){
            node = guess(node);
        } else if(yesTo(node.data)){
            node.left = askQuestions(node.left);
        } else {
            node.right = askQuestions(node.right);
        }
        return node;
    }

    //pre: is passed a QuestionNode
    //post: guesses the user's object; if the guess is wrong, it will prompt
    //      for the correct answer, a new question, and the answer to that question
    //      returns the QuesitonNode(modified with new info if the guess was wrong)
    private QuestionNode guess(QuestionNode node){
        if(!yesTo("Would your object happen to be " + node.data + "?")){
            System.out.print("What is the name of your object? ");
            String answer = console.nextLine().trim();
            System.out.print("Please give me a yes/no question that\n"
                    + "distinguishes between your object\n" + "and mine--> ");
            String question = console.nextLine().trim();
            if(yesTo("And what is the answer for your object?")){
                node = new QuestionNode(question, new QuestionNode(answer),
                        new QuestionNode(node.data));
            } else {
                node = new QuestionNode(question, new QuestionNode(node.data),
                        new QuestionNode(answer));
            }
        } else {
            System.out.println("Great, I got it right!");
        }
        return node;
    }

    //pre: is given a question to ask the user
    //post: asks the given question until the user types "y" or "n"
    //      returns true if "y" is typed, and false if "n"
    public boolean yesTo(String prompt){
        System.out.print(prompt + " (y/n)? ");
        String userAnswer = console.nextLine().toLowerCase().trim();

        while(!userAnswer.equals("y") && !userAnswer.equals("n")){
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            userAnswer = console.nextLine().toLowerCase().trim();
        }
        return userAnswer.equals("y");
    }
}
