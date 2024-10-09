package org.example;

import database.ExerciseDAO;
import panels.ExerciseDetailsPanel;
import panels.ExerciseListPanel;
import panels.WorkoutDetailsPanel;
import panels.WorkoutPlannerPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Workout Anatomy");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(false);

            ExerciseDetailsPanel exerciseDetailsPanel = new ExerciseDetailsPanel();
            ExerciseListPanel exerciseListPanel = new ExerciseListPanel(exerciseDetailsPanel);
            JPanel exercisePanel = new JPanel();
            exercisePanel.setLayout(new BorderLayout());
            exercisePanel.add(exerciseListPanel, BorderLayout.WEST);
            exercisePanel.add(exerciseDetailsPanel, BorderLayout.CENTER);

            exercisePanel.setPreferredSize(new Dimension(100, 540));

            WorkoutPlannerPanel workoutPlannerPanel = new WorkoutPlannerPanel();
            WorkoutDetailsPanel workoutDetailsPanel = new WorkoutDetailsPanel();
            JPanel workoutPanel = new JPanel();
            workoutPanel.setLayout(new BorderLayout());
            workoutPanel.add(workoutPlannerPanel, BorderLayout.WEST);
            workoutPanel.add(workoutDetailsPanel, BorderLayout.CENTER);

            workoutPanel.setPreferredSize(new Dimension(100, 540));

            frame.add(exercisePanel, BorderLayout.NORTH);
            frame.add(workoutPanel, BorderLayout.CENTER);

            //frame.pack(); // Pack the frame to fit preferred sizes of components

            frame.setVisible(true);
        });

    }
}