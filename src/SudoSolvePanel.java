import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolvePanel extends JPanel implements ActionListener{
    //Properties
    public JTextField incompleteFld[][] = new JTextField[9][9];
    public JTextField completedFld[][] = new JTextField[9][9];
    public JButton theSolveButton = new JButton("Solve");
    public boolean blnSolveTime = false;
    /** WHY CAN'T I THIS.ADD(divider1)
    Rectangle divider1 = new Rectangle(0,147,450,6);
    Rectangle divider2 = new Rectangle(0,297,450,6);
    Rectangle divider3 = new Rectangle(147,0,6,450);
    Rectangle divider4 = new Rectangle(297,0,6,450);
    */
    
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
        this.repaint();
    }

    //This will update the textfields
    public void updateArray(int[][] intSudokuArray){
        for(int intRow=0 ; intRow < 9 ; intRow++){
            for(int intClm=0 ; intClm < 9 ; intClm++){
                try{
                    if(intSudokuArray[intRow][intClm]==0){
                        this.incompleteFld[intRow][intClm].setText("");
                    }else if(intSudokuArray[intRow][intClm]==1 || 
                    intSudokuArray[intRow][intClm]==2 || 
                    intSudokuArray[intRow][intClm]==3 || 
                    intSudokuArray[intRow][intClm]==4 || 
                    intSudokuArray[intRow][intClm]==5 || 
                    intSudokuArray[intRow][intClm]==6 || 
                    intSudokuArray[intRow][intClm]==7 || 
                    intSudokuArray[intRow][intClm]==8 || 
                    intSudokuArray[intRow][intClm]==9){
                        /**WHY WON'T IT SET THE TEXT IN THE TEXT FIELD. AHHHHH
                        JTextField theTextField = new JTextField(String.valueOf(intSudokuArray[intRow][intClm]));
                        theTextField.setBounds(intClm*50,intRow*50, 50, 50);
                        incompleteFld[intRow][intClm] = theTextField;
                        */
                        
                        /**Update the text in the array
                        this.incompleteFld[intRow][intClm].setText(String.valueOf(intSudokuArray[intRow][intClm]));
                        */
                        this.incompleteFld[intRow][intClm].setVisible(false);
                        this.incompleteFld[intRow][intClm] = new JTextField(String.valueOf(intSudokuArray[intRow][intClm]));
                        this.incompleteFld[intRow][intClm].setVisible(true);
                    }
                }catch(Exception e){
                    this.incompleteFld[intRow][intClm].setText("");
                    this.incompleteFld[intClm][intRow].setVisible(false);
                }
                this.repaint();
            }
        }
    }

    public void replaceFld(){
        for(int intRow=0 ; intRow < 9 ; intRow++ ){
            for(int intClm=0 ; intClm < 9 ; intClm++ ){
                //Place it in the array
                completedFld[intClm][intRow] = incompleteFld[intClm][intRow];
                completedFld[intClm][intRow].setBounds(intClm*50,intRow*50, 50, 50);
                completedFld[intClm][intRow].setVisible(true);
                
                //add that to the panel
                this.add(completedFld[intClm][intRow]);
                this.incompleteFld[intClm][intRow].setVisible(true);
            }
        }
    }

    //Constructor
    public SudoSolvePanel(){
        super();
        this.setLayout(null);

        //Add the solve button
        this.add(theSolveButton);
        theSolveButton.setBounds(205,450,85,30);
        theSolveButton.addActionListener(this);

        //Create the array
        for(int intRow=0 ; intRow < 9 ; intRow++ ){
            for(int intClm=0 ; intClm < 9 ; intClm++ ){
                //make the object
                JTextField theTextField = new JTextField("");
                theTextField.setBounds(intClm*50,intRow*50, 50, 50);
                //Place it in the array
                this.incompleteFld[intClm][intRow] = theTextField;
                
                //add that to the panel
                this.add(incompleteFld[intClm][intRow]);
                this.incompleteFld[intClm][intRow].setVisible(true);
            }
        }
    }
}
