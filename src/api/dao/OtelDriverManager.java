package api.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OtelDriverManager {
    static String url ="jdbc:mysql://localhost:3306/upod_otel";
    static String username = "upod";
    static String password = "12345678ç.";
    static String logFile ="/Users/sercan/Desktop/JDBC.log";
    private static Connection connection;

    public static Connection getConnection(){
        createConnection();
        return connection;
    }

    public static void main(String[] args) {
        createConnection();
    }


    static void createConnection(){
//        System.out.println("Using Class.forName() to load driver!");

        try{
            Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
            if(clazz == null){
                System.out.println("com.mysql.cj.jdbc.Driver is not loaded");
            }
        } catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        PrintWriter printWriter = null;

        try{
            printWriter = new PrintWriter(new File(logFile));
        } catch (FileNotFoundException ex){
            System.out.println("In driver manager log file cannot be created." + ex.getMessage());
        }

        DriverManager.setLogWriter(printWriter);
        //Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex){
            System.out.println("In driver connection there was a problem!" + ex.getMessage());
        }

        //checkConnection(connection);
    }

    private static void checkConnection(Connection connection){
        if(connection != null){
            System.out.println("Connected!");
            try{
                String catalog = connection.getCatalog();
                System.out.println("Catalog is " + catalog);
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            try {
                int majorVersion = connection.getMetaData().getDriverMajorVersion();
                int minorVersion = connection.getMetaData().getDriverMinorVersion();
                System.out.println("JDBC version is " + majorVersion +  "." +minorVersion);

            } catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        }
    }

}
