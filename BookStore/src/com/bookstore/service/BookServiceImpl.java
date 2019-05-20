package com.bookstore.service;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.dto.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.BookRepositoryImpl;

public class BookServiceImpl implements BookService {
	private static BookServiceImpl service;
	private BookRepository repo;
	
	private BookServiceImpl() {
		repo = BookRepositoryImpl.getInstance();
	}
	
	public static BookServiceImpl getInstance() {
		if(null == service) {
			service = new BookServiceImpl();
		}
		
		return service;
	}

	@Override
	public void createORUpdate(Book book) {
		repo.createORUpdate(book);

	}

	@Override
	public Book findById(int id) {
		return repo.findById(id);
	}

	@Override
	public void delete(int id) {
		repo.delete(id);

	}

	@Override
	public List<Book> getAll() {
		return repo.getAll();
	}

	@Override
	public List<Book> search(String name, LocalDate from, LocalDate to) {
		return repo.search(name, from, to);
	}

}
