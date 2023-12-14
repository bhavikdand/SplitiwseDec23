package com.example.splitwisedec232.strategy;

import com.example.splitwisedec232.models.Transaction;
import com.example.splitwisedec232.models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class TwoHeapsStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Double> condensedExpenses) {

        Queue<Pair<User,Double>> minHeap = new PriorityQueue<>(( a,  b) -> (int) (a.getSecond() - b.getSecond()));

        Queue<Pair<User,Double>> maxHeap = new PriorityQueue<>(( a,  b) -> (int) (b.getSecond() - a.getSecond()));

        for(Map.Entry<User, Double> entry: condensedExpenses.entrySet()){
            if(entry.getValue() > 0){
                maxHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
            else {
                minHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while (!minHeap.isEmpty() && !maxHeap.isEmpty()){
            Pair<User, Double> userToBePaid = maxHeap.poll();
            Pair<User, Double> userToPay = minHeap.poll();

            // Case 1: userToBePaid: 2000 , userToPay: 2000 -> 2000
            // Case 2: userToBePaid: 2000, userToPay: 1000  -> 1000
            // Case 3: userToBePaid: 1000, userToPay: 2000 -> 1000

            double amountToBeTransferred = Math.min(Math.abs(userToPay.getSecond()), userToBePaid.getSecond());

            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTransferred);
            transaction.setPaidFrom(userToPay.getFirst());
            transaction.setPaidTo(userToBePaid.getFirst());
            transactions.add(transaction);

            if(userToBePaid.getSecond() - amountToBeTransferred > 0){
                maxHeap.add(Pair.of(userToBePaid.getFirst(), userToBePaid.getSecond() - amountToBeTransferred));
            }

            if(userToPay.getSecond() + amountToBeTransferred < 0){
                minHeap.add(Pair.of(userToPay.getFirst(), userToPay.getSecond() + amountToBeTransferred));
            }

        }

        return transactions;

    }
}
