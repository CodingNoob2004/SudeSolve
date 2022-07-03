import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolveMain implements ActionListener{
    //Properties
    public JFrame theFrame = new JFrame("SudoSolve");
    public SudoSolvePanel thePanel = new SudoSolvePanel();
    public SudoSolveModel theModel = new SudoSolveModel();
    public Timer theTimer = new Timer(1000/1,this);

    //Methods
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==theTimer && thePanel.blnSolveTime==true){
            for(int intCnt=0;intCnt<81;intCnt++){
                theModel.intSudokuArray = SudoSolveUtilities.inputData(thePanel.txtFld);
                theModel.solveArray();
                thePanel.txtFld = SudoSolveUtilities.outputData(theModel.intSudokuArray);
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
