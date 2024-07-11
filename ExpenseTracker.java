import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String category, String description, double amount) {
        Expense expense = new Expense(category, description, amount);
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public HashMap<String, Double> getCategoryTotals() {
        HashMap<String, Double> categoryTotals = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
        }

        return categoryTotals;
    }
}
