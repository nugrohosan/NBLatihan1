/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;
 
import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author san
 */
public class koneksi_ {
    String userDb   = "root";
    String pswdDb   = "san";
    String urlDb    = "jdbc:mysql://localhost:3306/coba";
    
//    public Connection getConnection(){
    public Connection getConnection() {
        Connection con=null;
        try{
          Class.forName("com.mysql.jdbc.Driver");
          con=  DriverManager.getConnection(urlDb, userDb, pswdDb);
          
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return con;

        
    }
}
