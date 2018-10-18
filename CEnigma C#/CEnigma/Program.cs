using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataSet;
using InputEnigma;
using Location;
using Odometer;
using Portals;
using FileOperations;
using Encryption;
using Properties;
using WheelV3;
using XML_Output;
using XMLoutput;


//Justin Gauthier
//CIS 220
//Ed Cauthorn
//10/26/2013 Rough draft of final Enigma Machine
//11/2/2013 Added and fixed some errors
//11/12/2013 Added and fixed more errors
//11/18/2013 Program finally runs
//11/21/2013 Cleaned up program and added the proper comments

//This program allows the user to encrypt and decrypt a string or file

namespace CEnigma
{
    class Program
    {
        static void Main()
        {
            
            FileOperations.Files file = new Files();//initializes the use of the Files OBJs
            Location.LocateComp getCompIP = new LocateComp();//initializes the use of the Location LocateComp OBJ
            XMLoutput.Output1 getOutputXML = new Output1();//initializes the use of the XMLoutput Output1 OBJ
            Encryption.Output getOutput = new Output();//initializes the use of the Encryption Output OBJs
            Portals.ScreenPrint getScreenPrint = new ScreenPrint();//initializes the use of the Portals ScreenPrint OBJ
            Portals.Display getDisplay = new Display();//initializes the use of the Portals Display OBJs

            getCompIP.GetIP();//gets the IP Address from the computer running the program
            getDisplay.startText();//opens the title of the Menu
            getDisplay.menu();//opens the options of the Menu
            string userinput = Console.ReadLine();//allows user to input the desired Menu selection
            int key1, key2, key3;//initializes 3 int for the starting points of the 3 rotors

           

            if (userinput == "a" || userinput == "A")//if the user types 'a' or 'A' it allows the program to run this code only
            {

                getScreenPrint.centerJustified("Please enter a key value for Rotor I.");
                key1 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 1st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor II.");
                key2 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 2nd rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor III.");
                key3 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 3rd rotor

                Odometer.ThreeWheels getThreeWheels = new Odometer.ThreeWheels(key1, key2, key3);//initializes the use of the ThreeWheels OBJ and sets the 3 rotors
                
                getScreenPrint.centerJustified("Input text you would like to encrypt!");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//grabs string the user wants to encrypt
                    string tempInput = getThreeWheels.encode(userinput);//encrypts string
                    file.writeFileLine(tempInput, "encrypt.txt");//writes the encrypted string to a text file

                getOutput.encryptOut("encrypt.txt");//outputs encrypted string
                    Console.WriteLine("");
                
                getScreenPrint.centerJustified("Encrypted Text File Created!");
                    Console.WriteLine("");
                    getOutput.printHTMLEncrypt("encrypt.txt", "encrypt.html");//creates a html file of the encrypted string
                
                getScreenPrint.centerJustified("Encrypted HTML File Created!");
                    Console.WriteLine("");
                    getOutputXML.readFile("encrypt.txt");//reads the encrypted text file and converts it to an xml file
                    getOutputXML.addText("encrypt.txt", "encrypt.xml");
                
                getScreenPrint.centerJustified("Encrypted XML File Created!");
                    Console.WriteLine("");
                
                getScreenPrint.centerJustified("Press any key to continue or press 'X' to exit.");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows the user to start over or exit the program

                if (userinput == "x" || userinput == "X")
                {
                    getDisplay.shutdownText();//if the user inputs 'x' or 'X' then it shows the shutdown text and ends the program
                }
                else
                {
                    Console.Clear();//clears the Console screen
                    Main();//if the user inputs and other value it restarts the program

                }
            }

            if (userinput == "b" || userinput == "B")//if the user types 'b' or 'B' it allows the program to run this code only
            {
                getScreenPrint.centerJustified("Please enter a key value for Rotor I.");
                key1 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 1st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor II.");
                key2 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 2st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor III.");
                key3 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 3st rotor

                Odometer.ThreeWheels getThreeWheels = new Odometer.ThreeWheels(key1, key2, key3);//initializes the use of the ThreeWheels OBJ and sets the 3 rotors

                try//if the user inputs the wrong file name it throws an Exception
                {
                    getScreenPrint.centerJustified("Input name of text file you would like to encrypt!");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows user to input file name
                    
                    System.IO.StreamReader myFile = new System.IO.StreamReader(userinput);
                    string encryptFile = myFile.ReadToEnd();//reads the file and encrypts it
                    string tempFile = Convert.ToString(getThreeWheels.encode(encryptFile));
                    file.writeFileLine(tempFile, "encryptFile.txt");//writes the encrypted file
                }
                catch (Exception)
                {
                    getScreenPrint.centerJustified("Typed Incorrect key. Program will restart.");//outputs Exception
                    Main();
                }

                getScreenPrint.centerJustified("Encrypted Text File Created!");
                    Console.WriteLine("");
                    getOutput.printHTMLEncrypt("encryptFile.txt", "encryptFile.html");//converts the encrypted text file to an html file
                
                getScreenPrint.centerJustified("Encrypted HTML File Created!");
                    Console.WriteLine("");
                    getOutputXML.readFile("encryptFile.txt");//reads in the text file and converts it to an xml file
                    getOutputXML.addText("encryptFile.txt", "encryptFile.xml");
                
                getScreenPrint.centerJustified("Encrypted XML File Created!");
                    Console.WriteLine("");

                getScreenPrint.centerJustified("Press any key to continue or press 'X' to exit.");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows the user to input a value to restart the program or exit

                if (userinput == "x" || userinput == "X")
                {
                    getDisplay.shutdownText();//displays shutdown text and ends program if user inputs 'x' or 'X'
                }
                else
                {
                    Console.Clear();//clears the Console screen
                    Main();//restarts the program

                }
            }

            if (userinput == "c" || userinput == "C")//if the user types 'c' or 'C' it allows the program to run this code only
            {
                getScreenPrint.centerJustified("Please enter a key value for Rotor I.");
                key1 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 1st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor II.");
                key2 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 2st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor III.");
                key3 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 3st rotor

                Odometer.ThreeWheels getThreeWheels = new Odometer.ThreeWheels(key1, key2, key3);//initializes the use of the ThreeWheels OBJ and sets the 3 rotors
                
                getScreenPrint.centerJustified("Input text you would like to decrypt!");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows the user to input a string to decrypt
                    string tempInput = getThreeWheels.decode(userinput);//decrypts string
                    file.writeFileLine(tempInput, "decrypt.txt");//writes decrypted string to text file

                getOutput.decryptOut("decrypt.txt");//outputs decrypted string
                    Console.WriteLine("");
                
                getScreenPrint.centerJustified("Decrypted Text File Created!");
                    Console.WriteLine("");
                    getOutput.printHTMLEncrypt("decrypt.txt", "decrypt.html");//converts decrypted text file to a html file
                
                getScreenPrint.centerJustified("Decrypted HTML File Created!");
                    Console.WriteLine("");
                    getOutputXML.readFile("decrypt.txt");//reads text file and converts it to an xml file
                    getOutputXML.addText("decrypt.txt", "decrypt.xml");
                
                getScreenPrint.centerJustified("Decrypted XML File Created!");
                    Console.WriteLine("");
                
                getScreenPrint.centerJustified("Press any key to continue or press 'X' to exit.");
                    Console.WriteLine("");    
                    userinput = Console.ReadLine();//allows user to either restart program or end it

                if (userinput == "x" || userinput == "X")
                {
                    getDisplay.shutdownText();//if user inputs 'x' or 'X' then the program will show shutdown text and exit
                }
                else
                {
                    Console.Clear();//clears the Console screen
                    Main();//restarts program

                }
            }
            if (userinput == "d" || userinput == "D")//if the user types 'd' or 'D' it allows the program to run this code only
            {
                getScreenPrint.centerJustified("Please enter a key value for Rotor I.");
                key1 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 1st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor II.");
                key2 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 2st rotor
                
                getScreenPrint.centerJustified("Please enter a key value for Rotor III.");
                key3 = Convert.ToInt32(Console.ReadLine());//asks and grabs the users input for the 3st rotor

                Odometer.ThreeWheels getThreeWheels = new Odometer.ThreeWheels(key1, key2, key3);//initializes the use of the ThreeWheels OBJ and sets the 3 rotors

                try//if file is not found it throws an Exception
                {
                    getScreenPrint.centerJustified("Input name of text file you would like to decrypt!");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows user to input the file name he wants to decrypt

                    System.IO.StreamReader myFile = new System.IO.StreamReader(userinput);
                    string encryptFile = myFile.ReadToEnd();//reads file
                    string tempFile = Convert.ToString(getThreeWheels.decode(encryptFile));//decrypts file
                    file.writeFileLine(tempFile, "decryptFile.txt");//writes a decrypted text file
                }
                catch (Exception)//outputs Exception
                {
                    getScreenPrint.centerJustified("Typed Incorrect key. Program will resart.");
                    Main();
                }  
                
                getScreenPrint.centerJustified("Decrypted Text File Created!");
                    Console.WriteLine("");
                    getOutput.printHTMLEncrypt("decryptFile.txt", "decryptFile.html");//converts text file to html file
                   
                getScreenPrint.centerJustified("Decrypted HTML File Created!");
                    Console.WriteLine("");
                    getOutputXML.readFile("decryptFile.txt");//reads text file and converts it to a xml file
                    getOutputXML.addText("decryptFile.txt", "decryptFile.xml");
                
                getScreenPrint.centerJustified("Decrypted XML File Created!");
                    Console.WriteLine("");
                
                getScreenPrint.centerJustified("Press any key to continue or press 'X' to exit.");
                    Console.WriteLine("");
                    userinput = Console.ReadLine();//allows user to enter an input to either end the program or restart it

                if (userinput == "x" || userinput == "X")
                {
                    getDisplay.shutdownText();//starts shutdown text and ends program if user inputs 'x' or 'X'
                }
                else
                {
                    Console.Clear();//clears the Console screen
                    Main();//restarts program

                }
            }

            if (userinput == "x" || userinput == "X")//if the user types 'x' or 'X' it allows the program to run this code only
            {
                getDisplay.shutdownText();//starts shutdown text and ends program
            }
            else
            {
                getScreenPrint.centerJustified("Invalid entry. Press any key to retry.");
                Console.ReadKey();//lets the user know they input an invalid value in the menu and allows him to retry
                Console.Clear();//clears the Console screen
                Main();//restarts program
            }
        }
    }
}
