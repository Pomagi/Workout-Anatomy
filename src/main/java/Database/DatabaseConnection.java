package Database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/workout_anatomy";
    private static final String user = "root";
    private static final String password = "password";

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
