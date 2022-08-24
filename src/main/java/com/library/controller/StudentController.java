package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Student;
import com.library.service.StudentServices;

@RestController
public class StudentController {

	@Autowired
	private StudentServices studentServices;

	@GetMapping("/getStudent")
	public ResponseEntity<List<Student>> getAll() {

		// getting list of Student
		List<Student> list = studentServices.getAll();

		// checking if list is empty or not
		if (list.size() <= 0) {

			// on success
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {

			// on error
			return ResponseEntity.of(Optional.of(list));
		}
	}

	@PostMapping("/createStudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student st) {

		try {

			// creating student
			Student create = studentServices.saveStudent(st);

			// on success
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			// on error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
