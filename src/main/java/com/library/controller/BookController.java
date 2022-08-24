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

import com.library.entity.Book;
import com.library.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/getBook")
	public ResponseEntity<List<Book>> getAll() {
		
		//getting list of Books
		List<Book> list = bookService.getAll();
		
		//checking if list is empty or not
		if (list.size() <= 0) {
			
			//on success
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			
			//on error
			return ResponseEntity.of(Optional.of(list));
		}
	}

	@PostMapping("/createBook")
	public ResponseEntity<Book> createBook(@RequestBody Book b) {
		
		try {
			
			//creating Book in DB
			Book create = bookService.saveBook(b);
			
			//on success
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			//on error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
