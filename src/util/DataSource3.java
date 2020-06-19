/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hela
 */
//design pattern => singleton (1 seule cnx)
public class DataSource3 {
    private String url="jdbc:mysql://localhost:3306/jardinenfant";
    private String login="root";
    private String pwd="";
    //variable d'instance "new"
    private Connection cnx;
    //variable de class
    //nom de laclass.nom de la variable ou de la methode
    private static DataSource3 instance;
   
    private DataSource3()
    {
        try {
            cnx=DriverManager.getConnection(url,login, pwd);
                    } catch (SQLException ex) {
            Logger.getLogger(DataSource3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //variable partagé entre les instances
    public static DataSource3 getInstance()
    {
        if (instance==null)
            instance=new DataSource3();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
