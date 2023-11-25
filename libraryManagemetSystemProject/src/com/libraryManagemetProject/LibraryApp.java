package com.libraryManagemetProject;

//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

//	public static void loadSavedData(Library library) {
//		try {
//			FileReader fileReader = new FileReader("/Desktop/LibraryData/library_data.txt");
//			BufferedReader reader = new BufferedReader(fileReader);
//			String line;
//			while ((line = reader.readLine()) != null) {
//				if (line.startsWith("Member Name: ")) {
//				} else if (line.startsWith("Book Title: ")) {
//				}
//			}
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void saveDataToFile(Library library) {
		try {
String desktopPath = System.getProperty("user.home") + "/Desktop/LibraryData/";
			File desktopdir = new File(desktopPath);
			if (!desktopdir.exists()) {
				desktopdir.mkdirs();
			}
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String filePath = desktopPath + "library_data_ " + timeStamp + ".txt";
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(library.serializeData());
			writer.close();
			System.out.println("Data saved to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void displaySearchResults(List<Book> searchResults) {
		if (searchResults.isEmpty()) {
			System.out.println("No books found matching the criteria.");
		} else {
			System.out.println("Search Results:");
			for (Book result : searchResults) {
				System.out.println(result.getTitle() + " by " + result.getAuthor());
			}
		}
	}

	public static void main(String[] args) {
		Library library = new Library();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
//		loadSavedData(library);

		while (true) {

			System.out.println("\nLibrary Management System Menu:");
			System.out.println("1. Register a Member");
			System.out.println("2. Add a Book");
			System.out.println("3. Borrow a Book");
			System.out.println("4. Return a Book");
			System.out.println("5. View Books");
			System.out.println("6. View Member Details");
			System.out.println("7. Search for a Book");
			System.out.println("8. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				System.out.print("Enter member name: ");
				String memberName = scanner.next();
				System.out.print("Enter member ID: ");
				int memberID = scanner.nextInt();
				System.out.print("Enter member email: ");
				String memberEmail = scanner.next();
				System.out.print("Enter member phone number: ");
				String memberPhoneNumber = scanner.next();
				Member member = new Member(memberName, memberID, memberEmail, memberPhoneNumber);
				library.addMember(member);
				System.out.println("Member registered successfully.");
				break;

			case 2:
				System.out.print("Enter book title: ");
				String bookTitle = scanner.next();
				System.out.print("Enter book author: ");
				String bookAuthor = scanner.next();
				Book book = new Book(bookTitle, bookAuthor);
				library.addBook(book);
				System.out.println("Book added successfully.");
				break;

			case 3:
				System.out.print("Enter member ID: ");
				int borrowerID = scanner.nextInt();
				System.out.print("Enter book title to borrow: ");
				String bookToBorrow = scanner.next();
				Member borrower = null;
				for (Member m : library.getMembers()) {
					if (m.getMemberID() == borrowerID) {
						borrower = m;
						break;
					}
				}
				if (borrower != null) {
					Book bookBorrowed = library.findBookByTitle(bookToBorrow);
					if (bookBorrowed != null) {
						borrower.borrowBook(bookBorrowed);
					} else {
						System.out.println("Book not found.");
					}
				} else {
					System.out.println("Member not found.");
				}
				break;

			case 4:
				System.out.print("Enter Member ID: ");
				int returnerID = scanner.nextInt();
				System.out.print("Enter book title to return: ");
				String bookToReturn = scanner.next();
				Member returner = null;
				for (Member m : library.getMembers()) {
					if (m.getMemberID() == returnerID) {
						returner = m;
						break;
					}
				}
				if (returner != null) {
					Book bookReturned = library.findBookByTitle(bookToReturn);
					if (bookReturned != null) {
						returner.returnBook(bookReturned);
					} else {
						System.out.println("Book not found.");
					}
				} else {
					System.out.println("Member not found.");
				}
				break;

			case 5:
				if (library.getBooks().isEmpty()) {
					System.out.println("No books present in the library.");
				} else {
					System.out.println("Books present in the library: ");
					for (Book book1 : library.getBooks()) {
						System.out.println(book1.getTitle() + " by " + book1.getAuthor());
					}
				}
				break;

			case 6:
				System.out.print("Enter member ID: ");
				int memberIdToShow = scanner.nextInt();
				Member memberToShow = null;
				for (Member m : library.getMembers()) {
					if (m.getMemberID() == memberIdToShow) {
						memberToShow = m;
						break;
					}
				}
				if (memberToShow != null) {
					System.out.println("Member Details: ");
					System.out.println(memberToShow);
				} else {
					System.out.println("Member not found.");
				}
				break;

			case 7:
				// Search for a book
				System.out.print("Enter the search criteria (e.g., title, author): ");
				String searchCriteria = scanner.next();
				List<Book> searchResults = library.searchBooks(searchCriteria);
				displaySearchResults(searchResults);
				break;

			case 8:
				saveDataToFile(library);
				System.out.println("Thank you for using Library Management System.");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
				break;

			}
		}
	}
}
