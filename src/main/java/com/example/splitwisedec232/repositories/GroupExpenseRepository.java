package com.example.splitwisedec232.repositories;

import com.example.splitwisedec232.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {

    List<GroupExpense> findAllByGroupId(long groupId);
}
