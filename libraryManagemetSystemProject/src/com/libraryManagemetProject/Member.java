package com.libraryManagemetProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {

	private String name;
	private int memberID;
	private List<Book> borrowedBooks;
	private List<BorrowingRecord> borrowingHistory;
	private String email;
	private String phoneNumber;

	public Member(String name, int memberID, String email, String phoneNumber) {
		this.name = name;
		this.memberID = memberID;
		this.borrowedBooks = new ArrayList<>();
		this.borrowingHistory = new ArrayList<>();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public int getMemberID() {
		return memberID;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public List<BorrowingRecord> getBorrowingHistory() {
		return borrowingHistory;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void borrowBook(Book book) {
		if (book.isAvailable()) {
			borrowedBooks.add(book);
			borrowingHistory.add(new BorrowingRecord(book, new Date()));
			book.checkOut();
		} else {
			System.out.println("Sorry, the book is not available for borrowing.");
		}
	}

	public void returnBook(Book book) {
		if (borrowedBooks.contains(book)) {
			borrowedBooks.remove(book);
//			borrowingHistory.add(new BorrowingRecord(book, new Date()));
			BorrowingRecord record = new BorrowingRecord(book, new Date());
	        borrowingHistory.add(record);
			book.returnBook();
		} else {
			System.out.println("You have not borrowed this book.");
		}
	}

	@Override
	public String toString() {
		return "Member Name: " + name + "\nMember ID: " + memberID + "\nBorrowed Books: " + borrowedBooks.size()
				+ "\nBorrowing History: " + borrowingHistory.size() + " records.";
	}
}