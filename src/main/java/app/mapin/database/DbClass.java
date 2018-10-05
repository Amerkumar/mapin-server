package app.mapin.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbClass {


    /**
     * Get database connection
     *
     * @return a Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {


        Connection conn = null;

        try (FileInputStream f = new FileInputStream("D:\\development\\mapin-server\\rest-jdbc-server\\src\\main\\java\\app\\mapin\\database\\db.properties")) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);


            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
