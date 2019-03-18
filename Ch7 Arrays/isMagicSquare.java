/* Written by Leslie Mares on 3-16-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 7 Practice-It Exercise:
Write a method called isMagicSquare that accepts a two-dimensional array of integers as a parameter 
and returns true if it is a magic square. A square matrix is a magic square if it is square in shape 
(same number of rows as columns, and every row the same length), and all of its row, column, and 
diagonal sums are equal. For example, [[2, 7, 6], [9, 5, 1], [4, 3, 8]] is a magic square because 
all eight of the sums are exactly 15.
*/

public static boolean isMagicSquare(int[][] a){
    int size = a.length;
    //is it a square?
    for(int i = 0; i < size; i++){
        if(size != a[i].length){
            retun false;
        }
    }
    //what is the number all diagonals, rows, and colums should add up to?
    int targetSum = 0;
    if(size == 0){
        return true;
    } else {
        for(int i = 0; i < a[0].length; i ++){
            targetSum += a[0][i];
        }
    }
    //are the row, column, and diagonal sums the sameas the target?
    int rightDiagonalSum = 0;
    int leftDiagonalSum = 0;
    for(int i = 0; i < size; i++){
        int rowSum = 0;
        int columnSum = 0;
        for(int j = 0; j < size; j++){
            rowSum += a[i][j];
            columnSum += a[j][i];
            if(i == j){
                rightDiagonalSum += a[i][i];
            }
            if(j == size - 1 - i){
                leftDiagonalSum += a[i][j];
            }
        }
        if(rowSum != targetSum || columnSum != targetSum){
            return false;
        }
    } 
    return rightDiagonalSum == targetSum && leftDiagonalSum == targetSum;
}
