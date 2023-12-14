package com.example.splitwisedec232.services;

import com.example.splitwisedec232.models.Transaction;

import java.util.List;

public interface SettleUpService {

    public List<Transaction> settleGroup(long groupId);

    public List<Transaction> settleUser(long userId);
}
