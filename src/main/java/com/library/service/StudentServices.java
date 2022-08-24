package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.StudentRepository;
import com.library.entity.Student;

@Component
public class StudentServices {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll() {
		List<com.library.entity.Student> list = studentRepository.findAll();
		return list;
	}
	
	public Student saveStudent(Student st) {
		Student save = studentRepository.save(st);
		return save;
	}

	public Student findStudentById(int id) {
		return studentRepository.getStudentById(id);
	}
	
	
}
