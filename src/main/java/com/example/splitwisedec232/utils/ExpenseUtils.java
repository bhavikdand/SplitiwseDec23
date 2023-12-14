package com.example.splitwisedec232.utils;

import com.example.splitwisedec232.models.Expense;
import com.example.splitwisedec232.models.ExpenseType;
import com.example.splitwisedec232.models.ExpenseUser;
import com.example.splitwisedec232.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseUtils {

    public static Map<User, Double> getCondensedExpenses(List<Expense> expenses) {
        Map<User, Double> condensedExpenses = new HashMap<>();
        for(Expense expense: expenses){
            for(ExpenseUser expenseUser: expense.getExpenseUsers()){
                double amount = expenseUser.getExpenseType().equals(ExpenseType.PAID)
                        ? expenseUser.getAmount(): expense.getAmount() * -1;
                condensedExpenses.put(expenseUser.getUser(),
                        amount + condensedExpenses.getOrDefault(expenseUser.getUser(), 0.0));
            }
        }
        return condensedExpenses;
    }
}
