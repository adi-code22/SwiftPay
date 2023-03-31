package com.stackroute.com.TransactionService.service;

import com.stackroute.com.TransactionService.model.QueueModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QueueServiceInterface {
    List<QueueModel> findMessagesWithPending();

    void saveQueue(String message);
}
