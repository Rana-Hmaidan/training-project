
package com.mycompany.mavenproject.newpackage;

import com.blade.Blade;
import com.blade.mvc.handler.RouteHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import java.sql.*;


public class NewMain {

    public static void main(String[] args) {
        
         //Add Blade to  project
        Blade.me().get("/search", new RouteHandler(){
            
        //Add RequestHandler to the route "/search"
        public void handle(Request request, Response response) {
            
            // Read name to search from query parameter "s"
                String  search= request.query("s").get();
               
            //Select students from database that match the searched name
                getData(search);
               
           //Return Json array of the selected students
                
                response.json(search);
            }

            private void getData(String search) {
                
                try{
                // Load the JDBC driver
                 Class.forName("org.sqlite.JDBC");
                   
                  // Connect to a database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:E:\\tawjihii.db");
                  
                 // Create a statement
                Statement statement = connection.createStatement();
                
                //select name from data where name = search 
                String sql="SELECT Name FROM student WHERE  Name="+search;
                
                ResultSet resultSet = statement.executeQuery(sql);
                
                while(resultSet.next()){
                    
                String name= resultSet.getString("Name"); 
                
        }       
                 // Close the connection with data base 
                   connection.close(); 
          
            }catch(Exception e){
                
            }
 

            }
        
        }).start();
             
     /*
        
        Blade.me().get("/search", new RouteHandler(){
        
        public void handle(Request request, Response response) {
                String  search= request.query("s").get();
                response.text(search);
            }
        }).start();
     */
    /*
       Blade.me().get("/", (req, res) -> {
        res.text("Hello Blade");
    }).start();
    */
  
        
    }
}
