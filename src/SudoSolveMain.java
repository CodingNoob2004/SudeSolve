import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolveMain{
    //Properties
    public JFrame theFrame = new JFrame("SudoSolve");
    public SudoSolvePanel thePanel = new SudoSolvePanel();
    public SudoSolveModel theModel = new SudoSolveModel();

    //Methods


    //Constructor
    SudoSolveMain(){
        thePanel.setPreferredSize(new Dimension(450,450));
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
