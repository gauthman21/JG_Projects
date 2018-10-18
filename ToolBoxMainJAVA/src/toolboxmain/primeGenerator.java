/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolboxmain;

//import java.util.ArrayList;
import java.io.IOException;
import java.util.*;
//http://programmingpraxis.com/programming-with-prime-numbers/

/**
 *Justin Gauthier
 * CIS 210
 * Ed Cauthorn
 * 5/7/13
 * 
 * 
 * This program allows you to search for n amount of prime numbers
 * @author jgauthier576
 */
public class primeGenerator {
    LogFile log = new LogFile();
    
    public void pGenerator(String s, int sum) throws IOException{ 
    log.Logger(107);
        
        primeParser parse = new primeParser();
        primeGCD pGCD = new primeGCD();

          
        Scanner input = new Scanner(System.in); 
        int max; //amount of primes they would like to search for
        long [][] array = new long [10][10]; //array to hold all of the numbers 
        
        System.out.println("Please enter the max number of primes you would like to find.");
        max = input.nextInt(); //user inputs max amount
        long [] primeNums = new long [max/2]; //array that takes primes and puts them into the GCD against a number to see if its prime
        primeNums[0] = 2; primeNums[1] = 3; primeNums[2] = 5; primeNums[3] = 7; primeNums[4] = 11; primeNums[5] = 13; //gives the GCD primes to compare a number to
        int pPointer = 6; //sets pointer to 6 because we already have 6 primes
        
      for(int bucketNum = 0; bucketNum < max / 100; bucketNum++){
        for(int i = 0; i < 10; i++){    //These for loops fill the buckets and increment them if the user is asking over 100
            for(int j = 0; j < 10; j++){   
                array[i][j] = 10 * i + j + bucketNum * 100;}}
        
          for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){ //this for loop sets all the columns that we dont need to 0
                
                if(j == 2 || j == 5 || j == 4 || j == 6 || j == 8 || j == 0){
                    array[i][j] = 0;}//gets rid of the columns that are not needed
                         
                if(array[i][j] == 1){
                    array[i][j] = 0;}  //sets 1 to 0

            }
          }
 
         for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){ //this for loop sets the numbers divisible by 9 to 0
                               
                if(j == 1 || j == 3 || j == 7 || j == 9){
                    int answer = parse.parser(array[i][j]);     //brings number to primeParser
                   
                    if(answer == 9){
                        array[i][j] = 0;    //if magic9s equals 9 then the number is set to 0
              }
            }
          }
       }   
                
          for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){        //this for loop uses Euclids Algorithm to set the rest of the non primes to 0
                
                
                for(int num = 1; num < pPointer; num++){  //this for loop uses a prime that we already have and sends it to the GCD
                   
                    if(array[i][j] <= 2 * primeNums[num]){ //this saves time because it says that if the number it is looking at is
                        break;}                             //less than or equal to 2 times the number we are putting it up against then to end the loop
                    
                    if(primeNums[num] != 0 && array[i][j] != 0){ //sends the number in if the primeNum and the number we are checking isnt 0
                
               if(pGCD.GCD(array[i][j], primeNums[num]) != 0){
                    array[i][j] = 0;                    //if the GCD is not equal to 0 then it is not prime
                    break;}
             }  
                }if(array[i][j] != 0 && array[i][j] > 13){
                    primeNums[pPointer] = array[i][j];         //if the number we are checking is not equal to 0 and greater than 13
                    pPointer++; }                                //it will add the number to the prime array
            }
          }
        }
      
      for(int i = 0; i < primeNums.length; i++)
      {
          if(primeNums[i] != 0)
          {
          if(i % 10 == 0){              //outputs prime numbers and sends them to the next line after ten of them output
              System.out.println();
          }
          System.out.print(primeNums[i] + " ");
          }
          
      }
            openORclose YorN = new openORclose();//asks if you would like to re run program
            YorN.yesORno();
        }
            
    }
