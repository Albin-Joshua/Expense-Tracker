import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpenseTrackerGUI {
    private ExpenseTracker expenseTracker;
    private JFrame frame;
    private JTextField categoryField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTable expensesTable;
    private DefaultTableModel expensesTableModel;
    private JTextArea reportArea;

    public ExpenseTrackerGUI() {
        expenseTracker = new ExpenseTracker();
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new AddExpenseActionListener());
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        expensesTableModel = new DefaultTableModel(new String[]{"Category", "Description", "Amount"}, 0);
        expensesTable = new JTable(expensesTableModel);
        frame.add(new JScrollPane(expensesTable), BorderLayout.CENTER);

        reportArea = new JTextArea();
        frame.add(new JScrollPane(reportArea), BorderLayout.SOUTH);

        JButton reportButton = new JButton("View Report");
        reportButton.addActionListener(new ViewReportActionListener());
        frame.add(reportButton, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private class AddExpenseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String category = categoryField.getText();
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            expenseTracker.addExpense(category, description, amount);
            expensesTableModel.addRow(new Object[]{category, description, amount});

            categoryField.setText("");
            descriptionField.setText("");
            amountField.setText("");
        }
    }

    private class ViewReportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, Double> categoryTotals = expenseTracker.getCategoryTotals();
            StringBuilder report = new StringBuilder();
            for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                report.append(entry.getKey()).append(": ₹").append(entry.getValue()).append("\n");
            }
            reportArea.setText(report.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpenseTrackerGUI::new);
    }
}
