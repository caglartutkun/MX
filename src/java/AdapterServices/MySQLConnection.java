/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdapterServices;
import java.sql.*;

/**
 *
 * @author Caglar
 */
public class MySQLConnection {
    public Connection ConnectDB(){
        Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/MX", "caglar", "1907");
        }
        catch (Exception e){}
           
        return conn;
    }
}
