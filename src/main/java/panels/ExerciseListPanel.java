package panels;

import database.ExerciseDAO;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseListPanel extends JPanel{

    ExerciseDAO exerciseDAO = new ExerciseDAO();
    List<String> exercises;

    public ExerciseListPanel() {

        exercises = exerciseDAO.getAllExercises();

        JList list = new JList(exercises.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setPreferredSize(new Dimension(200, 600));
        list.setFont(new Font("Arial", Font.PLAIN, 25));

        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane);

    }

}
