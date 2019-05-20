package com.bookstore.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private LocalDate releasedDate;
	private String author;
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
