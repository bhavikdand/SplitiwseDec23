package com.example.splitwisedec232.strategy;

import com.example.splitwisedec232.models.Transaction;
import com.example.splitwisedec232.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {

    public List<Transaction> settleUp(Map<User, Double> condensedExpenses);
}
