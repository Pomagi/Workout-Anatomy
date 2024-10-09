package database;

import java.sql.*;
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

    public List<String> getExercisesByName(String name){

        List<String> exercises = new ArrayList<String>();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exercise WHERE name LIKE ?"))
        {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                exercises.add(resultSet.getString("name"));
            }

            resultSet.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return exercises;

    }

    public List<String> getMuscleGroupsByExerciseName(String exerciseName) {
        List<String> muscleGroups = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT muscle.name " +
                     "FROM exercise_target_muscle " +
                     "INNER JOIN exercise ON exercise_target_muscle.exercise_id = exercise.id " +
                     "INNER JOIN muscle ON exercise_target_muscle.muscle_id = muscle.id " +
                     "WHERE exercise.name = ?;"))
        {

            statement.setString(1, exerciseName);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                muscleGroups.add(resultSet.getString("name"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return muscleGroups;
    }



}
