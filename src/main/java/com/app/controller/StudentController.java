package com.app.controller;

import com.app.dto.StudentInput;
import com.app.model.Student;
import com.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @QueryMapping
    public Student studentById(@Argument long id) {
        return studentService.getById(id);
    }

    @MutationMapping
    public Student addStudent(@Argument StudentInput input) {
        return studentService.add(input);
    }

}
