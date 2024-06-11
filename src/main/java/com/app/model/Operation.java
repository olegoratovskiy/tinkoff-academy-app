package com.app.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@AllArgsConstructor
public class Operation {
    @Id
    private String id;
    private String content;
    private LocalDateTime date;
    private OperationType type;

    public enum OperationType {
        WRITE, READ
    }
}
