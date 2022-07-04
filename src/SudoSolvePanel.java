import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolvePanel extends JPanel implements ActionListener{
    //Properties
    JTextField txtFld[][] = new JTextField[9][9];
    JButton theSolveButton = new JButton("Solve");
    /** WHY CAN'T I THIS.ADD(divider1)
    Rectangle divider1 = new Rectangle(0,147,450,6);
    Rectangle divider2 = new Rectangle(0,297,450,6);
    Rectangle divider3 = new Rectangle(147,0,6,450);
    Rectangle divider4 = new Rectangle(297,0,6,450);
    */
    boolean blnSolveTime = false;
    
    //Methods
    //When they hit the solve button, it's time to solve
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==theSolveButton){
            blnSolveTime=true;
        }
    }

    //This just paints the lines creating boxes
    public void paintComponent(Graphics g){
        g.fillRect(0,147,450,6);
        g.fillRect(0,297,450,6);
        g.fillRect(147,0,6,450);
        g.fillRect(297,0,6,450);
    }

    //Constructor
    public SudoSolvePanel(){
        super();
        this.setLayout(null);

        //Add the solve button
        this.add(theSolveButton);
        theSolveButton.setBounds(205,450,40,20);
        theSolveButton.addActionListener(this);

        //Create the array
        for(int intRow=0 ; intRow < 9 ; intRow++ ){
            for(int intClm=0 ; intClm < 9 ; intClm++ ){
                txtFld[intClm][intRow] = new JTextField("");
                txtFld[intClm][intRow].setBounds(intClm*50,intRow*50, 50, 50);
                this.add(txtFld[intClm][intRow]);
            }
        }
    }
}
