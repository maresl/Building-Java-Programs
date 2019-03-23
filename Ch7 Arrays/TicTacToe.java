/* Written by Leslie Mares on 3-20-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 7 Programming Project
Use a two dimensional array to write a game of Tic Tac Toe that
represents the board
*/
import java.util.*;
public class TicTacToe {
    public static final int SIZE = 3;   //can play games with matrices up to size 9
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static void main(String[] args){
        intro();
        //sets up the tic tac toe board and an array with a partial alphabet for column header
        char[][] board = new char[SIZE][SIZE];
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        Scanner console = new Scanner(System.in);

        initializeBoard(board);     //sets the initial conditions of the board
        boolean gameOver = false;   //if a player won the game
        boolean tie = false;        //if a tie was reached; no more empty spaces on board
        int player = 2;             //whose turn it is
        //what a winning String of characters should look like
        String playerOneWinningString = targetString(PLAYER_ONE);
        String playerTwoWinningString = targetString(PLAYER_TWO);
        printBoard(board, alphabet);

        while(!gameOver && !tie){
            player = updatePlayer(player);
            //player selects the row + column they want to add a token to
            System.out.println("Player " + player + " please make a selection:");
            int row = promptRow(console);
            int column = promptColumn(console, alphabet);
            System.out.println();

            //adds player's token and tests whether player won the game or a tie was reached
            if(player == 1) {
                addToken(row, column, board, PLAYER_ONE, console, alphabet);
                gameOver = hasWon(playerOneWinningString, board);
            } else {
                addToken(row, column, board, PLAYER_TWO, console, alphabet);
                gameOver = hasWon(playerTwoWinningString, board);
            }
            tie = boardIsFull(board);

            printBoard(board, alphabet);
        }
        reportResults(tie, player);
    }

    //updates and returns the player whose turn it is
    public static int updatePlayer(int player){
        if(player == 1){
            return 2;
        } else {
            return 1;
        }
    }

    //prints a short into to the game
    public static void intro(){
        System.out.println("This is a two-player game of Tic Tac Toe!");
        System.out.println("Get " + SIZE + " tokens in a straight line to win the game.");
        System.out.println("Ready? Lets go!");
        System.out.println();
    }

    //intitalizes all values on the new two dimensional array/matrix as spaces(' ')
    public static void initializeBoard(char[][] board){
        for(int i = 0; i < SIZE; i++){
            Arrays.fill(board[i], ' ');
        }
    }

    //prints the current state of the board
    public static void printBoard(char[][] board, char[] alphabet){
        //print column header
        System.out.print(" ");
        for(int i = 0; i < SIZE; i++){
            System.out.print("   " + alphabet[i]);
        }
        printDashes();
        //print grid values and row headers
        for(int i = 0; i < SIZE; i++){
            System.out.print(i + 1 + " | ");
            for(int j = 0; j < SIZE; j++){
                System.out.print(board[i][j] + " | ");
            }
            printDashes();
        }
        System.out.println();
    }

    //prints a series of dashes to separate rows
    public static void printDashes(){
        System.out.println();
        System.out.print(" ");
        for(int j = 0; j < SIZE; j++){
            System.out.print("   -");
        }
        System.out.println();
    }

    //prompts player for a row on the board
    //returns an index on for selection on the board array
    public static int promptRow(Scanner console){
        System.out.print("\trow(1-" + SIZE + "): ");
        if(!console.hasNextInt()) {
            System.out.print("\tPlease type a number between 1 and " + SIZE + ": ");
            console.next();
        }
        int row = console.nextInt();
        while (row < 1 || row > SIZE){
            System.out.print("\tPlease type a number between 1 and " + SIZE + ": ");
            row = console.nextInt();
        }
        return row - 1;
    }

    //prompts player for a column on the board
    //returns an index on for selection on the array representing the board
    public static int promptColumn(Scanner console, char[] alphabet){
        System.out.print("\tcolumn(A-" + alphabet[SIZE - 1] + "): ");
        char selection = console.next().toUpperCase().charAt(0);
        int index = findSelection(alphabet, selection);
        //propmts user until a valid column is chosen
        while(index == -1) {
            System.out.print("\tPlease type a letter between A and " + alphabet[SIZE - 1] + ": ");
            selection = console.next().toUpperCase().charAt(0);
            index = findSelection(alphabet, selection);
        }
        return index;
    }

    //searches the alphabet array for the user's column selection
    //returns the index of that character or -1 if it is not found
    public static int findSelection(char[] alphabet, char selection){
        for (int i = 0; i < SIZE; i++) {
            if (selection == alphabet[i]) {
                return i;
            }
        }
        return -1;
    }

    //determines whether the matrix space selected by the player is empty
    //adds the player's token to that space if is empty or re-prompts user if it not
    public static void addToken(int row, int column, char[][] board, char token,
                                Scanner console, char[] alphabet){
        if(board[row][column] != ' '){ //space already has a user's token
            //method calls its self and prompts user for another space on the board
            System.out.println("\tThat space is already taken. Please choose again.");
            addToken(promptRow(console), promptColumn(console, alphabet),
                    board, token, console, alphabet);
        } else {
            board[row][column] = token;   //adds player's token to specified space
        }
    }

    //creates and returns what a winning sequence looks like
    public static String targetString(char token){
        String target = "";
        for(int i = 0; i < SIZE; i ++){
            target += token;
        }
        return target;
    }

    //determines whether a player has won the game returns true or false
    public static boolean hasWon(String winningString, char[][]board){
        String rightDiagonal = "";
        String leftDiagonal = "";
        for(int i = 0; i < SIZE; i++){
            String row = "";
            String column = "";
            for(int j = 0; j < SIZE; j++){
                row += board[i][j];
                column += board[j][i];
                if(i == j){
                    rightDiagonal += board[i][i];
                }
                if(j == SIZE - 1 - i){
                    leftDiagonal += board[i][j];
                }
            }
            if(row.equals(winningString) || column.equals(winningString)){
                return true;
            }
        }
        return rightDiagonal.equals(winningString) || leftDiagonal.equals(winningString);
    }

    //determines whether all the spaces in the board have player tokens and returns true or false
    public static boolean boardIsFull(char[][] board){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    //reports who, if anyone, won the game
    public static void reportResults(boolean tie, int player){
        if(tie){
            System.out.println("There are no more spaces left on the board.");
            System.out.println("No one won this game.");
        } else {
            System.out.println("Congratulations Player " + player + " you have won the game!");
            System.out.println("Do a happy dance!");
        }
    }
}
