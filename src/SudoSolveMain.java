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
        //Every time the timer goes off, we...
        if(evt.getSource()==theTimer && thePanel.blnSolveTime==true){
            //Get the data from the panel, try to solve, and output the answers onto the panel again
            theModel.intSudokuArray = SudoSolveUtilities.inputData(thePanel.incompleteFld);
            for(int intCnt1=0;intCnt1<81;intCnt1++){
                for(int intCnt2=0;intCnt2<81;intCnt2++){
                    theModel.simpleSolveArray();
                }
                for(int intCnt3=0;intCnt3<81;intCnt3++){
                    theModel.complexSolveArray();
                }
            }
            thePanel.updateArray(theModel.intSudokuArray);
            thePanel.replaceFld();
            thePanel.repaint();

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
