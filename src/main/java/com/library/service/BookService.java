package com.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.dao.BookRepository;
import com.library.entity.Book;
import com.library.entity.Student;

@Component
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAll() {
		List<Book> list = bookRepository.findAll();
		return list;
	}

	public Book saveBook(Book b) {
		Book save = bookRepository.save(b);
		return save;
	}

	public List<Book> getBooksById(List<Integer> bookId) {
		List<Book> bookList = bookRepository.findAllById(bookId);
		return bookList;
	}

	public void processBooks(List<Book> bookList, Student student) {

		student.setBooks(bookList);
		ListIterator<Book> it = bookList.listIterator();
		while (it.hasNext()) {
			Book b = it.next();
			b.setStudent(student);
			bookRepository.save(b);
		}
	}

	public List<Book> remainingBooks(List<Book> studentBooks, List<Book> bookList) {
		List<Book> unique = new ArrayList<>();
		unique.addAll(studentBooks.stream().filter(str -> !bookList.contains(str)).collect(Collectors.toList()));
		return unique;
	}

	public void deleteBooks(List<Book> bookList) {

		ListIterator<Book> it = bookList.listIterator();
		while (it.hasNext()) {
			Book b = it.next();
			b.setStudent(null);
			bookRepository.save(b);
		}
	}
}
