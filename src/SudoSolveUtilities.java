public class SudoSolveUtilities {
     //Every time we move onto a new box, we need to make sure we have all the possibilities again
    public static boolean[] resetPossibilities(){
        boolean[] blnResetArray = new boolean[9];
        for(int intCnt=0 ; intCnt<9 ; intCnt++){
            blnResetArray[intCnt] = true;
        }
        return blnResetArray;
    }

    //use this to locate row and column
    public static int[] locateBox(int[] intCoordinates){
        int[] intBox = new int[2];
        intBox[0] = (int)(Math.ceil(intCoordinates[0]/3));
        intBox[1] = (int)(Math.ceil(intCoordinates[1]/3));
        return intBox;
    }
}
