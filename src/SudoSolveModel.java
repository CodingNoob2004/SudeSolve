public class SudoSolveModel {
    //Properties
    //This variable will hold the array data
    public int[][] intSudokuArray;
    //this variable tells us how many possible values a certain index/slot in the array could be.
    public boolean[] blnPossibleValues = SudoSolveUtilities.resetPossibilities();
    //This variable is used to track the coordinates. [0] is the row. [1] is the colun
    public int[] intChosenCoords = new int[2];
    //This variable tells us which row and column the box the coordinates are in are
    public int[] intBoxCoords = new int[2];

    //Methods
    public void solveArray(){
        //We will try to solve for every single value in each row and column
        for(intChosenCoords[0] = 0; intChosenCoords[0] < 9 ;intChosenCoords[0]++){
            for(intChosenCoords[1] = 0; intChosenCoords[1] < 9 ;intChosenCoords[1]++){
                //Is the number isn't there arelady, then try solving
                if(intSudokuArray[intChosenCoords[0]][intChosenCoords[1]]==0){
                    //Locate which row and column the box is in.
                    intBoxCoords =  SudoSolveUtilities.locateBox(intChosenCoords);

                    //We use process of elimination
                    blnPossibleValues = SudoSolveUtilities.eliminateBoxPossibilities(intSudokuArray, blnPossibleValues, intBoxCoords, intChosenCoords);
                    blnPossibleValues = SudoSolveUtilities.eliminateClmPossibilities(intSudokuArray, blnPossibleValues, intChosenCoords);
                    blnPossibleValues = SudoSolveUtilities.eliminateRowPossibilities(intSudokuArray, blnPossibleValues, intChosenCoords);

                    //Based on the possible answers, update the array if there's only 1 possible answer left
                    intSudokuArray = SudoSolveUtilities.trySolving(intSudokuArray, blnPossibleValues, intChosenCoords);
                    //Now that we're done, we make the possible answers true again for next run.
                    blnPossibleValues = SudoSolveUtilities.resetPossibilities();
                }
            }
        }
    }

    //Constructor
    public SudoSolveModel(){
         
    }
}
