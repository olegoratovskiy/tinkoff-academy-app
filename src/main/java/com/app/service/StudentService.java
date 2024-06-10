package com.app.service;

import com.app.dto.StudentInput;
import com.app.model.Student;
import com.app.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new NoSuchElementException("Student with id " + id + " not found");
        }
        return student.get();
    }

    public Student add(StudentInput input) {
        return studentRepository.save(
            Student.builder()
                .name(input.getName())
                .secondName(input.getSecondName())
                .grade(input.getGrade())
                .build()
        );
    }
}
