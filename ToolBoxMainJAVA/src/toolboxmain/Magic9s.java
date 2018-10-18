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
public class Magic9s {
    /**
     *
     * @param s
     * @return
     * 
     * adds up digits in a number until they are less than or equal to 9
     */
    LogFile log = new LogFile();
    public int Magic9s(String s) throws IOException{
    log.Logger(109);

    int added = 0;
    
    for (int i = 0; i < s.length(); i++)    //takes each digit out of the string
    {
        added = perhaps(s); //makes added equal to the digits left in the string

}
    return added;
}
public int perhaps(String temp)throws IOException{
    log.Logger(111);
    int added;
    int intTemp;
    added = Integer.valueOf(temp); //makes added equal to the passing digit
    
   while(added > 9){
        added = 0;   //goes through the loop until the number is less than 9
        for(int i = 0; i < temp.length(); i++){ 

        intTemp = Integer.valueOf(temp.substring(i, i + 1));//sets intTemp to the value of the first digit
        added = intTemp + added; //adds the digit to the previous one
        }
        temp = Integer.toString(added); //sets the integer back to a string
    }
        return added; 
    }
  
}