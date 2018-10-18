/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;

import java.io.IOException;

/**
 *
 * @author jgauthier576
 */
//parses objects
public class primeParser {
    LogFile log = new LogFile();
    public int parser(double c) throws IOException{
    log.Logger(108);
        

        String s = Double.toString(c);
        s = s.replaceAll("\\." ,"");  //parses double to string and removes decimal
        
        
        Magic9s pStack = new Magic9s();
        int answer = pStack.Magic9s(s);  //sends string to magic 9s

     return answer;   //returns the number to the prime generator
    }
    public void menuParse(String s, stkClass stk) throws IOException{
    log.Logger(114);
        
        stk.reset();
        for(int num = s.length()-1; num >= 0; num--){
            stk.push(s.substring(num, num + 1)); //parses menu in reverse order to print in correct order
        }
    }           
}


        
