package com.libraryManagemetProject;

public class Book {

	private String title;
	private String author;
	private boolean available;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void checkOut() {
		if (available) {
			available = false;
			System.out.println("Book '" + title + "' has been checked out.");
		} else {
			System.out.println("Sorry, the book '" + title + "' is already checked out.");
		}
	}

	public void returnBook() {
		if (!available) {
			available = true;
			System.out.println("Thank you for returning the book '" + title + "'.");
		} else {
			System.out.println("This book has not been checked out yet.");
		}
	}

	@Override
	public String toString() {
		return "Book Title: " + title + "\nAuthor: " + author + "\nAvailable: " + (available ? "Yes" : "No");
	}
}
