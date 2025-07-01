import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;

    public ToDoApp() {
        setTitle("📝 To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center

        // Panel setup
        JPanel panel = new JPanel(new BorderLayout());

        // Input field
        taskField = new JTextField();
        panel.add(taskField, BorderLayout.NORTH);

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        addButton = new JButton("➕ Add Task");
        deleteButton = new JButton("🗑️ Delete Task");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // Action Listeners
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
    int[] selectedIndices = taskList.getSelectedIndices();
    if (selectedIndices.length > 0) {
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            taskListModel.removeElementAt(selectedIndices[i]);
        }
    } else {
        JOptionPane.showMessageDialog(this, "⚠️ Please select a task to delete.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
    }
});


        setVisible(true);
    }

    public static void main(String[] args) {
        // Always run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
