package gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class StudentLoginScreen extends JFrame {

    public StudentLoginScreen() {
        setTitle("Student Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));

        JLabel title = new JLabel("Student Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField idField = new JTextField();
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("Back");

        loginBtn.setBackground(new Color(45, 140, 240));
        loginBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(150, 150, 150));
        backBtn.setForeground(Color.WHITE);

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String pass = new String(passField.getPassword()).trim();
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader("data/students.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(id) && data.length >= 4 && data[data.length - 1].equals(pass)) {
                        found = true;
                        dispose();
                        new StudentViewScreen(data[0], data[1], data[2]);
                        break;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading student data.");
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Student not found or wrong password.");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        formPanel.add(title);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(loginBtn);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(backBtn);

        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
