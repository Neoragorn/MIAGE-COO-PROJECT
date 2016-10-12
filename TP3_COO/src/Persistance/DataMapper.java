/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

/**
 *
 * @author casier
 */
import java.sql.*;


public class DataMapper {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://webtp.fil.univ-lille1.fr/casier";
	public static Connection conn;

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        DataMapper.conn = conn;
    }
        
    public String getJDBC_DRIVER()
    {
	return JDBC_DRIVER;
    }
    
    public String getDB_URL()
    {
        return DB_URL;
    }
}
    