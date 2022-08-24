package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.dao.StudentRepository;
import com.library.entity.Book;
import com.library.entity.Student;
import com.library.model.Booking;
import com.library.service.BookService;
import com.library.service.StudentServices;

@RestController
public class ReturnController {

	@Autowired
	private BookService bookService;

	@Autowired
	private StudentServices studentService;

	@Autowired
	private StudentRepository studentRepository;

	@PostMapping("/returnBook")
	public ResponseEntity<?> issueBook(@RequestBody Booking booking) {

		// get Books by book Id
		List<Book> bookList = bookService.getBooksById(booking.getBookId());

		System.out.println(bookList);
		// get Student by student ID
		Student student = studentService.findStudentById(booking.getStudentId());

		// getting list of Books
		List<Book> list = bookService.getAll();

		// checking student
		if (student!=null) {

			if (!(bookList.isEmpty())) {
				// getting student owned books
				List<Book> studentBooks = student.getBooks();

				// removing books from student
				bookService.deleteBooks(bookList);

				// getting book left with student
				List<Book> remainingBooks = bookService.remainingBooks(studentBooks, bookList);
				student.setBooks(remainingBooks);

				// saving the student
				studentService.saveStudent(student);
				// on success
				return ResponseEntity.ok().body(student);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Book");
			}

		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Student");

		}

	}

}
