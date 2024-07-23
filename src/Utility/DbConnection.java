
package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DbConnection {
    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //final static String DB_URL = "jdbc:mysql://localhost:3306/exampledatabase";  //this code is for the database connectivity
    final static String DB_URL ="jdbc:mysql://localhost:3306/ordermanaement?useSSL=false&serverTimezone=UTC";
    //final static String DB_URL =PathSet.getDBUrl();
    final static String USER = "root";
    final static String PASS = "Nijaadh20#";

    public static Connection connection() {
        try {
            Class.forName(JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("DB Connection Successfull..");
            return conn;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
