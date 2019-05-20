package com.bookstore.controller;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.domain.Book;
import com.bookstore.exception.BookStoreException;
import com.bookstore.service.BookService;
import com.bookstore.service.BookServiceImpl;
import com.bookstore.util.BookUtils;

public class BookStoreController {
	private BookService service;
	private AccountController accountController;

	public BookStoreController() {
		service = BookServiceImpl.getInstance();
		accountController = new AccountController();
	}

	public void run() {
		while (true) {
			accountController.startAccount();
			startBook();
		}
	}

	private void startBook() {
		showHeaderOrFooter(true);

		do {
			try {
				BookOpearation opearation = BookOpearation.values()[showMenu() - 1];
				doOpearation(opearation);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while ("y".equalsIgnoreCase(BookUtils.readString("Do you want to do next again? Enter y/Other : ")));

		System.out.println();
	}

	private void showHeaderOrFooter(boolean start) {
		System.out.println("*************************");

		if (start)
			System.out.println("*******BOOK STORE********");
		else
			System.out.println("*******THANK YOU*********");

		System.out.println("*************************");
	}

	private int showMenu() {
		for (BookOpearation op : BookOpearation.values()) {
			System.out.println(op);
		}

		System.out.println();
		return BookUtils.readInt("Choose One Option : ");

	}

	private void doOpearation(BookOpearation opearation) {

		switch (opearation) {
		case SHAWALL:
			search(true);
			break;

		case CREATE:
			createOrUpdate(true);
			break;

		case UPDATE:
			createOrUpdate(false);
			break;

		case DELETE:
			delete();
			break;

		case SEARCH:
			search(false);
			break;

		case LOGOUT:
			showHeaderOrFooter(false);
			run();
			break;

		default:
			throw new BookStoreException.InvalidOptionCodeException("Invalid Option Code!");
		}

	}

	private void showAll(List<? extends Book> books) {
		String header = String.format("%-5s%-20s%-20s%15s%10s", "ID", "NAME", "AUTHOR", "RELEASE DATE", "PRICE");
		String data = "%-5d%-20s%-20s%15s%10d";

		System.out.printf("%40s%n", "BOOK LIST");

		String line = "";

		for (int i = 0; i < header.length(); i++) {
			line += "*";
		}

		if (null != books && !books.isEmpty()) {

			showTitle(line, header);

			books.forEach(book -> {
				System.out.println(String.format(data, book.getId(), book.getName(), book.getAuthor(),
						book.getReleasedDate().format(BookUtils.df), book.getPrice()));
			});

			System.out.println(line);
			System.out.println();
		} else {

			showTitle(line, "Book haven't here, Please insert book before find!");
		}
	}

	private void showTitle(String line, String title) {
		System.out.println(line);
		System.out.println(title);
		System.out.println(line);
	}

	private void createOrUpdate(boolean create) {
		Book book = null;
		if (!create) {
			book = service.findById(BookUtils.readInt("Enter Book ID : "));
		} else {
			book = new Book();
		}

		book.setName(BookUtils.readString("Book Name : "));
		book.setAuthor(BookUtils.readString("Author Name : "));
		book.setReleasedDate(BookUtils.readDate("Released Date (dd/MM/yyyy) : "));
		book.setPrice(BookUtils.readInt("Price : "));

		service.createORUpdate(book);

	}

	private void delete() {
		service.delete(BookUtils.readInt("Enter ID For Delete Book : "));
	}

	private void search(boolean flag) {
		if (flag) {
			showAll(service.getAll());

			while ("y".equalsIgnoreCase(BookUtils.readString("Do you want to search book? Enter y/Other : ")))
				search(false);

		} else {
			String name = BookUtils.readString("Book Name : ");
			LocalDate from = BookUtils.readDate("Start Date : ");
			LocalDate to = BookUtils.readDate("End Date : ");

			showAll(service.search(name, from, to));
		}

	}
}
