package com.stackroute.com.TransactionService.service;

import com.stackroute.com.TransactionService.model.QueueModel;
import com.stackroute.com.TransactionService.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService implements QueueServiceInterface {

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public List<QueueModel> findMessagesWithPending() {
        List<QueueModel> list = queueRepository.findByStatus("PENDING");
        return list;
    }

    @Override
    public void saveQueue(String message) {
        QueueModel queueModel = new QueueModel();
        queueModel.setMessage(message);
        queueModel.setStatus("PENDING");
        queueRepository.save(queueModel);
    }

}
