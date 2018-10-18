/*Justin Gauthier
 * Ed Cauthorn
 * CIS 148
 * 10/14/12
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This class is to end or bring you back to the beginning of the program so you can run it again.
 * 
 */
package toolboxmain;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *lets the user end the program or re run it
 * 
 * @author Justin
 */
public class openORclose {
    LogFile log = new LogFile();
    public void yesORno() throws IOException{
      log.Logger(115);
        Scanner input = new Scanner (System.in);    //allows user to input
        String user_input;
    { 
        System.out.println();
        System.out.println();
        System.out.println("     Would you like to return to the Main Menu? 'y' for yes and 'n' for no.");
        System.out.println();
        user_input = input.next();  /*Outputs statement and lets user respond*/
       

        if (user_input.equals("y")){ 
            char c = '\n';
            int length = 25;                //prints 25 blank lines to clean the output screen
            char[] chars = new char[length];  //everytime the user wants the program to be re run
            Arrays.fill(chars, c);
            System.out.print(String.valueOf(chars));
            Menus pMenu = new Menus();
            pMenu.PrimeGenMenu();/*If they clicked 'y' then it will bring them back to the beginning of the menu*/
        }
            
        if (user_input.equals("n")){ 
            System.out.println();
            System.out.println("    Program Ended");
            System.exit(0); /*If they clicked 'n' then it will end the program*/
            }
        else{
            System.out.println("    Incorrect key");
            System.out.println();
            yesORno();/*If they hit an incorrect key this will bring them back to the beginning so they can pick again*/
        
    }
    }
}
}