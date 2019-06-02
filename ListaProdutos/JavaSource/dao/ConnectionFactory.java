/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ciro
 */
public class ConnectionFactory {
	//jdbc:mysql://localhost:3306/unisantos?useTimezone=true&serverTimezone=UTC
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/unisantos?useTimezone=true&serverTimezone=UTC";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    public static Connection getConnection() throws SQLException {
        try {
        	//Class.forName("com.mysql.jdbc.Driver");
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        	Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        	
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}
