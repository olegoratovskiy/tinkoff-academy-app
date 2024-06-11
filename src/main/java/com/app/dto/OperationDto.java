package com.app.dto;

import com.app.model.Operation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationDto {

    private String content;
    private LocalDateTime date;
    private Operation.OperationType type;
}
