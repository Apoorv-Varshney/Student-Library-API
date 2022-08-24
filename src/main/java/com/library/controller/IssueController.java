package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.entity.Student;
import com.library.model.Booking;
import com.library.service.BookService;
import com.library.service.StudentServices;

@RestController
public class IssueController {

	@Autowired
	private BookService bookService;

	@Autowired
	private StudentServices studentService;

	@PostMapping("/issueBook")
	public ResponseEntity<?> issueBook(@RequestBody Booking booking) {

		// get Books by book Id
		List<Book> bookList = bookService.getBooksById(booking.getBookId());

		// get Student by student ID
		Student student = studentService.findStudentById(booking.getStudentId());

		// checking student
		if (!(student == null)) {

			if (!(bookList.isEmpty())) {

				bookService.processBooks(bookList, student);

				// saving student in DB
				studentService.saveStudent(student);

				// on success
				return ResponseEntity.ok().body(student);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Book");
			}
		} else {

			// on error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Student");
		}

	}

}
