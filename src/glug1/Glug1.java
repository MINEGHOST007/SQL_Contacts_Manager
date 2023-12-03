
package glug1;

import java.sql.*;
import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import oracle.xml.parser.v2.DOMParser;
import oracle.xml.parser.v2.XMLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Glug1 {


    public static void main(String[] args) throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/sankeerth1";
        String uname="root";
        String pass="Sankeerth@2003";
        int x;
        Scanner ob= new Scanner(System.in);
        System.out.println("enter roll number");
        x=ob.nextInt();
        String query ="select mobilenumber from glug where rollnumber=" + x ;
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);
        rs.next();
        String mobilenumber=rs.getString("mobilenumber");
        System.out.println(mobilenumber);
        st.close();
        con.close();
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
try {
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = builder.parse(new File("contacts.xml"));


            document.getDocumentElement().normalize();



            NodeList laptopList = document.getElementsByTagName("laptop");
            for(int i = 0; i <laptopList.getLength(); i++) {
                Node laptop = laptopList.item(i);
                if(laptop.getNodeType() == Node.ELEMENT_NODE) {

                    Element laptopElement = (Element) laptop;
                    System.out.println("Laptop Name: " + laptopElement.getAttribute("name"));

                    NodeList laptopDetails =  laptop.getChildNodes();
                    for(int j = 0; j < laptopDetails.getLength(); j++){
                        Node detail = laptopDetails.item(j);
                        if(detail.getNodeType() == Node.ELEMENT_NODE) {
                            Element detailElement = (Element) detail;
                            String tag=detailElement.getTagName();
                            String attribute=detailElement.getAttribute("value");
                            System.out.println("     " + tag + ": " + attribute );
                        }

                    }

                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
    
    }
    
}
