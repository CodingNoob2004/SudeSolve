import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SudoSolvePanel extends JPanel implements ActionListener{
    //Properties
    public TextField txtFld[][] = new TextField[9][9];
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
    }

    //This will update the textfields
    public void updateArray(int[][] intSudokuArray){
        for(int intRow=0 ; intRow < 9 ; intRow++){
            for(int intClm=0 ; intClm < 9 ; intClm++){
                try{
                    if(intSudokuArray[intRow][intClm]==0){
                        this.txtFld[intRow][intClm].setText("");
                    }else if(intSudokuArray[intRow][intClm]==1 || 
                    intSudokuArray[intRow][intClm]==2 || 
                    intSudokuArray[intRow][intClm]==3 || 
                    intSudokuArray[intRow][intClm]==4 || 
                    intSudokuArray[intRow][intClm]==5 || 
                    intSudokuArray[intRow][intClm]==6 || 
                    intSudokuArray[intRow][intClm]==7 || 
                    intSudokuArray[intRow][intClm]==8 || 
                    intSudokuArray[intRow][intClm]==9){
                        this.add(this.txtFld[intRow][intClm]);
                        this.txtFld[intRow][intClm].setText(String.valueOf(intSudokuArray[intRow][intClm]));
                        System.out.println(txtFld[intRow][intClm].getText());
                    }
                }catch(Exception e){
                    this.txtFld[intRow][intClm].setText("");
                }
            }
        }
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
                this.txtFld[intClm][intRow] = new TextField("");
                this.txtFld[intClm][intRow].setBounds(intClm*50,intRow*50, 50, 50);
                this.add(txtFld[intClm][intRow]);
            }
        }
    }
}
