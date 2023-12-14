package com.example.splitwisedec232.controller;

import com.example.splitwisedec232.dtos.ResponseStatus;
import com.example.splitwisedec232.dtos.SettleGroupRequestDto;
import com.example.splitwisedec232.dtos.SettleGroupResponseDto;
import com.example.splitwisedec232.models.Transaction;
import com.example.splitwisedec232.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto){
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        List<Transaction> transactions = settleUpService.settleGroup(requestDto.getGroupId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        responseDto.setTransactions(transactions);
        return responseDto;
    }



}
