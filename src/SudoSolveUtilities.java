import javax.swing.*;
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


    //Complex Methods
    public static boolean[][][] complexEliminateBoxPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intBoxCoords, int[] intChosenCoords){
        boolean blnOnePlace = true;
        //Check all numbers in here
        for(int intNumber=0; intNumber<9; intNumber++){
            blnOnePlace = true;
            //If in our coordinate part, there is a number x that is possible there
            if(blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intNumber]=true){
                //Check whether number x is possible anywhere else.
                for(int intRow = (intBoxCoords[0]-1) * 3 ; intRow < (intBoxCoords[0])*3; intRow++){
                    for(int intClm = (intBoxCoords[1]-1) * 3 ; intClm < (intBoxCoords[1])*3; intClm++){
                        //If the number is possible somewhere else, then it can't only be in one place
                        if(blnPossibilitiesArray[intRow][intClm][intNumber]==true){
                            //This means it can be in more than one place
                            blnOnePlace=false;
                        }
                    }
                }
            }
            //We get rid of the possibilities in that index slot
            blnPossibilitiesArray = onePlace(blnPossibilitiesArray, blnOnePlace, intNumber, intChosenCoords, intBoxCoords);
        }
        return blnPossibilitiesArray;
    }
    public static boolean[][][] complexEliminateRowPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intBoxCoords, int[] intChosenCoords){
        boolean blnOnePlace = true;
        //Check all numbers in here
        for(int intNumber=0; intNumber<9; intNumber++){
            blnOnePlace = true;
            //If in our coordinate part, there is a number x that is possible there
            if(blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intNumber]=true){
                //Check whether number x is possible anywhere else.
                for(int intClm = 0; intClm<9;intClm++){
                    //If the number is possible somewhere else, then it can't only be in one place
                    if(blnPossibilitiesArray[intChosenCoords[0]][intClm][intNumber]==true){
                        //This means it can be in more than one place
                        blnOnePlace=false;
                    }
                }
            }
            //We get rid of the possibilities in that index slot
            blnPossibilitiesArray = onePlace(blnPossibilitiesArray, blnOnePlace, intNumber, intChosenCoords, intBoxCoords);
        }
        return blnPossibilitiesArray;
    }
    public static boolean[][][] complexEliminateClmPossibilities(int[][] intSudokuArray, boolean[][][] blnPossibilitiesArray, int[] intBoxCoords, int[] intChosenCoords){
        boolean blnOnePlace = true;
        //Check all numbers in here
        for(int intNumber=0; intNumber<9; intNumber++){
            blnOnePlace = true;
            //If in our coordinate part, there is a number x that is possible there
            if(blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intNumber]=true){
                //Check whether number x is possible anywhere else.
                for(int intRow = 0; intRow<9;intRow++){
                    //If the number is possible somewhere else, then it can't only be in one place
                    if(blnPossibilitiesArray[intRow][intChosenCoords[1]][intNumber]==true){
                        //This means it can be in more than one place
                        blnOnePlace=false;
                    }
                }
            }
            //We get rid of the possibilities in that index slot
            blnPossibilitiesArray = onePlace(blnPossibilitiesArray, blnOnePlace, intNumber, intChosenCoords, intBoxCoords);
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
    //Use this method for complex falsifying for one place
    public static boolean[][][] onePlace(boolean[][][] blnPossibilitiesArray, boolean blnOnePlace, int intNumber, int[] intChosenCoords, int[] intBoxCoords){
        //If we know the number can only be in one place, then...
        if(blnOnePlace == true){
            //We also get rid of the possibility of that number in other places(same row, column, and box)
            //First, we get rid of that number possibility in the same row
            for(int intClm = 0; intClm<9;intClm++){
                blnPossibilitiesArray[intChosenCoords[0]][intClm][intNumber]=false;
            }
            //Second,we get rid of that number possibility in the same column
            for(int intRow = 0; intRow<9;intRow++){
                blnPossibilitiesArray[intRow][intChosenCoords[1]][intNumber]=false;
            }
            //Third, we get rid of that number possibility in the same box
            for(int intRow = (intBoxCoords[0]-1) * 3 ; intRow < (intBoxCoords[0])*3; intRow++){
                for(int intClm = (intBoxCoords[1]-1) * 3 ; intClm < (intBoxCoords[1])*3; intClm++){
                    blnPossibilitiesArray[intRow][intClm][intNumber]=false;
                }
            }

            //Last, we falsify all other possibilities in that one index slot
            for(int intCnt=0; intCnt<9; intCnt++){
                blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intCnt]=false;
            }
            //then make that one spot the only possible spot that is true
            blnPossibilitiesArray[intChosenCoords[0]][intChosenCoords[1]][intNumber]=true;
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
