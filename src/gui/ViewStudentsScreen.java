package gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class ViewStudentsScreen extends JFrame {
    public ViewStudentsScreen() {
        setTitle("View Students");
        setSize(600, 400);
        setLayout(new BorderLayout());

        String[] columns = {"Name", "Roll No", "Subjects"};
        Vector<String[]> data = new Vector<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                data.add(parts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[][]{}), columns);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        JButton exportBtn = new JButton("Export to CSV");
        exportBtn.addActionListener(e -> {
            try (FileWriter fw = new FileWriter("data/export.csv")) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        fw.write(model.getValueAt(i, j).toString() + (j == model.getColumnCount() - 1 ? "" : ","));
                    }
                    fw.write("\n");
                }
                JOptionPane.showMessageDialog(this, "Exported to data/export.csv");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(exportBtn, BorderLayout.SOUTH);

        setVisible(true);
    }
}
