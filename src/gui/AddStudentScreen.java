package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class AddStudentScreen extends JFrame {

    private final ArrayList<String> subjectList = new ArrayList<>();
    private final JLabel subjectInfoLabel = new JLabel("No subjects added.");

    public AddStudentScreen() {
        setTitle("Add Student");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton addSubjectBtn = new JButton("➕ Add Subject");
        JButton saveBtn = new JButton("💾 Save Student");
        JButton backBtn = new JButton("🔙 Back");

        addSubjectBtn.addActionListener(e -> openSubjectDialog());

        saveBtn.setBackground(new Color(45, 140, 240));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        saveBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (subjectList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add at least one subject.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String subjectData = String.join(";", subjectList);

            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/students.txt", true))) {
                bw.write(id + "," + name + "," + subjectData + "," + password);
                bw.newLine();
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                dispose();
                new AdminPanel();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving student data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.setBackground(new Color(150, 150, 150));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            dispose();
            new AdminPanel();
        });

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(addSubjectBtn);
        formPanel.add(subjectInfoLabel);

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(backBtn);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openSubjectDialog() {
        JTextField subjectField = new JTextField();
        JTextField totalField = new JTextField();
        JTextField scoredField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Subject Name:"));
        panel.add(subjectField);
        panel.add(new JLabel("Total Marks:"));
        panel.add(totalField);
        panel.add(new JLabel("Marks Scored:"));
        panel.add(scoredField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Subject",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String subject = subjectField.getText().trim();
            String total = totalField.getText().trim();
            String scored = scoredField.getText().trim();

            if (subject.isEmpty() || total.isEmpty() || scored.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All subject fields are required.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int totalVal = Integer.parseInt(total);
                int scoredVal = Integer.parseInt(scored);
                if (scoredVal > totalVal || scoredVal < 0 || totalVal <= 0) {
                    throw new NumberFormatException();
                }

                subjectList.add(subject + ":" + scored + "/" + total);
                subjectInfoLabel.setText(subjectList.size() + " subject(s) added.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numeric marks.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
