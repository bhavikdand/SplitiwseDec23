package com.example.splitwisedec232.dtos;

import com.example.splitwisedec232.models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class SettleGroupResponseDto {
    private ResponseStatus responseStatus;
    private List<Transaction> transactions;
}
