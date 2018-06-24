package com.mycompany.myapi.newpackage;
import com.blade.Blade;
import com.blade.mvc.handler.RouteHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class myAPI {
    
static ArrayList <student> Student ;

  public static void main(String[] args) {
      
      Blade.me().gzip(true).get("/search", new RouteHandler(){
       
     public void handle(Request request, Response response) {
     Connection connection = null;  
     ResultSet resultSet1 = null; 
     ResultSet resultSet2 = null;  
     ResultSet resultSet3 = null;  
     Statement statement = null;
               try 
     {  
         Class.forName("org.sqlite.JDBC");  
         
         connection = DriverManager.getConnection("jdbc:sqlite:E:\\tawjihii.db");
         System.out.println("connect");
         statement = connection.createStatement();  
               
     } 
     catch (Exception e) 
     {  
         e.printStackTrace();  
     } 
        String search = request.query("s").get(); 
       
        String[] se = search.split(" ");
  
         
             String l =se[se.length-1];
             String replaceString=search.replace(' ','%');
            
             String word ="";
             int j= se.length-1;
            
             for(int i=j;i>-1;--i){
                 System.out.println(j);
                 word+=" "+se[i];
             }

             String q="";
             for(int i=0;i<se.length;i++){
                 se[i] ="like "+ "'%" + se[i] +"%'";
                 
                 if(i==0){
                     
                     q+=" where student.Name "+se[i];
                 }
                 else{
                     
                 q+=" and student.Name "+se[i];
                 
                 }
             }
           
             String wordString=word.replace(' ','%');
             
String s="SELECT student.Year ,student.RegNo ,student.Name As Sn, student.Avg,student.SchoolId\n" +
",student.MohafathaId,student.MasterId,student.Is_WestBank ,school.school_name as scn,mohafatha.Mohafatha_name as mon,master.Master_name as msn\n" +
"FROM student\n" +
"INNER JOIN school       \n" +
"ON student.SchoolId = school.school_Id\n" +
"INNER JOIN mohafatha       \n" +
"ON student.MohafathaId = mohafatha.Mohafatha_Id\n" +
"INNER JOIN master       \n" +
"ON student.MasterId= master.Master_Id"+q;

String myname ="'"+search+"'";

            String sql2 =s;
         
                try {
                    resultSet2  = statement.executeQuery(sql2);
                    Student = new ArrayList<student> ();
                    Student.clear();
                   
            while (resultSet2.next()) {
                
            int year =resultSet2.getInt("Year");
            String RegNo = resultSet2.getString("RegNo");
            String Name = resultSet2.getString("sn");
            String replaceString1 = Name.replace('"',' ');
            String replaceString0 = replaceString1.replace("  "," ");
            float  Avg =resultSet2.getFloat("Avg");
            int  SchoolId =resultSet2.getInt("SchoolId");
            int  MohafathaId =resultSet2.getInt("MohafathaId");
            int  MasterId =resultSet2.getInt("MasterId");
            int  IsWestBank =resultSet2.getInt("Is_WestBank");
            String Scname = resultSet2.getString("scn");
            String replaceString3=Scname.replace('"',' ');
            String replaceString4=replaceString3.replace("  "," ");
            String Moname = resultSet2.getString("mon");
            String Mname = resultSet2.getString("msn");
            
           Student.add(new student(year,RegNo,Name,Avg,SchoolId,MohafathaId,MasterId,IsWestBank,replaceString4,Moname,Mname));
           }
                } catch (SQLException ex) {
                    Logger.getLogger(myAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
     
         response.json(Student);
     
           }
       })
              .enableCors(true).listen(9000).start();
   
    }  
}
class student {
    private int  year;
    private String  RegNo;
    private String  Name;
    private float  avg;
    private int SchoolId;
    private String Schoolname;
    private int  MohafathaId;
    private String Mohafathaname;
    private int  MasterId;
    private String Mastername;
    private int  IsWestBank;
   
     public student(int year,String RegNo,String Name,float avg,int SchoolId,int MohafathaId,int MasterId,int  IsWestBank,String Schoolname,String Mohafathaname,String Mastername){
        this.year = year;
        this.RegNo = RegNo;
        this.Name = Name ;
        this.avg  = avg;
        this.SchoolId =SchoolId;
        this.MohafathaId  =MohafathaId;
        this.MasterId =MasterId;
        this.IsWestBank =IsWestBank;
        this.Schoolname  =Schoolname;
        this.Mohafathaname =Mohafathaname;
        this.Mastername =Mastername;     
    }
    public String getRegNo() {
        return RegNo;
    }
    public int getyear() {
        return year;
    }
     public String getName() {
        return Name;
    }
    public float getavg() {
        return avg;
    }
       public int getSchoolId() {
        return SchoolId;
    }
    public int getMohafathaId() {
        return MohafathaId;
    }
     public int getMasterId() {
        return MasterId;
    }
    public int getIsWestBank() {
        return IsWestBank;
    }
    public String getSchoolname() {
        return Schoolname;
    }
     public String getMohafathaname() {
        return Mohafathaname;
    }
    public String getMastername() {
        return Mastername;
    }
    
     public void setSchoolId(int SchoolId) {
        this.SchoolId = SchoolId;
    }
      public void setMohafathaId(int MohafathaId) {
        this.MohafathaId= MohafathaId;
    }
       public void setMasterId(int MasterId) {
        this.MasterId= MasterId;
    }
     
    
}