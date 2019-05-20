package com.bookstore.repository;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.dto.Book;

public interface BookRepository{
	void createORUpdate(Book book);
	Book findById(int id);
	void delete(int id);
	List<Book> getAll();
	List<Book> search(String name, LocalDate from, LocalDate to);
}
