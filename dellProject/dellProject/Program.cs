using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

//Justin Gauthier
//7/21/2014

//This program takes in an XML file. Searches for specific attributes, then takes the XML nodes with the 
//specific attributes and writes them to an html file.

namespace dellProject
{
    //allows the user to input location of XML file.
    class Program
    {
        static void Main(string[] args)
        {
            readXML sendFile = new readXML();
            string fileName;

            Console.WriteLine("Please enter XML file name.");   //prints text to console.
            fileName = Convert.ToString(Console.ReadLine());    //allows user input
            
            sendFile.XMLread(fileName);     //sends user input to readXML class.
        }
    }
}