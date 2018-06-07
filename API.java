
package com.mycompany.mavenproject.newpackage;

import com.blade.Blade;
import com.blade.mvc.handler.RouteHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import java.sql.*;


public class NewMain1 {
    
    public static void main(String[] args) {   
         //Add Blade to  project
        Blade.me().get("/search", new RouteHandler(){
            
        //Add RequestHandler to the route "/search"
        public void handle(Request request, Response response) {    
            // Read name to search from query parameter "s"
                String  search= request.query("s").get();
                
            //split search by %   
                String[] parts = search.split("%20");
                String s = null;
                
                for(int i=0;i<parts.length;i++){
            //Select students from database that match the searched name
                //parts[i]="%"+parts[i]+"%";
                
                try{
                // Load the JDBC driver
                 Class.forName("org.sqlite.JDBC");
                   
                  // Connect to a database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:E:\\tawjihii.db");
                  
                 // Create a statement
                Statement statement = connection.createStatement();
                
                //select name from data WHERE name like '%parts[i]%'
                String sql="SELECT Name FROM student WHERE  Name LIKE '%"+parts[i]+"%';";

                ResultSet resultSet = statement.executeQuery(sql);
                
                while(resultSet.next()){
                //result
                String name= resultSet.getString("Name"); 
                
                //join 
                s = String.join("AND",name);
                
               }     
              // Close the connection with data base 
                   connection.close(); 
          
            }catch(Exception e){
                
              }
    
            }
            //Return Json array of the selected students
                response.json(s);
            }
        
        }).start();
            
    }

}
