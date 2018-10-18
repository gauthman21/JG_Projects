/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;

/**
 *
 * @author jgauthier576
 */
public class stkClass {
        Object p;
        int Maxsize, i, c = -1; 
        Object stack[];
      public stkClass(int m)
      {
            Maxsize = m;
            stack = new Object[Maxsize];    //sets values
      }
      void push(Object i)
      {
          if(c != (Maxsize - 1)) {
              
              c++;          //adds value to the stack and pushing one down
              stack[c] = i;
          }
            else
            {
               System.out.println("\tStack Overflow");   //error checking if there is to much in the stack
            }
      }
      Object pop()
      {  
          if(c == -1) {
              System.out.println("\tStack is empty"); //error checking if stack is empty
          }
            else
            { 
                p = stack[c];  //takes first value off stack
                c--;
            }
        return p; //returns the value
      }
      Object peek()
      {
        if(c != -1){
           System.out.println("\nStack Status : ");
                                                        //peeks at value in the stack
           for(i = c; i >= 0; i--) {
                System.out.println("\t "+ stack[i]);
        }
    }
        return stack[c]; //returns value in the location you looked
}
       boolean isEmpty(){
              
           return(c == -1); //empties the stack
             
           }
       void reset(){
           
           c = -1;  //resets stack
           
           }
       boolean isFull(){
           
           return(c == Maxsize);//returns if stack is full
           }
       int count(){
           return c + 1; //counts size of stack
       }
}