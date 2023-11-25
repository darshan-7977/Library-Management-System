package com.libraryManagemetProject;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Book> books;
	private List<Member> members;
	private List<BorrowingRecord> borrowingHistory;

	public Library() {
		books = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void addMember(Member member) {
		members.add(member);
	}

	public Book findBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book;
			}
		}
		return null;
	}

	public List<Book> getBooks() {
		return books;
	}

	public List<Member> getMembers() {
		return members;
	}

	public List<Book> searchBooks(String searchCriteria) {
		List<Book> searchResults = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().toLowerCase().contains(searchCriteria.toLowerCase())
					|| book.getAuthor().toLowerCase().contains(searchCriteria.toLowerCase())) {
				searchResults.add(book);
			}
		}
		return searchResults;
	}

	public String serializeData() {
		StringBuilder serializedData = new StringBuilder();

		// Serialize members
		for (Member member : members) {
			serializedData.append("Member\n");
			serializedData.append(member.getMemberID()).append("\n");
			serializedData.append(member.getName()).append("\n");
			serializedData.append(member.getPhoneNumber()).append("\n");
			// Add more member properties if needed
		}

		// Serialize books
		for (Book book : books) {
			serializedData.append("Book\n");
			serializedData.append(book.getTitle()).append("\n");
			serializedData.append(book.getAuthor()).append("\n");
			// Add more book properties if needed
		}

		// Serialize borrowing records (assuming you have a list of BorrowingRecord
		// objects)
		for (BorrowingRecord record : borrowingHistory) {
			serializedData.append("BorrowingRecord\n");
			serializedData.append(record.getBorrowedBook().getTitle()).append("\n");
			serializedData.append(record.getBorrowingDate()).append("\n");
			// You may need to serialize the borrowing date and return status here
		}

		return serializedData.toString();
	}

}	