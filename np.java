
package com.mycompany.mavenproject2;

import com.blade.Blade;
import com.blade.mvc.handler.RouteHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;

public class np {

    public static void main(String[] args) {
   Blade.me().get("/search", new RouteHandler(){
        
        public void handle(Request request, Response response) {
                String  search= request.query("s").get();
                response.text(search);
            }
        }).start();
     
    /*
       Blade.me().get("/", (req, res) -> {
        res.text("Hello Blade");
    }).start();
    */

    
      }
}
