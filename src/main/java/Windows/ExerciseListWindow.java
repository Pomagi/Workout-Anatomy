package Windows;

import Database.ExerciseDAO;
import Entities.Exercise;
import Entities.Muscle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.util.List;

public class ExerciseListWindow {

    private ListView<Exercise> exerciseList;
    private ExerciseDAO exerciseDAO;


    private Exercise selectedExercise;
    private Label selectedExerciseLabel;

    private ListView<Muscle> muscleList;
    private ImageView muscleImage;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    public void show(){

        exerciseDAO = new ExerciseDAO();

        Label exerciseListLabel = new Label("Exercise List");

        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Enter exercise name");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {

            if(!searchTextField.getText().isEmpty()){
                getExercisesByName(searchTextField.getText());
            }
            else{
                getAllExercises();
            }

        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {

            searchTextField.clear();
            getAllExercises();

        });

        HBox searchPanel = new HBox(searchTextField, searchButton, clearButton);
        searchPanel.setSpacing(10);

        exerciseList = new ListView<>();
        exerciseList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedExercise = exerciseList.getSelectionModel().getSelectedItem();
                selectedExerciseLabel.setText(selectedExercise.getName());
                getAllMusclesForExercise(selectedExercise);

                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }

                media = new Media(getClass().getResource("/videos/" + selectedExercise.getVideo()).toString());
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            }

        });
        exerciseList.setPrefWidth(300);
        getAllExercises();

        VBox exerciseListPanel = new VBox(exerciseListLabel ,searchPanel, exerciseList);
        exerciseListPanel.setSpacing(10);
        exerciseListPanel.setPadding(new Insets(10));

        selectedExerciseLabel = new Label("Exercise not selected");

        muscleList = new ListView<>();
        muscleList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                Muscle selectedMuscle = muscleList.getSelectionModel().getSelectedItem();
                muscleImage.setImage(new Image(getClass().getResourceAsStream("/images/" + selectedMuscle.getImage())));
            }

        });
        muscleList.setPrefWidth(300);

        VBox selectedExerciseMuscleListPanel = new VBox(selectedExerciseLabel, muscleList);
        selectedExerciseMuscleListPanel.setSpacing(10);
        selectedExerciseMuscleListPanel.setPadding(new Insets(10));

        muscleImage = new ImageView();
        muscleImage.setImage(new Image(getClass().getResourceAsStream("/images/blank.png")));
        muscleImage.setFitHeight(400);
        muscleImage.setFitWidth(400);

        VBox muscleImagePanel = new VBox(muscleImage);
        muscleImagePanel.setPadding(new Insets(35,0,0,0));

        media = new Media(getClass().getResource("/videos/video.mp4").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaView =  new MediaView(mediaPlayer);
        VBox mediaPanel = new VBox(mediaView);
        mediaPanel.setSpacing(10);
        mediaPanel.setPadding(new Insets(35, 0, 0, 0));

        HBox generalPanel = new HBox(exerciseListPanel, selectedExerciseMuscleListPanel, muscleImagePanel, mediaPanel);
        generalPanel.setSpacing(10);
        generalPanel.setPadding(new Insets(10));

        Scene scene = new Scene(generalPanel, 1800, 500);
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

    private void getExercisesByName(String name){
        List<Exercise> exercises = exerciseDAO.getExercisesByName(name);
        exerciseList.getItems().clear();
        exerciseList.getItems().addAll(exercises);
    }

    private void getAllMusclesForExercise(Exercise exercise){

        if(exercise != null){
            List<Muscle> muscles = exerciseDAO.getMusclesForExercise(exercise);
            muscleList.getItems().clear();
            muscleList.getItems().addAll(muscles);
        }

    }

}
