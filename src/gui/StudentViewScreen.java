package gui;

import javax.swing.*;
import java.awt.*;

public class StudentViewScreen extends JFrame {

    public StudentViewScreen(String id, String name, String subjects) {
        setTitle("Student Result");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));

        JLabel title = new JLabel("📊 Your Result");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel("🆔 Student ID: " + id);
        JLabel nameLabel = new JLabel("👤 Name: " + name);
        JLabel marksLabel = new JLabel("📈 Subjects: " + subjects.replace(";", " | "));

        for (JLabel lbl : new JLabel[]{idLabel, nameLabel, marksLabel}) {
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbl.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        }

        JButton logoutBtn = new JButton("🚪 Logout");
        logoutBtn.setBackground(new Color(230, 70, 70));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setMaximumSize(new Dimension(150, 35));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen();  // acts as back to login
        });

        infoPanel.add(title);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(idLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(marksLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(logoutBtn);

        add(infoPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
