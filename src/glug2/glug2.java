
package glug2;

import java.sql.*;
import java.util.*;

import java.net.URL;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;


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
import java.lang.String;
import java.io.*;
import java.lang.*;

public class glug2 
{
   

    public static void main(String args[]) throws Exception
    {
       Scanner ob= new Scanner(System.in);
       int y;
       
        
        do
        {
        System.out.println("enter 1.from type 2.from xml 3.veiw data");
        int x=ob.nextInt();
        if(x==1)
        {
            System.out.println("enter number of entries");
            int n= ob.nextInt();
            
            for(int i=1;i<=n;i++)
            {
            System.out.println("enter first name");
            String firstname=ob.next();
            System.out.println("enter last name");
            String lastname=ob.next();
            System.out.println("enter phonenumber");
            String number =ob.next();
            System.out.println("enter email");
            String email =ob.next();
            databse(firstname,lastname,number,email);
            }
            
        }
        if(x==2)
        {
            System.out.println("enter file name");
            String fname=ob.next();
            getdataxml(fname);
        }
        
        if(x==3)
        {
            dataveiw();
        }
        System.out.println("if you wish to continue press 1");
        y=ob.nextInt();
        
        }
        while(y==1);

       
    }
    public static void dataveiw() throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/glugdatabase";
        String uname="root";
        String pass="Sankeerth@2003";
        int x;
        Scanner ob= new Scanner(System.in);
        System.out.println("enter 1.to see the whole list 2.to search number wise 3.to search name wise");
        x=ob.nextInt();
        
        if(x==1)
        {
        String query ="select * from glugdatabase.projectdata "  ;
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);
        String userdata ="";
        while(rs.next())
        {
        userdata =rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4) ;
        System.out.println(userdata);
     
        }
        st.close();
        con.close();
        }
        if(x==3)
        {
            System.out.println("enter first name");
            String fname=ob.next();
            String query ="select * from glugdatabase.projectdata "  ;
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);
        String userdata ="";
        while(rs.next())
        {
        userdata =rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4) ;
        if(fname.equalsIgnoreCase(rs.getString(1)))
        System.out.println(userdata);
     
        }
        st.close();
        con.close();
        }
        if(x==2)
        {
            System.out.println("enter mobilenumber");
            String number=ob.next();
            String query ="select * from glugdatabase.projectdata "  ;
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);
        String userdata ="";
        while(rs.next())
        {
        userdata =rs.getString(1)+"  :  "+rs.getString(2)+"  :  "+rs.getString(3)+"  :  "+rs.getString(4) ;
        if(number.equalsIgnoreCase(rs.getString(4)))
        System.out.println(userdata);
     
        }
        st.close();
        con.close();
        }
    }
    
    public static void getdataxml( String x ) throws SQLException
    {
         String contactphonenumber="";
                    String contactfname = "";
                     String contactlname="";
                     String contactemail="";
        
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
try {
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = builder.parse(new String(x));


            document.getDocumentElement().normalize();
             Scanner ob= new Scanner(System.in);
            System.out.println("enter tag name");
            String tagname=ob.next();
           

            NodeList List = document.getElementsByTagName(tagname);
            for(int i = 0; i <List.getLength(); i++) {
                Node contact = List.item(i);
                if(contact.getNodeType() == Node.ELEMENT_NODE) {

                    Element contactElement = (Element) contact;
                   
                    
                    NodeList contactDetails =  contact.getChildNodes();
                    for(int j = 0; j < contactDetails.getLength(); j++){
                        Node detail = contactDetails.item(j);
                        if(detail.getNodeType() == Node.ELEMENT_NODE) 
                        {
                            Element detailElement = (Element) detail;
                            String tag=detailElement.getTagName();
                            String attribute=detailElement.getAttribute("value");
                            if(tag.contains("PHONE"))
                                contactphonenumber= attribute;
                            if(tag.contains("FIRST"))
                                contactfname= attribute;
                            if(tag.contains("LAST"))
                               contactlname= attribute;
                            if(tag.contains("EMAIL"))
                                contactemail= attribute;
                            
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
        databse(contactfname,contactlname,contactphonenumber,contactemail);
    
    }
    public static void databse(String x,String w ,String y,String z) throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/glugdatabase";
        String uname="root";
        String pass="Sankeerth@2003";
        
        String query ="insert into projectdata values ( ? , ? , ? , ? )";
        
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,x);
        st.setString(2,w);
        st.setString(3,z);
        st.setString(4,y);
        int countname= st.executeUpdate();
        
        System.out.println(countname + " rows affected");
        st.close();
        con.close();
    }

    
}
