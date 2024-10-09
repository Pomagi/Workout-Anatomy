package panels;

import database.ExerciseDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExerciseDetailsPanel extends JPanel {

    ExerciseDAO exerciseDAO = new ExerciseDAO();
    String exerciseName;
    JLabel exerciseTitle;
    List<String> muscles;
    DefaultListModel<String> musclesModel;
    JList<String> musclesList;



    public ExerciseDetailsPanel() {

        setPreferredSize(new Dimension(1000, 540));

        exerciseTitle = new JLabel("Select an exercise to view");
        exerciseTitle.setFont(new Font("Serif", Font.BOLD, 30));

        add(exerciseTitle);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());



    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        exerciseTitle.setText(exerciseName);
    }
}
