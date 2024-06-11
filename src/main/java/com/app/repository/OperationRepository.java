package com.app.repository;

import com.app.model.Operation;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findAllByType(Operation.OperationType type);
}