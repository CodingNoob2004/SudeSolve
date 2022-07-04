import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolveMain implements ActionListener{
    //Properties
    public JFrame theFrame = new JFrame("SudoSolve");
    //This variable is used to show what's on the screen
    public SudoSolvePanel thePanel = new SudoSolvePanel();
    //This variable holds the data
    public SudoSolveModel theModel = new SudoSolveModel();
    public Timer theTimer = new Timer(1000/1,this);

    //Methods
    public void actionPerformed(ActionEvent evt){
        //Every time the timer goes off, we...
        if(evt.getSource()==theTimer && thePanel.blnSolveTime==true){
            //Get the data from the panel, try to solve, and output the answers onto the panel again
            for(int intCnt=0;intCnt<81;intCnt++){
                theModel.intSudokuArray = SudoSolveUtilities.inputData(thePanel.txtFld);
                theModel.solveArray();
                thePanel.txtFld = SudoSolveUtilities.outputData(theModel.intSudokuArray);
                thePanel.repaint();
            }
        }
    }

    //Constructor
    SudoSolveMain(){
        thePanel.setPreferredSize(new Dimension(450,470));
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
