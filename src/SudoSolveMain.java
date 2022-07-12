import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudoSolveMain implements ActionListener{
    //Properties
    public JFrame theFrame = new JFrame("SudoSolve");
    //This variable is used to show what's on the screen
    public SudoSolvePanel thePanel = new SudoSolvePanel();
    //This variable holds the data
    public SudoSolveModel theModel = new SudoSolveModel();
    public Timer theTimer = new Timer(1000,this);

    //Methods
    public void actionPerformed(ActionEvent evt){
        //Every we press the button and time the timer goes off, we...
        if(evt.getSource()==theTimer && thePanel.blnSolveTime==true){
            //Get the data from the panel, try to solve, and output the answers onto the panel again
            theModel.intSudokuArray = SudoSolveUtilities.inputData(thePanel.incompleteFld);
            
            //Loop 81(9 row by 9 column) to ensure we capture every value
            for(int intCnt1=0;intCnt1<81;intCnt1++){
                //When we have an index with a number already, get rid of all possibilities except that 1 number
                theModel.selfReflection();
                //Loop 81(9 row by 9 column) to ensure we capture every value
                for(int intCnt2=0;intCnt2<81;intCnt2++){
                    //We use the simple method
                    theModel.simpleSolveArray();
                }
                //Loop 81(9 row by 9 column) to ensure we capture every value
                for(int intCnt3=0;intCnt3<81;intCnt3++){
                    //We use the more complex methods
                    theModel.complexSolveArray();
                }
            }
            //Here, we make the fields invisible
            thePanel.updateArray(theModel.intSudokuArray);
            //We then replace the fields with definite values with new JTextFields
            thePanel.replaceFld();
            //Repaint
            thePanel.repaint();
            //We don't want it to constantly be running since it uses a lot of threads so
            //we stop it until they click the solve button again
            thePanel.blnSolveTime=false;
            
            System.out.println("works");
        }
    }

    //Constructor
    SudoSolveMain(){
        thePanel.setPreferredSize(new Dimension(800,450));
        theFrame.setContentPane(thePanel);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setResizable(false);
		theFrame.setVisible(true);
        theTimer.start();
	}

    //Main
    public static void main(String[] args){
        new SudoSolveMain();
    }
}
