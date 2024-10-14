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

            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exercises;

    }

    public List<Muscle> getMusclesForExercise(Exercise exercise){

        List<Muscle> muscles = new ArrayList<>();
        String exerciseName = exercise.getName();
        String query = "SELECT muscle.id, muscle.name, muscle.image " +
                "FROM exercise_target_muscle " +
                "INNER JOIN exercise ON exercise_target_muscle.exercise_id = exercise.id " +
                "INNER JOIN muscle ON exercise_target_muscle.muscle_id = muscle.id " +
                "WHERE exercise.name = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query))
        {

            statement.setString(1, exerciseName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int muscleId = resultSet.getInt("muscle.id");
                String muscleName = resultSet.getString("muscle.name");
                String muscleImage = resultSet.getString("muscle.image");

                Muscle muscle = new Muscle(muscleId, muscleName, muscleImage);
                muscles.add(muscle);
            }

            statement.close();
            resultSet.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return muscles;
    }


}
