public class SudoSolveModel {
    //Properties
    public int[][] intSudokuArray = new int[9][9];
    public boolean[] blnPossibleValues = SudoSolveUtilities.resetPossibilities();
    public int[] intChosenCoords = new int[2];
    public int[] intBoxCoords = new int[2];

    //Methods

    public void solveArray(){
        for(int intRow = 0; intRow < 9 ;intRow++){
            for(int intClm = 0; intClm < 9 ;intClm++){
                //Is the number isn't there arelady, then try solving
                if(intSudokuArray[intRow][intClm]==0){
                    intChosenCoords[0]=intRow;
                    intChosenCoords[1]=intClm;
                    intBoxCoords =  SudoSolveUtilities.locateBox(intChosenCoords);

                    blnPossibleValues = SudoSolveUtilities.eliminateBoxPossibilities(intSudokuArray, blnPossibleValues, intBoxCoords, intChosenCoords);
                    blnPossibleValues = SudoSolveUtilities.eliminateClmPossibilities(intSudokuArray, blnPossibleValues, intChosenCoords);
                    blnPossibleValues = SudoSolveUtilities.eliminateRowPossibilities(intSudokuArray, blnPossibleValues, intChosenCoords);

                    intSudokuArray = SudoSolveUtilities.trySolving(intSudokuArray, blnPossibleValues, intChosenCoords);
                    blnPossibleValues = SudoSolveUtilities.resetPossibilities();
                }
            }
        }
    }

    //Constructor
    public SudoSolveModel(){
         
    }
}
