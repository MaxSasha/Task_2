package com.maxsasha.secondarydb.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.secondarydb.api.dto.StudentDto;
import com.maxsasha.secondarydb.entity.Student;

public class StudentTransformer {

    public static StudentDto transform(Student student, String id) {
        return StudentDto.builder()
                .id(id).name(student.getName())
                .address(student.getAddress())
                .build();
    }

    public static Student transform(StudentDto dto, String id) {
        return Student.builder()
                .id(id).name(dto.getName())
                .address(dto.getAddress())
                .build();
    }

    public static List<StudentDto> transform(List<Student> students) {
        return students.stream()
                .map(s -> transform(s, s.getId()))
                .collect(Collectors.toList());
    }
}