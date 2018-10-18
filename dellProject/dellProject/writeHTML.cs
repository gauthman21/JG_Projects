using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

//writes specific nodes to HTML file found on the dashboard

namespace dellProject
{
    class writeHTML
    {
        public void createHTML(string[] nodeString)
        {
            using (FileStream fs = new FileStream("C:\\Users\\justin\\Desktop\\logHTML.html", FileMode.Create))     //creates an HTML file
            {

                string[] htmlText = new string[100];
                char[] delimiterChars = { '/', '<', '>' };  //characters to delete before HTML prints

                using (StreamWriter w = new StreamWriter(fs, Encoding.UTF8))    //writes to the HTML file
                {

                    w.WriteLine("<h3>All XML Nodes with same InGAC, ProcessorArchitecture, PublicKeyToken attributes.</h3>");
                    w.WriteLine("<br />");      //prints to HTML file
                    w.WriteLine("<p>");   

                    for (int i = 0; i < nodeString.Length; i++)     //prints nodes to HTML file
                    {

                        if (nodeString[i] != null)      //if location of i is not null it sets htmlText array to proper node
                        {
                            htmlText = nodeString[i].Split(delimiterChars);     //deletes symbols
                            w.WriteLine(htmlText[1] + "<br />");    //prints final nodes to HTML file
                        }
                    }
                    if (htmlText[1] == null)
                    {
                        w.WriteLine("No matches found with your specifications in XML file.");  //if nodes do not pass specifications then
                    }                                                               //this prints to the HTML file
                    w.WriteLine("</p>");
                }
            }
        }
    }
}
