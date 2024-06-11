package com.app.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto implements Serializable {

    private long id;
    private String name;
    private String secondName;
    private int grade;

}
