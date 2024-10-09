package panels;

import javax.swing.*;
import java.awt.*;

public class ExerciseDetailsPanel extends JPanel {

    String exerciseName;

    public ExerciseDetailsPanel() {

        setPreferredSize(new Dimension(1000, 800));

    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
