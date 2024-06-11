package com.app.controller;

import com.app.dto.AddStudentRequestDto;
import com.app.dto.ImageDto;
import com.app.dto.StudentDto;
import com.app.model.Image;
import com.app.model.Student;
import com.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RedisHash
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @Cacheable(value = "StudentController::get", key = "#id")
    @GetMapping("/{id}")
    public StudentDto get(@PathVariable long id) {
        Student student = service.getById(id);
        return toDto(student);
    }

    @Cacheable(value = "StudentController::getImage", key = "#id + '.image'")
    @GetMapping("/{id}/image")
    public ImageDto getImage(@PathVariable long id) {
        Image image = service.getImage(id);
        return new ImageDto(
            image.getName(),
            image.getSize(),
            image.getUid()
        );
    }

    @PostMapping("/add")
    public StudentDto add(@RequestBody AddStudentRequestDto dto) {
        Student student = service.add(dto);
        return toDto(student);
    }

    private StudentDto toDto(Student student) {
        return new StudentDto(
            student.getId(),
            student.getName(),
            student.getSecondName(),
            student.getGrade()
        );
    }
}
