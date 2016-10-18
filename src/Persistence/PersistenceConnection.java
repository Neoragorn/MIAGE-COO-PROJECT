package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersistenceConnection {

    static PersistenceConnection inst;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://webtp.fil.univ-lille1.fr/casier";

    static public PersistenceConnection getInstance() {
        if (inst == null) {
            inst = new PersistenceConnection();
        }
        return inst;
    }

    public static Connection conn;

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        PersistenceConnection.conn = conn;
    }

    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void startConnection(String name, String mdp) throws SQLException
    {
        DriverManager.getConnection(this.DB_URL, name, mdp);
    }
    
    public PersistenceConnection() {

    }

}
