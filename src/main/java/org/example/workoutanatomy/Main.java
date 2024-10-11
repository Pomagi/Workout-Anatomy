package org.example.workoutanatomy;

import Windows.ExerciseListWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("WorkoutAnatomy");

        Button exerciseListButton = new Button("Exercise List");
        exerciseListButton.setOnAction(e -> {
            ExerciseListWindow exerciseListWindow = new ExerciseListWindow();
            exerciseListWindow.show();
        });

        Button workoutOrganizerButton = new Button("Workout Organizer");


        VBox vBox = new VBox();
        vBox.getChildren().addAll(exerciseListButton, workoutOrganizerButton);
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox, 800, 400);

        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {

        launch();

    }
}