/*Justin Gauthier
 * Ed Cauthorn
 * CIS 148
 * 11/18/12
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This class has all the menus that give the person an idea of what to press when they are running the program.
 */
package toolboxmain;
import java.io.IOException;

/**
 *
 * @author Justin
 */
public class Menus {
    public void ABCmenu(){
        
    System.out.println("         A.XXXXXXXXXXXXXXXXXXXXXXXXXX       B.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         C.XXXXXXXXXXXXXXXXXXXXXXXXXX       D.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         E.XXXXXXXXXXXXXXXXXXXXXXXXXX       F.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         G.XXXXXXXXXXXXXXXXXXXXXXXXXX       H.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         I.XXXXXXXXXXXXXXXXXXXXXXXXXX       J.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         K.XXXXXXXXXXXXXXXXXXXXXXXXXX       L.XXXXXXXXXXXXXXXXXXXXXXX");        /*Outputs menu*/
    System.out.println("         M.XXXXXXXXXXXXXXXXXXXXXXXXXX       N.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println("         O.XXXXXXXXXXXXXXXXXXXXXXXXXX       P.XXXXXXXXXXXXXXXXXXXXXXX");
    System.out.println();
    System.out.println();
    
    JumpTrees ABC = new JumpTrees();
    ABC.ABCjumpTree();
    }
    public void GeoMenu(){
        
        System.out.println("     1. Perimeter of a Rectangle: ");
        System.out.println("     2. Area of a Rectangle: ");
        System.out.println("     3. Perimeter of a Square: ");
        System.out.println("     4. Area of a Square: ");                   /*Outputs menu*/
        System.out.println("     5. Perimeter of a Parallelogram: ");
        System.out.println("     6. Area of a Parallelogram: ");
        System.out.println("     7. Perimeter of a Rhombus: ");
        System.out.println("     8. Area of a Rhombus: ");
        System.out.println("     9. Perimeter of a Triangle: ");
        System.out.println("     10. Area of a Triangle: ");
        System.out.println("     11. Perimeter of a Trapezoid: ");
        System.out.println("     12. Area of a Trapezoid: ");
        System.out.println("     13. Perimeter of a Circle: ");
        System.out.println("     14. Area of a Circle: ");
        System.out.println();
        System.out.println("All info. found at http://www.basic-mathematics.com/common-geometry-formulas.html"); /*This is where I found all the geometric formulas*/
        System.out.println();
        
       //JumpTrees GeoJump = new JumpTrees();
       //GeoJump.GeoJumpTree();      /*This brings you to the jump tree so you can pick a number from the menu*/
    }
    public void ClassMenu(){
        
        System.out.println("                a. CIS 118 Visual Basic.NET");
        System.out.println("                b. CIS 111 INTRO to Programming");
        System.out.println("                c. CIS 148 JAVA Programming");          /*Outputs menu*/
        System.out.println();
        System.out.println();
        
        //JumpTrees ClassJump = new JumpTrees(); /*Brings menu to jump tree for input*/
        //ClassJump.classJumpTree();
    }
    LogFile log = new LogFile();
    stkClass stk = new stkClass(77); //sets stack size to 77
    String [][] mArray = new String [25][80]; //creates jagged array
    public void PrimeGenMenu() throws IOException{
    log.Logger(105);       
        
           addRow(1, "JUSTIN GAUTHIER                                                  CIS 210");
           addRow(2, "4/22/2013                                                    ED CAUTHORN");
           addRow(3, "                                                                        ");
           addRow(4, "                         PRIME NUMBER GENERATOR                         ");
           addRow(5, "                                                                        ");
           addRow(6, "Please choose an option below by typing in the number...                ");
           addRow(7, "                                                                        ");
           addRow(8, "                                                                        ");       //output of menu
           addRow(9, "         OPTION 1: LOG FILE                                             ");
           addRow(10, "                                                                        ");
           addRow(11, "                                                                        ");
           addRow(12, "         OPTION 2: PRIME NUMBER GENERATOR                               ");
           addRow(13, "                                                                        ");
           addRow(14, "                                                                        ");
           addRow(15, "         OPTION 3: GCD                                                  ");
           addRow(16, "                                                                        ");
           addRow(17, "                                                                        ");
           addRow(18, "         OPTION 4: ***************                                      ");     
           addRow(19, "                                                                        ");
           addRow(20, "                                                                        ");
           addRow(21, "         OPTION 5: ***************                                      ");
           addRow(22, "                                                                        ");
           addRow(23, "                                                                        ");
        

        for (int i = 0; i < 23; i++){
            for(int j = 0; j < 77; j++){  
             if(mArray[i][j] != null){  //sends each char into specific location on output screen
                 System.out.print(mArray[i][j]);}  //outputs menu      
            }
        System.out.println();
    }
     
     JumpTrees jTree = new JumpTrees();
     jTree.PrimeJumpTree(); //sends user to next location
   }
    public void addRow(int row, String s) throws IOException{ //adds each row seperately
        
     primeParser mParser = new primeParser();
     mParser.menuParse(s, stk);//sends each row to parser
     
     int count = stk.count();
     
     for(int i = 1; i <= count; i++){
         mArray[row][i] = stk.pop().toString(); //sends the parsed rows to stack

        }
    }
}
    