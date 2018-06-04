package parsing1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Parsing1 { 
    
    public static void main(String[] args) {
        
        String csvFile = "E:\\2009.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        //create 3 hashtables
        Hashtable<String,Integer> schools=new Hashtable<String,Integer>();  
        Hashtable<String,Integer> mohafathat=new Hashtable<String,Integer>(); 
        Hashtable<String,Integer> masters=new Hashtable<String,Integer>(); 
       
 
        try {
            
             // create BufferedReader for file
            br = new BufferedReader(new FileReader(csvFile));
            
            // fill BufferedReader with data from file
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes("UTF-8"));
                // create array of string and use comma as separator 
                String[] student = line.split(cvsSplitBy);
                
               //print elements 
                   System.out.println("الطالب[المديرية =" +  student[0] + ", المدرسة= " + student[1] + " , رقم الجلوس =" + 
                    student[2]+ ", الاسم الاول =" +  student[3] + ", اسم الاب = " +  student[4] + ", اسم الجد = " + student[5] + 
                    " , اسم العائلة = " +  student[6] +", المجموع = " +  student[7] +", المعدل = " +  student[8] +
                    ", الفرع = " +  student[9] +"]");    
          
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

 