package com.libraryManagemetProject;

import java.util.Date;

public class BorrowingRecord {
	private Book borrowedBook;
	private Date borrowingDate;

	public BorrowingRecord(Book borrowedBook, Date borrowingDate) {
		this.borrowedBook = borrowedBook;
		this.borrowingDate = borrowingDate;
	}

	public Book getBorrowedBook() {
		return borrowedBook;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}
}
