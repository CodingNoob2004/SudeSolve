import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class SudoSolveUtilities {
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
        intBoxCoords[0] = (int)(Math.ceil(intChosenCoords[0]/3));
        intBoxCoords[1] = (int)(Math.ceil(intChosenCoords[1]/3));
        return intBoxCoords;
    }

    //Use this method to check whether a number is in that box yet. Return value if it's there or not.
    public static boolean[] eliminateBoxPossibilities(int[][] intSudokuArray, boolean[] blnPossibleValues, int[] intBoxCoords, int[] intChosenCoords){
        for(int intRow = (intBoxCoords[0]) * 3 ; intRow < (intBoxCoords[0]+1)*3; intRow++){
            for(int intClm = (intBoxCoords[1]) * 3 ; intClm < (intBoxCoords[1]+1)*3; intClm++){
                //If in row x and clm y there's a value there already, then erase it off the list.
                blnPossibleValues = falsifyPossibilities(intSudokuArray, blnPossibleValues, intRow, intClm);
            }
        }
        return blnPossibleValues;
    }

    //Use this method to check whether a number is in that row yet.
    public static boolean[] eliminateRowPossibilities(int[][] intSudokuArray, boolean[] blnPossibleValues, int[] intChosenCoords){
        for(int intClm = 0; intClm<9;intClm++){
            blnPossibleValues = falsifyPossibilities(intSudokuArray, blnPossibleValues, intChosenCoords[0], intClm);
        }
        return blnPossibleValues;
    }

    //Use this method to check whether a number is in that column yet.
    public static boolean[] eliminateClmPossibilities(int[][] intSudokuArray, boolean[] blnPossibleValues, int[] intChosenCoords){
        for(int intRow = 0; intRow<9;intRow++){
            blnPossibleValues = falsifyPossibilities(intSudokuArray, blnPossibleValues, intRow, intChosenCoords[1]);
        }
        return blnPossibleValues;
    }

    //Use this method to turn booleans false
    public static boolean[] falsifyPossibilities(int[][] intSudokuArray, boolean[] blnPossibleValues, int intRow, int intClm){
        if(intSudokuArray[intRow][intClm]==1){
            blnPossibleValues[0] = false;
        }else if(intSudokuArray[intRow][intClm]==2){
            blnPossibleValues[1] = false;
        }else if(intSudokuArray[intRow][intClm]==3){
            blnPossibleValues[2] = false;
        }else if(intSudokuArray[intRow][intClm]==4){
            blnPossibleValues[3] = false;
        }else if(intSudokuArray[intRow][intClm]==5){
            blnPossibleValues[4] = false;
        }else if(intSudokuArray[intRow][intClm]==6){
            blnPossibleValues[5] = false;
        }else if(intSudokuArray[intRow][intClm]==7){
            blnPossibleValues[6] = false;
        }else if(intSudokuArray[intRow][intClm]==8){
            blnPossibleValues[7] = false;
        }else if(intSudokuArray[intRow][intClm]==9){
            blnPossibleValues[8] = false;
        }
        return blnPossibleValues;
    }

    //Use this method to attempt to solve for a value
    public static int[][] trySolving(int[][] intSudokuArray, boolean[] blnPossibleValues, int[] intChosenCoords){
        int intPossibleSolutions = 0;
        //check whether we have 1 solution or more
        for(int intCnt=0; intCnt<9;intCnt++){
            if(blnPossibleValues[intCnt]==true){
                intPossibleSolutions++;
            }
        }

        //If we only have 1 solution, then
        if(intPossibleSolutions==1){
            for(int intCnt=0; intCnt<9;intCnt++){
                if(blnPossibleValues[intCnt]==true){
                    intSudokuArray[intChosenCoords[0]][intChosenCoords[1]]=intCnt+1;
                }
            }
        }
        return intSudokuArray;
    }

    //Use this method to transwer user data into sudoku array data
    public static int[][] inputData(JTextField[][] txtFld){
        int[][] intSudokuArray = new int[9][9];
        for(int intRow=0 ; intRow < 9 ; intRow++){
            for(int intClm=0 ; intClm < 9 ; intClm++){
                try{
                    intSudokuArray[intRow][intClm] = Integer.parseInt(txtFld[intRow][intClm].getText());
                }catch(Exception e){
                    System.out.println("null");
                    intSudokuArray[intRow][intClm]=0;
                }
            }
        }
        return intSudokuArray;
    }

    //Use this method to output data back onto text field array
    public static JTextField[][] outputData(int[][] intSudokuArray){
        JTextField[][] txtFld = new JTextField[9][9];
        for(int intRow=0 ; intRow < 9 ; intRow++){
            for(int intClm=0 ; intClm < 9 ; intClm++){
                try{
                    if(intSudokuArray[intRow][intClm]==0){
                        txtFld[intRow][intClm].setText(" ");
                    }else{
                        txtFld[intRow][intClm].setText("" + intSudokuArray[intRow][intClm]);
                    }
                }catch(Exception e){
                    
                }
            }
        }
        return txtFld;
    }
}
