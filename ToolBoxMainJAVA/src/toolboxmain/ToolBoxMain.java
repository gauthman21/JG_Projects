/*
 *  Justin Gauthier
 *  CIS 148
 *  Ed Cauthorn
 *  12/13/12
 * 
 *  ToolBox
 * 
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;


/**
 *This is the main module for the tool box. This is where you call the classes that you want to run.
 * @author Justin
 */
import java.io.IOException;

public class ToolBoxMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Menus PNmenu = new Menus();
        PNmenu.PrimeGenMenu(); //runs the prime generator menu

        }
        
        
}
