/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;

/**
 *
 * @author Justin
 */
public class myQueue {
    
    private int Maxsize, c;
    private int first, last;
    private long queue[]; 
      
    public myQueue(int m)               //constructor
      {        
            Maxsize = m;
            queue = new long[Maxsize]; //sets values
            last = -1;
            first = 0;
            c = 0;
      }
      void push(long i)
      { 
       
          if(last == Maxsize - 1) {        // deal with wraparound
             last = -1;}         
           
            last++;
            queue[last] = i;         //increments last and pushes            
            c++;          //one more item

      }
      long pop()
      {           
          if(first == Maxsize){     //deals with wraparound
             first = 0;}
                   
             c--;        //one less item
          
        return queue[first++];   
      }
      long peekFirst()
      {
          if(first == Maxsize){     //deals with wraparound
           
              return queue[0];}    //returns the 0 location in queue
      else{
          
              return queue[first]; //returns first value in queue
      }          
   }
      long peekLast()
       {
         if(last == Maxsize) {        // deal with wraparound

           return queue[0];} //returns the 0 location in queue
       else{
           return queue[last]; //returns last value in queue
       }
    } 
      boolean isEmpty()
       {

             return c == 0; //true if queue is empty         
       
       }
       void reset()
       {
           c = 0;
           first = 0;           //resets queue
           last = c - 1;
           
           }
       boolean isFull()
       {
           return c == Maxsize;   //true if queue is full
       }
       int count()
       {
           return c;  //number of items in queue
       }
}

