
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource2 {
 private static DataSource2 data;
    private Connection con;
    String url = "jdbc:mysql://localhost:3306/jardinenfant";
    String login = "root";
    String pwd = "";

    private DataSource2() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
   

    public Connection getConnection() {
        return con;
    }

    public static DataSource2 getInstance() {
        if (data == null) {
            data = new DataSource2();
        }
        return data;
    }

    

    
    
}
