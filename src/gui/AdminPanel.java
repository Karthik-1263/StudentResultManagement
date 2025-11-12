package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton deleteButton = new JButton("Delete Student");
        JButton logoutButton = new JButton("Logout");
        JButton topperButton = new JButton("Show Topper");
        JButton averageButton = new JButton("Subject Averages");

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        panel.add(topperButton);
        panel.add(averageButton);
        panel.add(logoutButton);

        add(panel);

        addButton.addActionListener(e -> new AddStudentScreen());
        viewButton.addActionListener(e -> new ViewStudentsScreen());
        deleteButton.addActionListener(e -> new DeleteStudentScreen());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        topperButton.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("student.txt"))) {
                String line;
                String topperName = "";
                int highestTotal = -1;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";", 2);
                    if (parts.length < 2) continue;

                    String[] info = parts[0].split(",");
                    if (info.length < 2) continue;
                    String name = info[1];

                    String[] subjects = parts[1].split(",");
                    int total = 0;

                    for (String subject : subjects) {
                        String[] scoreParts = subject.split(":")[1].split("/");
                        total += Integer.parseInt(scoreParts[0]);
                    }

                    if (total > highestTotal) {
                        highestTotal = total;
                        topperName = name;
                    }
                }

                JOptionPane.showMessageDialog(this, "Topper: " + topperName + " with total marks: " + highestTotal);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error calculating topper: " + ex.getMessage());
            }
        });

        averageButton.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("student.txt"))) {
                String line;
                Map<String, Integer> totalScores = new HashMap<>();
                Map<String, Integer> count = new HashMap<>();

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";", 2);
                    if (parts.length < 2) continue;

                    String[] subjects = parts[1].split(",");
                    for (String subject : subjects) {
                        String[] subjectParts = subject.split(":");
                        if (subjectParts.length < 2) continue;
                        String subjectName = subjectParts[0];
                        String[] scoreParts = subjectParts[1].split("/");
                        int score = Integer.parseInt(scoreParts[0]);

                        totalScores.put(subjectName, totalScores.getOrDefault(subjectName, 0) + score);
                        count.put(subjectName, count.getOrDefault(subjectName, 0) + 1);
                    }
                }

                StringBuilder avgText = new StringBuilder("Subject Averages:\n");
                for (String subject : totalScores.keySet()) {
                    double avg = totalScores.get(subject) / (double) count.get(subject);
                    avgText.append(subject).append(": ").append(String.format("%.2f", avg)).append("\n");
                }

                JOptionPane.showMessageDialog(this, avgText.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error calculating averages: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
