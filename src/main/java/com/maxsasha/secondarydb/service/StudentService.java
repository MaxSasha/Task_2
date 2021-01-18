package com.maxsasha.secondarydb.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maxsasha.secondarydb.db.repository.StudentRepository;
import com.maxsasha.secondarydb.entity.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Page<Student> getStudents(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public Optional<Student> getStudent(String id) {
		return studentRepository.findById(id);
	}

	public Student create(Student student) {
		return studentRepository.save(student);
	}

	public Student edit(Student student) {
        return studentRepository.save(student);
	}

	public void delete(String id) {
		studentRepository.deleteById(id);
	}
}