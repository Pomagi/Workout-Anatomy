package panels;

import database.ExerciseDAO;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ExerciseListPanel extends JPanel{

    ExerciseDAO exerciseDAO = new ExerciseDAO();
    List<String> exercises;
    DefaultListModel<String> exercisesModel;
    JList<String> list;
    ExerciseDetailsPanel exerciseDetailsPanel;

    public ExerciseListPanel(ExerciseDetailsPanel exerciseDetailsPanel) {
        this.exerciseDetailsPanel = exerciseDetailsPanel;

        setPreferredSize(new Dimension(370, 540));
        setLayout(new BorderLayout());

        exercises = new ArrayList<>();
        exercisesModel = new DefaultListModel<>();

        getAllExercises();

        list = new JList<>(exercisesModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setFont(new Font("Arial", Font.PLAIN, 20));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String selectedExercise = list.getSelectedValue();
                    if(selectedExercise != null) {
                        exerciseDetailsPanel.setExerciseName(selectedExercise);
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 350));
        add(scrollPane, BorderLayout.CENTER);


        JPanel searchPanel = new JPanel();
        JTextField exerciseSearchField = new JTextField(15);
        exerciseSearchField.setFont(new Font("Arial", Font.PLAIN, 17));

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {

            if(!exerciseSearchField.getText().isEmpty()){
                updateExerciseList(exerciseSearchField.getText());
            } else {
                getAllExercises();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            exerciseSearchField.setText("");
           getAllExercises();
        });

        searchPanel.add(exerciseSearchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        add(searchPanel, BorderLayout.NORTH);

    }

    private void updateExerciseList(String name){

        exercises = exerciseDAO.getExercisesByName(name);

        exercisesModel.clear();
        for (String exercise : exercises) {
            exercisesModel.addElement(exercise);
        }

    }

    private void getAllExercises(){

        exercises = exerciseDAO.getAllExercises();

        exercisesModel.clear();
        for (String exercise : exercises) {
            exercisesModel.addElement(exercise);
        }
    }

}
