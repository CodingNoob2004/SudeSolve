import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolveMain implements ActionListener{
    //Properties
    public JFrame theFrame = new JFrame("SudoSolve");
    public SudoSolvePanel thePanel = new SudoSolvePanel();
    public SudoSolveModel theModel = new SudoSolveModel();

    //Methods
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==thePanel.theSolveButton){
            theModel.intSudokuArray = SudoSolveUtilities.enterData(thePanel.txtFld);
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
	}

    //Main
    public static void main(String[] args){
        new SudoSolveMain();
    }
}
