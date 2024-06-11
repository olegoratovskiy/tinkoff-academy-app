package com.app.service;

import com.app.dto.AddStudentRequestDto;
import com.app.model.Image;
import com.app.model.Operation;
import com.app.model.Student;
import com.app.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final OperationService operationService;
    private final ImageService imageService;

    public Student getById(long id) {
        Student student = checkAndGet(id);

        operationService.logOperation(
            String.format("Read student: %s", student),
            LocalDateTime.now(),
            Operation.OperationType.READ
        );
        return student;
    }

    public Student add(AddStudentRequestDto input) {
        String imageUid = input.getUid();

        Image image = imageUid == null ? null : imageService.checkAndGet(imageUid);

        Student student = studentRepository.save(
            Student.builder()
                .name(input.getName())
                .secondName(input.getSecondName())
                .grade(input.getGrade())
                .image(image)
                .build()
        );

        operationService.logOperation(
            String.format("Add student: %s", input),
            LocalDateTime.now(),
            Operation.OperationType.WRITE
        );

        return student;
    }

    public Image getImage(long id) {
        Student student = checkAndGet(id);

        operationService.logOperation(
            String.format("Read student image: %s", student.getImage()),
            LocalDateTime.now(),
            Operation.OperationType.READ
        );

        return student.getImage();
    }

    private Student checkAndGet(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new NoSuchElementException("Student with id " + id + " not found");
        }
        return student.get();
    }

}
