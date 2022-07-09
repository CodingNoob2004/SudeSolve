import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class SudoSolveUtilities {
    public static boolean[][][] setPossibilitiesTrue(){
        boolean[][][] blnPossibilitiesArray = new boolean[9][9][9];
        for(int intRow=0; intRow<9;intRow++){
            for(int intClm=0; intClm<9;intClm++){
                for(int intPossibility=0; intPossibility<9;intPossibility++){
                    blnPossibilitiesArray[intRow][intClm][intPossibility] = true;
                }
            }
        }

        return blnPossibilitiesArray;
    }

     //Every time we move onto a new box, we need to make sure we have all the possibilities again
    public static boolean[] resetPossibilities(){
        boolean[] blnResetArray = new boolean[9];
        for(int intCnt=0 ; intCnt<9 ; intCnt++){
            blnResetArray[intCnt] = true;
        }
        return blnResetArray;
    }

    //Use this to locate row and column
    public static int[] locateBox(int[] intChosenCoords){
        int[] intBoxCoords = new int[2];
        intBoxCoords[0] = (int)(Math.ceil(((double)(intChosenCoords[0])+1.0)/3.0));
        intBoxCoords[1] = (int)(Math.ceil(((double)(intChosenCoords[1])+1.0)/3.0));
        return intBoxCoords;
    }

    //Use this method to check whether a number is in that box yet. Return value if it's there or not.
    public static boolean[][][] simpleEliminateBoxPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intBoxCoords, int[] intChosenCoords){
        for(int intRow = (intBoxCoords[0]-1) * 3 ; intRow < (intBoxCoords[0])*3; intRow++){
            for(int intClm = (intBoxCoords[1]-1) * 3 ; intClm < (intBoxCoords[1])*3; intClm++){
                //If in row x and clm y there's a value there already, then erase it off the list.
                blnPossibilitiesArray = falsifyPossibilities(intSudokuArray, blnPossibilitiesArray, intChosenCoords, intRow, intClm);
            }
        }
        return blnPossibilitiesArray;
    }
    //Use this method to check whether a number is in that row yet.
    public static boolean[][][] simpleEliminateRowPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intChosenCoords){
        for(int intClm = 0; intClm<9;intClm++){
            blnPossibilitiesArray = falsifyPossibilities(intSudokuArray, blnPossibilitiesArray, intChosenCoords, intChosenCoords[0], intClm);
        }
        return blnPossibilitiesArray;
    }
    //Use this method to check whether a number is in that column yet.
    public static boolean[][][] simpleEliminateClmPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intChosenCoords){
        for(int intRow = 0; intRow<9;intRow++){
            blnPossibilitiesArray = falsifyPossibilities(intSudokuArray, blnPossibilitiesArray, intChosenCoords, intRow, intChosenCoords[1]);
        }
        return blnPossibilitiesArray;
    }

    //Use this method to turn booleans false
    public static boolean[][][] falsifyPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intChosenCoords, int intRow, int intClm){
        if(intSudokuArray[intRow][intClm]==1){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][0] = false;
        }else if(intSudokuArray[intRow][intClm]==2){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][1] = false;
        }else if(intSudokuArray[intRow][intClm]==3){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][2] = false;
        }else if(intSudokuArray[intRow][intClm]==4){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][3] = false;
        }else if(intSudokuArray[intRow][intClm]==5){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][4] = false;
        }else if(intSudokuArray[intRow][intClm]==6){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][5] = false;
        }else if(intSudokuArray[intRow][intClm]==7){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][6] = false;
        }else if(intSudokuArray[intRow][intClm]==8){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][7] = false;
        }else if(intSudokuArray[intRow][intClm]==9){
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][8] = false;
        }
        return blnPossibilitiesArray;
    }

    //Use this method to attempt to solve for a value
    public static int[][] trySolving(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intChosenCoords){
        int intPossibleSolutions = 0;
        //check whether we have 1 solution or more
        for(int intCnt=0; intCnt<9;intCnt++){
            if(blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intCnt]==true){
                intPossibleSolutions++;
            }
        }

        //If we only have 1 solution, then
        if(intPossibleSolutions==1){
            for(int intCnt=0; intCnt<9;intCnt++){
                if(blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intCnt]==true){
                    intSudokuArray[intChosenCoords[0]][intChosenCoords[1]]=intCnt+1;
                }
            }
        }
        return intSudokuArray;
    }

    //Use this method to transwer user data into sudoku array data
    public static int[][] inputData(JTextField[][] incompleteFld){
        int[][] intSudokuArray = new int[9][9];
        for(int intRow=0 ; intRow < 9 ; intRow++){
            for(int intClm=0 ; intClm < 9 ; intClm++){
                try{
                    intSudokuArray[intRow][intClm] = Integer.parseInt(incompleteFld[intRow][intClm].getText());
                }catch(Exception e){
                    intSudokuArray[intRow][intClm]=0;
                }
            }
        }
        return intSudokuArray;
    }
}
