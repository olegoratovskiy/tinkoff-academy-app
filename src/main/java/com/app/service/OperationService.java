package com.app.service;

import com.app.model.Operation;
import com.app.repository.OperationRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository repository;

    public void logOperation(String content, LocalDateTime date, Operation.OperationType type) {
        repository.save(Operation.builder()
            .content(content)
            .date(date)
            .type(type)
            .build()
        );
    }

    public List<Operation> getOperationsByType(Operation.OperationType type) {
        return repository.findAllByType(type);
    }
}
