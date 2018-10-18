/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Justin Gauthier
 * CIS 210
 * Ed Cauthorn
 * 2/1/13
 * 
 * This class prints outputs to a log file to keep track of when those classes were used in the program.
 * It also shows the last class that was used before an error ocurred giving you a rough estimate of
 * were the error occured.
 */

package toolboxmain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author jgauthier576
 */
public class LogFile {
    public void Logger(int error)throws IOException {
    
  try{ 
    FileWriter fstream = new FileWriter("LogFile.txt",true);
    BufferedWriter out = new BufferedWriter(fstream);//creates a text file if there isn't one
                                                    //writes on it if the file already exists
    
    
    if (error == 101){
        out.write("Error 101: In 'MultiDimensionalArrays' TwoByTwo");} //writes to log file if class is run
       
    
    else if (error == 102){
        out.write("Error 102: In 'MultiDimensionalArrays' ThreeByThree");} //writes to log file if class is run
       
        
    else if (error == 103){
        out.write("Error 103: In 'MultiDimensionalArrays' FourByFour");} //writes to log file if class is run
       
        
    else if (error == 104){
        out.write("Error 104: In 'PrimeNumbers'");} //writes to log file if class is run
       
        
    else if (error == 105){
        out.write("Error 105: In 'Prime Number Menu'");} //writes to log file if class is run
       
        
    else if (error == 106){
        out.write("Error 106: In 'Prime Number Jump Tree'");} //writes to log file if class is run
       
        
    else if (error == 107){
        out.write("Error 107: In 'Prime Number Generator'");} //writes to log file if class is run
       
        
    else if (error == 108){
        out.write("Error 108: In 'Prime Number Parser'");} //writes to log file if class is run
       
    
    else if (error == 109){
        out.write("Error 109: In 'Magic9s'");} //writes to log file if class is run
        
    
    else if (error == 110){
        out.write("Error 110: In 'GCD'");} //writes to log file if class is run
       
        
    else if (error == 111){
        out.write("Error 111: In 'Magic9s: Second Method'");} //writes to log file if class is run
       
        
    else if (error == 112){
        out.write("Error 112: In 'Prime GCD'");} //writes to log file if class is run
       
        
    else if (error == 113){
        out.write("Error 113: In 'Prime GCD: Second Method'");} //writes to log file if class is run
        
        
    else if (error == 114){
        out.write("Error 114: In 'Prime Parser: Second Method'");} //writes to log file if class is run
    
    else if (error == 115){
        out.write("Error 115: In 'Re Open or Close'");} //writes to log file if class is run
        
    

    
    out.newLine();
    out.close(); //Closes the output to text file
  }
     catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }
          
    }
    
}