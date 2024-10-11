package Windows;

import Database.ExerciseDAO;
import Entities.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ExerciseListWindow {

    private ListView<Exercise> exerciseList;
    private ExerciseDAO exerciseDAO;

    public void show(){

        exerciseDAO = new ExerciseDAO();

        Label exerciseListLabel = new Label("Exercise List");

        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Enter exercise name");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {});

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {});

        HBox searchPanel = new HBox(searchTextField, searchButton, clearButton);
        searchPanel.setSpacing(10);

        exerciseList = new ListView<>();
        exerciseList.setPrefWidth(300);
        getAllExercises();

        VBox exerciseListPanel = new VBox(exerciseListLabel ,searchPanel, exerciseList);
        exerciseListPanel.setSpacing(10);
        exerciseListPanel.setPadding(new Insets(10));


        Scene scene = new Scene(exerciseListPanel, 800, 400);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Exercise List");
        window.show();

    }

    private void getAllExercises(){

        List<Exercise> exercises = exerciseDAO.getAllExercises();
        exerciseList.getItems().clear();
        exerciseList.getItems().addAll(exercises);

    }

}
