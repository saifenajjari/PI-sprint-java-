/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aissa
 */
public class DataSource1 {

    private static DataSource1 instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/jardinenfant";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private DataSource1() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource1 getInstance() {
        if (instance == null) {
            instance = new DataSource1();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
