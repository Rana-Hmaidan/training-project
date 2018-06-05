package parsing3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Parsing3 { 
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        String csvFile = "E:\\2009.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        //create 3 hashtables
        Hashtable<String,Integer> schools=new Hashtable<String,Integer>();  
        Hashtable<String,Integer> mohafathat=new Hashtable<String,Integer>(); 
        Hashtable<String,Integer> masters=new Hashtable<String,Integer>(); 
        
        //schools.containsKey("العائشية");
        
        try {
            
             // create BufferedReader for file
            br = new BufferedReader(new FileReader(csvFile));
            
            // fill BufferedReader with data from file
            while ((line = br.readLine()) != null) {
                
               // line = new String(line.getBytes("UTF-8"));
                // create array of string and use comma as separator 
                String[] student = line.split(cvsSplitBy);
                 
                //Year	RegNo	Name	Avg	SchoolId	MohafathaId	MasterId	Is_WestBank
        
                int SchoolId = schools.size();
                int MohafathaId = mohafathat.size();
                int MasterId = masters.size();
                int Is_WestBank = 0; //للضفة 0 ولغزة 1
                
                
                try{
                // Load the JDBC driver
                 Class.forName("com.mysql.jdbc.Driver");
                 //System.out.println("Driver loaded");
                  
                  // Connect to a database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tawjihii" , "root", "");
               // System.out.println("Database connected");
                  
                 // Create a statement
                Statement statement = connection.createStatement();
                
            //add elements to hash table and increase id for each and adding to database table
               if(!schools.containsKey(student[1])){ 
                   
            //if hash table not contains elemnt adding it 
                schools.put(student[1],++SchoolId);
                
            //insert element to databse              
                String sql="INSERT INTO school(school_Id,school_name) VALUES('"+SchoolId+"','"+student[1]+"');"; 
                 
                //  System.out.println(sql);
                    
                  statement.executeUpdate(sql);
                  
                  //System.out.println(SchoolId);
                  // System.out.println(student[1]);
                  //statement.addBatch(sql);
            
               }
             //System.out.println(schools);
             
                if(!mohafathat.containsKey(student[0])){  
                    
                mohafathat.put(student[0],++MohafathaId);
                
                String sql2="INSERT INTO mohafatha(Mohafatha_Id,Mohafatha_name) VALUES('"+MohafathaId+"','"+student[0]+"');";  
                 
                  statement.executeUpdate(sql2);
                 //statement.addBatch(sql2);
                // connection.commit();
   
                }
                
                
                if(!masters.containsKey(student[9])){  
                    
                masters.put(student[9],++MasterId);
                      
    
             String sql3="INSERT INTO master(Master_Id,Master_name) VALUES('"+MasterId+"','"+student[9]+"');"; 
             
                  statement.executeUpdate(sql3);
                 // statement.executeQuery(sql3);
                 //statement.addBatch(sql3);
                
                  }
                
               //print elements 
                 /*  System.out.println("الطالب[المديرية =" +  student[0] + ", المدرسة= " + student[1] + " , رقم الجلوس =" + 
                    student[2]+ ", الاسم الاول =" +  student[3] + ", اسم الاب = " +  student[4] + ", اسم الجد = " + student[5] + 
                    " , اسم العائلة = " +  student[6] +", المجموع = " +  student[7] +", المعدل = " +  student[8] +
                    ", الفرع = " +  student[9] +"]");  
                   
                  */
                
                int year=2009;
                String RegNo=student[0];
                String name=student[3]+""+student[4]+""+student[5]+""+student[6] ;
                double avg=Double.parseDouble(student[8]);
                
 String sql4="INSERT INTO student(Year,RegNo,Name,Avg ,SchoolId,MohafathaId,MasterId,Is_WestBank) VALUES('"+  year +"','"+ RegNo + "','"+ name +"','"+ avg +"','"+SchoolId+"','"+MohafathaId + "','" +MasterId+"','"+Is_WestBank+"');"; 
                 
                statement.executeUpdate(sql4);
                
                //statement.addBatch(sql4);
      
               //connection.commit(); 
               
            // Close the connection with data base 
                   connection.close(); 
                  
            }catch(Exception e){
                    
            }
   
            }
            
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       
        
    
    }//end of main
}//end of class

 