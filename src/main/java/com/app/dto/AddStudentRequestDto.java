package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStudentRequestDto {

    private String name;
    private String secondName;
    private int grade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uid;
}
