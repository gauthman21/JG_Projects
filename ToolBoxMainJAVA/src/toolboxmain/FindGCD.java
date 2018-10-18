/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;
//Program demonstrating Stack in Java
import java.io.IOException;
import java.util.*;
 
/**Justin Gauthier
 * Ed Cauthorn
 * CIS 210
 * 2/27/13
 * 
 * Gives you the GCD
 *
 * @author Justin
 */
public class FindGCD{
    LogFile log = new LogFile();
    public void GCD() throws IOException{
    log.Logger(110);       
        int number1;
        int number2;

      //Enter two number whose GCD needs to be calculated.      
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first number to find GCD");
        number1 = scanner.nextInt();//user inputs first number
        System.out.println();
        
        System.out.println("Please enter second number to find GCD");
        number2 = scanner.nextInt();//user inputs next number
        System.out.println();
        
        System.out.println("GCD of two numbers " + number1 +" and " 
                           + number2 +" is :" + findGCD(number1,number2));//outputs GCD
        System.out.println();
        
        openORclose YorN = new openORclose();//asks if they would like to go back to the menu
        YorN.yesORno();
    }

    public int findGCD(int number1, int number2) throws IOException {
        
        if(number2 == 0){   //if the second number is equal to 0 it returns the first number
            return number1;
        }

        return findGCD(number2, number1%number2); //checks to see if both numbers are divisible
    }     
 } 