public class SudoSolveModel {
    //Properties
    public int[][] intSudokuArray = new int[9][9];
    public boolean[] blnPossibleValues = SudoSolveUtilities.resetPossibilities();
    public int[] intChosenCoords = new int[2];
    public int[] intBoxCoords = new int[2];

    //Methods


    //Constructor
    public SudoSolveModel(){
         
    }
}
