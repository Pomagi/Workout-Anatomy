package org.example;

import database.ExerciseDAO;
import panels.ExerciseListPanel;

import javax.swing.*;
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

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(false);

            ExerciseListPanel exerciseListPanel = new ExerciseListPanel();

            frame.add(exerciseListPanel);

            frame.setVisible(true);
        });

    }
}