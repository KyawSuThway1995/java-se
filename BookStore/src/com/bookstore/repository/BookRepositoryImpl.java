package com.bookstore.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bookstore.domain.Book;
import com.bookstore.exception.BookStoreException;

public class BookRepositoryImpl implements BookRepository {

	private List<Book> bookList;
	private static BookRepositoryImpl repo;
	private static int id;

	@SuppressWarnings("unchecked")
	private BookRepositoryImpl() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.obj"))) {
			bookList = (List<Book>) ois.readObject();
			id = bookList.stream().mapToInt(b -> b.getId()).max().getAsInt();
		} catch (Exception e) {
			bookList = new ArrayList<>();
		}
	}

	public static BookRepositoryImpl getInstance() {
		if (null == repo) {
			repo = new BookRepositoryImpl();
		}

		return repo;
	}

	@Override
	public void createORUpdate(Book book) {
		if (book.getId() > 0) {
			int index = bookList.indexOf(book);
			bookList.set(index, book);
		} else {
			book.setId(++id);
			bookList.add(book);
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.obj"))) {
			oos.writeObject(bookList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Book findById(int id) {
		return bookList.stream().filter(book -> id == book.getId()).findFirst()
				.orElseThrow(() -> new BookStoreException.InvalidBookIDException("Invalid Book ID!"));

	}

	@Override
	public void delete(int id) {
		Book book = findById(id);

		if (Objects.isNull(book)) {
			throw new BookStoreException.BookNotFoundException("Book Not Found!");
		}

		bookList.remove(book);
	}

	@Override
	public List<Book> getAll() {
		return bookList;
	}

	@Override
	public List<Book> search(String name, LocalDate from, LocalDate to) {
		List<Book> temp = new ArrayList<Book>(bookList);
		Iterator<Book> itr = temp.iterator();

		while (itr.hasNext()) {
			Book book = itr.next();

			if (!name.isEmpty() && null != from && null != to) {
				return temp.stream().filter(b -> b.getName().equals(name))
						.filter(b -> b.getReleasedDate().compareTo(from) >= 0)
						.filter(b -> b.getReleasedDate().compareTo(to) <= 0).collect(Collectors.toList());
			} else {
				if (!name.isEmpty()) {
					if (!book.getName().equals(name)) {
						itr.remove();
					}
				}

				if (null != from) {
					if (!(book.getReleasedDate().compareTo(from) >= 0)) {
						itr.remove();
					}
				}

				if (null != to) {
					if (!(book.getReleasedDate().compareTo(to) <= 0)) {
						itr.remove();
					}
				}
			}
		}

		return temp;
	}

}
