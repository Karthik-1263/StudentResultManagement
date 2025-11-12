package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;



public class DeleteStudentScreen extends JFrame {
    private String lastDeletedLine;

    public DeleteStudentScreen() {
        setTitle("Delete Student");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        JTextField rollField = new JTextField();
        JButton deleteBtn = new JButton("Delete");
        JButton undoBtn = new JButton("Undo");

        add(new JLabel("Enter Roll No:"));
        add(rollField);
        add(deleteBtn);
        add(undoBtn);

        deleteBtn.addActionListener(e -> {
            String roll = rollField.getText();
            File file = new File("data/students.txt");
            if (!file.exists()) return;

            try {
                List<String> lines = new ArrayList<>(java.nio.file.Files.readAllLines(file.toPath()));
                List<String> updated = new ArrayList<>();
                lastDeletedLine = null;

                for (String line : lines) {
                    if (line.contains("," + roll + ",")) {
                        lastDeletedLine = line;
                    } else {
                        updated.add(line);
                    }
                }

                java.nio.file.Files.write(file.toPath(), updated);
                JOptionPane.showMessageDialog(this, "Student deleted. You can undo within 5s.");

                new javax.swing.Timer(5000, ev -> lastDeletedLine = null).start();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        undoBtn.addActionListener(e -> {
            if (lastDeletedLine != null) {
                try (FileWriter fw = new FileWriter("data/students.txt", true)) {
                    fw.write(lastDeletedLine + "\n");
                    JOptionPane.showMessageDialog(this, "Undo successful!");
                    lastDeletedLine = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No action to undo!");
            }
        });

        setVisible(true);
    }
}
