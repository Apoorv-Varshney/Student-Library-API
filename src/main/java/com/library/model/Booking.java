package com.library.model;

import java.util.List;

public class Booking {
	
	private int studentId;
	private List<Integer> bookId;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public List<Integer> getBookId() {
		return bookId;
	}
	public void setBookId(List<Integer> bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "Booking [studentId=" + studentId + ", bookId=" + bookId + "]";
	}
	public Booking(int studentId, List<Integer> bookId) {
		super();
		this.studentId = studentId;
		this.bookId = bookId;
	}

}
