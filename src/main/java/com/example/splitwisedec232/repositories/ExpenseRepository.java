package com.example.splitwisedec232.repositories;

import com.example.splitwisedec232.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Given a user id, use expense, expense_user and group expense tables to
    // return non group expenses for the user.
    @Query("Select e from Expense e " +
            "inner join ExpenseUser eu on e.id = eu.expense.id and eu.user.id = :userId" +
            "left join GroupExpense ge on e.id = ge.expense.id where ge.id is null")
    List<Expense> findNonGroupExpensesForUser(@Param("userId") long userId);
}
