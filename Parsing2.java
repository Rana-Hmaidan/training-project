package parsing2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.sql.*;

public class Parsing2 { 
    public static void main(String[] args) {
        
        String csvFile = "E:\\2009.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        //create 3 hashtables
        Hashtable<Integer,String> schools=new Hashtable<Integer,String>();  
        Hashtable<Integer,String> mohafathat=new Hashtable<Integer,String>(); 
        Hashtable<Integer,String> masters=new Hashtable<Integer,String>(); 
   
        try {
            
             // create BufferedReader for file
            br = new BufferedReader(new FileReader(csvFile));
            // fill BufferedReader with data from file 
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes("UTF-8"));
                // create array of string and use comma as separator 
                String[] student = line.split(cvsSplitBy);
                   System.out.println("الطالب[المديرية =" +  student[0] + ", المدرسة= " + student[1] + " , رقم الجلوس =" + 
                    student[2]+ ", الاسم الاول =" +  student[3] + ", اسم الاب = " +  student[4] + ", اسم الجد = " + student[5] + 
                    " , اسم العائلة = " +  student[6] +", المجموع = " +  student[7] +", المعدل = " +  student[8] +
                    ", الفرع = " +  student[9] +"]");  
     
                   
            for (int i=0;i<student.length;i++){
               int school_Id =i;
               String school_name =student[i];
               
               if(school_name != ""){
                    try{
                //connect to database 
                  Class.forName("com.mysql.jdbc.Driver");

                  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tawjihi" , "root", "");
                
                  Statement statement = connection.createStatement();
                  
                  String sql="INSERT INTO school(school_Id , school_name) VALUES ('"+ school_Id+"','"+ school_name + "' )";  
                  
                  statement.executeUpdate(sql);
     
            }catch(Exception e){
                
            }
                    
               }
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
        
        

        
    }
    
}

 