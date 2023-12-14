package com.example.splitwisedec232.services;

import com.example.splitwisedec232.models.*;
import com.example.splitwisedec232.repositories.ExpenseRepository;
import com.example.splitwisedec232.repositories.GroupExpenseRepository;
import com.example.splitwisedec232.strategy.SettleUpStrategy;
import com.example.splitwisedec232.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    private GroupExpenseRepository groupExpenseRepository;

    private SettleUpStrategy settleUpStrategy;

    private ExpenseRepository expenseRepository;

    @Autowired
    public SettleUpServiceImpl(GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy, ExpenseRepository expenseRepository) {
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Transaction> settleGroup(long groupId) {
        // Step 1: Check if the group exists or not
        // Step 2: Figure out all the expenses linked to this group
        List<GroupExpense> groupExpenses = groupExpenseRepository.findAllByGroupId(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();

        Map<User, Double> condensedExpenses = ExpenseUtils.getCondensedExpenses(expenses);

        return settleUpStrategy.settleUp(condensedExpenses);
    }



    @Override
    public List<Transaction> settleUser(long userId) {
        // Check if the user exists or not
        // Figure out all the non group expenses that the user is part of
        List<Expense> nonGroupExpensesForUser = expenseRepository.findNonGroupExpensesForUser(userId);
        Map<User, Double> condensedExpenses = ExpenseUtils.getCondensedExpenses(nonGroupExpensesForUser);
        return settleUpStrategy.settleUp(condensedExpenses);

    }
}
