package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    public List<String> getAllExercises(){

        List<String> exercises = new ArrayList<String>();

        try(Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM exercise"))
        {

            while(resultSet.next()){
                exercises.add(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exercises;

    }

}
