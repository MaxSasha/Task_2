package com.maxsasha.secondarydb.api.controller;

import static com.maxsasha.secondarydb.api.transformer.StudentTransformer.transform;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxsasha.secondarydb.api.dto.StudentDto;
import com.maxsasha.secondarydb.entity.Student;
import com.maxsasha.secondarydb.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public Page<Student> getUsers(@RequestParam(defaultValue = "${default.page}") Integer page,
            @RequestParam(defaultValue = "${default.size}") Integer size) {
        log.info("Received request to get students. Page size: {}, current Page: {}", size, page);
        return service.getStudents(PageRequest.of(page, size));
    }

    @PostMapping
    public StudentDto create(@RequestBody StudentDto studentDto) {
        log.info("Received request to create student with info: name: {}, address: {}", studentDto.getName(),
                studentDto.getAddress());
        Student student = service.create(transform(studentDto, studentDto.getId()));
        return transform(student, student.getId());
    }

    @PutMapping("/{id}")
    public StudentDto put(@PathVariable String id, @RequestBody StudentDto studentDto) {
        log.info("Received request to update user with info id: {}, name: {}, email: {} ", id, studentDto.getName(),
                studentDto.getAddress());
        return transform(service.edit(transform(studentDto, id)), id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        log.info("Received request to delete user id: {}", id);
        service.delete(id);
    }
}