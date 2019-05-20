package com.bookstore;

import java.io.IOException;

import com.bookstore.controller.BookStoreController;

public class BookStoreApp {

	public static void main(String[] args) throws IOException {
		new BookStoreController().run();
	}

}
