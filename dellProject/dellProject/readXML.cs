using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Threading.Tasks;
using System.Threading;

//reads in XML file and parses through specific attributes

namespace dellProject
{
    class readXML
    {
        XmlDocument doc = new XmlDocument();    //allows the use of reading in an XML file.
        Program restart = new Program();

        public void XMLread(string fileName)
        {
            try
            {
                doc.Load(fileName);     //reads in XML file
            }
            catch (Exception)       //throws exception
            {
                Console.WriteLine("XML file not found. Please try again.");     //prints to console if exception is made
                Thread.Sleep(2000);     //delays exit
                Environment.Exit(0);        //restarts program                
            }
            parseXML();
        }
        public void parseXML()
        {
            string attrVal, attrPro, attrPKT;
            string[] node = new string[100];

            writeHTML write = new writeHTML();
            XmlNodeList elemList = doc.SelectNodes("/FileList/File");   //grabs all nodes from XML file

            for (int i = 0; i < elemList.Count; i++)    //loop searches through XML file for specific attributes
            {
                attrVal = elemList[i].Attributes["InGAC"].Value;                //grabs values of attributes
                attrPro = elemList[i].Attributes["ProcessorArchitecture"].Value;
                attrPKT = elemList[i].Attributes["PublicKeyToken"].Value;

                if (attrVal == "true" & attrPro == "X86" & attrPKT == "b03f5f7f11d50a3a")   //if all attributes in node equal these then it sets the array
                {                                                                           //with that node.
                    node[i] = elemList[i].OuterXml;     //sets spot in array to
                }                                       //the specific node
            }
            write.createHTML(node);    //sends all the specified nodes to writeHtml class
        }
    }
}

