package Database;

import Entities.Exercise;
import Entities.Muscle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class ExerciseDAO {

    public List<Exercise> getAllExercises() {

        List<Exercise> exercises = new ArrayList<Exercise>();

        String query = "SELECT * FROM exercise";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String video = resultSet.getString("video");

                Exercise exercise = new Exercise(id, name, video);
                exercises.add(exercise);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exercises;

    }

    public List<Exercise> getExercisesByName(String name) {

        List<Exercise> exercises = new ArrayList<>();

        String query = "SELECT * FROM exercise WHERE name = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String exerciseName = resultSet.getString("name");
                String video = resultSet.getString("video");

                Exercise exercise = new Exercise(id, exerciseName, video);
                exercises.add(exercise);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exercises;

    }


}
