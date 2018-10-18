/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;

import java.io.IOException;

/**
 *find prime numbers using Euclid's Algorithm
 * @author Justin
 */
public class primeGCD {
    LogFile log = new LogFile(); //allows log file to be used in class 
    myQueue qu = new myQueue(3); //sizes queue
    public long GCD(long k, long y) throws IOException{
    log.Logger(112);  
    qu.reset(); //resets queue before it begins
    qu.push(k); //adds k to queue
    qu.push(y); //adds y to queue
    while(qu.peekLast() > 1){    //goes into loop if the first number is greater than 1                     
       if(qu.peekLast() > qu.peekFirst()){  
              qu.push(qu.pop()); //if the last number is bigger than the first number then it switches
          }    
          qu.push(qu.pop() - qu.peekLast());    //if not it subtracts the first number from the next
      }
     
    if(qu.peekLast() == 0){
        return qu.peekFirst(); //returns the last number if the first number equals 0
    }
    else {
        return  0;} //returns 0 if number is prime
        
}}
    