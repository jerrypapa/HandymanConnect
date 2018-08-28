package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static  Connection connection;

    public static Connection getConnection(){
        String user=("root");
        String pwd=("Wilfrida2017");
        String url=("jdbc:mysql://localhost:3306/Handymen");

        try {
            connection=DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.print(getConnection());
    }

}
