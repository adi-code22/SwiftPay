package com.stackroute.com.TransactionService.repository;

import com.stackroute.com.TransactionService.model.QueueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface QueueRepository extends JpaRepository<QueueModel, Integer> {
    List<QueueModel> findByStatus(String status);
}
