package util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DataSource {

    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jardinenfant";
    private static final String USer = "root";
    private static final String Password = "";
    private static Connection con;
    static DataSource mycnx ;
      Connection cnx;
    
    public Connection getCnx (){
        return cnx ; 
    }

    public static Connection connect() {
        try {
            Class.forName(Driver);
            System.out.println("Driver O.K.");
            con = DriverManager.getConnection(URL, USer, Password);

            System.out.println("Connection etablie");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void disconnect() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void dbExecuteQuery(String sql) throws SQLException {
        Statement st = null;
        try {

            connect();
            st = con.createStatement();
            st.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("query erreur");
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
        }
        disconnect();
    }

    public static ResultSet dbExecute(String sql) throws ClassNotFoundException, SQLException {
        Statement st = null;
        ResultSet rs = null;

        try {
            connect();
            st = con.createStatement();
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("dbExecute erreur" + e);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            disconnect();

        }
        return rs;
    }
    
        public static DataSource getInstance(){
        if(mycnx==null)
        {
           mycnx= new DataSource();}
      
        return mycnx;}
        
        
        
        
    
}
