package panels;

import database.ExerciseDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDetailsPanel extends JPanel {

    ExerciseDAO exerciseDAO = new ExerciseDAO();

    String exerciseName;
    JLabel exerciseTitle;

    List<String> muscles;
    DefaultListModel<String> musclesModel;
    JList<String> musclesList;

    JLabel muscleImageLabel;



    public ExerciseDetailsPanel() {

        setPreferredSize(new Dimension(1000, 540));
        setLayout(new BorderLayout());

        muscles = new ArrayList<>();
        musclesModel = new DefaultListModel<>();

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exerciseTitle = new JLabel("Select an exercise to view");
        exerciseTitle.setFont(new Font("Serif", Font.BOLD, 30));
        titlePanel.add(exerciseTitle);

        add(titlePanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        musclesList = new JList<>(musclesModel);
        musclesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musclesList.setLayoutOrientation(JList.VERTICAL);
        musclesList.setFont(new Font("Arial", Font.PLAIN, 20));
        musclesList.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if(e.getClickCount() == 1) {
                   String selectedMuscle = musclesList.getSelectedValue();
                   if(selectedMuscle != null) {
                       updateMuscleImage(selectedMuscle);
                   }
               }
           }
        });

        JScrollPane musclesListScrollPane = new JScrollPane(musclesList);
        musclesListScrollPane.setPreferredSize(new Dimension(300, 500));
        panel.add(musclesListScrollPane, BorderLayout.WEST);

        muscleImageLabel = new JLabel();
        muscleImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        muscleImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(muscleImageLabel, BorderLayout.CENTER);


        add(panel, BorderLayout.CENTER);

    }

    public void setExercise(String exerciseName) {
        this.exerciseName = exerciseName;
        exerciseTitle.setText(exerciseName);
        getMuscleGroups();
    }

    private void getMuscleGroups(){
        muscles = exerciseDAO.getMuscleGroupsByExerciseName(exerciseName);
        musclesModel.clear();

        for(String muscle : muscles){
            musclesModel.addElement(muscle);
        }
    }

    private void updateMuscleImage(String muscleName){

        String imageURL = exerciseDAO.getMuscleGroupImageURL(muscleName);
        System.out.println(imageURL);

        if(imageURL != null){

            ImageIcon muscleImage = new ImageIcon(getClass().getResource("/muscles/" + imageURL));
            System.out.println(muscleImage);
            Image scaledImage = muscleImage.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            muscleImageLabel.setIcon(new ImageIcon(scaledImage));

        }
        else{
            muscleImageLabel.setIcon(null);
        }
    }

}
